package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.StaffDAO;
import util.EmailUtil;

@WebServlet("/StaffForgotPasswordServlet")
public class StaffForgotPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String staffId = request.getParameter("staff_id");

		StaffDAO dao = new StaffDAO();

		
		// GET EMAIL FROM DATABASE
		

		String email = dao.getStaffEmail(staffId);

		if (email == null || email.isEmpty()) {

			request.setAttribute("message", "Invalid Staff ID");

			request.getRequestDispatcher("staffForgotPassword.jsp").forward(request, response);

			return;
		}

		try {

			
			// GENERATE OTP
			

			Random random = new Random();

			int otp = 100000 + random.nextInt(900000);

			
			// SAVE OTP
			

			boolean saved = dao.saveOTP(staffId, String.valueOf(otp));

			if (saved) {

				
				// SEND EMAIL
				

				String subject = "Staff Password Reset OTP";

				String message = "Your OTP for password reset is: " + otp;

				EmailUtil.sendEmail(email, subject, message);

				
				// STORE SESSION
				

				HttpSession session = request.getSession();

				session.setAttribute("staff_id", staffId);

				
				// REDIRECT TO OTP PAGE
				

				response.sendRedirect(request.getContextPath() + "/staffVerifyOtp.jsp");

			}

			else {

				response.sendRedirect(request.getContextPath() + "/staffForgotPassword.jsp");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

			response.sendRedirect(request.getContextPath() + "/staffForgotPassword.jsp");

		}

	}
}