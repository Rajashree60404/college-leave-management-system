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

import dao.DBConnection;

@WebServlet("/StaffLoginServlet")
public class StaffLoginServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String staff_id = request.getParameter("staff_id");
		String password = request.getParameter("password");
		System.out.println("staffID: " + staff_id);
		System.out.println("Password: " + password);

		try {
			Connection con = DBConnection.getConnection();
			String sql = "SELECT * FROM staffs WHERE staff_id=? AND password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, staff_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				HttpSession session = request.getSession();
				session.setAttribute("staff_id", rs.getString("staff_id"));
				session.setAttribute("staff_name", rs.getString("staff_name"));

				// Redirect to staff dashboard
				response.sendRedirect("staffDashboard.jsp");
			} else {

				request.setAttribute("errorMessage", "Invalid Staff ID or Password");
				request.getRequestDispatcher("stafflogin.jsp").forward(request, response);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Database Error: " + e.getMessage());
			request.getRequestDispatcher("stafflogin.jsp").forward(request, response);
		}
	}
}
