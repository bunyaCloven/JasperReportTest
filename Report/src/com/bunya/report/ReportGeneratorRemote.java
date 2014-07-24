package com.bunya.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportGeneratorRemote {

	public abstract String[] generateFieldReport() throws JRException;

}