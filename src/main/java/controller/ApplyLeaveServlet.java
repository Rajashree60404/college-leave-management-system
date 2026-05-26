package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import util.EmailUtil;
import dao.LeaveDAO;
import model.LeaveRequest;

import dao.StudentDAO;

@WebServlet("/ApplyLeaveServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ApplyLeaveServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = (String) request.getSession().getAttribute("student_id");
		String fromDate = request.getParameter("from_date");
		String toDate = request.getParameter("to_date");
		String reason = request.getParameter("reason");

		Part filePart = request.getPart("medical_file");

		LeaveDAO leaveDao = new LeaveDAO();
		StudentDAO studentDao = new StudentDAO();

		try {

			// CALCULATE DAYS

			java.time.LocalDate start = java.time.LocalDate.parse(fromDate);
			java.time.LocalDate end = java.time.LocalDate.parse(toDate);

			long days = java.time.temporal.ChronoUnit.DAYS.between(start, end) + 1;

			// FILE UPLOAD LOGIC

			String fileName = "";

			if (filePart != null && filePart.getSize() > 0) {
				fileName = filePart.getSubmittedFileName();
			}

			// VALIDATION RULE

			if (days > 7 && (fileName == null || fileName.isEmpty())) {

				response.getWriter().println(
						"<script>alert('Medical file required for leave more than 7 days');location='studentDashboard.jsp';</script>");
				return;
			}

			// SAVE FILE TO FOLDER

			if (fileName != null && !fileName.isEmpty()) {

				String uploadPath = getServletContext().getRealPath("") + "uploads";
				File dir = new File(uploadPath);
				if (!dir.exists())
					dir.mkdir();

				filePart.write(uploadPath + File.separator + fileName);
			}

			// CREATE LEAVE OBJECT

			LeaveRequest leave = new LeaveRequest();
			leave.setStudentId(studentId);
			leave.setFromDate(java.sql.Date.valueOf(fromDate));
			leave.setToDate(java.sql.Date.valueOf(toDate));
			leave.setReason(reason);
			leave.setMedicalFile(fileName);

			// INSERT INTO DB

			int leaveId = leaveDao.applyLeave(leave);

			if (leaveId > 0) {

				String parentEmail = studentDao.getStudentEmail(studentId);

				String approveLink = "http://localhost:8080/CollegeLeaveManagement/ParentApprovalServlet?leaveId="
						+ leaveId + "&action=approve";

				String rejectLink = "http://localhost:8080/CollegeLeaveManagement/ParentApprovalServlet?leaveId="
						+ leaveId + "&action=reject";

				String message = "Leave Request\n" + "Student ID: " + studentId + "\n" + "From: " + fromDate + "\n"
						+ "To: " + toDate + "\n\n" + "Approve: " + approveLink + "\n" + "Reject: " + rejectLink;

				EmailUtil.sendEmail(parentEmail, "Leave Approval Request", message);

				response.getWriter().println(
						"<script>alert('Leave Submitted Successfully');location='studentdashboard.jsp';</script>");

			} else {

				response.getWriter()
						.println("<script>alert('Error submitting leave');location='studentdashboard.jsp';</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}