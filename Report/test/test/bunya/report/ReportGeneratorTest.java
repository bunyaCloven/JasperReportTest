package test.bunya.report;

import net.sf.jasperreports.engine.JRException;

import org.junit.Test;

import com.bunya.report.ReportGeneratorRemote;
import com.bunya.report.ReportGeneratorService;

public class ReportGeneratorTest {

	@Test
	public void testGenerateFieldReport() throws JRException {
		ReportGeneratorRemote reportGenerator = new ReportGeneratorService();
		System.out.println("Start");
		for (String s : reportGenerator.generateFieldReport()) {
			System.out.println(s);
		}
		System.out.println("End");
	}
}
