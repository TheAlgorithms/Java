import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class TestCSVFile {

	
	@Test
	public void testConstructor1() {
		CSVFile testObj = new CSVFile("testData.csv",',');
		assertEquals(testObj.getElementDouble(1, 1),65.78331, 0.001);
		assertEquals(testObj.getElementString(1, 1),"65.78331");
		assertEquals(testObj.getElementString(0, 1),"\"Height(Inches)\"");
		assertEquals(testObj.getNumberOfRows(),25029);
	}
	
	@Test
	public void testConstructor2() {
		CSVFile testObj = new CSVFile(',');
		testObj.addRow("1, 65.78331, 112.9925");
		testObj.addRow("12, 67.62333, 114.143");
		testObj.addRow("6, 68.69784, 123.3024");
//		testObj.commit("testData2.csv");
//		testObj.commit(new File("testData2.csv"));
	}
	
	@Test
	public void testConstructor3() {
		CSVFile testObj = new CSVFile(new File("testData.csv"),',');
		assertEquals(testObj.getElementDouble(1, 1),65.78331, 0.001);
		assertEquals(testObj.getElementString(1, 1),"65.78331");
		assertEquals(testObj.getElementString(0, 1),"\"Height(Inches)\"");
	}
	
	@Test
	public void testIsPunctuation() {
		assertTrue(CSVFile.isPunctuation(':'));
	}
	
	@Test
	public void testCompile() {
		ArrayList<String>  columns = new ArrayList<String>();
		columns.add("2");
		columns.add("71.51521");
		columns.add("136.4873");
		
		
		assertEquals(CSVFile.compile("2, 71.51521, 136.4873", ','),columns);
		columns.clear();

		// test successful
		columns.add("\"Index\"");
		columns.add("\"Height(Inches)\"");
		columns.add("\"Weight(Pounds)\"");
		
		assertEquals(CSVFile.compile("\"Index\", \"Height(Inches)\", "
				+ "\"Weight(Pounds)\"", ','),columns);
		
		
	}
	
	@Test
	public void testAddRowCommit() {
//		CSVFile testObj = new CSVFile("testData.csv",',');
//		testObj.addRow("1,1,1");
//		testObj.addRow("1,2,3");
//		testObj.commit();
		// test successful
	}
	
	@Test
	public void testFindRow() {
		CSVFile testObj = new CSVFile("testData.csv",',');
		ArrayList<String>  columns = new ArrayList<String>();
		columns.add("2");
		columns.add("71.51521");
		columns.add("136.4873");
		assertEquals(testObj.findRow("71.51521"),columns);
	}
	
	@Test
	public void testContains() {
		CSVFile testObj = new CSVFile("testData.csv",',');
		ArrayList<String>  columns = new ArrayList<String>();
		columns.add("2");
		columns.add("71.51521");
		columns.add("136.4873");
		assertTrue(testObj.contains("71.51521"));
		assertFalse(testObj.contains("9889678"));
	}
	
	
	@Test
	public void testGetColumn() {
		CSVFile testObj = new CSVFile("testData2.csv",',');
		CSVFile testObj2 = new CSVFile("testData3.csv",',');
		ArrayList<String>  columns = new ArrayList<String>();
		columns.add("height");
		columns.add("65.78331");
		columns.add("67.62333");
		assertEquals(testObj.getColumn(1),columns);
		columns.clear();
		columns.add("65.78331");
		columns.add("67.62333");
		assertEquals(testObj.getColumn("height"),columns);
		columns.clear();
		assertEquals(testObj2.getColumn("height"),columns);
	}
	
	
	@Test
	public void testRemoving() {
		CSVFile testObj = new CSVFile("testData4.csv",',');
		//testObj.removeRow("68.69784");
//		testObj.removeRow(0);
//		testObj.updateFile(new File("testData4.csv"));
		// test successful
	}
	

}
