<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password - Leave Management System</title>

<style>

body {
    font-family: Arial, sans-serif;
    background: linear-gradient(to right, #4facfe, #00f2fe);
    margin: 0;
    padding: 0;
}

.container {
    width: 400px;
    margin: 80px auto;
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 0 10px gray;
}

h2 {
    text-align: center;
    color: #333;
}

input[type=text],
input[type=email] {

    width: 100%;
    padding: 10px;
    margin: 8px 0 15px 0;
    border-radius: 5px;
    border: 1px solid #ccc;

}

button {

    width: 100%;
    padding: 10px;
    background: #4facfe;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;

}

button:hover {
    background: #007bff;
}

.link {
    text-align: center;
    margin-top: 15px;
}

.link a {
    text-decoration: none;
    color: #007bff;
}

.message {
    text-align: center;
    color: red;
}

</style>

</head>

<body>

<div class="container">

<h2>Forgot Password 🔑</h2>

<!-- Display Message -->
<%
String message = (String) request.getAttribute("message");
if (message != null) {
%>

<p class="message"><%= message %></p>

<%
}
%>

<form action="StudentForgotPasswordServlet" method="post">

<label>Student ID</label>

<input type="text"
       name="student_id"
       placeholder="Enter your ID"
       required>

<label>Email Address</label>

<input type="email"
       name="email"
       placeholder="Enter your Registered Email"
       required>

<button type="submit">
Send OTP 📧
</button>

</form>

<div class="link">

<a href="studentlogin.jsp">
Back to Login
</a>

</div>

</div>

</body>
</html>