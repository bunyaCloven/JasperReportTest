package com.bunya.report;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportGeneratorService implements ReportGeneratorRemote {

	@Override
	public String[] generateFieldReport() throws JRException {
		List<String> result = new LinkedList<String>();
		InputStream is = getClass().getResourceAsStream("Report.jrxml");
		JasperReport report = JasperCompileManager.compileReport(is);
		JasperPrint print = JasperFillManager.fillReport(report, null,
				new MyDataSource());
		String s = JasperExportManager.exportReportToXml(print);
		String[] a = s.split("\n");
		for (String st : a) {
			String mark = "CDATA";
			if (st.contains(mark)) {
				String cut = st.substring(st.indexOf(mark) + mark.length() + 1);
				result.add(cut.substring(0, cut.indexOf("]")));
			}
		}
		return result.toArray(new String[] {});
	}
}
