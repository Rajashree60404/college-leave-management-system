package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DBConnection;

@WebServlet("/GeneratePDFServlet")
public class GeneratePDFServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=LeaveReport.pdf");

		try {

			// ✅ A4 Portrait with margins
			Document document = new Document(PageSize.A4, 40, 40, 40, 40);
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			// 🎨 Fonts
			Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
			Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
			Font normalFont = new Font(Font.FontFamily.HELVETICA, 11);

			try {
				Image logo = Image.getInstance(getServletContext().getRealPath("logo.png"));
				logo.scaleToFit(80, 80);
				logo.setAlignment(Image.ALIGN_CENTER);
				document.add(logo);
			} catch (Exception e) {
				System.out.println("Logo not found, skipping...");
			}

			// 🏫 College Name
			Paragraph college = new Paragraph("VIVEKANANDHA INSTITUTE OF INFORMATION AND MANAGEMENT STUDIES",
					titleFont);
			college.setAlignment(Element.ALIGN_CENTER);
			college.setSpacingAfter(10);
			document.add(college);

			// 📄 Title
			Paragraph title = new Paragraph("Students Leave Report", headerFont);
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(15);
			document.add(title);

			// 📅 Date
			Paragraph date = new Paragraph("Generated on: " + new java.util.Date(), normalFont);
			date.setAlignment(Element.ALIGN_RIGHT);
			date.setSpacingAfter(20);
			document.add(date);

			// 📊 Table
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10);
			table.setSpacingAfter(10);
			table.setWidths(new float[] { 3, 2, 2, 4, 2 });

			// 🔷 Table Headers
			String[] headers = { "Student Name", "From Date", "To Date", "Reason", "Status" };

			for (String h : headers) {
				PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setPadding(8); // ✅ spacing inside cell
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(cell);
			}

			// 📥 Database 
			String sql = "SELECT s.name, l.from_date, l.to_date, l.reason, l.status "
					+ "FROM leave_request l JOIN students s " + "ON l.student_id = s.student_id";

			try (Connection con = DBConnection.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {

					PdfPCell c1 = new PdfPCell(new Phrase(rs.getString("name"), normalFont));
					PdfPCell c2 = new PdfPCell(new Phrase(rs.getString("from_date"), normalFont));
					PdfPCell c3 = new PdfPCell(new Phrase(rs.getString("to_date"), normalFont));
					PdfPCell c4 = new PdfPCell(new Phrase(rs.getString("reason"), normalFont));
					PdfPCell c5 = new PdfPCell(new Phrase(rs.getString("status"), normalFont));

					PdfPCell[] cells = { c1, c2, c3, c4, c5 };

					// ✅ Center align + padding
					for (PdfPCell cell : cells) {
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(8);
					}

					table.addCell(c1);
					table.addCell(c2);
					table.addCell(c3);
					table.addCell(c4);

					// 🎨 Status Color
					String status = rs.getString("status");
					if ("APPROVED".equalsIgnoreCase(status)) {
						c5.setBackgroundColor(BaseColor.WHITE);
					} else if ("REJECTED".equalsIgnoreCase(status)) {
						c5.setBackgroundColor(BaseColor.WHITE);
					} else {
						c5.setBackgroundColor(BaseColor.WHITE);
					}

					table.addCell(c5);
				}
			}

			document.add(table);

			// ✍ Signature
			Paragraph footer = new Paragraph("\n\nAuthorized Signature", normalFont);
			footer.setAlignment(Element.ALIGN_RIGHT);
			footer.setSpacingBefore(40);
			document.add(footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}