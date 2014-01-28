
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ActionServlet extends HttpServlet{

protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
resp.setContentType("text/html");
PrintWriter out = resp.getWriter();
try{
String num1 = req.getParameter("num1");
String num2 = req.getParameter("num2");
String op = req.getParameter("op");

float n1 = Float.parseFloat(num1);
float n2 = Float.parseFloat(num2);


out.print("<h3>Result: </h3>");
if(op.equals("+")){
	float result=n1+n2;
	out.println(n1+" + "+n2+" : "+result);
}
else if(op.equals("-")){
	float result=n1-n2;
	out.println(n1+" - "+n2+" : "+result);
}
else if(op.equals("*")){
	float result=n1*n2;
	out.println(n1+" * "+n2+" : "+result);
}
else if(op.equals("/")){
	float result=n1/n2;
	out.println(n1+" / "+n2+" : "+result);
}
else{
	out.println("Error in Input.");
}
}
catch(Exception e){
	out.println("<h3>Invalid Input.</h3>");
}
out.println("<br/><a href=\"index.html\">Go Back</a>");
}

}