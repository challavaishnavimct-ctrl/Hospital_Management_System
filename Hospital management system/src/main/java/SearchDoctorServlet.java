import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SearchDoctorServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

String doctorId = request.getParameter("doctor_id");

try{

// Load MySQL Driver
Class.forName("com.mysql.cj.jdbc.Driver");

// Connect to database
Connection con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/hospital_management",
"root",
"Challa@123");

// SQL Query
PreparedStatement ps = con.prepareStatement(
"SELECT * FROM doctor WHERE doctor_id=?");

ps.setString(1, doctorId);

ResultSet rs = ps.executeQuery();

if(rs.next())
{

out.println("<h2>Doctor Details</h2>");
out.println("Doctor ID: " + rs.getInt("doctor_id") + "<br>");
out.println("Doctor Name: " + rs.getString("doctor_name") + "<br>");
out.println("Department: " + rs.getString("department") + "<br>");
out.println("Specialization: " + rs.getString("specialization") + "<br>");
out.println("Available Time: " + rs.getString("available_time") + " " + rs.getString("ampm") + "<br>");

}
else
{
out.println("Doctor not found");
}

con.close();

}
catch(Exception e)
{
out.println(e);
}

}
}