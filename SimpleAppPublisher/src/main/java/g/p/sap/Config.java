package g.p.sap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Basic design:  config comes from a hardcoded location on the server filesystem.
 * 
 * TODO: Can be overridden using environment variable.
 *  
 * @author Dave
 *
 */
public class Config {
	
	public static final String DEFAULT_FILENAME = "/var/nearzero/sap.war.conf";
	
	private static Config instance = null;
	
	public static Config getConfig(){
		if(instance == null){
			//lets hope this runs in a version of java that isnt broken
			synchronized(Config.class) {
				if(instance == null){
					instance = new Config();
				}
			}
		}
		return instance;
	}

	
	private boolean error = false;
	
	private Map<String, String> config = new TreeMap<String, String>();
	
	public Config(){
		
		
		try{
			List<String> list = readConfFile(DEFAULT_FILENAME);
			for(String s : list){
				s = s.trim();
				if(s.contains("=") && 
						! (s.startsWith("#") || s.startsWith("//"))){
					
					String[] nameValue = s.split("=", 2);
					config.put(nameValue[0], nameValue[1]);
					
					
				}
			}
			
		}catch(Exception ex){
			error = true;
			
		}
		

		
	}

	
	public boolean loadFailed(){
		return this.error;
	}
	
	public String get(String name){
		return this.config.get(name);
	}
	
	public static String readFile(String filename){
		BufferedReader br = null;
	    try {
	    	br = new BufferedReader(new FileReader(filename));
	        StringBuilder sb = new StringBuilder();
	        
	        String line = null;
	        while(null != (line = br.readLine())){
	        	sb.append(line).append("\n");
	        }
	        return sb.toString();
	        
	    }catch(Exception ex){
	    	
	    	return null;
	    	
	    } finally {
	        try{ if(br != null) br.close(); }catch(Exception ex){}
	    }
	}
	
	public static List<String> readConfFile(String filename) throws IOException{
		List<String> list = new ArrayList<String>(20);
		
		BufferedReader br = null;
	    try {
	    	br = new BufferedReader(new FileReader(filename));
	        
	        
	        String line = null;
	        while(null != (line = br.readLine())){
	        	list.add(line);
	        }
	        return list;
	        
	    } finally {
	        try{ if(br != null) br.close(); }catch(Exception ex){}
	    }
	}

}
