<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color:#ffccff;
        padding: 10px 30px;
        color: white;
        box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        flex-wrap: wrap;
    }

    .header-section {
        flex: 1;
        text-align: center;
    }

    .header-left {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: flex-start;
    }

    .header-right {
        flex: 1;
        display: flex;
        justify-content: flex-end;
    }

    .college-info img {
        height: 150px;
        width: 150px;
        margin-right: 50px;
        margin-left:170px;
    }

    .college-text h1 {
        margin: 0;
        font-size: 50px;
        font-weight: bold;
        color: #300000;
        text-shadow: 1px 1px white;
    }

    .college-text h2 {
        margin:5px 0;
        font-size: 20px;
        color: #003f5c;
        font-weight:bold;
        white-space:nowrap;
        text-shadow: 1px 1px white;
    }
    .college-text h3 {
        margin:5px 0;
        font-size: 16px;
        color: #003f5c;
        font-weight:bold;
    }

    .college-text p {
        margin: 5px 0;
        font-size: 20px;
        color:darkblue;
        font-weight: bold;
    }

    nav a {
    background-color: black;
    color: white;
    text-decoration: none;
    padding: 8px 16px;
    margin-left: 15px;
    border-radius: 2px;
    font-size: 15px;
    font-weight: bold;
    transition: all 0.3s ease;
    border: none;
    box-shadow: 0 2px 4px rgba(0,0,0,0.2);
}

nav a:hover {
    background-color: #003f5c;
    color: white;
}
        
    @media (max-width: 768px) {
        header {
            flex-direction: column;
        }

        .header-left, .header-section, .header-right {
            flex: 100%;
            justify-content: center;
            text-align: center;
        }

        nav {
            margin-top: 10px;
        }
    }
</style>

<header>
    <div class="header-left college-info">
        <img src="logo.png" alt="College Logo">
    </div>

    <div class="header-section college-text">
        <h1>VIVEKANANDHA</h1>
        <h2>INSTITUTE OF INFORMATION AND MANAGEMENT STUDIES</h2>
        <h3>Elayampalayam, Tiruchengode-TK, Namakkal-DT, Tamil Nadu</h3>
        <p>Permanently Affiliated by Anna University</p>
    </div>

    <div class="header-right">
        <nav>
        <a href="about.jsp">About</a>
        <a href="home.jsp">Home</a>
        <a href="contact.jsp">Contact</a>
        </nav>
    </div>
</header>
