package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.StudentDAO;
import util.EmailUtil;

@WebServlet("/StudentForgotPasswordServlet")
public class StudentForgotPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("student_id");

		System.out.println("Entered ID: " + studentId);

		StudentDAO dao = new StudentDAO();

		String email = dao.getStudentEmail(studentId);

		if (email == null || email.isEmpty()) {

			request.setAttribute("message", "Invalid Student ID");

			request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);

			return;
		}

		try {

			// ============================
			// GENERATE OTP
			// ============================

			Random random = new Random();

			int otp = 100000 + random.nextInt(900000);

			// ============================
			// SAVE OTP
			// ============================

			boolean saved = dao.saveOTP(studentId, String.valueOf(otp));

			if (saved) {

				// ============================
				// SEND EMAIL
				// ============================

				String subject = "Student Password Reset OTP";

				String message = "Your OTP is: " + otp;

				EmailUtil.sendEmail(email, subject, message);

				// ============================
				// STORE SESSION
				// ============================

				HttpSession session = request.getSession();

				session.setAttribute("student_id", studentId);

				// ============================
				// REDIRECT TO OTP PAGE
				// ============================

				response.sendRedirect(request.getContextPath() + "/studentVerifyOtp.jsp");

			}

			else {

				response.sendRedirect(request.getContextPath() + "/forgotPassword.jsp");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

			response.sendRedirect(request.getContextPath() + "/forgotPassword.jsp");

		}

	}
}