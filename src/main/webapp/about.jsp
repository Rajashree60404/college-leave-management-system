<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>About - Leave Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #e0f7fa, #ffffff);
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #007acc;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .container {
            max-width: 900px;
            margin: 40px auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: green;
            margin-bottom: 20px;
        }

        p {
            line-height: 1.8;
            color: #333;
            font-size: 16px;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #007acc;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="header">
    <h1>About the Leave Management System</h1>
</div>

<div class="container">
    <h2>Our Mission</h2>
    <p>
        The Leave Management System is designed to simplify and automate the process of applying and managing student leaves. 
        It ensures a streamlined workflow for both students and administrators by allowing quick leave applications, 
        approvals, and real-time status tracking.
    </p>

    <h2>Why This System?</h2>
    <p>
        In traditional institutions, the leave application process often involves paperwork, manual approvals, and long delays.
        Our web-based system eliminates those issues by providing a fast, secure, and user-friendly digital interface.
    </p>

    <h2>Who Can Use It?</h2>
    <p> - Students can apply for leaves online and check the status.<br>
        - Administrators can approve/reject leaves and monitor reports.<br>
        - Designed for educational institutions seeking digital transformation.
    </p>

    <a href="home.jsp">← Back to Home</a>
</div>

</body>
</html>
    