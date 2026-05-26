package dao;
import java.util.*;
import java.sql.*;
public class EmailDAO {
	public String getStudentEmail(String userId) {

	    String email = null;

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps =
	            con.prepareStatement(
	                "SELECT email FROM students WHERE student_id=?"
	            );

	        ps.setString(1, userId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            email = rs.getString("email");

	        }

	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return email;
	}
	public String getStaffEmail(String staffId) {

	    String email = null;

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps =
	            con.prepareStatement(
	                "SELECT email FROM staff WHERE staff_id=?"
	            );

	        ps.setString(1, staffId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            email = rs.getString("email");

	        }

	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	    }

	    return email;
	}
}
