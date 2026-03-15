import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String username = request.getParameter("username");
String password = request.getParameter("password");

// Set username and password here
String correctUsername = "admin";
String correctPassword = "admin123";

if(username.equals(correctUsername) && password.equals(correctPassword))
{
response.sendRedirect("doctor.html");
}
else
{
response.getWriter().println("Invalid Username or Password");
}

}
}