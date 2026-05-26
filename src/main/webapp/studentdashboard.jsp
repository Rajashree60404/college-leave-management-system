<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Dashboard</title>

<style>

body{
    margin:0;
    font-family:Arial, sans-serif;
    background:#FFEBCD;
}

/* ===== LAYOUT ===== */

.dashboard-wrapper{
    display:flex;
    min-height:100vh;
}

.sidebar{
    width:70px;
    background:linear-gradient(90deg,#003366,#0F52BA,#4B0082);
    color:white;
    display:flex;
    flex-direction:column;
    align-items:center;
    padding-top:20px;
}

.sidebar a{
    color:white;
    text-decoration:none;
    margin:20px 0;
    font-size:22px;
}

.main{
    flex:1;
    padding:25px;
}

/* ===== CARDS ===== */

.cards{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(200px,1fr));
    gap:20px;
    margin-bottom:25px;
}

.card{
    background:linear-gradient(90deg,#003366,#0F52BA,#4B0082);
    color:white;
    padding:18px;
    border-radius:10px;
}

/* ===== CALENDAR ===== */

.content-row{
    display:grid;
    grid-template-columns:380px 1fr;
    gap:20px;
}

.calendar{
    background:#fff;
    padding:15px;
    border-radius:10px;
}

.calendar-header{
    display:flex;
    justify-content:space-between;
    font-weight:bold;
}

.calendar-days{
    display:grid;
    grid-template-columns:repeat(7,1fr);
    gap:5px;
    text-align:center;
}

.calendar-days div{
    height:38px;
    line-height:38px;
    border-radius:6px;
    cursor:pointer;
}

.future:hover{
    background:#dbeafe;
}

.today{
    background:#2563eb;
    color:white;
}

/* ===== SUMMARY ===== */

.box{
    background:white;
    padding:20px;
    border-radius:10px;
}

/* Upload Button */

.upload-label{

display:inline-block;
padding:10px 15px;
background:linear-gradient(90deg,#2563eb,#1e40af);
color:white;
border-radius:6px;
cursor:pointer;
margin-top:10px;

}

.upload-label input{

display:none;

}

#fileName{

font-size:14px;
margin-top:6px;

}

/* ===== MODAL ===== */

.modal{
display:none;
position:fixed;
inset:0;
background:rgba(0,0,0,0.5);
justify-content:center;
align-items:center;
}

.modal-content{
background:#fff;
padding:20px;
width:350px;
border-radius:10px;
}

.modal-content input,
.modal-content textarea,
.modal-content button{
width:100%;
margin-top:10px;
padding:8px;
}

</style>

<script>

/* Modal functions */

function openModal(date){

document.getElementById("leaveModal").style.display="flex";

document.getElementById("fromDate").value=date;

document.getElementById("toDate").value=date;

}

function closeModal(){

document.getElementById("leaveModal").style.display="none";

}

/* Validation */

function validateLeave(){

let fromDate=document.getElementById("fromDate").value;

let toDate=document.getElementById("toDate").value;

let medicalFile=document.getElementById("medicalFile").value;

if(toDate<fromDate){

alert("To Date must be after From Date");

return false;

}

let from=new Date(fromDate);

let to=new Date(toDate);

let diff=(to-from)/(1000*60*60*24)+1;

if(diff>7 && medicalFile===""){

alert("Medical certificate required for leave more than 7 days");

return false;

}

return true;

}

/* File Name Display */

window.onload=function(){

let fileInput=document.getElementById("medicalFile");

if(fileInput){

fileInput.addEventListener("change",function(){

let fileName=this.files[0]
? this.files[0].name
: "No file selected";

document.getElementById("fileName").innerText=fileName;

});

}

}

</script>

</head>

<body>

<%@ include file="header.jsp" %>

<div class="dashboard-wrapper">

<div class="sidebar">

<a href="home.jsp">🏠</a>

<a href="logout">🚪</a>

</div>

<div class="main">

<h2>
Welcome, ${sessionScope.student_name} 👋
</h2>

<div class="cards">

<div class="card">
<h4>Total Allowed Leaves</h4>
<p>${totalAllowedLeaves}</p>
</div>

<div class="card">
<h4>Leaves Taken</h4>
<p>${leavesTaken}</p>
</div>

<div class="card">
<h4>Score</h4>
<p>${score}%</p>
</div>

<div class="card">
<h4>Status</h4>
<p>${status}</p>
</div>

</div>

<div class="content-row">

<!-- CALENDAR -->

<div class="calendar">

<div class="calendar-header">

<button onclick="prevMonth()">⬅</button>

<span id="monthYear"></span>

<button onclick="nextMonth()">➡</button>

</div>

<div class="calendar-days"
id="calendarDays">

</div>

</div>

<!-- SUMMARY -->

<div class="box">

<h3>📊 Leave Summary</h3>

<p><b>Allowed Leaves:</b> ${totalAllowedLeaves}</p>

<p><b>Taken Leaves:</b> ${leavesTaken}</p>

<p><b>Remaining:</b> ${leaveRemaining}</p>

<a href="ViewLeaveStatusServlet">

<button style="
padding:10px 15px;
background:linear-gradient(90deg,#2563eb,#1e40af);
color:white;
border-radius:6px;
margin-top:10px;">

View Leave Status

</button>

</a>

<hr>

<h4>📎 Upload Medical Certificate</h4>

<label class="upload-label">

Choose Medical File

<input type="file"
id="medicalFile"
name="medical_file"
accept=".pdf,.jpg,.png">

</label>

<p id="fileName">
No file selected
</p>

</div>

</div>

</div>

</div>

<!-- APPLY LEAVE MODAL -->

<div class="modal"
id="leaveModal">

<div class="modal-content">

<h3>Apply Leave</h3>

<form action="ApplyLeaveServlet"
method="post"
enctype="multipart/form-data"
onsubmit="return validateLeave()">

<label>From Date</label>

<input type="date"
id="fromDate"
name="from_date"
readonly>

<label>To Date</label>

<input type="date"
id="toDate"
name="to_date"
required>

<label>Reason</label>

<textarea
name="reason"
required></textarea>

<button type="submit">

Submit Leave

</button>

<button type="button"
onclick="closeModal()">

Close

</button>

</form>

</div>

</div>

<script src="studentDashboard.js"></script>

</body>
</html>