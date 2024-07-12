/*
 * Sources:
 * https://forums.oracle.com/ords/apexds/post/multiple-selection-from-jlist-without-using-ctrl-ot-shift-k-3779
 * https://www.w3schools.com/
 * https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class TrafficOffenseFrame extends JFrame implements ActionListener {

	// Declaring labels
	JLabel titleLabel = new JLabel("Traffic Offenses");
	JLabel typeLabel = new JLabel("Province/Territory");
	JLabel crimeLabel = new JLabel("Offense");
	JLabel yearLabel = new JLabel("Change Years");

	// Declaring sliders
	JSlider minYearSlider = new JSlider(2008, 2012, 2010);
	JSlider maxYearSlider = new JSlider(2008, 2012, 2010);
	JSlider typeSlider = new JSlider(0, 1);

	// Declaring buttons
	JButton updateButton = new JButton("Update Settings");
	JButton helpButton = new JButton("How to use Graph");
	JButton backButton = new JButton("<");

	// Declaring ArrayLists for crime and provinces
	public static ArrayList<String> crime = new ArrayList<String>();
	public static ArrayList<String> provinces = new ArrayList<String>();
	public static ArrayList<String> cities = new ArrayList<String>();


	ChartPanel chartPanel;

	// Declaring an array for TrafficOffense objects
	public static TrafficOffense trafficOffence[] = new TrafficOffense[1281];

	// Declaring lists
	JList crimeList;
	JList areaList;

	// Declaring menu bar and menus
	
	/*
	JMenuBar menuBar = new JMenuBar();
	JMenu homeFrame = new JMenu("Home Page");
	JMenu violentCrimes = new JMenu("Violent Crime Severity");
	JMenu propertyCrimes = new JMenu("Property Crimes Rates");
	JMenu incidentCrimes = new JMenu("Incident-Based Crimes Rates");
	JMenu homicideCrimes = new JMenu("Homicide");
	JMenu trafficCrimes = new JMenu("Traffic Offenses");
	*/

	public TrafficOffenseFrame() {

		// Setting frame properties
		setTitle("Traffic Offenses 2008-2012");
		setLayout(null);
		setVisible(true);

		// Adding menus to menu bar
		/*
		setJMenuBar(menuBar);
		menuBar.add(homeFrame);
		menuBar.add(violentCrimes);
		menuBar.add(propertyCrimes);
		menuBar.add(incidentCrimes);
		menuBar.add(homicideCrimes);
		menuBar.add(trafficCrimes);
		homeFrame.addMenuListener(this);
		violentCrimes.addMenuListener(this);
		propertyCrimes.addMenuListener(this);
		incidentCrimes.addMenuListener(this);
		homicideCrimes.addMenuListener(this);
		trafficCrimes.addMenuListener(this);
		add(menuBar);
		*/

		// Setting frame size and colour
		setSize(1920, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);

		// Adding title label
		titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
		titleLabel.setBounds(500, 50, 700, 60);
		add(titleLabel);

		// Adding type label
		typeLabel.setFont(new Font("Arial", Font.BOLD, 15));
		typeLabel.setBounds(1225, 150, 200, 50);
		add(typeLabel);

		// Adding crime label
		crimeLabel.setFont(new Font("Arial", Font.BOLD, 15));
		crimeLabel.setBounds(1025, 150, 200, 50);
		add(crimeLabel);

		// Adding year label
		yearLabel.setFont(new Font("Arial", Font.BOLD, 25));
		yearLabel.setBounds(1060, 400, 200, 50);
		add(yearLabel);

		// Configuring the sliders(the temp min and max sliders)
		minYearSlider.setPaintLabels(true);
		minYearSlider.setMajorTickSpacing(1);
		minYearSlider.setValue(2008);
		minYearSlider.setBackground(Color.WHITE);
		minYearSlider.setBounds(1000, 450, 300, 75);
		add(minYearSlider);

		maxYearSlider.setPaintLabels(true);
		maxYearSlider.setMajorTickSpacing(1);
		maxYearSlider.setValue(2012);
		maxYearSlider.setBackground(Color.WHITE);
		maxYearSlider.setBounds(1000, 550, 300, 75);
		add(maxYearSlider);

		// Configuring the area type slider
		typeSlider.setMajorTickSpacing(1);
		typeSlider.setBackground(Color.WHITE);
		typeSlider.setBounds(1215, 135, 75, 25);
		add(typeSlider);

		// Configuring back button
		backButton.setFont(new Font("Arial", Font.BOLD, 20));
		backButton.setBounds(60, 40, 50, 30);
		add(backButton);
		backButton.addActionListener(this);

		// Configuring help button
		helpButton.setBounds(1000, 700, 300, 50);
		add(helpButton);
		helpButton.addActionListener(this);

		// Configuring update button
		updateButton.setBounds(1000, 650, 300, 50);
		add(updateButton);
		updateButton.addActionListener(this);

		// Reading data from file
		readFile();
		//readCityFile();

		// Configuring crime list
		crimeList = new JList<>(crime.toArray());
		crimeList.setSelectedIndex(0);
		crimeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane crimeScrollPane = new JScrollPane(crimeList);
		crimeScrollPane.setBounds(900, 200, 250, 200);
		add(crimeScrollPane);

		// Configuring area list
		areaList = new JList<>(provinces.toArray());
		areaList.setSelectionModel(new DefaultListSelectionModel() {
			// link for code to make the list have a max of 5 options chosen
			// https://forums.oracle.com/ords/apexds/post/multiple-selection-from-jlist-without-using-ctrl-ot-shift-k-3779

			public void setSelectionInterval(int index0, int index1) {
				int selectionLength = areaList.getSelectedIndices().length;

				if (areaList.isSelectedIndex(index0)) {
					areaList.removeSelectionInterval(index0, index1);
				} else {
					if (selectionLength < 5) {

						areaList.addSelectionInterval(index0, index1);
					}

					else
						JOptionPane.showMessageDialog(areaList, "Only a maximum of 5 areas can be chosen",
								"Selection Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		areaList.setSelectedIndex(0);
		JScrollPane areaScrollPane = new JScrollPane(areaList);
		areaScrollPane.setBounds(1200, 200, 250, 200);
		add(areaScrollPane);

		// Configuring the dataset for the chart and chart
		CategoryDataset dataset = defaultDataset();
		JFreeChart chart = ChartFactory.createLineChart(crimeList.getSelectedValue() + " by Year", "Year",
				"Number of Offenses", dataset, PlotOrientation.VERTICAL, true, true, true);
		chartPanel = new ChartPanel(chart);

		chartPanel.setBounds(50, 150, 800, 600);
		chartPanel.setBackground(Color.WHITE);
		add(chartPanel);

		// adding a change listener to the slider so it can change the area type
		typeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				if (typeSlider.getValue() == 1) {
					typeLabel.setText("City");

				}
				if (typeSlider.getValue() == 0)
					typeLabel.setText("Province/Territory");

			}
		});

	}

	

	// Method to read data from a file
	private void readFile() {
		try {

			// Read the input file
			Scanner input = new Scanner(new File("Files/trafficProvince.csv"));

			input.useDelimiter(",|\r\n");
			int i = 0;

			while (input.hasNext()) {

				String region = input.next();

				// For every 28th line,a new region is introduced so add the first instance of
				// this region to the corresponding area list
				if (i % 28 == 0)
					provinces.add(region);

				String offense = input.next();

				// Since each crime reapeats only add the first instance of the crime to the
				// crime list
				if (i <= 27) {
					crime.add(offense);
				}

				int y2008 = input.nextInt();
				int y2009 = input.nextInt();
				int y2010 = input.nextInt();
				int y2011 = input.nextInt();
				int y2012 = input.nextInt();

				trafficOffence[i] = new TrafficOffense(region, offense, y2008, y2009, y2010, y2011, y2012);

				i++;

			}

			// Close the file
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	private void readCityFile() {
		try {

			// Read the input file
			Scanner input = new Scanner(new File("Files/trafficCity.csv"));

			input.useDelimiter(",|\r\n");
			int i = 364;

			while (input.hasNext()) {

				String region = input.next();

				// For every 28th line,a new region is introduced so add the first instance of
				// this region to the corresponding area list
				if (i % 28 == 0)
					cities.add(region);

				String offense = input.next();
				int y2008 = input.nextInt();
				int y2009 = input.nextInt();
				int y2010 = input.nextInt();
				int y2011 = input.nextInt();
				int y2012 = input.nextInt();

				trafficOffence[i] = new TrafficOffense(region, offense, y2008, y2009, y2010, y2011, y2012);

				i++;

			}

			// Close the file
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
	}

	// Method to create a dataset for the chart
	private CategoryDataset defaultDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int minY;
		int maxY;

		// store indexes selected by the user
		int[] areas = areaList.getSelectedIndices();

		// figure out the min and max year values
		if (minYearSlider.getValue() > maxYearSlider.getValue()) {
			maxY = minYearSlider.getValue();
			minY = maxYearSlider.getValue();

		} else {
			maxY = maxYearSlider.getValue();
			minY = minYearSlider.getValue();
		}

		for (int i = 0; i < areas.length; i++) {
			ArrayList<Integer> years = new ArrayList<Integer>();

			years.add(trafficOffence[(areas[i] * 28) + crimeList.getSelectedIndex()].getY2008());
			years.add(trafficOffence[(areas[i] * 28) + crimeList.getSelectedIndex()].getY2009());
			years.add(trafficOffence[(areas[i] * 28) + crimeList.getSelectedIndex()].getY2010());
			years.add(trafficOffence[(areas[i] * 28) + crimeList.getSelectedIndex()].getY2011());
			years.add(trafficOffence[(areas[i] * 28) + crimeList.getSelectedIndex()].getY2012());
			for (int x = (minY - 2008); x <= (maxY - 2008); x++) {
				String name = trafficOffence[areas[i] * 28].getRegion();
				String year = String.valueOf(x + 2008);
				dataset.addValue(years.get(x), name, year);

			}

		}

		return dataset;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			setVisible(false);
			new HomeFrame();

		}
		if (e.getSource() == helpButton) {
			JOptionPane.showMessageDialog(maxYearSlider, "To zoom in to a specific spot on the map,"
					+ "hold the cursor and drag the cursor from the top left to the bottom right around the are "
					+ "you intend to zoom in on.If you want to reset the graph then hold the cursor and drag "
					+ "from the bottom right to the top left", "How to use Graph", JOptionPane.INFORMATION_MESSAGE);

		}

		if (e.getSource() == updateButton) {

			if (minYearSlider.getValue() == maxYearSlider.getValue()) {
				JOptionPane.showMessageDialog(maxYearSlider, "You cannot use the same year for the slider",
						"Selection Error", JOptionPane.ERROR_MESSAGE);

			}
			if (areaList.getSelectedIndices().length == 0)
				JOptionPane.showMessageDialog(areaList, "A minimum of 1 area must be chosen", "Selection Error",
						JOptionPane.ERROR_MESSAGE);

			else if (minYearSlider.getValue() != maxYearSlider.getValue()) {
				remove(chartPanel);

				JFreeChart chart = ChartFactory.createLineChart(crimeList.getSelectedValue() + " by Year", "Year",
						"Number of Offenses", defaultDataset(), PlotOrientation.VERTICAL, true, true, true);
				chartPanel = new ChartPanel(chart);
				chartPanel.setBounds(50, 150, 800, 600);
				chartPanel.setBackground(Color.WHITE);
				add(chartPanel);
				revalidate();
		        repaint();
			}
		}

	}

	
	/*
	public void menuSelected(MenuEvent e) {
		if (e.getSource().equals(homeFrame)) {
			setVisible(false);
			new HomeFrame();
		}
		if (e.getSource().equals(violentCrimes)) {
			setVisible(false);
			new ViolentCrimeFrame();
		}
		if (e.getSource().equals(propertyCrimes)) {
			setVisible(false);
			new PropertyFrame();
		}
		if (e.getSource().equals(incidentCrimes)) {
			setVisible(false);
			new IncidentBasedFrame();
		}
		if (e.getSource().equals(homicideCrimes)) {
			setVisible(false);
		}
		if (e.getSource().equals(trafficCrimes)) {
			setVisible(false);
			new TrafficOffenseFrame();
		}

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}
	*/

}
