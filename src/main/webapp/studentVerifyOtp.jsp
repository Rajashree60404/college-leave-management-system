<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Verify OTP</title>

<style>

body {
    font-family: Arial;
    background: linear-gradient(to right, #36d1dc, #5b86e5);
}

.container {

    width: 350px;
    margin: 100px auto;
    background: white;
    padding: 25px;
    border-radius: 10px;
    box-shadow: 0 0 10px gray;

}

h2 {
    text-align: center;
}

input {

    width: 100%;
    padding: 10px;
    margin-top: 10px;
    margin-bottom: 15px;
    border-radius: 5px;
    border: 1px solid #ccc;

}

button {

    width: 100%;
    padding: 10px;
    background: #5b86e5;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;

}

button:hover {

    background: #3a66c2;

}

</style>

</head>

<body>

<div class="container">

<h2>Enter OTP 🔐</h2>

<form action="VerifyStudentOtpServlet" method="post">

<label>Enter the OTP</label>

<input type="text"
       name="otp"
       placeholder="Enter OTP"
       required>

<button type="submit">
Verify OTP
</button>

</form>

</div>

</body>

</html>