<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Verify OTP</title>

<style>

body {

    font-family: Arial, sans-serif;
    background-color: #f2f2f2;

}

.container {

    width: 350px;
    margin: 100px auto;
    background: white;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0px 0px 10px gray;

}

h2 {

    text-align: center;
    margin-bottom: 20px;

}

label {

    font-weight: bold;

}

input {

    width: 100%;
    padding: 10px;
    margin-top: 8px;
    margin-bottom: 15px;
    border-radius: 5px;
    border: 1px solid #ccc;

}

button {

    width: 100%;
    padding: 10px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;

}

button:hover {

    background-color: #218838;

}

a {

    display: block;
    text-align: center;
    margin-top: 15px;

}

</style>

</head>

<body>

<div class="container">

<h2>Verify OTP</h2>

<form action="<%= request.getContextPath() %>/StaffVerifyOtpServlet"
      method="post">
<label>Enter OTP</label>

<input type="text"
       name="otp"
       placeholder="Enter OTP"
       required>

<button type="submit">

Verify OTP

</button>

</form>

<br>

<a href="<%= request.getContextPath() %>/staffForgotPassword.jsp">
Back to Forgot Password
</a>

</div>

</body>

</html>