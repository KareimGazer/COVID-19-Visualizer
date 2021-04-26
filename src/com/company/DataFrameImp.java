package com.company;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.zip.DataFormatException;
import java.text.SimpleDateFormat;

public class DataFrameImp implements DataFrame {

	public DynamicArray<DynamicArray<Object>> DF;
	public int colsNum;
	public int rowsNum;
	public DynamicArray<Pair> Header;

	public  DataFrameImp(){
		DF = new DynamicArray<DynamicArray<Object>>();
		Header = new DynamicArray<Pair>();
		colsNum = 0;
		rowsNum = 0;
	}
	public DataFrameImp(DynamicArray<Pair> header, DynamicArray<DynamicArray<Object>> frame){
		DF = frame;
		Header = header;
		colsNum = header.size();
		rowsNum = frame.get(0).size();
	}

	public void setHeader(DynamicArray<Pair> header) {
		Header = header;
	}

	@Override
	//Return the number of columns in the data frame
	public int getNbCols() {
		// TODO Auto-generated method stub
		return  colsNum;
	}

	@Override
	public boolean addCol(String colName, Class<?> colType) {
		// TODO Auto-generated method stub
		for(int i=0; i<Header.size(); i++){
			String name = (String) Header.get(i).first;
			if(name.equals(colName)) return false;
		}
		Pair<String, Class<?>> p = new Pair<String, Class<?>>(colName, colType);
		Header.add(p);
		colsNum++;
		return true;
	}

	@Override
	public boolean addCol(String colName, Class<?> colType, Array<Object> col) {
		// TODO Auto-generated method stub
		if(addCol(colName, colType)){
			DF.add((DynamicArray<Object>) col);
			return true;
		}
		else return false;
	}

	@Override
	public boolean isCol(String colName) {
		// TODO Auto-generated method stub
		for(int i=0; i<Header.size(); i++){
			String name = (String) Header.get(i).first;
			if(name.equals(colName)) return true;
		}
		return false;
	}

	@Override
	public int getColInd(String colName) {
		// TODO Auto-generated method stub
		for(int i=0; i<Header.size(); i++){
			String name = (String) Header.get(i).first;
			if(name.equals(colName)) return i;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String getColName(int j) {
		// TODO Auto-generated method stub
		if (j<0 || j>colsNum-1) throw new ArrayIndexOutOfBoundsException();
		return (String) Header.get(j).first ;
	}

	@Override
	public Map<String, Pair<Integer, Class<?>>> getColInfo() {
		// TODO Auto-generated method stub
		Map<String, Pair<Integer, Class<?>>> map = new BSTMap<String, Pair<Integer, Class<?>>>();
		for(int i=0; i<Header.size(); i++){
			String name = (String) Header.get(i).first;
			Pair<Integer, Class<?>> p = new Pair<Integer, Class<?>>(null, null);
			p.first = i;
			p.second =(Class<?>) Header.get(i).second;
			map.insert(name, p);
		}
		return map;
	}

	@Override
	public Array<Object> getCol(int j) {
		// TODO Auto-generated method stub
		if (j<0 || j>colsNum-1) throw new ArrayIndexOutOfBoundsException();
		return DF.get(j);
	}

	@Override
	public Array<Object> getCol(String colName) {
		// TODO Auto-generated method stub
		for(int i=0; i<Header.size(); i++){
			String name = (String) Header.get(i).first;
			if(name.equals(colName)) return DF.get(i);
		}
		throw new IllegalArgumentException();
	}

	//bottelneck
	public Array<Object> readLine(String[] line){
		if(line.length==0 || line.length != colsNum) throw new IllegalArgumentException("Error reading short line");
		Array<Object> row = new DynamicArray<Object>();
		for(String word : line){
			Object data;
			try {
				//integer
				data = Integer.parseInt(word);
			}
			catch(Exception a){
				try {
					//double
					data = Double.parseDouble(word);
				}
				catch (Exception b){
					try {
						//date
						data = new SimpleDateFormat("yyyy-MM-dd").parse(word);
					}
					catch (Exception c){
						//string
						data = word;
					}
				}
			}
			row.add(data);
		}
		return row;
	}

	public void readSecondLine(String[] line){
		DynamicArray<Object> row = new DynamicArray<Object>();

		for(int i=0; i<Header.size(); i++){
			String word = line[i];
			Object data;
			Pair<String, Class<?>> p;
			p = Header.get(i);
			try {
				//integer
				data = Integer.parseInt(word);
				p.second = Integer.class;
			}
			catch(Exception a){
				try {
					//double
					data = Double.parseDouble(word);
					p.second = Double.class;
				}
				catch (Exception b){
					try {
						//date
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(word);
						data = date;
						//System.out.println(date.getClass() + "-------------------------------------------");
						p.second = Date.class;
					}
					catch (Exception c){
						//string
						data = word;
						p.second = String.class;
					}
				}
			}
			row.add(data);
			Header.set(i, p);
		}

		for(int i=0; i<row.size(); i++){
			DynamicArray<Object> arr = new DynamicArray<Object>();
			arr.add(row.get(i));
			DF.add(arr);
			//this function assumes the header is taken so no need to count cols
			//colsNum++;
		}
		rowsNum++;
	}

	@Override
	public int getNbRows() {
		// TODO Auto-generated method stub
		return rowsNum;
	}

	@Override
	public void addRow(Array<Object> row) {
		// TODO Auto-generated method stub
		//check types of cols of row matches those of other rows
		if(rowsNum<1) {
			for (int i = 0; i < row.size(); i++) {
				DynamicArray<Object> col = new DynamicArray<Object>();
				//System.out.println(row.get(i));
				col.add(row.get(i));
				DF.add(col);
				rowsNum++;
			}
			colsNum = row.size();
		}
		else{
			if(row.size()==0 || row.size()!= colsNum) throw new IllegalArgumentException();
			else {
				for (int i = 0; i < colsNum; i++) {
					DynamicArray<Object> col;
					col = DF.get(i);
					col.add(row.get(i));
					//DF.elements[i].add(row.get(i)); // old
					DF.set(i, col);
				}
				rowsNum++;
			}
		}
	}

	@Override
	public Array<Object> getRow(int i) {
		// TODO Auto-generated method stub
		//System.out.println("row" + rowsNum + " cols" + colsNum);
		if (i<0 || i>rowsNum-1) throw new ArrayIndexOutOfBoundsException();
		else {
			Array<Object> row = new DynamicArray<Object>();
			for(int j=0; j<colsNum; j++){
				row.add(DF.get(j).get(i));
			}
			return row;
		}
	}

	public void csvHeader(String [] headers){
		for(int i=0; i<headers.length; i++){
			Pair<String, Class<?>> p = new Pair<String, Class<?>>(null, null);
			p.first = headers[i];
			p.second = String.class;
			Header.add(p);
			colsNum++;
			//System.out.println(p.first);
		}
	}

	@Override
	/*the file contains any errors , such
    as incompatible number of columns or wrong data type , then the data frame remains empty ,
    and the method returns false .*/
	public boolean loadCSV(String fileName) {
		// TODO Auto-generated method stub
		//read headers
		//read first line
		//read the remaining
		DF = new DynamicArray<DynamicArray<Object>>();
		Header = new DynamicArray<Pair>();
		colsNum = 0;
		rowsNum = 0;

		String filePath = new File("").getAbsolutePath();

		filePath = filePath + "\\data\\" + fileName;
		String line = "";

		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);

			if (myReader.hasNextLine()) {
				line = myReader.nextLine();
				String[] words = line.split(",");
				csvHeader(words);
			} else return false;
			//second line after the header
			if (myReader.hasNextLine()) {
				line = myReader.nextLine();
				String[] words = line.split(",");
				readSecondLine(words);
			}
			while (myReader.hasNextLine()) {
				line = myReader.nextLine();
				String[] words = line.split(",");
				Array<Object> row = readLine(words);
				addRow(row);
			}
			//testing here
			//printHeader();
			//printColRow(getCol("People Fully Vaccinated"));

			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println("Can't find file.");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DataFrame filterCols(Array<String> colNames) {
		// TODO Auto-generated method stub
		DynamicArray<DynamicArray<Object>> base = new DynamicArray<DynamicArray<Object>>();
		DynamicArray<Pair> h = new DynamicArray<Pair>();
		for(int i=0; i<colNames.size(); i++){
			int index = getColInd(colNames.get(i));
			base.add((DynamicArray<Object>) getCol(index));
			h.add(Header.get(index));
		}
		return new DataFrameImp(h, base);
	}

	@Override
	public DataFrame filterRows(Condition cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double mean(String colName) {
		// TODO Auto-generated method stub
		int sum =0;
		Array<Object> col = getCol(colName);
		int index = getColInd(colName);
		if(Header.get(index).second == Date.class || Header.get(index).second == String.class){
			throw new IllegalArgumentException();
		}
		else{
			for(int i=0; i<col.size();i++){
				sum += (Integer) col.get(i);
			}
		}
		return sum / col.size();
	}

	public void printColRow(Array<Object> colRow){
		for(int i=0; i<colRow.size(); i++){
			System.out.println(colRow.get(i) + " " +colRow.get(i).getClass());
		}
	}

	public void printHeader(){
		for(int i=0; i<Header.size(); i++){
			System.out.println(Header.get(i).first + " " + Header.get(i).second);
		}
	}

	public DataFrame filterRowByCountry(String country){
		DataFrameImp data_frame = new DataFrameImp();
		for(int i=0; i<rowsNum; i++){
			Array<Object> row = getRow(i);
			if(row.get(0).equals(country)){
				data_frame.addRow(row);
			}
		}
		data_frame.setHeader(Header);
		return data_frame;
	}

}
