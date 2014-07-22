package com.bunya.report;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseField;

public class MyDataSourceProvider implements JRDataSourceProvider {

	private class MyField extends JRBaseField {
		private static final long serialVersionUID = 9100993853951205670L;

		MyField(String name, String description, Class<?> type) {
			this.name = name;
			this.description = description;
			this.valueClass = type;
			this.valueClassName = type.getName();
		}

		MyField(String name, String description) {
			this(name, description, String.class);
		}
	}

	@Override
	public JRField[] getFields(JasperReport report) throws JRException,
			UnsupportedOperationException {
		if (!supportsGetFieldsOperation()) {
			throw new UnsupportedOperationException(
					"getFields is not available");
		}
		return new JRField[] { new MyField("alpha", "the first field"),
				new MyField("beta", "is this even a letter"),
				new MyField("delta", "the triangle thingy") };
	}

	@Override
	public JRDataSource create(JasperReport report) throws JRException {
		return new MyDataSource();
	}

	@Override
	public void dispose(JRDataSource dataSource) throws JRException {
	}

	@Override
	public boolean supportsGetFieldsOperation() {
		return true;
	}

}
