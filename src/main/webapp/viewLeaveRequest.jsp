<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="dao.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Leave Requests</title>
<link rel="stylesheet" href="../css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<div class="menu-btn" onclick="toggleSidebar()">☰</div>

<div class="sidebar" id="sidebar">
    <h3>Staff Panel</h3>
    <a href="staffDashboard.jsp">Dashboard</a>
    <a href="viewLeaveRequests.jsp">Leave Requests</a>
    <a href="../logout">Logout</a>
</div>

<div class="content">
    <h2>Student Leave Requests</h2>

    <div class="table-responsive">
        <table class="leave-table">
            <tr>
                <th>Student ID</th>
                <th>From</th>
                <th>To</th>
                <th>Days</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <%
                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM leave_requests ORDER BY applied_date DESC");
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String status = rs.getString("status");
            %>
            <tr>
                <td><%= rs.getString("student_id") %></td>
                <td><%= rs.getDate("from_date") %></td>
                <td><%= rs.getDate("to_date") %></td>
                <td><%= rs.getInt("total_days") %></td>
                <td><%= rs.getString("reason") %></td>
                <td>
                    <span class="status <%= status.toLowerCase() %>">
                        <%= status %>
                    </span>
                </td>
                <td>
                    <% if ("Pending".equals(status)) { %>
                        <a href="../staffAction?id=<%= rs.getInt("id") %>&status=Approved"
                           style="color:green;">Approve</a>
                        |
                        <a href="../staffAction?id=<%= rs.getInt("id") %>&status=Rejected"
                           style="color:red;">Reject</a>
                    <% } else { %>
                        ---
                    <% } %>
                </td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
    </div>
</div>

<script>
function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle("active");
}
</script>

</body>
</html>
