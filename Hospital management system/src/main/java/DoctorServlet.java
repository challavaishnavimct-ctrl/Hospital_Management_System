import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DoctorServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

// Getting values from doctor.html
String id = request.getParameter("doctor_id");
String name = request.getParameter("doctor_name");
String department = request.getParameter("department");
String specialization = request.getParameter("specialization");
String time = request.getParameter("available_time");
String ampm = request.getParameter("ampm");

try {

// Load MySQL Driver
Class.forName("com.mysql.cj.jdbc.Driver");

// Database connection
Connection con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/hospital_management",
"root",
"Challa@123");

// Insert query
PreparedStatement ps = con.prepareStatement(
"INSERT INTO doctor VALUES(?,?,?,?,?,?)");

ps.setString(1, id);
ps.setString(2, name);
ps.setString(3, department);
ps.setString(4, specialization);
ps.setString(5, time);
ps.setString(6, ampm);

int i = ps.executeUpdate();

if(i > 0)
{
out.println("<h2>Doctor Added Successfully</h2>");
}
else
{
out.println("<h2>Error Adding Doctor</h2>");
}

con.close();

}
catch(Exception e)
{
out.println(e);
}

}
}