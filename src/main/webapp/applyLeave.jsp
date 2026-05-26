<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply Leave</title>

<link rel="stylesheet" href="../css/style.css">

<meta name="viewport"
      content="width=device-width, initial-scale=1.0">

<style>

.form-card {
    background: #ffffff;
    padding: 20px;
    max-width: 500px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.form-card label {
    display: block;
    margin-top: 10px;
}

.form-card input,
.form-card textarea {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
}

.form-card button {
    margin-top: 15px;
    padding: 10px;
    background: #2c3e50;
    color: white;
    border: none;
    cursor: pointer;
}



</style>



</head>

<body>

<div class="menu-btn"
     onclick="toggleSidebar()">☰</div>

<div class="sidebar"
     id="sidebar">

    <h3>Student Panel</h3>

    <a href="studentDashboard.jsp">
        Dashboard
    </a>

    <a href="applyLeave.jsp">
        Apply Leave
    </a>

    <a href="leaveStatus.jsp">
        Leave Status
    </a>

    <a href="logout.jsp">
        Logout
    </a>

</div>

<div class="content">

<h2>Apply Leave</h2>

<form action="ApplyLeaveServlet"
      method="post"
      enctype="multipart/form-data"
      onsubmit="return validateLeave()">

<input type="hidden"
       name="student_id"
       value="<%=session.getAttribute("student_id")%>">

<label>From Date</label>

<input type="date"
       name="from_date"
       id="fromDate"
       required>

<br><br>

<label>To Date</label>

<input type="date"
       name="to_date"
       id="toDate"
       required>

<br><br>

<label>Reason</label>

<textarea name="reason"
          rows="4"
          required></textarea>

<br><br>



</form>

<%
    String msg =
        request.getParameter("msg");

    if ("limitExceeded".equals(msg)) {
%>

<p class="error">
⚠ You already used 5 days leave.
</p>

<%
    }
    else if ("invalidDate".equals(msg)) {
%>

<p class="error">
⚠ Invalid date selection.
</p>

<%
    }
%>

</div>

<script>

function toggleSidebar() {

    document
    .getElementById("sidebar")
    .classList.toggle("active");

}

</script>

</body>
</html>