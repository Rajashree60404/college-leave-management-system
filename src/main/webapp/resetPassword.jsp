<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Reset Password</title>
</head>

<body>

<h2>Reset Password</h2>

<form action="SendOTPServlet"
method="post">

<input type="hidden"
name="student_id"
value="<%=request.getParameter("student_id")%>">

Enter OTP:

<input type="text"
name="otp"
required>

<br><br>

New Password:

<input type="password"
name="new_password"
required>

<br><br>

<input type="submit"
value="Reset Password">

</form>

</body>
</html>