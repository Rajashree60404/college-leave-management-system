package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.StudentDAO;
import dao.DBConnection;

@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String studentId = request.getParameter("student_id");

		String password = request.getParameter("password");

		StudentDAO dao = new StudentDAO();

		boolean valid = dao.checkStudentLogin(studentId, password);

		if (valid) {

			// Login success
			HttpSession session = request.getSession();

			session.setAttribute("student_id", studentId);

			response.sendRedirect("studentdashboard.jsp");

		} else {

			// Wrong password message
			response.getWriter().println(
					"<script>alert('Please enter the correct password');location='studentlogin.jsp';</script>");
		}
	}
}