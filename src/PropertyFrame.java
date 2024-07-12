import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.List;

public class PropertyFrame extends JFrame implements ActionListener {

	

	JComboBox<String> filter1ComboBox = new JComboBox<>();
	JComboBox<String> filter2ComboBox = new JComboBox<>();
	JComboBox<String> filter3ComboBox = new JComboBox<>();

	JButton backButton = new JButton("Back");
	JButton helpButton = new JButton("Help");

	JButton overviewButton = new JButton("Overview");
	JButton legendButton = new JButton("Legend");

	JTextArea textArea = new JTextArea();

	JLabel titleLabel = new JLabel("Property Crime Rate");
	JLabel filter1Label = new JLabel("Province:");
	JLabel filter2Label = new JLabel("City:");
	JLabel filter3Label = new JLabel("Crime Type:");

	ChartPanel chartPanel;

	boolean isOverviewDisplayed = true;

	public PropertyFrame() {
		setSize(1280, 720);
		setTitle("Property Details");
		setResizable(false);
		setLayout(null);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		titleLabel.setBounds(500, 0, 400, 100);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 40));
		add(titleLabel);

		

		textArea.setBounds(800, 150, 400, 350);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 16));
		textArea.setText(getOverviewText());
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		add(textArea);

		filter1Label.setBounds(150, 530, 100, 40);
		add(filter1Label);
		filter1ComboBox.setBounds(100, 575, 150, 40);
		filter1ComboBox.addItem("");
		filter1ComboBox.addItem("Ontario");
		filter1ComboBox.addItem("Quebec");
		filter1ComboBox.addItem("British Columbia");
		filter1ComboBox.addItem("Alberta");
		filter1ComboBox.addItem("Manitoba");
		filter1ComboBox.addItem("Saskatchewan");
		filter1ComboBox.addItem("Nova Scotia");
		filter1ComboBox.addItem("New Brunswick");
		filter1ComboBox.addItem("Newfoundland and Labrador");
		filter1ComboBox.addItem("Prince Edward Island");
		filter1ComboBox.addItem("Northwest Territories");
		filter1ComboBox.addItem("Yukon");
		filter1ComboBox.addItem("Nunavut");
		filter1ComboBox.addActionListener(this);
		add(filter1ComboBox);

		filter2Label.setBounds(390, 530, 100, 40);
		add(filter2Label);
		filter2ComboBox.setBounds(325, 575, 150, 40);
		filter2ComboBox.addItem("");
		filter2ComboBox.addItem("Kingston");
		filter2ComboBox.addItem("Peterborough");
		filter2ComboBox.addItem("Brantford");
		filter2ComboBox.addItem("Guelph");
		filter2ComboBox.addItem("London");
		filter2ComboBox.addItem("Windsor");
		filter2ComboBox.addItem("Barrie");
		filter2ComboBox.addItem("Sudbury");
		filter2ComboBox.addItem("Thunder Bay");
		filter2ComboBox.addItem("Hamilton");
		filter2ComboBox.addActionListener(this);
		add(filter2ComboBox);

		filter3Label.setBounds(590, 530, 100, 40);
		add(filter3Label);
		filter3ComboBox.setBounds(550, 575, 150, 40);
		filter3ComboBox.addItem("");
		filter3ComboBox.addItem("Shoplifting");
		filter3ComboBox.addItem("Fraud");
		filter3ComboBox.addItem("Identity theft");
		filter3ComboBox.addItem("Identity fraud");
		filter3ComboBox.addItem("Mischief");
		filter3ComboBox.addItem("Arson");
		filter3ComboBox.addItem("Theft");
		filter3ComboBox.addActionListener(this);
		add(filter3ComboBox);

		backButton.setBounds(1000, 575, 200, 50);
		backButton.addActionListener(this);
		backButton.setBackground(Color.WHITE);
		add(backButton);

		helpButton.setBounds(800, 575, 200, 50);
		helpButton.addActionListener(this);
		helpButton.setBackground(Color.WHITE);
		add(helpButton);

		overviewButton.setBounds(1000, 100, 200, 50);
		overviewButton.addActionListener(this);
		overviewButton.setBackground(Color.WHITE);
		add(overviewButton);

		legendButton.setBounds(800, 100, 200, 50);
		legendButton.addActionListener(this);
		legendButton.setBackground(Color.WHITE);
		add(legendButton);

		createChart(null, null, null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			new HomeFrame();
			this.setVisible(false);
		}

		if (event.getSource() == helpButton) {
			String helpMessage = "Welcome to the Property Details Program!\n\n"
					+ "This program allows you to view property crime rates across different regions and years.\n\n"
					+ "Features:\n" + "- Overview: Displays general information about the program.\n"
					+ "- Legend: Shows the meaning of different elements in the program.\n"
					+ "- Province/City Filter: Use the dropdown menus to filter the data by province or city.\n"
					+ "- Chart: Displays property crime rate data based on your filter selection.\n"
					+ "- Menu Bar: Navigate between different screens of the program.\n"
					+ "- Back Button: Return to the Title Screen.\n\n"
					+ "Feel free to explore and analyze the property crime rate data!";

			JOptionPane.showMessageDialog(this, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
		}

		if (event.getSource() == overviewButton && !isOverviewDisplayed) {
			textArea.setText(getOverviewText());
			isOverviewDisplayed = true;
		}

		if (event.getSource() == legendButton && isOverviewDisplayed) {
			textArea.setText(getLegendText());
			isOverviewDisplayed = false;
		}

		String selectedProvince = (String) filter1ComboBox.getSelectedItem();
		String selectedCity = (String) filter2ComboBox.getSelectedItem();
		String selectedCrime = (String) filter3ComboBox.getSelectedItem();
		createChart(selectedProvince, selectedCity, selectedCrime);
	}

	public String getOverviewText() {
		// Create and return the overview text
		return "This program allows you to view property crime rates\n" + "across different regions and years.\n\n"
				+ "Use the Overview button to view general information about\n"
				+ "the program and the Legend button to see the meaning of\n" + "different elements in the graph.";
	}

	public String getLegendText() {
		// Create and return the legend text
		return "Each point on the graph represents the number of property\n" + "crime occurrences in a specific year.\n"
				+ "The x-axis represents the year, and the y-axis represents\n"
				+ "the number of property crime occurrences.";
	}

	public void createChart(String provinceFilter, String cityFilter, String crimeFilter) {
		XYSeriesCollection dataset = new XYSeriesCollection();

		List<Property> properties = ChartData.getProperties();
		XYSeries series = new XYSeries("Number of Occurrences vs Year");

		boolean dataAdded = false;
		for (Property property : properties) {
			if ((provinceFilter == null || provinceFilter.isEmpty() || property.getGeography().contains(provinceFilter))
					&& (cityFilter == null || cityFilter.isEmpty() || property.getGeography().contains(cityFilter))
					&& (crimeFilter == null || crimeFilter.isEmpty() || property.getCrime().contains(crimeFilter))) {
				series.add(2008, property.getYear2008());
				series.add(2009, property.getYear2009());
				series.add(2010, property.getYear2010());
				series.add(2011, property.getYear2011());
				series.add(2012, property.getYear2012());
				dataAdded = true;
			}
		}

		if (!dataAdded) {
			JOptionPane.showMessageDialog(this, "No data available for the selected filters.");
			return;
		}

		dataset.addSeries(series);

		JFreeChart chart = ChartFactory.createScatterPlot("Number of Occurrences vs Year", "Year",
				"Number of Occurrences", dataset, PlotOrientation.VERTICAL, true, true, false);

		chart.setBackgroundPaint(Color.white);

		if (chartPanel != null) {
			remove(chartPanel);
		}

		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(50, 100, 700, 400);
		add(chartPanel);
		revalidate();
		repaint();
	}

	
}