

// Import required java libraries
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.sql.*;

// Extend HttpServlet class
public class CopyOflogin extends HttpServlet 
{
	  DBConnection db=null;
      Connection con=null;
      Statement st=null;
      String query=null;
      PrintWriter out=null;
      int value=0;
      ResultSet rs=null;
  // Method to handle GET method request.
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
    
   try
   {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      db=new DBConnection();
		
		con=db.getConnection();

      st = con.createStatement();
	   // Set response content type      
	  // out.println("connection to create ststment ......");		
		String username=request.getParameter("UserName");
		//out.println(username);
		String password=request.getParameter("Password");
		//out.println(password);
		boolean bothflag=true;
		boolean usernameflag=true;
		//boolean passwordflag=true;
		String n="Select * from user_detail;"; 
		//out.println("select query"+ n);
		ResultSet res = st.executeQuery(n);
		while(res.next())
		{
			//checks for user name password are correct
		if(res.getString("username").equals(username))
		{
			usernameflag=false;
			if(res.getString("password").equals(password))
			{   
				HttpSession ss= request.getSession();
				ss.setAttribute("user", username);
				ss.setMaxInactiveInterval(10);
				RequestDispatcher rd=request.getRequestDispatcher("viewpost");
				rd.include(request, response);
//				String title = "Login Form";	  
//				String docType ="<!DOCTYPE html>\n";
//				out.println(docType +
//				"<html>\n" +
//				"<head><title>" + title + "</title></head>\n" +
//				"<body bgcolor=\"#DBDBFF\">\n" +
//				"<h1 align=\"center\">" + title + "</h1>\n" +
//				"<table width=\"100%\" border=\"thin dotted gray\" border-collapse=\"collapse\" align=\"center\">\n" +
//				"<tr bgcolor=\"#66FF33\">\n" +
//				"<th>Fields</th><th>Values</th>\n"+
//				"</tr>\n");
//				out.print("<tr><td>FirstName:</td>\n<td>"+res.getString("firstname")+"</td></tr>");
//				out.print("<tr><td>LastName:</td>\n<td>"+res.getString("lastname")+"</td></tr>");
//				out.print("<tr><td>Phone-Number:</td>\n<td>"+res.getString("phone")+"</td></tr>");
//				out.print("<tr><td>Email:</td>\n<td>"+res.getString("email")+"</td></tr>");
//				out.print("<tr><td>Gender:</td>\n<td>"+res.getString("gender")+"</td></tr>");
//				out.print("<tr><td>Date of Birth:</td>\n<td>"+res.getString("dob")+"</td></tr>");
//				out.print("<tr><td>Phone:</td>\n<td>"+res.getString("phone")+"</td></tr>");
//				out.print("<tr><td>Address:</td>\n<td>"+res.getString("address")+"</td></tr>");
//				out.println("\n</table>\n</body></html>");
				bothflag=false;				
			}

		}
		}		
		if(usernameflag)
		{
			
			out.println("&nbsp &nbsp &nbsp User name not present. Please register<br>");
			out.println("<br>&nbsp &nbsp &nbsp click <a href=\"http://localhost:8080/web_programming/login.html\">here</a> to Go back and try again");
			out.println("<br>&nbsp &nbsp &nbsp click <a href=\"http://localhost:8080/web_programming/registration.html\">here</a> to redirect to registration page");
			
		}		
		if((!usernameflag) && (bothflag))
		{	 
			out.println("&nbsp &nbsp &nbsp Wrong Password.Please enter crct password<br>");
			out.println("<br>&nbsp &nbsp &nbsp click <a href=\"http://localhost:8080/web_programming/login.html\">here</a> to Go back and try again");
			out.println("<br>&nbsp &nbsp &nbsp click <a href=\"http://localhost:8080/web_programming/login.html\">here</a> to redirect to login page");

		}
   }
   catch(SQLException se)
   {
      //Handle errors for JDBC
      se.printStackTrace();
   }
   catch(Exception e)
   {
      //Handle errors for Class.forName
      e.printStackTrace();
   }
   finally
   {
      //finally block used to close resources
      try
	  {
         if(st!=null)
            con.close();
      }
	  catch(SQLException se)
	  {
      }// do nothing
      try
	  {
         if(con!=null)
            con.close();
      }
	  catch(SQLException se)
	  {
         se.printStackTrace();
      }//end finally try
   }//end try
   	   
  }
  // Method to handle POST method request.
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
     doGet(request, response);
  }
}