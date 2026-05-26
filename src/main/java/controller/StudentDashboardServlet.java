package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LeaveDAO;
import model.Student;

@WebServlet("/StudentDashboardServlet")
public class StudentDashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get existing session
		HttpSession session = request.getSession(false);

		// Check login session
		if (session == null || session.getAttribute("student_id") == null) {

			response.sendRedirect("studentlogin.jsp");

			return;
		}

		// Get student_id from session
		String studentId = (String) session.getAttribute("student_id");

		LeaveDAO dao = new LeaveDAO();

		// =============================
		// GET VALUES FROM DAO
		// =============================

		int leavesTaken = dao.getLeaveTaken(studentId);

		int leaveRemaining = dao.getLeaveRemaining(studentId);

		int score = dao.getScore(studentId);

		// =============================
		// CALCULATE STATUS
		// =============================

		String status;

		if (score >= 90) {

			status = "Excellent";

		}

		else if (score >= 75) {

			status = "Good";

		}

		else if (score >= 50) {

			status = "Average";

		}

		else {

			status = "Poor";

		}

		// =============================
		// SET ATTRIBUTES
		// =============================

		request.setAttribute("totalAllowedLeaves", 7);

		request.setAttribute("leavesTaken", leavesTaken);

		request.setAttribute("leaveRemaining", leaveRemaining);

		request.setAttribute("score", score);

		request.setAttribute("status", status);

		// =============================
		// FORWARD TO JSP
		// =============================

		request.getRequestDispatcher("studentdashboard.jsp").forward(request, response);

	}
}