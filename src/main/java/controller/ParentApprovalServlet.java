package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LeaveDAO;
import util.EmailUtil;

@WebServlet("/ParentApprovalServlet")
public class ParentApprovalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int leaveId = Integer.parseInt(request.getParameter("leaveId"));

		String action = request.getParameter("action");

		LeaveDAO dao = new LeaveDAO();

		try {

			if ("approve".equals(action)) {

				dao.updateParentApproval(leaveId, "Approved");


// SEND EMAIL TO STAFF


				String staffEmail = dao.getStaffEmail();

				String approveLink = "http://localhost:8080/CollegeLeaveManagementSystem/StaffActionServlet?leaveId="
						+ leaveId + "&action=approve";

				String rejectLink = "http://localhost:8080/CollegeLeaveManagementSystem/StaffActionServlet?leaveId="
						+ leaveId + "&action=reject";

				String message = "Leave Approved by Parent\n\n" + "Leave ID: " + leaveId + "\n\n" + "Approve:\n"
						+ approveLink + "\n\n" + "Reject:\n" + rejectLink;

				EmailUtil.sendEmail(staffEmail, "Leave Request for Staff Approval", message);

				response.getWriter().println("<h2>Parent Approved Successfully</h2>");

			}

			else {

				dao.updateParentApproval(leaveId, "Rejected");

				response.getWriter().println("<h2>Leave Rejected by Parent</h2>");

			}

		}

		catch (Exception e) {

			e.printStackTrace();

			response.getWriter().println("<h2>Error Processing Parent Action</h2>");

		}

	}
}