import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import objects.Place;

@SuppressWarnings("serial")
public class ViolentCrimeFrame extends JFrame implements ActionListener, ItemListener{

	// Fields
	// Application logo image
	ImageIcon logo = new ImageIcon("logo.png");
	// Background panel
	JPanel background = new JPanel();

	// JScrollPane scrollPane = new JScrollPane(background);

	// Back button
	JButton back = new JButton("<");
	// Page title
	JLabel title = new JLabel("Violent Crime Severity");
	// Page subtitle
	JLabel subtitle = new JLabel("Ontario | Calculated Severity Index 2008-2012");
	// Search bar button
	JButton search = new JButton("🔎");
	// Search bar
	JTextArea textSearch = new JTextArea();
	// Options heading
	JLabel heading = new JLabel("View Data");

	// Subheadings of options
	JLabel[] subheadings = new JLabel[2];

	// Year checkboxes
	JCheckBox[] year = { new JCheckBox("2008"), new JCheckBox("2009"), new JCheckBox("2010"), new JCheckBox("2011"),
			new JCheckBox("2012") };

	// Place checkboxes
	JCheckBox[] geography = new JCheckBox[29];

	// Place object array
	Place[] places = new Place[29];

	// String array to hold combobox option names
	String[] scope = new String[] { "Choose scope", "Country", "Provinces/Territories", "Cities" };
	// Create combobox
	JComboBox<String> comboScope = new JComboBox<String>(scope);

	// Button to switch between page setups
	JButton transition = new JButton();

	// Heading for risk section
	JLabel riskHeading = new JLabel("Areas at Risk");
	// Subtitle for risk section
	JLabel riskSub = new JLabel();
	// JLabels for ordering risk ranking for provinces and territories
	JTextArea ptRiskResults = new JTextArea();
	// JLabels for ordering risk ranking for cities
	JTextArea cRiskResults = new JTextArea();

	

	ChartPanel chartPanel;

	// Constructor method
	public ViolentCrimeFrame() {

		// Set up application
		setTitle("CrimeMap Canada");
		setIconImage(logo.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		// Set up GUI elements
		// Set up background panel
		background.setBackground(Color.WHITE);
		background.setLayout(null);

		// Set up back button
		back.setFont(new Font("Arial", Font.BOLD, 20));
		back.setBounds(60, 40, 50, 30);

		// Set up page title
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setBounds(450, 30, 500, 45);

		// Set up page subtitle
		subtitle.setBounds(530, 70, 800, 30);

		// Set up options heading
		heading.setFont(new Font("Arial", Font.BOLD, 15));
		heading.setBounds(100, 220, 100, 30);

		transition.setBackground(Color.WHITE);
		transition.setForeground(Color.BLUE);
		transition.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		// Run readFile method
		readFile();

		// Run setupFilterPage method
		setupFilterPage();

	
		
		// Add actionListeners
		transition.addActionListener(this);
		back.addActionListener(this);
		search.addActionListener(this);

		comboScope.addItemListener(this);

		// Set size of application to full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Set background to the contentPane
		setContentPane(background);
		// scrollPane.setBounds(0,0,1000,500);
		// scrollPane.setSize(JFrame.MAXIMIZED_VERT,JFrame.MAXIMIZED_HORIZ);
		// add(background);
	}

	// Method to read the file and create Place objects
	private void readFile() {

		try {

			// Read the input file
			Scanner input = new Scanner(new File("Files/violent_crime_severity_index(in).csv"));

			int i = 0;

			input.useDelimiter(",|\r\n");

			while (input.hasNext()) {

				// Get the name of the area and set the current index of
				// geography and places arrays with the name
				String area = input.next();
				geography[i] = new JCheckBox(area);
				places[i] = new Place(area);
				// System.out.println(area + " " + i);

				// Get the severity index of each year and add the value to the
				// places array's Place object
				double severity = input.nextDouble();
				places[i].setYear8(severity);

				severity = input.nextDouble();
				places[i].setYear9(severity);

				severity = input.nextDouble();
				places[i].setYear10(severity);

				severity = input.nextDouble();
				places[i].setYear11(severity);

				severity = input.nextDouble();
				places[i].setYear12(severity);

				// System.out.println(severity);

				i++;
			}

			// Close the file
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	// Method for options page setup
	public void setupFilterPage() {

		// Add the default elements onto the background
		background.add(back);
		background.add(title);
		background.add(subtitle);
		background.add(heading);

		// Set up transition button
		transition.setText("See Results");
		transition.setBounds(600, 600, 130, 40);
		background.add(transition);

		// Set up search bar
		textSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		textSearch.setFont(new Font("Arial", Font.ITALIC, 20));
		textSearch.setBounds(300, 150, 700, 30);
		background.add(textSearch);

		// Set up search button
		search.setBounds(1000, 150, 50, 30);
		background.add(search);

		// Set up filter subheadings
		subheadings[0] = new JLabel("Year");
		subheadings[0].setBounds(100, 260, 70, 30);
		background.add(subheadings[0]);

		subheadings[1] = new JLabel("Geography");
		subheadings[1].setBounds(300, 260, 70, 30);
		background.add(subheadings[1]);

		// Set up year checkboxes
		for (int box = 0; box < year.length; box++) {

			year[box].setBounds(100, 300 + (20 * box), 100, 20);
			year[box].setBackground(Color.WHITE);
			background.add(year[box]);
		}

		// Set up column 1 of geography checkboxes
		for (int i = 0; i < 10; i++) {

			geography[i].setBounds(300, 300 + (20 * i), 200, 20);
			geography[i].setBackground(Color.WHITE);
			background.add(geography[i]);
		}

		// Set up column 2 of geography checkboxes
		for (int i = 10; i < 20; i++) {

			geography[i].setBounds(500, 300 + (20 * i) - 200, 230, 20);
			geography[i].setBackground(Color.WHITE);
			background.add(geography[i]);
		}

		// Set up column 3 of geography checkboxes
		for (int i = 20; i < 29; i++) {

			geography[i].setBounds(730, 300 + (20 * i) - 400, 210, 20);
			geography[i].setBackground(Color.WHITE);
			background.add(geography[i]);
		}

		// Set up comboScope
		comboScope.setBounds(1000, 230, 130, 20);
		background.add(comboScope);
	}

	// Method for chart page setup
	public void setupChartPage() {

		// Add the default elements onto the background
		background.add(back);
		background.add(title);
		background.add(subtitle);
		background.add(heading);

		// Reset and add background
		background.setSize(JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		heading.setBounds(100, 150, 500, 80);
		background.add(heading);

		// Set up transition button
		transition.setText("Modify Sort");
		transition.setBounds(900, 150, 130, 40);
		background.add(transition);

		// Set up heading for risk section
		riskHeading.setFont(new Font("Arial", Font.BOLD, 15));
		riskHeading.setBounds(910, 190, 100, 100);
		background.add(riskHeading);

		// Create new dataset
		CategoryDataset newDataset;

		// If any filter was selected, use method that creates a dataset using
		// the selected filters
		if (checkSelectionG() == true || checkSelectionY() == true || comboScope.getSelectedIndex() != 0) {

			// System.out.println(checkSelectionG());
			// System.out.println(checkSelectionY());
			newDataset = createDataset();
		}

		// If no filters were selected, use the method that creates a dataset
		// with all data from the file
		else {

			newDataset = createDefaultDataset();
		}

		// Create the chart
		createChart(newDataset);

		// Set up risk sections subheading
		riskSub.setBounds(910, 230, 300, 100);
		background.add(riskSub);
	}

	// Method to create a bar chart using the created dataset
	private void createChart(CategoryDataset dataset) {

		// Set up bar chart
		JFreeChart chart = ChartFactory.createBarChart("Violent Crime Severity Index", "Area", "Severity Index Number",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		chartPanel = new ChartPanel(chart, false);
		chartPanel.setBounds(30, 210, 800, 400);
		background.add(chartPanel);

		// Run method to set up the risk ranking section text
		setRiskResults();

		// Set up the risk rankings of the provinces and territories
		ptRiskResults.setEditable(false);
		ptRiskResults.setBounds(920, 320, 150, 100);
		ptRiskResults.setText(
				"Provinces/Territories:\n1.Nunavut\n" + "2.Northwest Territories\n3.Yukon\n4.Manitoba\n5.Saskachewan");
		background.add(ptRiskResults);

		// Set up city risk rankings
		cRiskResults.setEditable(false);
		cRiskResults.setBounds(920, 450, 150, 100);
		background.add(cRiskResults);
	}

	// Method to set the city risk ranking results depending on cities selected
	private void setRiskResults() {

		// If no years were selected, set text to default rankings
		if (checkSelectionY() == false) {

			riskSub.setText("The areas of the highest risk during all years are:");

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Brantford" + "\n4.Sudbury\n5.Hamilton");
		}

		// If any years were selected, find which ones were selected and set ranking
		// text depending on them
		else {

			int timeframe = 0;

			// Check if more than one year was selected
			for (JCheckBox box : year) {
				if (box.isSelected() == true) {

					timeframe++;
				}
			}

			// If multiple years were selected, find the range and set subheading
			// text accordingly
			if (timeframe > 1) {

				if (year[0].isSelected() && year[1].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[0].getText() + "-"
							+ year[1].getText() + " are:");
				}

				if (year[0].isSelected() && year[2].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[0].getText() + "-"
							+ year[2].getText() + " are:");
				}

				if (year[0].isSelected() && year[3].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[0].getText() + "-"
							+ year[3].getText() + " are:");
				}

				if (year[0].isSelected() && year[4].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[0].getText() + "-"
							+ year[4].getText() + " are:");
				}

				if (year[1].isSelected() && year[2].isSelected() && !year[0].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[1].getText() + "-"
							+ year[2].getText() + " are:");
				}

				if (year[1].isSelected() && year[3].isSelected() && !year[0].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[1].getText() + "-"
							+ year[3].getText() + " are:");
				}

				if (year[1].isSelected() && year[4].isSelected() && !year[0].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[1].getText() + "-"
							+ year[4].getText() + " are:");
				}

				if (year[2].isSelected() && year[3].isSelected() && !year[0].isSelected() && !year[1].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[2].getText() + "-"
							+ year[3].getText() + " are:");
				}

				if (year[2].isSelected() && year[4].isSelected() && !year[0].isSelected() && !year[1].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[2].getText() + "-"
							+ year[4].getText() + " are:");
				}

				if (year[3].isSelected() && year[4].isSelected() && !year[0].isSelected() && !year[1].isSelected()
						&& !year[2].isSelected()) {

					riskSub.setText("The areas of the highest risk during " + year[3].getText() + "-"
							+ year[4].getText() + " are:");
				}
			}

			// If only one year was selected, set subheading text accordingly
			else {

				for (JCheckBox box : year) {

					if (box.isSelected() == true) {

						riskSub.setText("The areas of the highest risk during " + box.getText() + " are:");
					}
				}
			}
		}

		// Set the city risk rankings
		setCRiskResults();
	}

	// Method to check what year/year range was selected and set city risk ranking
	// according to selection
	private void setCRiskResults() {

		// If one year was chosen
		if (riskSub.getText().equals("The areas of the highest risk during 2008 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Hamilton" + "\n4.Brantford\n5.Windsor");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2009 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Sudbury\n3.Toronto" + "\n4.Brantford\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2010 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Brantford\n3.Toronto" + "\n4.Sudbury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2011 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Brantford\n3.Toronto" + "\n4.Sudbury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2012 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Sudbury" + "\n4.Brantford\n5.Winsor");
		}

		// If an year range was chosen
		if (riskSub.getText().equals("The areas of the highest risk during 2008-2009 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Subury" + "\n4.Hamilton\n5.Brantford");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2008-2010 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Brantford" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2008-2011 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Brantford\n3.Toronto" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2008-2012 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Brantford" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2009-2010 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Brantford" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2009-2011 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Brantford\n3.Toronto" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2009-2012 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Brantford" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2010-2011 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Brantford\n3.Toronto" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2010-2012 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Brantford\n3.Toronto" + "\n4.Subury\n5.Hamilton");
		}

		if (riskSub.getText().equals("The areas of the highest risk during 2011-2012 are:")) {

			cRiskResults.setText("Cities:\n1.Thunder Bay\n2.Toronto\n3.Brantford" + "\n4.Subury\n5.Hamilton");
		}
	}

	// Method to create dataset for when no filters are selected
	private CategoryDataset createDefaultDataset() {

		DefaultCategoryDataset defaultDataset = new DefaultCategoryDataset();

		// Add values for each year and every area
		for (int i = 0; i < 29; i++) {
			defaultDataset.addValue(places[i].getYear8(), places[i].getName(), "2008");
		}

		for (int i = 0; i < 29; i++) {
			defaultDataset.addValue(places[i].getYear9(), places[i].getName(), "2009");
		}

		for (int i = 0; i < 29; i++) {
			defaultDataset.addValue(places[i].getYear10(), places[i].getName(), "2010");
		}

		for (int i = 0; i < 29; i++) {
			defaultDataset.addValue(places[i].getYear11(), places[i].getName(), "2011");
		}

		for (int i = 0; i < 29; i++) {
			defaultDataset.addValue(places[i].getYear12(), places[i].getName(), "2012");
		}

		return defaultDataset;
	}

	// Method to create dataset using filter requirements
	private CategoryDataset createDataset() {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// If a year was selected, add the values of selected areas from that year
		if (checkSelectionY() == true) {

			for (int n = 0; n < 5; n++) {

				if (year[n].isSelected()) {

					addDatasetValue((n + 8) + 2000, dataset);
				}
			}
		}

		// If no year selected, add the values of selected areas from all years
		else {

			addDatasetValue(2008, dataset);
			addDatasetValue(2009, dataset);
			addDatasetValue(2010, dataset);
			addDatasetValue(2011, dataset);
			addDatasetValue(2012, dataset);
		}

		return dataset;
	}

	// Method to add a value to the dataset
	private void addDatasetValue(int year, DefaultCategoryDataset dataset) {

		// If a geography checkbox was selected, add that place's data from
		// the year in the parameter
		if (checkSelectionG() == true) {

			for (int box = 0; box < 29; box++) {

				if (geography[box].isSelected()) {

					String name = geography[box].getText();

					for (Place area : places) {

						if (name.equals(area.getName())) {

							dataset.addValue(area.getYear(year), area.getName(), String.valueOf(year));
						}
					}
				}
			}
		}

		// If no geography checkboxes were checked but a scope filter was selected,
		// find the chosen scope and add those areas' values from the year in
		// the parameters
		else if (checkSelectionG() == false && comboScope.getSelectedIndex() != 0) {

			int i;
			int length;

			if (comboScope.getSelectedIndex() == 1) {

				i = 0;
				length = 1;
			}

			else if (comboScope.getSelectedIndex() == 2) {

				i = 1;
				length = 14;
			}

			else {

				i = 14;
				length = 29;
			}

			for (int j = i; j < length; j++) {

				dataset.addValue(places[j].getYear(year), places[j].getName(), String.valueOf(year));

			}
		}

		// If no geography checkboxes or scope filter were chosen, add the values
		// from all areas from the year in the parameters
		else if (checkSelectionG() == false && comboScope.getSelectedIndex() == 0) {

			for (int i = 0; i < 29; i++) {
				dataset.addValue(places[i].getYear(year), places[i].getName(), String.valueOf(year));
			}
		}
	}

	// Action performed event method
	public void actionPerformed(ActionEvent e) {

		// If the back button was pressed, open HomeFrame
		if (e.getSource() == back) {

			setVisible(false);
			new HomeFrame();
		}

		// if the transition button was pressed
		else if (e.getSource() == transition) {

			// If the current page setup is the filter, switch to chart setup
			if (transition.getText().equals("See Results")) {

				background.removeAll();
				background.repaint();
				background.revalidate();
				setupChartPage();
			}

			// If the current page setup is the chart, switch to filter setup
			else if (transition.getText().equals("Modify Sort")) {

				background.removeAll();
				background.repaint();
				background.revalidate();
				setupFilterPage();
			}
		}

		// If the search button was pressed, set the checkbox with the same text
		// as the text in the search bar to selected
		else if (e.getSource() == search) {

			String text = textSearch.getText();

			for (JCheckBox years : year) {

				if (years.getText().equalsIgnoreCase(text)) {

					years.setSelected(true);
				}
			}

			for (JCheckBox area : geography) {

				if (area.getText().equalsIgnoreCase(text)) {

					area.setSelected(true);
				}
			}
		}
	}

	// Item changed event method
	public void itemStateChanged(ItemEvent e) {

		// If comboScope was changed: highlighting checkboxes depending on
		// which option was selected
		if (e.getSource() == comboScope) {

			if (comboScope.getSelectedIndex() == 0) {

				for (int i = 0; i < 29; i++) {

					geography[i].setBackground(Color.WHITE);
				}
			}

			if (comboScope.getSelectedIndex() == 1) {

				geography[0].setBackground(Color.GREEN);

				for (int i = 1; i < 29; i++) {

					geography[i].setBackground(Color.WHITE);
				}
			}

			if (comboScope.getSelectedIndex() == 2) {

				geography[0].setBackground(Color.WHITE);

				for (int i = 1; i < 14; i++) {

					geography[i].setBackground(Color.GREEN);
				}

				for (int i = 14; i < 29; i++) {

					geography[i].setBackground(Color.WHITE);
				}
			}

			if (comboScope.getSelectedIndex() == 3) {

				for (int i = 14; i < 29; i++) {

					geography[i].setBackground(Color.GREEN);
				}

				for (int i = 0; i < 14; i++) {

					geography[i].setBackground(Color.WHITE);
				}
			}
		}
	}

	// Method to check if any of the geography checkboxes were checked
	private boolean checkSelectionG() {

		for (int i = 0; i < 29; i++) {

			if (geography[i].isSelected()) {

				return true;
			}
		}

		return false;
	}

	// Method to check if any of the year checkboxes were checked
	private boolean checkSelectionY() {

		for (int i = 0; i < 5; i++) {

			if (year[i].isSelected()) {

				return true;
			}
		}

		return false;
	}

	
}