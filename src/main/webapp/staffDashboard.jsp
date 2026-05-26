<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<%@ page import="dao.DBConnection" %>
<%@ page session="true" %>

<%
    String staffName = (String) session.getAttribute("staff_name");
    if (staffName == null) {
        response.sendRedirect("stafflogin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Dashboard</title>

<style>

body{
    font-family: Arial, sans-serif;
    background:#f4f6f9;
    margin:0;
}

h2{
    text-align:center;
    margin:20px;
    color:#333;
}

.table-container{
    width:90%;
    margin:auto;
    background:#ffffff;
    border-radius:10px;
    box-shadow:0px 4px 15px rgba(0,0,0,0.1);
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
}

th{
    background:linear-gradient(135deg,#3498db,#2c80b4);
    color:black;
    padding:12px;
    font-size:16px;
}

td{
    padding:12px;
    border-bottom:1px solid #ddd;
    text-align:center;
}

/* Increase Action column width */
th:last-child, td:last-child{
    width:250px;
}

/* Align buttons nicely */
td:last-child{
    display:flex;
    justify-content:center;
    gap:10px;
}

tr:hover{
    background:#f1f9ff;
}

.button{
    padding:7px 14px;
    background:#2ecc71;
    color:white;
    border:none;
    border-radius:5px;
    cursor:pointer;
    font-size:14px;
}

.button:hover{
    background:#27ae60;
}

.reject{
    background:#e74c3c;
}

.reject:hover{
    background:#c0392b;
}

.pdf-btn{
    display:block;
    width:230px;
    margin:20px auto;
    padding:12px;
    background:orange;
    color:red;
    text-align:center;
    text-decoration:none;
    border-radius:6px;
    font-weight:bold;
}

.pdf-btn:hover{
    background:#e67e22;
}

</style>
</head>

<body>

<%@ include file="header.jsp" %>

<h2>Welcome, <%= staffName %></h2>

<a href="GeneratePDFServlet" class="pdf-btn">
Download Leave Requests PDF
</a>

<div class="table-container">

<table>

<tr>
<th>Student Name</th>
<th>From Date</th>
<th>To Date</th>
<th>Reason</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
PreparedStatement ps = null;
ResultSet rs = null;

try {

    Connection con = DBConnection.getConnection();

    String sql =
    		"SELECT lr.id, s.student_name, lr.from_date, lr.to_date, lr.reason, lr.status " +
    		"FROM leave_requests lr " +
    		"JOIN students s ON lr.student_id = s.student_id " +
    		"ORDER BY lr.id DESC";

    ps = con.prepareStatement(sql);
    rs = ps.executeQuery();

    boolean found = false;

    while(rs.next()){
    found = true;
%>

<tr>

<td><%= rs.getString("student_name") %></td>
<td><%= rs.getDate("from_date") %></td>
<td><%= rs.getDate("to_date") %></td>
<td><%= rs.getString("reason") %></td>
<td><%= rs.getString("status") %></td>

<td>

<%
String status = rs.getString("status");

if ("Pending".equalsIgnoreCase(status)) {
%>

<form action="StaffActionServlet" method="post" style="display:inline;">
<input type="hidden" name="leaveId" value="<%= rs.getInt("id") %>">
<input type="hidden" name="action" value="approve">
<button type="submit" class="button">Approve</button>
</form>

<form action="StaffActionServlet" method="post" style="display:inline;">
<input type="hidden" name="leaveId" value="<%= rs.getInt("id") %>">
<input type="hidden" name="action" value="reject">
<button type="submit" class="button reject">Reject</button>
</form>

<%
} else {
%>

<span style="color:green;font-weight:bold;">
<%= status %>
</span>

<%
}
%>

</td>

</tr>

<%
}

if(!found){
%>

<tr>
<td colspan="6">No leave requests found</td>
</tr>

<%
}

}catch(Exception e){
%>

<tr>
<td colspan="6">Error : <%= e.getMessage() %></td>
</tr>

<%
} finally {

if(rs!=null) rs.close();
if(ps!=null) ps.close();

}
%>

</table>

</div>

</body>
</html>

