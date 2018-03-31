/*
 * author: Christian Bender
 * class: CSVFile
 * 
 * This class implements a data structure for handling of 
 * CSV-files.
 * 
 * Overview
 * 
 * CSVFile(path : string, seperator : char)
 * 	compiles the CSV-file in the inner data structure.
 * 
 * CSVFile (file : File, seperator : char)
 * CSVFile (seperator : char)
 * 
 * compile (row : string, seperator : char) : string
 * 	compiles row in its columns.
 * 
 * isPunctuation (ch : char) : boolean
 * 	check whether ch is a punctuation character.
 * 
 * getElementString(row : int, column : int) : string
 * 	returns the specified element.
 * 
 * getElementDouble(row : int, column : int) : double
 * 	returns the specified element as double.
 * 
 * addRow(row : string) : void
 * 	adds a row to the inner data structure.
 * 	without writing into the CSV-file.
 * 
 * set (row : int, column : int, item : string) : void
 * 	replaces the specified item with a newer.
 * 
 * commit() : void 
 * 	writes the added data into CSV-file.
 * 
 * commit(path : String) : void
 * commit(file : File ) : void
 * 
 * findRow(key : string) : ArrayList<String>
 * 	returns the searched row otherwise null.
 * 
 * contains(key : string) : boolean
 * 	returns true if a row contains 'key' otherwise false.
 * 
 * getColumn(column : int) : ArrayList<String>
 * 	returns the specified column as ArrayList.
 * 
 * getColumn(key : string) : ArrayList<String>
 * 
 * removeRow(key : string) : void 
 * 	purpose removes the specified row at the inner data structure.
 * 
 * removeRow(column : int) : void
 * 
 * updateFile() : void
 * 	overwrites the CSV-file with the current inner data structure.
 * 	removed rows are remove in the CSV-file, too.
 * 
 * updateFile(file : File) : void
 * 
 * getNumberOfRows() : int
 * 	returns the number of rows in CSV-File
 * 	it counts only rows that in the table.
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class CSVFile {

	// the actual CSV-content
	private ArrayList<ArrayList<String>> table;
	// to tracking added rows. 
	private ArrayList<Integer> trackList;
	// notice the seperator
	private char seperator;
	// notice the path of the CSV-File.
	private String pathCSVFile;

	
	/**
	 * Constructor
	 * 
	 * @param path
	 * @param seperator
	 * @purpose loads the CSV-file and fills the inner table with the data
	 */
	public CSVFile(String path, char seperator) {
		table = new ArrayList<ArrayList<String>>();
		trackList = new ArrayList<Integer>();
		pathCSVFile = path;
		this.seperator = seperator;
		String row = null;
		File file = new File(path);
		ArrayList<String> colums = new ArrayList<String>();
		if (!file.canRead() || !file.isFile()) {
			System.out.println("unable to open file");
			System.exit(1);
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(path));
			while ((row = in.readLine()) != null) {
				// uses the compile method to compile the row
				// in its columns.
				table.add(compile(row, seperator));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	/**
	 * 
	 * @param file
	 * same constructor different arguments.
	 */
	public CSVFile(File file, char seperator) {
		table = new ArrayList<ArrayList<String>>();
		trackList = new ArrayList<Integer>();
		pathCSVFile = file.getPath();
		this.seperator = seperator;
		String row = null;
		ArrayList<String> colums = new ArrayList<String>();
		if (!file.canRead() || !file.isFile()) {
			System.out.println("unable to open file");
			System.exit(1);
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			while ((row = in.readLine()) != null) {
				// uses the compile method to compile the row
				// in its columns.
				table.add(compile(row, seperator));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	/**
	 * 
	 * @param separator
	 * @purpose Constructor for empty CSV-File.
	 */
	public CSVFile(char separator) {
		table = new ArrayList<ArrayList<String>>();
		trackList = new ArrayList<Integer>();
		pathCSVFile = "";
		this.seperator = seperator;
	}

	
	/**
	 * 
	 * @param row
	 * @param sep
	 *            the seperator
	 * @return ArrayList<String> that contains each column of row.
	 * @purpose compiles row in its columns.
	 * 
	 */
	public static ArrayList<String> compile(String row, char sep) {
		ArrayList<String> columns = new ArrayList<String>();
		int state = 0;
		char ch = ' ';
		String column = "";
		int countQuotes = 0;
		for (int i = 0; i < row.length(); i++) {
			// fetch next character
			ch = row.charAt(i);
			switch (state) {

			// state 0
			case 0:
				if (Character.isLetter(ch) || Character.isDigit(ch)) {
					state = 1;
					column += ch;
				} else if (ch == '"') { // catch "
					state = 2;
					column += ch;
				} else if (Character.isWhitespace(ch)) {
					state = 0;
				}
				break;
			// state 1
			case 1:
				if ((Character.isLetter(ch) || Character.isDigit(ch) 
						|| isPunctuation(ch) || Character.isWhitespace(ch))
						&& (ch != sep)) {
					state = 1;
					column += ch;
				} else if (ch == sep || ch == '\n') {
					state = 0;
					column = column.trim();
					columns.add(column);
					column = "";
				} else { // error case
					throw new RuntimeException("compile: invalid"
							+ " character " + ch);
				}
				break;

			// state 2
			case 2:
				if ((Character.isLetter(ch) || Character.isDigit(ch)
						|| Character.isWhitespace(ch) || isPunctuation(ch))
						&& (ch != '"')) {
					state = 2;
					column += ch;
				} else if (ch == '"') {
					state = 3;
					column += ch;
				} else { // error case
					throw new RuntimeException("compile: invalid"
							+ " character " + ch);
				}
				break;

			// state 3
			case 3:
				if ((Character.isLetter(ch) || Character.isDigit(ch)
						|| Character.isWhitespace(ch) || isPunctuation(ch))
						&& (ch != '"') && (ch != sep)) {
					state = 2;
					column += ch;
				} else if (ch == ',') {
					state = 0;
					column = column.trim();
					columns.add(column);
					column = "";
				} else { // error case
					throw new RuntimeException("compile: invalid"
							+ " character " + ch);
				}

			}

		}
		// for adding the remaining column
		columns.add(column);
		column = "";
		return columns;
	}

	
	/**
	 * 
	 * @param ch
	 * @returns true if ch is punctuation character otherwise false.
	 */
	public static boolean isPunctuation(char ch) {
		return Pattern.matches("\\p{Punct}", "" + ch);
	}

	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return the specific element as string
	 */
	public String getElementString(int row, int column) {
		// check arguments
		if (row < table.size() && column < table.get(0).size()) {
			return table.get(row).get(column);
		} else { // error case
			throw new RuntimeException("getElementString: "
					+ " arguments out of bound.");
		}
		
	}

	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return the specific element as double
	 * @throws NumberFormatException
	 */
	public double getElementDouble(int row, int column) 
			throws NumberFormatException {
		// check arguments
		if (row < table.size() && column < table.get(0).size()) {
			return Double.parseDouble(table.get(row).get(column));
		} else { // error case
			throw new RuntimeException("getElementString: "
					+ " arguments out of bound.");
		}
	}

	
	/**
	 * 
	 * @param row
	 * @purpose adds a row to the inner data structure.
	 * 			without writing into the CSV-file.
	 */
	public void addRow(String row) {
		table.add(compile(row, seperator));
		// tracking the last item.
		trackList.add(table.size() - 1);
	}

	
	/**
	 * @purpose: writes the added data into CSV-file.
	 */
	public void commit() {
		String row = "";
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					pathCSVFile, true)));

			// writes the tracked rows into CSV-file.
			for (int index : trackList) {
				for (int i = 0; i < table.get(index).size(); i++) {
					if (i != 0) {
						row += ",";
						row += table.get(index).get(i);
					} else {
						row += table.get(index).get(i);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row for the next one
				row = ""; 
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
		
		// remove tracked rows.
		trackList.clear();
	}
	
	
	/**
	 * @param path
	 * @purpose: writes the added data into CSV-file (given path).
	 */
	public void commit(String path) {
		String row = "";
		pathCSVFile = path;
		PrintWriter pWriter = null;
		try {

			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					pathCSVFile, true)));

			// writes the tracked rows into CSV-file.
			for (int index : trackList) {
				for (int i = 0; i < table.get(index).size(); i++) {
					if (i != 0) {
						row += ",";
						row += table.get(index).get(i);
					} else {
						row += table.get(index).get(i);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row
				row = ""; 
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
		
		// remove tracked rows.
		trackList.clear();
	}
	
	/**
	 * 
	 * @param file
	 * @purpose: writes the added data into CSV-file (given path).
	 */
	public void commit(File file) {
		String row = "";
		pathCSVFile = file.getPath();
		PrintWriter pWriter = null;
		try {

			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					file, true)));

			// writes the tracked rows into CSV-file.
			for (int index : trackList) {
				for (int i = 0; i < table.get(index).size(); i++) {
					if (i != 0) {
						row += ",";
						row += table.get(index).get(i);
					} else {
						row += table.get(index).get(i);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row
				row = ""; 
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
		
		// remove tracked rows.
		trackList.clear();
	}


	/**
	 * 
	 * @param key
	 * @returns the searched row otherwise null.
	 */
	public ArrayList<String> findRow(String key) {
		ArrayList<String> ans = null;
		key = key.trim();
		for (int i = 0; i < table.size(); i++) {
			for (String item : table.get(i)) {
				item = item.trim();
				if (item.equals(key)) {
					ans = table.get(i);
					break;
				}
			}
		}
		return ans;
	}
	
	
	/**
	 * 
	 * @param key
	 * @returns true if a row contains 'key' otherwise false.
	 */
	public boolean contains(String key) {
		boolean ans = false;
		key = key.trim();
		for (int i = 0; i < table.size(); i++) {
			for (String item : table.get(i)) {
				item = item.trim();
				if (item.equals(key)) {
					ans = true;
					break;
				}
			}
		}
		return ans;
	}
	
	
	/**
	 * 
	 * @param n of type integer
	 * @returns the specified column as ArrayList.
	 */
	public ArrayList<String> getColumn(int column) {
		ArrayList<String> ans = new ArrayList<String>();
		if (column < table.get(0).size()) {
			for (int i = 0; i < table.size(); i++) {
				ans.add(table.get(i).get(column));
			}
		} else { // error case
			throw new RuntimeException("getColumn: column is too large");
		}
		return ans;
	}
	
	
	/**
	 * 
	 * @param label of type string
	 * @returns the specified column at label.
	 */
	public ArrayList<String> getColumn(String label) {
		ArrayList<String> ans = new ArrayList<String>();
		int n = table.get(0).indexOf(label);
		// check whether label exists.
		if (n != -1) {	
			for (int i = 1; i < table.size(); i++) {
				ans.add(table.get(i).get(n));
			}
		} else { // error case
			throw new RuntimeException("getColumn: label " + label
					+ " don't exists.");
		}
		return ans;
	}
	
	
	/**
	 * 
	 * @param key of type string
	 * @purpose removes the specified row at the inner data structure.
	 */
	public void removeRow(String key) {
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i) != null) {
				for (String item : table.get(i)) {
					if (item.equals(key)) {
						table.set(i,null);
						// updates the track list
						if (trackList.indexOf(i) != -1) {
							trackList.remove(i);
						}
					}
				}
			}
		}
		// removes all null-elements
		table.removeAll(Collections.singleton(null));
	}
	
	
	/**
	 * 
	 * @param n of type integer
	 * @purpose removes the specified row at the inner data structure.
	 */
	public void removeRow(int column) {
		if (column < table.size()) {
			table.set(column, null);
			// removes all null-elements
			table.removeAll(Collections.singleton(null));
			// updates the track list
			if (trackList.indexOf(column) != -1) {
				trackList.remove(column);
			}
		} else {
			throw new RuntimeException("removeRow: column is too large");
		}
	}
	
	
	/**
	 * overwrites the CSV-file with the current inner data structure.
	 * removed rows are remove in the CSV-file, too.
	 */
	public void updateFile() {
		String row = "";
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					pathCSVFile)));

			// writes the table rows into CSV-file.
			for (int i = 0; i < table.size(); i++) {
				for (int j = 0; j < table.get(i).size(); j++) {
					if (j != 0) {
						row += ",";
						row += table.get(i).get(j);
					} else {
						row += table.get(i).get(j);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row
				row = ""; 
			}
			
			// writes the tracked rows into CSV-file.
			for (int index : trackList) {
				for (int i = 0; i < table.get(index).size(); i++) {
					if (i != 0) {
						row += ",";
						row += table.get(index).get(i);
					} else {
						row += table.get(index).get(i);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row for the next one
				row = ""; 
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
		
		// remove tracked rows.
		trackList.clear();
	}
	
	
	/**
	 * 
	 * @param file
	 * overwrites the CSV-file with the current inner data structure.
	 * removed rows are remove in the CSV-file, too.
	 */
	public void updateFile(File file) {
		String row = "";
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					file)));

			// writes the table rows into CSV-file.
			for (int i = 0; i < table.size(); i++) {
				for (int j = 0; j < table.get(i).size(); j++) {
					if (j != 0) {
						row += ",";
						row += table.get(i).get(j);
					} else {
						row += table.get(i).get(j);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row
				row = ""; 
			}
			
			// writes the tracked rows into CSV-file.
			for (int index : trackList) {
				for (int i = 0; i < table.get(index).size(); i++) {
					if (i != 0) {
						row += ",";
						row += table.get(index).get(i);
					} else {
						row += table.get(index).get(i);
					}
				}
				// add newline for next row
				row += "\n"; 
				pWriter.write(row);
				// clear row
				row = ""; 
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
		
		// remove tracked rows.
		trackList.clear();
	}
	
	
	/**
	 * 
	 * @returns the number of rows in CSV-File
	 * it counts only rows that in the table.
	 */
	public int getNumberOfRows() {
		return table.size();
	}
	
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @param item
	 * @purpose replaces the specified item with a newer.
	 */
	public void set(int row, int column, String item) {
		if (row < table.size()) {
			if (column < table.get(row).size()) {
				table.get(row).set(column, item);
			} else {
				throw new RuntimeException("set: column is too large!");
			}
		} else {
			throw new RuntimeException("set: row is too large!");
		}
	}
	
}
