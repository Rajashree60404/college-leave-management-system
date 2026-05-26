package dao;
import dao.DBConnection;

import java.sql.*;

public class StudentDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean checkStudentLogin(String studentId,
            String password) {

boolean status = false;

try {

con = DBConnection.getConnection();

String sql =
"SELECT * FROM students WHERE student_id=? AND password=?";

ps = con.prepareStatement(sql);

ps.setString(1, studentId);
ps.setString(2, password);

rs = ps.executeQuery();

if (rs.next()) {

status = true;

}

} catch (Exception e) {

e.printStackTrace();

}

return status;
}
    
    // =====================================
    // GET STUDENT ID BY EMAIL
    // =====================================
    public String getStudentIdByEmail(String email) {

        String studentId = null;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT student_id FROM students WHERE email=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            if (rs.next()) {
                studentId = rs.getString("student_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentId;
    }

    // =====================================
    // GET STUDENT EMAIL
    // (KEEP ONLY ONE METHOD - FIXED)
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

    // =====================================
    // GET STUDENT NAME
    // =====================================
    public String getStudentName(String studentId) {

        String name = "";

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT student_name FROM students WHERE student_id=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, studentId);

            rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("student_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    // =====================================
    // TOTAL LEAVES ALLOWED
    // =====================================
    public int getTotalLeavesAllowed() {
        return 30;
    }

    // =====================================
    // LEAVES TAKEN
    // FIXED COLUMN NAME
    // =====================================
    public int getLeavesTaken(String studentId) {

        int count = 0;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT COUNT(*) FROM leave_requests " +
                    "WHERE student_id=? AND staff_status='Approved'";

            ps = con.prepareStatement(sql);
            ps.setString(1, studentId);

            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // =====================================
    // LEAVE STATUS (LATEST)
    // FIXED COLUMN NAME
    // =====================================
    public String getLeaveStatus(String studentId) {

        String status = "No Leave";

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT status FROM leave_requests " +
                    "WHERE student_id=? ORDER BY leave_id DESC LIMIT 1";

            ps = con.prepareStatement(sql);
            ps.setString(1, studentId);

            rs = ps.executeQuery();

            if (rs.next()) {
                status = rs.getString("status");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // =====================================
    // SAVE OTP
    // =====================================
    public boolean saveOTP(String studentId, String otp) {

        boolean status = false;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "UPDATE students SET otp=? WHERE student_id=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, otp);
            ps.setString(2, studentId);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // =====================================
    // VERIFY OTP
    // =====================================
    public boolean verifyOTP(String studentId, String otp) {

        boolean status = false;

        try {

            con = DBConnection.getConnection();

            String sql =
                    "SELECT student_id FROM students WHERE student_id=? AND otp=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, otp);

            rs = ps.executeQuery();

            if (rs.next()) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}