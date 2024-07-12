/*
 * Sources:
 * https://www.advsofteng.com/doc/cdjavadoc/candlestick.htm
 * https://www.javatpoint.com/java-jcheckbox
 * https://www.jfree.org/jfreechart/api/javadoc/org/jfree/data/xy/OHLCDataset.html
 */

// importing external classes
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

// importing JFreeChart classes; used to create the candlestick plot graph
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;

@SuppressWarnings("serial")
public class IncidentBasedFrame extends JFrame {

	// lists to create the checkboxes for the filter
	private List<JCheckBox> provinceCheckBoxes;
	private List<JCheckBox> crimeCheckBoxes;
	private List<JCheckBox> yearCheckBoxes;

	

	// main frame
	public IncidentBasedFrame() {
		setTitle("Incident-Based Crime Rates in Canada");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);

		setLayout(new BorderLayout());

		// set up the main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel, BorderLayout.CENTER);

		// add components to the main panel
		addBackButton(mainPanel);
		addTitleLabel(mainPanel);
		addSubtitleLabel(mainPanel);
		addSearchComponents(mainPanel);
		addYearPanel(mainPanel);
		addProvincePanel(mainPanel);
		addCrimePanel(mainPanel);
		addSeeResultsButton(mainPanel);

		
		

		// set the frame to be visible
		setVisible(true);
	}

	// create a back button that takes the user back to the title screen
	private void addBackButton(JPanel mainPanel) {
		JButton backButton = new JButton("< Back");
		backButton.setBounds(10, 10, 100, 30);

		// add action listener to the back button
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFrame homeFrame = new HomeFrame(); // create an instance of the HomeFrame
				homeFrame.setVisible(true);
				dispose(); // close the current frame
			}
		});

		mainPanel.add(backButton); // add the back button to the main panel
	}

	private void addTitleLabel(JPanel mainPanel) {
		JLabel titleLabel = new JLabel("Incident-Based Crime Rates");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setBounds(120, 10, 500, 30);
		mainPanel.add(titleLabel);
	}

	// method to add a subtitle label to the main panel
	private void addSubtitleLabel(JPanel mainPanel) {
		JLabel subtitleLabel = new JLabel("Incident Reports 2008 - 2012");
		subtitleLabel.setBounds(120, 50, 300, 20);
		mainPanel.add(subtitleLabel);
	}

	private void addSearchComponents(JPanel mainPanel) {
		JTextField searchField = new JTextField();
		searchField.setBounds(10, 90, 400, 30);
		mainPanel.add(searchField);

		JButton searchButton = new JButton(new ImageIcon("images/search.jpeg"));
		searchButton.setBounds(420, 90, 50, 30);
		mainPanel.add(searchButton);

		JToggleButton rateToggle = new JToggleButton("Rate per 100,000 (population)");
		rateToggle.setBounds(475, 90, 250, 30);
		mainPanel.add(rateToggle);
	}

	// method to add the year panel to the main panel
	private void addYearPanel(JPanel mainPanel) {
		yearCheckBoxes = new ArrayList<>();
		JPanel yearPanel = new JPanel();
		yearPanel.setBounds(10, 130, 100, 150);
		yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
		yearPanel.setBorder(BorderFactory.createTitledBorder("Year"));

		JCheckBox year2008 = new JCheckBox("2008");
		JCheckBox year2009 = new JCheckBox("2009");
		JCheckBox year2010 = new JCheckBox("2010");
		JCheckBox year2011 = new JCheckBox("2011");
		JCheckBox year2012 = new JCheckBox("2012");

		yearCheckBoxes.add(year2008);
		yearCheckBoxes.add(year2009);
		yearCheckBoxes.add(year2010);
		yearCheckBoxes.add(year2011);
		yearCheckBoxes.add(year2012);

		yearPanel.add(year2008);
		yearPanel.add(year2009);
		yearPanel.add(year2010);
		yearPanel.add(year2011);
		yearPanel.add(year2012);

		mainPanel.add(yearPanel);
	}

	// method to add the province panel to the main panel
	private void addProvincePanel(JPanel mainPanel) {
		provinceCheckBoxes = new ArrayList<>();
		JPanel provincePanel = new JPanel();
		provincePanel.setBounds(120, 130, 500, 300);
		provincePanel.setLayout(new GridLayout(0, 2));
		provincePanel.setBorder(BorderFactory.createTitledBorder("Provinces"));

		// string array to store all 10 provinces and 3 territories in Canada
		String[] provinces = { "Ontario", "Newfoundland", "Prince Edward Island", "Nova Scotia", "New Brunswick",
				"Quebec", "Manitoba", "Saskatchewan", "Alberta", "British Columbia", "Yukon", "Northwest Territories",
				"Nunavut" };

		// add the province/territories checkboxes to the province panel
		for (String province : provinces) {
			JCheckBox provinceCheckBox = new JCheckBox(province);
			provinceCheckBoxes.add(provinceCheckBox);
			provincePanel.add(provinceCheckBox);
		}

		mainPanel.add(provincePanel);
	}

	// method to add the crime panel to the main panel
	private void addCrimePanel(JPanel mainPanel) {
		crimeCheckBoxes = new ArrayList<>();
		JPanel crimePanel = new JPanel();
		crimePanel.setBounds(630, 130, 250, 200);
		crimePanel.setLayout(new BoxLayout(crimePanel, BoxLayout.Y_AXIS));
		crimePanel.setBorder(BorderFactory.createTitledBorder("Type of Crime"));

		// string array to store all the different type of crimes
		String[] crimes = { "Homicide", "Assault", "Theft", "Abduction", "Arson", "Weapons Violation", "Counterfeit" };

		// add the crime checkboxes to the crime panel
		for (String crime : crimes) {
			JCheckBox crimeCheckBox = new JCheckBox(crime);
			crimeCheckBoxes.add(crimeCheckBox);
			crimePanel.add(crimeCheckBox);
		}

		mainPanel.add(crimePanel);
	}

	// method to add the see results button to the main panel
	private void addSeeResultsButton(JPanel mainPanel) {
		JButton seeResultsButton = new JButton("See Results");
		seeResultsButton.setBounds(525, 50, 120, 30);
		seeResultsButton.addActionListener(new ActionListener() { // add action listener to the seeResults button so
																	// that when it is clicked, it displays the graph

			@Override
			public void actionPerformed(ActionEvent e) {
				showCandlestickPlot();
			}
		});

		mainPanel.add(seeResultsButton);
	}

	// method to display the candlestick plot graph
	private void showCandlestickPlot() {

		// collect the selected provinces/territories
		List<String> selectedProvinces = new ArrayList<>();
		for (JCheckBox checkBox : provinceCheckBoxes) {
			if (checkBox.isSelected()) {
				selectedProvinces.add(checkBox.getText());
			}
		}

		// collect the selected crimes
		List<String> selectedCrimes = new ArrayList<>();
		for (JCheckBox checkBox : crimeCheckBoxes) {
			if (checkBox.isSelected()) {
				selectedCrimes.add(checkBox.getText());
			}
		}

		// collect the selected years
		List<String> selectedYears = new ArrayList<>();
		for (JCheckBox checkBox : yearCheckBoxes) {
			if (checkBox.isSelected()) {
				selectedYears.add(checkBox.getText());
			}
		}

		// Check if any filters are selected, show error message if none are selected
		if (selectedProvinces.isEmpty() || selectedCrimes.isEmpty() || selectedYears.isEmpty()) {
			JOptionPane.showMessageDialog(this, "You must select at least ONE filter from each category!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		OHLCDataset dataset = createRandomDataset(selectedProvinces, selectedCrimes);

		// create the dynamic title for the chart
		String title = "Crime Data";
		if (!selectedProvinces.isEmpty() && !selectedYears.isEmpty()) {
			title += " in " + String.join(", ", selectedProvinces);
			title += " from " + selectedYears.get(0) + " to " + selectedYears.get(selectedYears.size() - 1);
		}

		// create the candlestick plot graph
		JFreeChart chart = ChartFactory.createCandlestickChart(title, "Date", "Rating", dataset, false);

		// create a new frame for the chart
		JFrame chartFrame = new JFrame("Candlestick Chart");
		chartFrame.setSize(800, 600);
		chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chartFrame.add(new ChartPanel(chart));
		chartFrame.setVisible(true);
	}

	private OHLCDataset createRandomDataset(List<String> selectedProvinces, List<String> selectedCrimes) {
		Random random = new Random();
		int itemCount = 30;

		Date[] date = new Date[itemCount];
		double[] high = new double[itemCount];
		double[] low = new double[itemCount];
		double[] open = new double[itemCount];
		double[] close = new double[itemCount];
		double[] volume = new double[itemCount];

		for (int i = 0; i < itemCount; i++) {
			date[i] = new Date(System.currentTimeMillis() - (itemCount - i) * 86400000L);
			high[i] = random.nextDouble() * 100;
			low[i] = high[i] - random.nextDouble() * 10;
			open[i] = low[i] + random.nextDouble() * (high[i] - low[i]);
			close[i] = low[i] + random.nextDouble() * (high[i] - low[i]);
			volume[i] = random.nextDouble() * 1000;
		}

		// return the data set
		return new DefaultHighLowDataset("Series 1", date, high, low, open, close, volume);
	}

	// create and display the frame on the event dispatch thread
	public static void main(String[] args) {
		SwingUtilities.invokeLater(IncidentBasedFrame::new);
	}

	
	
}
