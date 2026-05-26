package controller;

import dao.StudentDAO;
import util.EmailUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.Random;

import java.io.IOException;
import java.util.Random;

@WebServlet("/SendOTPServlet")
public class SendOTPServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String email = request.getParameter("email");

		try {

			StudentDAO dao = new StudentDAO();

			// CHECK EMAIL EXISTS

			String studentId = dao.getStudentIdByEmail(email);

			if (studentId == null || studentId.isEmpty()) {

				response.getWriter().println("<script>alert('Email not found');location='studentlogin.jsp';</script>");
				return;
			}

			// GENERATE OTP

			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);

			// SAVE OTP IN DB

			boolean saved = dao.saveOTP(studentId, String.valueOf(otp));

			if (saved) {

				// SEND EMAIL OTP

				String subject = "Login OTP Verification";
				String message = "Your OTP for login is: " + otp;

				EmailUtil.sendEmail(email, subject, message);

				// STORE SESSION

				request.getSession().setAttribute("student_id", studentId);

				// REDIRECT TO OTP PAGE

				response.getWriter().println("<script>alert('OTP sent to email');location='verifyOtp.jsp';</script>");

			} else {

				response.getWriter()
						.println("<script>alert('OTP generation failed');location='studentlogin.jsp';</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();

			response.getWriter().println("<script>alert('Server Error');location='studentlogin.jsp';</script>");
		}
	}
}