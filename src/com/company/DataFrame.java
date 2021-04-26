package com.company;

public interface DataFrame {
	// Return the number of columns in the data frame
	int getNbCols();
	
	// Add a column to the data frame with the specified name and type. If a column with the same name already exists, no column is added and the method returns false. Otherwise, the new column is added and the method returns true.
	boolean addCol(String colName, Class<?> colType);
	
	// Same as addCol(colName, colType) but with the column itself passed as parameter. The column is deep-copied to the data frame. If the data frame and the size of the new columns is different from the number of rows of the data frame, the column is not added and the method returns false.
	boolean addCol(String colName, Class<?> colType, Array<Object> col);
	
	// Return true if the data frame contains a column named colName, false otherwise.
	boolean isCol(String colName);
	
	// Return the index of the column (order of insertion into the data frame starting with 0). If colName is not a valid column name, the method throws IllegalArgumentException.
	int getColInd(String colName);
	
	// Return the name of the column j. If j is not valid, the method throws ArrayIndexOutOfBoundsException.
	String getColName(int j);
	
	// Return a map containing information about the columns. The map is indexed by column name and contains the index and the type of each column.
	Map<String, Pair<Integer, Class<?>>> getColInfo();
	
	// Return a deep copy of column j. If j is not valid, the method throws ArrayIndexOutOfBoundsException.
	Array<Object> getCol(int j);
	
	// Return a deep copy of column colName. If colName is not valid, the method throws IllegalArgumentException.
	Array<Object> getCol(String colName);
	
	// Return the number of rows in the data frame.
	int getNbRows();
	
	// Add a row to the data frame. If the size of the array is zero or different from the number of columns, or the types of the arguments do not match that of the columns, the method throws IllegalArgumentException.
	void addRow(Array<Object> row);
	
	// Return a deep copy of row i. If i is not valid, the method throws ArrayIndexOutOfBoundsException.
	Array<Object> getRow(int i);
	
	// Load data frame from CSV file. All data previously existing in the data frame is cleared by this method. The first row (the header) contains the names of the columns. If the file contains only the header, the data frame will be empty and all columns are considered of type String. Otherwise, the types of the columns are deduced from the second line. Four types are supported: integer, double, date (with the format: yyyy-MM-dd) and finally String. The methods deduces the column type by trying to parse the second row in this order: try Integer, then Double, then Date, then if none of these work, the column type is set to String. If the method is successful, it returns true. If the file contains any errors, such as incompatible number of columns or wrong data type, then the data frame remains empty, and the method returns false.
	boolean loadCSV(String fileName);
	
	// Create a new data frame that contains only the columns specified in colNames. If colNames contains invalid column names, the method throws IllegalArgumentException. If colNames is empty,the returned data frame is also empty. The columns of the returned data frame are deep copies of the original columns.
	DataFrame filterCols(Array<String> colNames);
	
	// Create a new data frame that contains only the rows that satisfy the condition cond.
	DataFrame filterRows(Condition cond);
	
	// Return the mean of the column colName. If colName is not valid or the column is not numerical, the method throws IllegalArgumentException.
	double mean(String colName);
}
