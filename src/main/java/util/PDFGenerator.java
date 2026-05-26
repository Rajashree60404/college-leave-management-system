package util;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.LeaveRequest;

public class PDFGenerator {

    public static void generateMonthlyReport(List<LeaveRequest> leaveList,
                                             HttpServletResponse response) {

        try {

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=Monthly_Leave_Report.pdf");

            OutputStream out = response.getOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            // Title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Monthly Leave Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // Empty line

            // Table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.addCell("Student ID");
            table.addCell("From Date");
            table.addCell("To Date");
            table.addCell("Reason");
            table.addCell("Status");

            for (LeaveRequest leave : leaveList) {
                table.addCell(leave.getStudentId());
                table.addCell(leave.getFromDate().toString());
                table.addCell(leave.getToDate().toString());
                table.addCell(leave.getReason());
                table.addCell(leave.getStaffStatus());
            }

            document.add(table);

            document.close();
            out.close();

            System.out.println("PDF Generated Successfully!");

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
