<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout | Leave Management System</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:"Segoe UI", Arial, sans-serif;
}

body{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
    background: linear-gradient(135deg,#4facfe,#00f2fe);
}

.container{
    background:white;
    padding:40px;
    border-radius:12px;
    text-align:center;
    width:400px;
    box-shadow:0 8px 25px rgba(0,0,0,0.2);
}

.icon{
    font-size:65px;
    margin-bottom:15px;
}

h1{
    color:#333;
    margin-bottom:10px;
}

p{
    color:#555;
    margin-bottom:25px;
    font-size:15px;
}

.btn{
    display:inline-block;
    padding:12px 28px;
    background:#4facfe;
    color:white;
    text-decoration:none;
    border-radius:8px;
    font-size:15px;
    transition:0.3s;
}

.btn:hover{
    background:#00c6ff;
}

.footer{
    margin-top:20px;
    font-size:13px;
    color:#777;
}

</style>

</head>

<body>

<div class="container">

    <div class="icon">
        🔒
    </div>

    <h1>Logout Successful</h1>

    <p>
        You have been successfully logged out from the 
        <b>Leave Management System</b>.
    </p>

    <!-- Change login.jsp if your login page name is different -->
    <a href="home.jsp" class="btn">
        Login Again
    </a>

    <div class="footer">
        Thank you for using the system 😊
    </div>

</div>

</body>
</html>