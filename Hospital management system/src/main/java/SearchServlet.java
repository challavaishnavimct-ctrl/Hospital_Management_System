import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SearchServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

// Get patient ID from search form
String id = request.getParameter("patient_id");

try{

// Load MySQL Driver
Class.forName("com.mysql.cj.jdbc.Driver");

// Database connection
Connection con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/hospital_management",
"root",
"Challa@123");

// Search query
PreparedStatement ps = con.prepareStatement(
"SELECT * FROM patient_registration WHERE patient_id=?");

ps.setString(1,id);

ResultSet rs = ps.executeQuery();

if(rs.next())
{

out.println("<h2>Patient Details</h2>");
out.println("Patient ID: "+rs.getInt("patient_id")+"<br>");
out.println("Name: "+rs.getString("patient_name")+"<br>");
out.println("Age: "+rs.getInt("age")+"<br>");
out.println("Gender: "+rs.getString("gender")+"<br>");
out.println("Phone: "+rs.getString("phone")+"<br>");
out.println("Disease Type: "+rs.getString("disease_type")+"<br>");
out.println("Department: "+rs.getString("department")+"<br>");
out.println("Appointment Time: "+rs.getString("appointment_time")+" "+rs.getString("ampm")+"<br>");
out.println("Status: "+rs.getString("status")+"<br>");

}
else
{
out.println("Patient not found");
}

con.close();

}
catch(Exception e)
{
out.println(e);
}

}
}