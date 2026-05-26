package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConnection;

@WebServlet("/StaffDashboardServlet")
public class StaffDashboardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Map<String, String>> leaveList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = DBConnection.getConnection();

			String sql = "SELECT lr.id, s.student_name, " + "lr.from_date, lr.to_date, " + "lr.reason, lr.status "
					+ "FROM leave_requests lr " + "JOIN students s " + "ON lr.student_id = s.student_id "
					+ "ORDER BY lr.id DESC";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				Map<String, String> row = new HashMap<>();

				row.put("id", rs.getString("id"));

				row.put("name", rs.getString("student_name")); // ✅ FIXED

				row.put("from", rs.getString("from_date"));

				row.put("to", rs.getString("to_date"));

				row.put("reason", rs.getString("reason"));

				row.put("status", rs.getString("status"));

				leaveList.add(row);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignored) {
			}

			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignored) {
			}
		}

		request.setAttribute("leaveList", leaveList);

		request.getRequestDispatcher("staffDashboard.jsp").forward(request, response);
	}
}