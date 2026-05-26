package controller;

import dao.LeaveDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import util.EmailUtil;
import java.io.IOException;

@WebServlet("/StaffActionServlet")
public class StaffActionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int leaveId = Integer.parseInt(request.getParameter("leaveId"));

		String action = request.getParameter("action");

		LeaveDAO dao = new LeaveDAO();

		try {

			if ("approve".equals(action)) {

				dao.updateStaffApproval(leaveId, "Approved");

				response.getWriter().println("<h2>Leave Approved by Staff</h2>");

			}

			else {

				dao.updateStaffApproval(leaveId, "Rejected");

				response.getWriter().println("<h2>Leave Rejected by Staff</h2>");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("<h2>Error Processing Staff Action</h2>");

		}

	}
}