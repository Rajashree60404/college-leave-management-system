<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Login | Leave Management System</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:"Segoe UI", Arial, sans-serif;
}

body{
    min-height:100vh;
    display:flex;
}

/* ===== LEFT IMAGE SECTION ===== */

.left{
    flex:1.4;
    background:url("viims-background.jpg") no-repeat center/cover;
    position:relative;
    color:white;
}

.overlay{
    position:absolute;
    inset:0;
    background:rgba(0,0,0,0.45);
    display:flex;
    flex-direction:column;
    justify-content:center;
    padding:50px;
}

.overlay h1{
    font-size:40px;
    margin-bottom:12px;
}

.overlay p{
    font-size:16px;
    max-width:450px;
    line-height:1.6;
}


/* ===== RIGHT LOGIN SECTION ===== */

.right{
    flex:1;
    background:#ffffff;
    display:flex;
    justify-content:center;
    align-items:center;
    padding:30px;
}

.login-box{
    width:100%;
    max-width:380px;
}


/* Logo */

.login-box img{
    display:block;
    margin:0 auto 15px;
    width:80px;
}


/* Heading */

.login-box h2{
    text-align:center;
    margin-bottom:6px;
}

.login-box p{
    text-align:center;
    font-size:14px;
    color:#555;
    margin-bottom:25px;
}


/* Input Fields */

label{
    font-size:14px;
    font-weight:600;
}

input[type="text"],
input[type="password"]{

    width:100%;
    padding:12px;
    margin-top:6px;
    margin-bottom:15px;

    border:1px solid #cbd5e1;
    border-radius:6px;

    font-size:14px;
}


/* Options Row */

.options{
    display:flex;
    justify-content:space-between;
    align-items:center;

    font-size:13px;
    margin-bottom:20px;
}

.options a{
    text-decoration:none;
    color:#2563eb;
}

.options a:hover{
    text-decoration:underline;
}


/* Login Button */

button{

    width:100%;
    padding:12px;

    background:#22c55e;
    color:white;

    border:none;
    border-radius:6px;

    font-size:16px;
    cursor:pointer;
}

button:hover{
    background:#16a34a;
}


/* Error Message */

.error{
    color:red;
    font-size:13px;
    text-align:center;
    margin-bottom:10px;
}


/* Responsive */

@media(max-width:900px){

    body{
        flex-direction:column;
    }

    .left{
        height:40vh;
    }

    .overlay h1{
        font-size:26px;
    }

}

</style>

</head>

<body>

<!-- ===== LEFT SIDE ===== -->

<div class="left">

    <div class="overlay">

        <h1>Staff Leave Portal</h1>

        <p>
            Manage student leave requests with ease.
            View applications, update leave status,
            and maintain organized student records.
        </p>

    </div>

</div>



<!-- ===== RIGHT SIDE ===== -->

<div class="right">

<div class="login-box">

<!-- Logo -->
<img src="logo.png" alt="College Logo">

<h2>Staff Login</h2>

<p>Login to manage student leave requests</p>


<!-- Error Message Display -->
<%
String error = request.getParameter("error");
if(error != null){
%>

<div class="error">
Invalid Staff ID or Password
</div>

<%
}
%>


<form action="StaffLoginServlet" method="post">

<label for="staff_id">
Staff ID
</label>

<input type="text"
       placeholder="ID"
       name="staff_id"
       id="staff_id"
       required>


<label for="password" >
Password
</label>

<input type="password"
       placeholder="Password"
       name="password"
       id="password"
       required>



<div class="options">

<label>

<input type="checkbox">
 Remember me

</label>


<a href="staffForgetPassword.jsp">

Forgot Password?

</a>

</div>


<button type="submit">

Login

</button>

</form>

</div>

</div>

</body>
</html>