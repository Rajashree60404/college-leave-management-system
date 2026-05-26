<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us - Leave Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to left, #fce4ec, #ffffff);
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #d81b60;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .container {
            max-width: 800px;
            margin: 40px auto;
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        h2 {
            color: #d81b60;
            margin-bottom: 20px;
        }

        p {
            line-height: 1.7;
            color: #333;
            font-size: 16px;
        }

        .contact-info {
            margin-top: 20px;
        }

        .contact-info p {
            margin: 10px 0;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #d81b60;
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
    <h1>Contact Us</h1>
</div>

<div class="container">
    <h2>Need Help or Have Questions?</h2>
    <p>
        If you have any queries related to leave applications, login issues, or system feedback, feel free to reach out.
    </p>

    <div class="contact-info">
        <p><strong>📍 Address:</strong> Vivekanandha Institute of Information and Management Studies,<br>
        Elayampalayam - 637205, Tiruchengode Tk, Namakkal Dt, Tamil Nadu, India</p>

        <p><strong>📞 Phone:</strong> +91 98765 43210</p>
        <p><strong>📧 Email:</strong>mbaprincipal@gmail.com</p>
    </div>

    <a href="home.jsp">← Back to Home</a>
</div>

</body>
</html>
    