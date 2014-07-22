package com.bunya.report;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@WebServlet("/Feeder")
public class Feeder extends HttpServlet {
	private static final long serialVersionUID = 5155185946020308259L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		InputStream is = getClass().getResourceAsStream("Report.jrxml");
		try {
			JasperReport report = JasperCompileManager.compileReport(is);
			JasperPrint print = JasperFillManager.fillReport(report, null,
					new MyDataSource());
			String location = getClass().getProtectionDomain().getCodeSource()
					.getLocation().toString();
			String folderLoc = location.substring(
					location.indexOf(File.separator),
					location.lastIndexOf(File.separator));
			String targetLoc = folderLoc.concat("/../../../../../result.html");
			System.out.println(targetLoc);
			JasperExportManager.exportReportToHtmlFile(print, targetLoc);
			response.sendRedirect("result.html");
		} catch (JRException e) {
			e.printStackTrace(out);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
