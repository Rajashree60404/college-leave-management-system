package model;

import java.sql.Date;

public class LeaveRequest {

    private int leaveId;
    private String studentId;

    private Date fromDate;
    private Date toDate;

    private String reason;

    private String status;
    private String parentStatus;
    private String staffStatus;

    private String medicalFile;
    

    // ======================
    // GETTERS AND SETTERS
    // ======================

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // ✅ REQUIRED METHODS

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentStatus() {
        return parentStatus;
    }

    public void setParentStatus(String parentStatus) {
        this.parentStatus = parentStatus;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getMedicalFile() {
        return medicalFile;
    }

    public void setMedicalFile(String medicalFile) {
        this.medicalFile = medicalFile;
    }

}