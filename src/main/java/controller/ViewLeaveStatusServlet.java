package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LeaveDAO;
import model.LeaveRequest;

@WebServlet("/ViewLeaveStatusServlet")
public class ViewLeaveStatusServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		HttpSession session = request.getSession(false);

		if (session == null) {
			System.out.println("❌ Session is NULL");
		} else {
			System.out.println("✅ Session exists");
			Enumeration<String> names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				String key = names.nextElement();
				System.out.println("SESSION [" + key + "] = " + session.getAttribute(key));
			}
		}

		

		HttpSession session1 = request.getSession(false);

		if (session1 == null || session1.getAttribute("student_id") == null) {
			response.sendRedirect("studentlogin.jsp");
			return;
		}

		String studentId = (session1.getAttribute("student_id").toString());

		LeaveDAO dao = new LeaveDAO();
		List<LeaveRequest> leaveList = dao.getLeavesByStudentId(studentId);

		request.setAttribute("leaveList", leaveList);
		request.getRequestDispatcher("viewLeaveStatus.jsp").forward(request, response);
	}
}
