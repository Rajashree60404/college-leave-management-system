package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.LeaveRequest;
import dao.DBConnection;

public class LeaveDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // =====================================
    // APPLY LEAVE (Return Leave ID)
    // =====================================

    public int applyLeave(LeaveRequest leave) {

        int leaveId = 0;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO leave_requests " +
                    "(student_id, from_date, to_date, reason, medical_file, " +
                    "parent_status, staff_status, status) " +
                    "VALUES (?, ?, ?, ?, ?, 'Pending', 'Pending', 'Pending')";

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, leave.getStudentId());
            ps.setDate(2, leave.getFromDate());
            ps.setDate(3, leave.getToDate());
            ps.setString(4, leave.getReason());
            ps.setString(5, leave.getMedicalFile());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    leaveId = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return leaveId;
    }

    // =====================================
    // GET student email by leave id
    // =====================================
    public String getStudentEmailByLeaveId(int leaveId) {

        String email = null;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT s.email FROM students s " +
                    "JOIN leave_requests l ON s.student_id = l.student_id " +
                    "WHERE l.id=?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, leaveId);

            rs = ps.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }
    
 // =====================================
 // GET get student email
 // =====================================
    public String getStudentEmail(String studentId) {

        String email = null;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT email FROM students WHERE student_id=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, studentId);

            rs = ps.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }
    
    //Get Staff Email
    public String getStaffEmail() {

        String email = null;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT email FROM staffs LIMIT 1";

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }
    
    
    
 
    
    // UPDATE PARENT APPROVAL
    
 // Parent approval update

    public void updateParentApproval(
    int leaveId,
    String status) {

    try {

    Connection con =
    DBConnection.getConnection();

    String sql =
    "UPDATE leave_requests "
    + "SET parent_status=? "
    + "WHERE id=?";

    PreparedStatement ps =
    con.prepareStatement(sql);

    ps.setString(1, status);
    ps.setInt(2, leaveId);

    ps.executeUpdate();

    }

    catch(Exception e) {

    e.printStackTrace();

    }

    }
   
 // Staff approval update

    public void updateStaffApproval(
    int leaveId,
    String status) {

    try {

    Connection con =
    DBConnection.getConnection();

    String sql =
    "UPDATE leave_requests "
    + "SET staff_status=? "
    + "WHERE id=?";

    PreparedStatement ps =
    con.prepareStatement(sql);

    ps.setString(1, status);
    ps.setInt(2, leaveId);

    ps.executeUpdate();

    }

    catch(Exception e) {

    e.printStackTrace();

    }

    }
   
    // STAFF APPROVE / REJECT
    

    public boolean updateStaffStatus(
            int leaveId,
            String statusValue) {

        boolean status = false;

        try {

            con = DBConnection.getConnection();

            String sql =
            "UPDATE leave_requests "
            + "SET staff_status=? "
            + "WHERE id=?";

            ps = con.prepareStatement(sql);

            ps.setString(1,
            statusValue);

            ps.setInt(2,
            leaveId);

            int rows =
            ps.executeUpdate();

            if (rows > 0) {

                status = true;

            }

        }
        catch(Exception e) {

            e.printStackTrace();

        }

        return status;

    }
    //final status
    public boolean updateFinalStatus(int leaveId, String statusValue) {

        boolean status = false;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "UPDATE leave_requests SET status=? WHERE id=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, statusValue);
            ps.setInt(2, leaveId);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    

 
    // GET LEAVE TAKEN


    public int getLeaveTaken(
            String studentId) {

        int taken = 0;

        try {

            con = DBConnection.getConnection();

            String sql =
            "SELECT COUNT(*) "
            + "FROM leave_requests "
            + "WHERE student_id=? "
            + "AND staff_status='Approved'";

            ps = con.prepareStatement(sql);

            ps.setString(1,
            studentId);

            rs = ps.executeQuery();

            if (rs.next()) {

                taken =
                rs.getInt(1);

            }

        }
        catch(Exception e) {

            e.printStackTrace();

        }

        return taken;

    }

   
    // GET LEAVE REMAINING
  

    public int getLeaveRemaining(
            String studentId) {

        int totalLeaves = 7;

        int taken =
        getLeaveTaken(studentId);

        return totalLeaves - taken;

    }
    //update
    public boolean updateLeaveStatus(
    		int leaveId,
    		String status) {

    		boolean result = false;

    		try {

    		Connection con =
    		DBConnection.getConnection();

    		String sql =
    		"UPDATE leave_requests "
    		+ "SET status=? "
    		+ "WHERE id=?";

    		PreparedStatement ps =
    		con.prepareStatement(sql);

    		ps.setString(1, status);
    		ps.setInt(2, leaveId);

    		int rows =
    		ps.executeUpdate();

    		result = rows > 0;

    		}

    		catch(Exception e) {

    		e.printStackTrace();

    		}

    		return result;

    		}

   
    // GET SCORE
   
    public int getScore(
            String studentId) {

        int remaining =
        getLeaveRemaining(studentId);

        int score = 0;

        if (remaining >= 18) {

            score = 10;

        }

        else if (remaining >= 15) {

            score = 8;

        }

        else if (remaining >= 10) {

            score = 6;

        }

        else {

            score = 4;

        }

        return score;

    }

    
    // GET LEAVES BY STUDENT
    
    public List<LeaveRequest> getLeavesByStudentId(String studentId) {

        List<LeaveRequest> list = new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                "SELECT * FROM leave_requests WHERE student_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, studentId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                LeaveRequest leave = new LeaveRequest();

                leave.setFromDate(
                        rs.getDate("from_date"));

                leave.setToDate(
                        rs.getDate("to_date"));

                leave.setReason(
                        rs.getString("reason"));

                leave.setStatus(
                        rs.getString("status"));

                list.add(leave);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    
}