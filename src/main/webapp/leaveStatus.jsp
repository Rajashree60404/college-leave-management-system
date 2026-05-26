<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="dao.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leave Status</title>
<link rel="stylesheet" href="../css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
/* ---------- TABLE ---------- */
.table-responsive {
    overflow-x: auto;
}

.leave-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.leave-table th,
.leave-table td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

.leave-table th {
    background: #2c3e50;
    color: white;
}

/* ---------- STATUS COLORS ---------- */
.status {
    padding: 5px 10px;
    border-radius: 5px;
    color: white;
    font-size: 14px;
}

.status.pending {
    background: orange;
}

.status.approved {
    background: green;
}

.status.rejected {
    background: red;
}

</style>
</head>

<body>

<div class="menu-btn" onclick="toggleSidebar()">☰</div>

<div class="sidebar" id="sidebar">
    <h3>Student Panel</h3>
    <a href="studentDashboard.jsp">Dashboard</a>
    <a href="applyLeave.jsp">Apply Leave</a>
    <a href="leaveStatus.jsp">Leave Status</a>
    <a href="../logout">Logout</a>
</div>

<div class="content">
    <h2>Your Leave Status</h2>

    <div class="table-responsive">
        <table class="leave-table">
            <tr>
                <th>From Date</th>
                <th>To Date</th>
                <th>Total Days</th>
                <th>Status</th>
            </tr>

            <%
                String studentId = (String) session.getAttribute("studentId");

                try {
                    Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM leave_requests WHERE student_id=? ORDER BY applied_date DESC");
                    ps.setString(1, studentId);

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getDate("from_date") %></td>
                <td><%= rs.getDate("to_date") %></td>
                <td><%= rs.getInt("total_days") %></td>
                <td>
                    <span class="status <%= rs.getString("status").toLowerCase() %>">
                        <%= rs.getString("status") %>
                    </span>
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
