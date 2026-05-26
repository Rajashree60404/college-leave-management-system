package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.StaffDAO;

@WebServlet("/StaffVerifyOtpServlet")
public class StaffVerifyOtpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

		String enteredOtp = request.getParameter("otp");

		

		HttpSession session = request.getSession(false);

		if (session == null) {

			response.sendRedirect(request.getContextPath() + "/staffForgotPassword.jsp");

			return;
		}

		

		String staffId = (String) session.getAttribute("staff_id");

		if (staffId == null) {

			response.sendRedirect(request.getContextPath() + "/staffForgotPassword.jsp");

			return;
		}

		try {

			StaffDAO dao = new StaffDAO();

			// ============================
			// GET OTP FROM DATABASE
			// ============================

			String dbOtp = dao.getOTP(staffId);

			if (dbOtp != null && dbOtp.equals(enteredOtp)) {

				// ============================
				// STORE VERIFIED OTP IN SESSION
				// ============================

				session.setAttribute("verifiedOtp", enteredOtp);

				// ============================
				// REDIRECT TO RESET PASSWORD
				// ============================

				response.sendRedirect(request.getContextPath() + "/staffResetPassword.jsp");

			} else {

				// OTP WRONG

				response.sendRedirect(request.getContextPath() + "/staffVerifyOtp.jsp");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

			response.sendRedirect(request.getContextPath() + "/staffVerifyOtp.jsp");

		}

	}
}