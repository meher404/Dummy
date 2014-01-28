
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GuessingServlet extends HttpServlet{

protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{

resp.setContentType("text/html");
PrintWriter out = resp.getWriter();
/*Random r = new Random();
int rand = r.nextInt(100);
out.println(""+rand); */
//ServletContext context = getServletConfig().getServletContext();

HttpSession session = req.getSession();
Object num = session.getAttribute("number");

if(num==null){
	Random r = new Random();
	int rand = r.nextInt(100);
	//out.println("Generating: "+rand);
	session.setAttribute("number",""+rand);
	session.setAttribute("count",0+"");
}
num = session.getAttribute("number");
int count = Integer.parseInt(session.getAttribute("count").toString());
count = count+1;
session.setAttribute("count",count);
//out.println("Existing: "+num.toString()+" Guess count: "+count);
out.println("Guess count: "+count);
	
int user_guess = Integer.parseInt(req.getParameter("guess_num"));
int actual_num = Integer.parseInt(num.toString());

if(user_guess>actual_num){
	out.println("<h3>Your Guess is GREATER than the actual number</h3>");
}
else if(user_guess<actual_num){
	out.println("<h3>Your Guess is SMALLER than the actual number</h3>");
}
else if(user_guess==actual_num){
	out.println("<h3>Your Guess is <b>CORRECT</b><br/><h1>You Won!!</h1></h3>");
}
out.println("Actual Number: "+num.toString());
RequestDispatcher rd = req.getRequestDispatcher("/guessing.html");
rd.include(req,resp);

}

}