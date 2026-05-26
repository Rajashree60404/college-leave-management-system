<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>College Leave Management</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">


  <link rel="stylesheet" href="Style.css">
</head>
<body>
  <div class="college-home">
    <img src="logo.png" alt="Logo" class="logo">
<div class="college-text">
  <h1>VIVEKANANDHA</h1>
  <h2>INSTITUTE OF INFORMATION AND MANAGEMENT STUDIES</h2>
  <p>Approved by AICTE, New Delhi & Permanently Affiliated to Anna University, Chennai.</p>
  <p>An ISO 9001:2015 Certified Institution,</p>
  <h2>Elayampalayam, Tiruchengode-TK, Namakkal-DT, Tamilnadu</h2>
</div>
<img src="image.jpg" alt="image" class="right-img">
</div>
<!-- NAVBAR -->
<nav class="navbar">
<script>
function toggleMenu(event) {
  event.preventDefault();
  event.stopPropagation();
  document.querySelector(".dropdown-menu").classList.toggle("show");
}

document.addEventListener("click", function () {
  document.querySelector(".dropdown-menu").classList.remove("show");
});
</script>


  <div class="logo">VIIMS</div>

  <ul class="nav-links">
    <li><a href="#">About</a></li>
    <li><a href="#">Home</a></li>

    <li class="dropdown">
      <a href="#" onclick="toggleMenu(event)">Login</a>
      <ul class="dropdown-menu">
        <li><a href="studentlogin.jsp">Student</a></li>
        <li><a href="stafflogin.jsp">Staff</a></li>
      </ul>
    </li>
    <li><a href="logout.jsp">Logout</a>
  </ul>
</nav>



<!-- HERO -->
<section class="hero">
    </section>

<!-- FEATURES -->
<div class="features">
    <div class="card">
        <h3>🎓 For Students</h3>
        <ul>
            <li>Apply leave online</li>
            <li>Check approval status</li>
            <li>View leave history</li>
        </ul>
    </div>

    <div class="card">
        <h3>👩‍🏫 For Faculty & Staff</h3>
        <ul>
            <li>Approve or reject leave</li>
            <li>Manage students records</li>
            <li>Generate reports</li>
        </ul>
    </div>

    <div class="card">
        <h3>⚙️ Key Features</h3>
        <ul>
            <li>Real-time updates</li>
            <li>Easy to use</li>
            <li>Secure system</li>
        </ul>
    </div>
</div>

</body>
</html>
