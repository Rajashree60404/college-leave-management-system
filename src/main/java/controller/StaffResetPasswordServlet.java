package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.StaffDAO;
import util.EmailUtil;

@WebServlet("/StaffResetPasswordServlet")

public class StaffResetPasswordServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String staffId = request.getParameter("staffId");

		String otp = request.getParameter("otp");

		String newPassword = request.getParameter("newPassword");

		StaffDAO dao = new StaffDAO();

		boolean valid = dao.verifyOTP(staffId, otp);

		if (valid) {

			dao.updatePassword(staffId, newPassword);

			response.getWriter().println("<script>");
			response.getWriter().println("alert('Password Reset Successful');");
			response.getWriter().println("location='stafflogin.jsp';");
			response.getWriter().println("</script>");

		} else {

			response.getWriter().println("<script>");
			response.getWriter().println("alert('Invalid OTP');");
			response.getWriter().println("location='staffResetPassword.jsp';");
			response.getWriter().println("</script>");

		}

	}

}