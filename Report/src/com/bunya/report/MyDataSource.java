package com.bunya.report;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MyDataSource implements JRDataSource {

	private class MyDataStructure {
		String alpha;
		String beta;
		String delta;

		MyDataStructure(String alpha, String beta, String delta) {
			this.alpha = alpha;
			this.beta = beta;
			this.delta = delta;
		}
	}

	List<MyDataStructure> data;
	Iterator<MyDataStructure> it;
	MyDataStructure currentData;

	MyDataSource() {
		data = new LinkedList<MyDataStructure>();
		data.add(new MyDataStructure("a1", "a2", "a3"));
		data.add(new MyDataStructure("b1", "b2", "b3"));
		data.add(new MyDataStructure("c1", "c2", "c3"));
		data.add(new MyDataStructure("d1", "d2", "d3"));
		data.add(new MyDataStructure("e1", "e2", "e3"));
		data.add(new MyDataStructure("f1", "f2", "f3"));
		it = data.iterator();
	}

	@Override
	public boolean next() throws JRException {
		Boolean value = it.hasNext();
		currentData = (value) ? it.next() : currentData;
		System.out.println(value + ": " + currentData);
		return value;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		switch (jrField.getName()) {
		case "alpha":
			return currentData.alpha;
		case "beta":
			return currentData.beta;
		case "delta":
			return currentData.delta;
		default:
			return null;
		}
	}

}
