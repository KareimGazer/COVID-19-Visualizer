package com.company;


public class Main {

    public static void main(String[] args) {
        // write your code here

        String fileName ="Vaccination.csv";
        DataFrameImp DF = new DataFrameImp();
        DF.loadCSV(fileName);
        COVID19Vaccination cov = new COVID19VaccinationImp();
        cov.setData(DF);

        //System.out.println(cov.getAvgVaccinatedPerDay("Albania"));

        Dashboard DB = new Dashboard(cov, "Albania");

    }

}
