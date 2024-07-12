//Imports
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Year;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import objects.City;
import objects.Province;

/*
 * Emma Shi 
 * Group #3 - Justice and Public Safety
 * Course Code: ICS4U1
 * Date: May 26th, 2024
 * Teacher: Mr. Fernandes
 * Major Skills: HashMaps (NEW), Control Statements, JFreeChart (NEW), Enhanced For Loops, Array Lists
 * Time Series Chart
 */

// This class creates the TimeSeriesChart
public class TimeSeriesChart extends JPanel {

	// Constructor method
	public TimeSeriesChart() {
		// Create dataset
		XYDataset dataset = createDataset();

		// Create chart
		JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart(HomicideFrame.selectedCrimeType + " Statistics", // Chart
																															// title
				"Year", // X-Axis Label
				"Number of deaths/year", // Y-Axis Label
				dataset, true, true, false);

		// Customize the plot background color
		XYPlot plot = (XYPlot) timeSeriesChart.getPlot();
		plot.setBackgroundPaint(new Color(255, 228, 196));

		// Customize the x-axis to show years only
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("yyyy"));
		axis.setTickUnit(new DateTickUnit(DateTickUnit.YEAR, 1));

		// Add chart to a panel
		ChartPanel chartPanel = new ChartPanel(timeSeriesChart);
		add(chartPanel); // Add the chart panel to this JPanel
	}

	// This method creates the dataset that will be displayed
	private XYDataset createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		// If the data is about provinces
		if (HomicideFrame.isProvince) {
			// Iterate over matched provinces
			for (Province province : HomicideFileInput.matchedProvinces) {
				// Check if the province is selected
				if (HomicideFrame.provinceSelectionList.contains(province.getProvince())) {
					TimeSeries series = new TimeSeries(province.getProvince(), Year.class);
					// Get the data for each year
					Map<Integer, Double> yearData = getProvinceYearData(province);
					// Add data points to the series
					addDataPoints(series, yearData);
					// Add series to the dataset
					dataset.addSeries(series);
				}
			}
		} // If the data is about cities

		else {
			// Iterate over matched cities
			for (City city : HomicideFileInput.matchedCities) {
				// Check if the city is selected
				if (HomicideFrame.citySelectionList.contains(city.getCity())) {
					TimeSeries series = new TimeSeries(city.getCity(), Year.class);
					// Get the data for each year
					Map<Integer, Double> yearData = getCityYearData(city);
					// Add data points to the series
					addDataPoints(series, yearData);
					// Add series to the dataset
					dataset.addSeries(series);
				}
			}
		}

		return dataset;
	}

	// Get province data for each year
	private Map<Integer, Double> getProvinceYearData(Province province) {
		Map<Integer, Double> yearData = new HashMap<>();
		yearData.put(2008, province.getNumCrimes2008());
		yearData.put(2009, province.getNumCrimes2009());
		yearData.put(2010, province.getNumCrimes2010());
		yearData.put(2011, province.getNumCrimes2011());
		yearData.put(2012, province.getNumCrimes2012());
		return yearData;
	}

	// Get city data for each year
	private Map<Integer, Double> getCityYearData(City city) {
		Map<Integer, Double> yearData = new HashMap<>();
		yearData.put(2008, city.getNumCrimes2008());
		yearData.put(2009, city.getNumCrimes2009());
		yearData.put(2010, city.getNumCrimes2010());
		yearData.put(2011, city.getNumCrimes2011());
		yearData.put(2012, city.getNumCrimes2012());
		return yearData;
	}

	// Add data points to the
	private void addDataPoints(TimeSeries series, Map<Integer, Double> yearData) {
		// Iterate through the years that match the range selected
		for (Integer year : HomicideFrame.yearList) {
			Double value = yearData.get(year);
			if (value != null) {
				series.add(new Year(year), value);
			}
		}
	}
}