package com.company;

public class COVID19VaccinationImp implements COVID19Vaccination {

	public DataFrameImp DF;

	@Override
	public void setData(DataFrame df) {
		// TODO Auto-generated method stub
		DF = (DataFrameImp) df;
		
	}

	@Override
	public DataFrame getPeopleVaccinated(String countryName) {
		// TODO Auto-generated method stub
		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("Date");
		cols.add("People Vaccinated");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);
		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);

		cols = new DynamicArray<String>();
		cols.add("Date");
		cols.add("People Vaccinated");
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		return (DataFrame) dataFrame;
	}

	@Override
	public DataFrame getPeopleFullyVaccinated(String countryName) {
		// TODO Auto-generated method stub
		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("Date");
		cols.add("People Fully Vaccinated");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);
		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		cols = new DynamicArray<String>();
		cols.add("Date");
		cols.add("People Fully Vaccinated");
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);


		return (DataFrame) dataFrame;
	}

	@Override
	public DataFrame getPeopleVaccinatedPerHundred(String countryName) {
		// TODO Auto-generated method stub
		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("Date");
		cols.add("Percentage of People Vaccinated");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);
		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		cols = new DynamicArray<String>();
		cols.add("Date");
		cols.add("Percentage of People Vaccinated");
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		return (DataFrame) dataFrame;
	}

	@Override
	public DataFrame getPercentageOfPeopleFullyVaccinated(String countryName) {
		// TODO Auto-generated method stub
		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("Date");
		cols.add("Percentage of People Fully Vaccinated");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);
		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		cols = new DynamicArray<String>();
		cols.add("Date");
		cols.add("Percentage of People Fully Vaccinated");
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		return (DataFrame) dataFrame;
	}

	@Override
	public Set<String> getVaccines(String countryName) {
		// TODO Auto-generated method stub
		Set<String> s = new BSTSet<String>();

		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("Vaccines");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);
		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		Array<Object> vaccines = dataFrame.getCol("Vaccines");

		for(int i=0; i<vaccines.size(); i++){
			//System.out.println(vaccines.get(i));
			s.insert((String) vaccines.get(i));
		}
		return s;
	}

	@Override
	public double getAvgVaccinatedPerDay(String countryName) {
		// TODO Auto-generated method stub
		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("People Vaccinated");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);
		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		return dataFrame.mean("People Vaccinated");
	}

	@Override
	public double getAvgFullyVaccinatedPerDay(String countryName) {
		// TODO Auto-generated method stub
		Array<String> cols = new DynamicArray<String>();
		cols.add("Country");
		cols.add("People Fully Vaccinated");

		DataFrameImp dataFrame = new DataFrameImp();
		dataFrame = (DataFrameImp) DF.filterCols(cols);

		//dataFrame.printColRow(dataFrame.getCol("People Fully Vaccinated"));

		dataFrame = (DataFrameImp) dataFrame.filterRowByCountry(countryName);
		dataFrame = (DataFrameImp) dataFrame.filterCols(cols);

		//System.out.println("----------------------------------------------------");

		//dataFrame.printColRow(dataFrame.getCol("People Fully Vaccinated"));

		return dataFrame.mean("People Fully Vaccinated");
		//return 0.00;
	}

}
