<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Reset Password</title>

<style>

body {
    font-family: Arial;
    background-color: #f2f2f2;
}

.container {
    width: 350px;
    margin: 120px auto;
    background: white;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 0 10px gray;
}

h2 {
    text-align: center;
}

label {
    font-weight: bold;
}

input {
    width: 100%;
    padding: 10px;
    margin-top: 8px;
    margin-bottom: 15px;
}

button {
    width: 100%;
    padding: 10px;
    background: green;
    color: white;
    border: none;
}

</style>

</head>

<body>

<div class="container">

<h2>Staff Reset Password</h2>

<form action="<%= request.getContextPath() %>/StaffResetPasswordServlet"
      method="post">

<!-- Hidden staff_id from session -->

<input type="hidden"
       name="staff_id"
       value="<%= session.getAttribute("staff_id") %>">


<label>Enter OTP</label>

<input type="text"
       name="otp"
       placeholder="Enter OTP"
       required>


<label>New Password</label>

<input type="password"
       name="newPassword"
       placeholder="Enter New Password"
       required>


<button type="submit">

Reset Password

</button>

</form>

</div>

</body>

</html>