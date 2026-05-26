// ===== GLOBAL DATES =====
let today = new Date();
let todayZero = new Date();
todayZero.setHours(0,0,0,0);

let currentMonth = today.getMonth();
let currentYear = today.getFullYear();

const monthNames = ["Jan","Feb","Mar","Apr","May","Jun",
                    "Jul","Aug","Sep","Oct","Nov","Dec"];

// ===== CALENDAR =====
function renderCalendar(){

    const daysDiv = document.getElementById("calendarDays");
    const monthYear = document.getElementById("monthYear");

    if(!daysDiv || !monthYear) return;

    monthYear.innerText =
        monthNames[currentMonth] + " " + currentYear;

    daysDiv.innerHTML = "";

    ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"]
    .forEach(d=>{
        let div = document.createElement("div");
        div.innerHTML = "<b>"+d+"</b>";
        daysDiv.appendChild(div);
    });

    let firstDay =
        new Date(currentYear,currentMonth,1).getDay();

    let totalDays =
        new Date(currentYear,currentMonth+1,0).getDate();

    for(let i=0;i<firstDay;i++){
        daysDiv.appendChild(
            document.createElement("div")
        );
    }

    for(let d=1; d<=totalDays; d++){

        let div = document.createElement("div");
        div.innerText = d;

        let dateObj =
            new Date(currentYear,currentMonth,d);

        dateObj.setHours(0,0,0,0);

        if(dateObj < todayZero){

            div.classList.add("past");

        } else {

            div.classList.add("future");
            div.onclick = () => openModal(dateObj);

        }

        if(dateObj.getTime() === todayZero.getTime()){

            div.classList.add("today");

        }

        daysDiv.appendChild(div);
    }
}

// ===== MONTH NAV =====
function prevMonth(){

    currentMonth--;

    if(currentMonth < 0){

        currentMonth = 11;
        currentYear--;

    }

    renderCalendar();
}

function nextMonth(){

    currentMonth++;

    if(currentMonth > 11){

        currentMonth = 0;
        currentYear++;

    }

    renderCalendar();
}

// ===== MODAL =====
function openModal(dateObj){

    let y = dateObj.getFullYear();
    let m = String(dateObj.getMonth()+1).padStart(2,"0");
    let d = String(dateObj.getDate()).padStart(2,"0");

    document.getElementById("fromDate").value =
        `${y}-${m}-${d}`;

    document.getElementById("toDate").min =
        `${y}-${m}-${d}`;

    document.getElementById("leaveModal").style.display = "flex";
}

function closeModal(){

    document.getElementById("leaveModal").style.display = "none";
}

// ===== AJAX SUBMIT =====
function submitLeave(event){

    event.preventDefault();

    let fromDate =
        document.getElementById("fromDate").value;

    let toDate =
        document.getElementById("toDate").value;

    let reason =
        document.getElementById("reason").value;

    // 🔴 Get student_id from hidden field
    let studentId =
        document.getElementById("student_id").value;

    // ===== VALIDATION =====
    if(fromDate === "" || toDate === ""){

        alert("Please select both dates");
        return;

    }

    let from = new Date(fromDate);
    let to = new Date(toDate);

    if(to <= from){

        alert("To Date must be after From Date");
        return;

    }

    let diff =
        (to - from) / (1000*60*60*24) + 1;

    if(diff > 5){

        alert("You can apply maximum 5 days leave only");
        return;

    }

    // ===== SEND DATA TO SERVLET =====
    fetch("ApplyLeaveServlet",{

        method:"POST",

        headers:{
            "Content-Type":
            "application/x-www-form-urlencoded"
        },

        body:

            "student_id="+
            encodeURIComponent(studentId)+

            "&from_date="+
            encodeURIComponent(fromDate)+

            "&to_date="+
            encodeURIComponent(toDate)+

            "&reason="+
            encodeURIComponent(reason)

    })

    .then(res=>res.text())

    .then(result=>{

        if(result === "success"){

            document.getElementById("formMsg").innerText =
                "✅ Leave submitted successfully";

            setTimeout(()=>{

                closeModal();

                document.getElementById("leaveForm").reset();

                document.getElementById("formMsg").innerText = "";

            },1500);

        }
        else{

            document.getElementById("formMsg").innerText =
                "❌ Failed to submit leave";

        }

    })

    .catch(()=>{

        document.getElementById("formMsg").innerText =
            "❌ Server error";

    });

}

// ===== INIT =====
document.addEventListener("DOMContentLoaded", renderCalendar);