<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" %>
<!DOCTYPE html>

<html>

<head>

<title>Staff Forgot Password</title>

<link rel="stylesheet"
      href="forgot-reset.css">

</head>
<body>

<div class="container">

<h2>Forgot Password</h2>

<form action="<%= request.getContextPath() %>/StaffForgotPasswordServlet"
      method="post">

<label>Staff ID</label>

<input type="text"
       name="staff_id"
       placeholder="Enter Staff ID"
       required>

<label>Email</label>

<input type="email"
       name="email"
       placeholder="Enter Email"
       required>

<button type="submit">

Send OTP

</button>

</form>

<br>

<a href="<%= request.getContextPath() %>/staffLogin.jsp">

Back to Login

</a>

</div>

</body>

</html>