package dao;

import java.sql.*;
import java.util.*;
public class StaffDAO {

Connection con;
PreparedStatement ps;
ResultSet rs;

public String getOTP(String staffId) {

    String otp = null;

    try {

        Connection con = DBConnection.getConnection();

        String query =
            "SELECT otp FROM staff WHERE staff_id=?";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setString(1, staffId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            otp = rs.getString("otp");

        }

        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return otp;

}
//save otp
public boolean saveOTP(String staffId, String otp) {

    boolean status = false;

    try {

        Connection con = DBConnection.getConnection();

        String query =
            "UPDATE staff SET otp=? WHERE staff_id=?";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setString(1, otp);
        ps.setString(2, staffId);

        int row = ps.executeUpdate();

        if (row > 0) {

            status = true;

        }

        con.close();

    } catch (Exception e) {

        e.printStackTrace();

    }

    return status;

}

// ===============================
// GET STAFF EMAIL
// ===============================

public String getStaffEmail(
        String staffId) {

    String email = null;

    try {

        con =
        DBConnection.getConnection();

        String sql =
        "SELECT email "
        + "FROM staffs "
        + "WHERE staff_id=?";

        ps = con.prepareStatement(sql);

        ps.setString(1, staffId);

        rs = ps.executeQuery();

        if (rs.next()) {

            email =
            rs.getString("email");

        }

    }
    catch(Exception e) {

        e.printStackTrace();

    }

    return email;

}



// ===============================
// SAVE OTP
// ===============================



// ===============================
// VERIFY OTP
// ===============================

public boolean verifyOTP(
        String staffId,
        String otp) {

    boolean status = false;

    try {

        con =
        DBConnection.getConnection();

        String sql =
        "SELECT staff_id "
        + "FROM staffs "
        + "WHERE staff_id=? "
        + "AND otp=?";

        ps = con.prepareStatement(sql);

        ps.setString(1, staffId);
        ps.setString(2, otp);

        rs = ps.executeQuery();

        if (rs.next()) {

            status = true;

        }

    }
    catch(Exception e) {

        e.printStackTrace();

    }

    return status;

}



// ===============================
// UPDATE PASSWORD
// ===============================

public boolean updatePassword(
        String staffId,
        String newPassword) {

    boolean status = false;

    try {

        con =
        DBConnection.getConnection();

        String sql =
        "UPDATE staffs "
        + "SET password=? "
        + "WHERE staff_id=?";

        ps = con.prepareStatement(sql);

        ps.setString(1, newPassword);
        ps.setString(2, staffId);

        int row =
        ps.executeUpdate();

        if (row > 0) {

            status = true;

        }

    }
    catch(Exception e) {

        e.printStackTrace();

    }

    return status;

}
public String getAllStaffEmail() {

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
public List<String> getAllStaffEmails() {

    List<String> emails = new ArrayList<>();

    try {

        con = DBConnection.getConnection();

        String sql = "SELECT email FROM staffs";

        ps = con.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {
            emails.add(rs.getString("email"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emails;
}

}