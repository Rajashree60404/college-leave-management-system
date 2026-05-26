<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Enter OTP</title>

<style>

/* Page Background */
body {
    font-family: Arial, Helvetica, sans-serif;
    background: linear-gradient(135deg, #4facfe, #00f2fe);
    margin: 0;
    padding: 0;
}

/* Center Container */
.container {
    width: 350px;
    background: white;
    padding: 30px;
    margin: 100px auto;
    border-radius: 12px;
    box-shadow: 0px 8px 20px rgba(0,0,0,0.2);
    text-align: center;
}

/* Heading */
h2 {
    margin-bottom: 25px;
    color: #333;
}

/* Label */
label {
    font-weight: bold;
    display: block;
    margin-bottom: 8px;
    text-align: left;
}

/* OTP Input */
input[type="text"] {
    width: 100%;
    padding: 12px;
    border-radius: 8px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    font-size: 16px;
    transition: 0.3s;
}

/* Input Focus Effect */
input[type="text"]:focus {
    border-color: #4facfe;
    outline: none;
    box-shadow: 0px 0px 5px rgba(79,172,254,0.5);
}

/* Button */
button {
    width: 100%;
    padding: 12px;
    background: linear-gradient(135deg, #4facfe, #00f2fe);
    border: none;
    border-radius: 8px;
    font-size: 16px;
    color: white;
    cursor: pointer;
    transition: 0.3s;
}

/* Button Hover */
button:hover {
    background: linear-gradient(135deg, #43e97b, #38f9d7);
}

/* Responsive */
@media screen and (max-width: 400px) {
    .container {
        width: 90%;
        margin-top: 80px;
    }
}

</style>

</head>

<body>

<div class="container">

<h2>Enter OTP</h2>

<form action="VerifyOtpServlet" method="post">

<label>Enter OTP</label>

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