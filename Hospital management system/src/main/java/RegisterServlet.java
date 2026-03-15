import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

response.setContentType("text/html");
PrintWriter out = response.getWriter();

String id = request.getParameter("patient_id");
String name = request.getParameter("patient_name");
String age = request.getParameter("age");
String gender = request.getParameter("gender");
String phone = request.getParameter("phone");
String disease = request.getParameter("disease_type");
String department = request.getParameter("department");
String time = request.getParameter("appointment_time");
String ampm = request.getParameter("ampm");

String status="";

try{

Class.forName("com.mysql.cj.jdbc.Driver");

Connection con = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/hospital_management",
"root",
"Challa@123");

PreparedStatement ps1 = con.prepareStatement(
"SELECT available_time FROM doctor WHERE department=?");

ps1.setString(1,department);

ResultSet rs = ps1.executeQuery();

if(rs.next())
{
String doctorTime = rs.getString("available_time");

if(time.compareTo(doctorTime) >= 0)
{
status="Confirmed";
}
else
{
status="Doctor Not Available At That Time";
}

}
else
{
status="No Doctor Available";
}

PreparedStatement ps2 = con.prepareStatement(
"INSERT INTO patient_registration VALUES(?,?,?,?,?,?,?,?,?,?)");

ps2.setString(1,id);
ps2.setString(2,name);
ps2.setString(3,age);
ps2.setString(4,gender);
ps2.setString(5,phone);
ps2.setString(6,disease);
ps2.setString(7,department);
ps2.setString(8,time);
ps2.setString(9,ampm);
ps2.setString(10,status);

ps2.executeUpdate();

out.println("<h2>Patient Registered</h2>");

con.close();

}
catch(Exception e)
{
out.println(e);
}

}
}