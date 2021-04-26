package com.company;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.text.ParseException;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private COVID19Vaccination vac;
	private String countryName;

	private Day getDay(Date d) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return new Day(day, month, year);
	}

	public Dashboard(COVID19Vaccination vac, String countryName) {
		super("COVID-19 Vaccination Data for: " + countryName);

		this.vac = vac;
		this.countryName = countryName;

		// Set layout
		setLayout(new FlowLayout());
		plotPeopleVaccinated();
		plotPeopleFullyVaccinated();
		plotPeopleVaccinatedPerHundred();
		plotPeopleFullyVaccinatedPerHundred();
		addAverageVaccinationsPerDay();
		addVaccines();
		pack();
		setVisible(true);
	}

	private void plotPeopleVaccinated() {

		// Create the dataset
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries pv = new TimeSeries("People Vaccinated");
		DataFrame df = vac.getPeopleVaccinated(countryName);
		for (int i = 0; i < df.getNbRows(); i++) {
			Array<Object> row = df.getRow(i);
			//System.out.println(row.get(0).getClass());
			pv.add(getDay((Date) row.get(0)), (Integer) row.get(1));

		}
		dataset.addSeries(pv);

		// Plot the data
		JFreeChart timeSeiesChart = ChartFactory.createTimeSeriesChart("Number of People Vaccinated", "Date", "",
				(XYDataset) dataset);

		// Add it to the dashborad
		ChartPanel chartPanel = new ChartPanel(timeSeiesChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		getContentPane().add(chartPanel);
	}

	private void plotPeopleFullyVaccinated() {

		// Create the dataset
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries pv = new TimeSeries("People Fully Vaccinated");
		DataFrame df = vac.getPeopleFullyVaccinated(countryName);
		for (int i = 0; i < df.getNbRows(); i++) {
			Array<Object> row = df.getRow(i);
			//System.out.println(row.get(0));
			pv.add(getDay((Date) row.get(0)), (Integer) row.get(1));
		}
		dataset.addSeries(pv);

		// Plot the data
		JFreeChart timeSeiesChart = ChartFactory.createTimeSeriesChart("Number of People Fully Vaccinated", "Date", "",
				(XYDataset) dataset);

		// Add it to the dashborad
		ChartPanel chartPanel = new ChartPanel(timeSeiesChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		getContentPane().add(chartPanel);
	}

	private void plotPeopleVaccinatedPerHundred() {

		// Create the dataset
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries pv = new TimeSeries("Percentage of People Vaccinated");
		DataFrame df = vac.getPeopleVaccinatedPerHundred(countryName);
		for (int i = 0; i < df.getNbRows(); i++) {
			Array<Object> row = df.getRow(i);
			//System.out.println(row.get(0).getClass());
			pv.add(getDay((Date) row.get(0)), (Double) row.get(1));
		}
		dataset.addSeries(pv);

		// Plot the data
		JFreeChart timeSeiesChart = ChartFactory.createTimeSeriesChart("Percentage of People Vaccinated", "Date", "%",
				(XYDataset) dataset);

		// Add it to the dashborad
		ChartPanel chartPanel = new ChartPanel(timeSeiesChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		getContentPane().add(chartPanel);
	}

	private void plotPeopleFullyVaccinatedPerHundred() {

		// Create the dataset
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries pv = new TimeSeries("Percentage of People Fully Vaccinated");
		DataFrame df = vac.getPercentageOfPeopleFullyVaccinated(countryName);
		for (int i = 0; i < df.getNbRows(); i++) {
			Array<Object> row = df.getRow(i);
			pv.add(getDay((Date) row.get(0)), (Double) row.get(1));
		}
		dataset.addSeries(pv);

		// Plot the data
		JFreeChart timeSeiesChart = ChartFactory.createTimeSeriesChart("Percentage of People Fully Vaccinated", "Date",
				"%", (XYDataset) dataset);

		// Add it to the dashborad
		ChartPanel chartPanel = new ChartPanel(timeSeiesChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		getContentPane().add(chartPanel);
	}

	private void addVaccines() {

		DefaultPieDataset<String> vaccines = new DefaultPieDataset<String>();
		List<String> res = vac.getVaccines(countryName).getKeys();
		if (!res.empty()) {
			//compansate for this
			res.findFirst();
			while (!res.last()) {
				vaccines.setValue(res.retrieve(), 1.0 / res.size());
				res.findNext();
			}
			vaccines.setValue(res.retrieve(), 1.0 / res.size());
		}

		// Plot the data
		JFreeChart pieChart = ChartFactory.createPieChart("Vaccines", vaccines, true, true, false);

		// Add it to the dashborad
		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		getContentPane().add(chartPanel);
	}

	private void addAverageVaccinationsPerDay() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Population in 2005
		dataset.addValue(vac.getAvgVaccinatedPerDay(countryName), "Vaccinated People", "");
		dataset.addValue(vac.getAvgFullyVaccinatedPerDay(countryName), "Fully Vaccinated People", "");
		JFreeChart barChart = ChartFactory.createBarChart("Average Number of Vaccinations per Day", "", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		// Add it to the dashborad
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(700, 300));
		getContentPane().add(chartPanel);
	}

}
