package g.p.sap;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("writing to STDOUT");
		System.err.println("writing to STDERR");
		
		response.setContentType("text/html");
	
		PrintWriter out = response.getWriter();
		out.println("<h1>server is running</h1>");
		}
}
