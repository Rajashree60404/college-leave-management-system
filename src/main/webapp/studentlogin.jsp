<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Login | Leave Management System</title>

<style>
*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family: "Segoe UI", Arial, sans-serif;
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
    font-size:38px;
    margin-bottom:10px;
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

.login-box img{
    display:block;
    margin:0 auto 15px;
    width:70px;
}

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

input{
    width:100%;
    padding:12px;
    margin-bottom:15px;
    border:1px solid #cbd5e1;
    border-radius:6px;
    font-size:14px;
}

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

/* ===== RESPONSIVE ===== */
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
        <h1>Student Leave Portal</h1>
        <p>
            Apply for leave online, track approval status,
            and receive instant updates from staff.
            A simple and secure way to manage your leave request.
        </p>
    </div>
</div>

<!-- ===== RIGHT SIDE ===== -->
<div class="right">
    <div class="login-box">

        <!-- optional college logo -->
        <img src="logo.png" alt="College Logo">

        <h2>Student Login</h2>
        <p>Login to apply and track your leave requests</p>

        <form action="StudentLoginServlet" method="post">
            <input type="text" name="student_id" placeholder="Student ID / Register Number" required>
            <input type="password" name="password" placeholder="Password" required>
            

            <div class="options">
                <label>
                    <input type="checkbox"> Remember me
                </label>
                <a href="forgotPassword.jsp">Forgot Password?</a>
            </div>

            <button type="submit">Log In</button>
        </form>
        <script>
function togglePassword() {

    var passField =
        document.getElementById("password");

    if (passField.type === "password") {

        passField.type = "text";

    } else {

        passField.type = "password";

    }
}
</script>

    </div>
</div>

</body>
</html>
