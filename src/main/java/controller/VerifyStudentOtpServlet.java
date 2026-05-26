package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.StudentDAO;

@WebServlet("/VerifyStudentOtpServlet")
public class VerifyStudentOtpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		// Get OTP entered
		String enteredOtp = request.getParameter("otp");

		// Get student_id from session
		HttpSession session = request.getSession();

		String studentId = (String) session.getAttribute("student_id");

		if (studentId == null) {

			response.getWriter()
					.println("<script>alert('Session expired. Try again');location='forgotPassword.jsp';</script>");

			return;
		}

		try {

			StudentDAO dao = new StudentDAO();

			boolean valid = dao.verifyOTP(studentId, enteredOtp);

			if (valid) {

				response.getWriter()
						.println("<script>alert('OTP Verified Successfully');location='resetPassword.jsp';</script>");

			} else {

				response.getWriter().println("<script>alert('Invalid OTP');location='studentVerifyOtp.jsp';</script>");

			}

		} catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("<script>alert('Server Error');location='studentVerifyOtp.jsp';</script>");
		}
	}
}