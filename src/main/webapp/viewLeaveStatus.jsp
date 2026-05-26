<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*, java.util.*,dao.DBConnection" %>
<%@ include file="header.jsp" %>
<%
    
    String userId = (String) session.getAttribute("student_id");

    if (userId == null) {
        response.sendRedirect("studentlogin.jsp");
        return;
    }
    

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
%>
<html>
<head>
    <title>Leave Status</title>
    <style>
        body {
            font-family: Arial;
            background: white;
            background-size:cover;
            margin: 0;
            padding:0;           
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            font-family: Arial;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
            font-size:14px;
        }
        tr:hover{
        background-color:#f1f8ff;
        }
        th {
            background-color: #1976d2;
        }
        h2 {
            text-align: center;
            font-family: Arial;
        }
         .back-btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 18px;
            background: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 14px;
        }

        .back-btn:hover {
            background: #0d47a1;
        }

        .btn-center {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>Your Leave Applications</h2>

    <table>
        <tr>
            <th>From Date</th>
            <th>To Date</th>
            <th>Reason</th>
            <th>Status</th>
        </tr>
        
        <%
            try {
                conn = DBConnection.getConnection();
                String sql = "SELECT from_date, to_date, reason, status FROM leave_requests WHERE student_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, userId);
                rs = stmt.executeQuery();

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("from_date") %></td>
            <td><%= rs.getString("to_date") %></td>
            <td><%= rs.getString("reason") %></td>
            <td><%= rs.getString("status") %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        %>
        
    </table>
    <div class="btn-center">
        <a href="studentdashboard.jsp" class="back-btn">⬅ Back to Dashboard</a>
    </div>
    
</body>
</html>