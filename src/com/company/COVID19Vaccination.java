package com.company;

public interface COVID19Vaccination {
	// Set the vaccination data.
	void setData(DataFrame df);
	
	// Return a two-column data frame containing the date and the number of people vaccinated by each date in the specified country.
	DataFrame getPeopleVaccinated(String countryName);
	
	// Return a two-column data frame containing the date and the number of people fully vaccinated by each date in the specified country.
	DataFrame getPeopleFullyVaccinated(String countryName);
	
	// Return a two-column data frame containing the date and the percentage of people vaccinated by each date in the specified country.
	DataFrame getPeopleVaccinatedPerHundred(String countryName);
	
	// Return a two-column data frame containing the date and the percentage of people fully vaccinated by each date in the specified country.
	DataFrame getPercentageOfPeopleFullyVaccinated(String countryName);
	
	// Return a set containing the names of the vaccines used in the specified country. Each vaccine name is stored separately.
	Set<String> getVaccines(String countryName);
	
	// Return the average number of people vaccinated per day for the specified country.
	double getAvgVaccinatedPerDay(String countryName);
	
	// Return the average number of people fully vaccinated per day for the specified country.
	double getAvgFullyVaccinatedPerDay(String countryName);
}
