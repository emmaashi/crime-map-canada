//Imports
import rangeSlider.RangeSlider;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import objects.City;
import objects.Province;

/*
 * Emma Shi 
 * Group #3 - Justice and Public Safety
 * Homicide Main Frame 
 * Course Code: ICS4U1
 * Date: May 26th, 2024
 * Teacher: Mr. Fernandes
 * Major Skills: Array Lists, Arrays, Enhanced For Loops, Loops, JPanels, JLabels, JButtons, 
 * OOP Concepts, Methods, Lambda Expressions (NEW)
 */

// This class is the main frame to display the data of homicides in Canadian provinces and cities 
public class HomicideFrame extends JFrame implements ActionListener {

	// GUI Fields
	// Panels
	private JPanel selectionPanel = new JPanel();
	private JPanel onboardPanel = new JPanel();
	private JPanel onboardCityPanel = new JPanel();
	private JPanel resultsPanel = new JPanel();
	// Background screens/images
	ImageIcon selectionScreen = new ImageIcon("images/Homicide.png");
	ImageIcon onboardScreen = new ImageIcon("images/HomicideOnboard.png");
	ImageIcon onboardCityScreen = new ImageIcon("images/HomicideCityOnboard.png");
	ImageIcon resultsScreen = new ImageIcon("images/HomicideResults.png");
	// Labels
	JLabel selectionScreenLabel = new JLabel(selectionScreen);
	JLabel onboardScreenLabel = new JLabel(onboardScreen);
	JLabel onboardCityScreenLabel = new JLabel(onboardCityScreen);
	JLabel resultsScreenLabel = new JLabel(resultsScreen);
	// Areas at risk labels
	private JLabel highRisk1Label = new JLabel();
	private JLabel highRisk2Label = new JLabel();
	private JLabel highRisk3Label = new JLabel();

	// Buttons
	JButton provinceButton = new JButton();
	JButton cityButton = new JButton();
	JButton backButton = new JButton();
	JButton backButtonCity = new JButton();
	JButton resultsButtonProvince = new JButton();
	JButton resultsButtonCity = new JButton();
	JButton homeButton = new JButton();

	// JCheckBoxes
	JCheckBox provinceCheckBoxes[] = new JCheckBox[13];
	JCheckBox cityCheckBoxes[] = new JCheckBox[14];
	JRadioButton[] crimeTypeRadioButtons = new JRadioButton[5];
	ButtonGroup crimeTypeButtonGroup = new ButtonGroup();
	// Range Slider Fields
	private JLabel rangeSliderValue1 = new JLabel();
	private JLabel rangeSliderValue2 = new JLabel();
	private RangeSlider rangeSlider = new RangeSlider();

	// Data storage fields to link user selection with file input
	public String[] provinces = { "Ontario", "Newfoundland and Labrador", "Prince Edward Island", "Nova Scotia",
			"New Brunswick", "Quebec", "Manitoba", "Saskatchewan", "Alberta", "British Columbia", "Yukon",
			"Northwest Territories", "Nunavut" };
	public String[] cities = { "Ottawa-Gatineau", "Kingston", "Peterborough", "Toronto", "Hamilton",
			"St.Catharines-Niagara", "Kitchener-Cambridge", "Brantford", "Guelph", "London", "Windsor", "Barrie",
			"Sudbury", "Thunder Bay" };

	// Fields for data storage
	public static ArrayList<String> provinceSelectionList = new ArrayList<>();
	public static ArrayList<String> citySelectionList = new ArrayList<>();
	public static String selectedCrimeType = ""; // Initialized to an empty string for now
	public static ArrayList<Integer> yearList = new ArrayList<>();

	static boolean isProvince; // differentiate between city and province

	// Constructor method
	public HomicideFrame() {
		dataSelectionScreenSetup(); // sets up the initial screen
		frameSetUp(); // sets up the initial frame
	}

	// Sets up the initial selection screen
	private void dataSelectionScreenSetup() {
		// Add onboarding label to the panel
		selectionPanel.add(selectionScreenLabel);
		addTransparentButton(provinceButton, 170, 344, 541, 345, selectionScreenLabel);
		addTransparentButton(cityButton, 771, 344, 541, 345, selectionScreenLabel);
		addTransparentButton(homeButton, 58, 66, 50, 43, selectionScreenLabel);

	}

	// If the user selects "Provinces", this method is called to set-up the data
	// selection for provinces
	private void onboardScreenSetup() {
		onboardPanel.add(onboardScreenLabel);
		// Helper methods
		addTransparentButton(homeButton, 55, 57, 50, 43, onboardScreenLabel);
		addTransparentButton(resultsButtonProvince, 942, 482, 214, 43, onboardScreenLabel);
		setComponents(provinceCheckBoxes, provinces, onboardScreenLabel);
		setRangeSlider();
		onboardScreenLabel.add(rangeSliderValue1);
		onboardScreenLabel.add(rangeSliderValue2);
		onboardScreenLabel.add(rangeSlider);
		// Iterate and add all of the radiobuttons to the onboard screen label
		for (int l = 0; l < crimeTypeRadioButtons.length; l++) {
			// Add each radio button to the label (or directly to the frame)
			onboardScreenLabel.add(crimeTypeRadioButtons[l]);
		}

	}

	// This method sets up the UI
	private void onboardCityScreenSetup() {
		onboardCityPanel.add(onboardCityScreenLabel);
		// Helper methods
		addTransparentButton(homeButton, 55, 57, 50, 43, onboardScreenLabel);
		addTransparentButton(resultsButtonCity, 972, 513, 216, 43, onboardCityScreenLabel);
		setComponents(cityCheckBoxes, cities, onboardCityScreenLabel);
		setRangeSlider();
		onboardCityScreenLabel.add(rangeSliderValue1);
		onboardCityScreenLabel.add(rangeSliderValue2);
		onboardCityScreenLabel.add(rangeSlider);
		// Add the radiobuttons onto the screen
		for (int l = 0; l < crimeTypeRadioButtons.length; l++) {
			onboardCityScreenLabel.add(crimeTypeRadioButtons[l]);
		}
	}

	// Helper method to add a transparent button onto a label
	private void addTransparentButton(JButton button, int x, int y, int width, int height, JLabel label) {
		button.setBounds(x, y, width, height);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.addActionListener(this);
		label.add(button);
	}

	// Sets up all the checkboxes and radiobuttons for the provinces or cities
	private void setComponents(JCheckBox[] checkBoxes, String[] items, JLabel label) {
		for (int k = 0; k < checkBoxes.length; k++) {
			checkBoxes[k] = new JCheckBox();
		}

		// For provinces
		if (items == provinces) {
			for (int i = 0; i < checkBoxes.length; i++) {
				if (i < 5) {
					checkBoxes[i].setBounds(370, 376 + i * 31, 23, 12);
				} else if (i >= 5 && i < 10) {
					checkBoxes[i].setBounds(700, 376 + (i - 5) * 31, 23, 12);
				} else {
					checkBoxes[i].setBounds(970, 376 + (i - 10) * 31, 23, 12);
				}
			}

		}
		// For cities
		else if (items == cities) {
			for (int i = 0; i < checkBoxes.length; i++) {
				if (i < 5) {
					checkBoxes[i].setBounds(369, 376 + i * 31, 23, 12);
				} else if (i >= 5 && i < 10) {
					checkBoxes[i].setBounds(640, 376 + (i - 5) * 31, 23, 12);
				} else {
					checkBoxes[i].setBounds(980, 376 + (i - 10) * 32, 23, 12);
				}
			}
		}

		// Add the checkboxes to the label
		for (int j = 0; j < checkBoxes.length; j++) {
			label.add(checkBoxes[j]);
		}

		// Create the crime type radiobuttons
		String[] crimeTypes = { "Homicide", "Murder first degree", "Murder second degree", "Manslaughter",
				"Infanticide" };
		for (int l = 0; l < crimeTypeRadioButtons.length; l++) {
			crimeTypeRadioButtons[l] = new JRadioButton(crimeTypes[l]);
			Font font = new Font(crimeTypeRadioButtons[l].getFont().getName(), Font.PLAIN, 1); // tiny font so that it
																								// isn't visible
			crimeTypeRadioButtons[l].setFont(font);
			crimeTypeRadioButtons[l].setForeground(Color.WHITE);
			crimeTypeRadioButtons[l].setBounds(160, 598 + l * 31, 20, 30);
			crimeTypeButtonGroup.add(crimeTypeRadioButtons[l]);
		}
	}

	// Sets up the range slider so that user can select a range of years
	private void setRangeSlider() {
		rangeSlider.setOrientation(JSlider.VERTICAL); // Set the slider to vertical
		rangeSlider.setMinimum(2008);
		rangeSlider.setMaximum(2012);
		rangeSlider.setValue(2008);
		rangeSlider.setUpperValue(2012);

		// NEW:'ChangeListener' is an interface in Java's Swing framework that listens
		// for changes to a component's state and performs actions accordingly.
		rangeSlider.addChangeListener(event -> {
			rangeSliderValue1.setText(String.valueOf(rangeSlider.getValue()));
			rangeSliderValue2.setText(String.valueOf(rangeSlider.getUpperValue()));
		});

		// Set up the layout and add the slider and labels to the panel
		rangeSliderValue1.setBounds(168, 508, 100, 18);
		rangeSliderValue2.setBounds(168, 381, 100, 18);
		rangeSlider.setBounds(150, 380, 70, 160);
	}

	// Sets up the Homicide Frame
	private void frameSetUp() {
		// First panel to be displayed
		add(selectionPanel);
		// Terminate the program when closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the frame to full screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		setResizable(false);
		// Make the frame visible
		setVisible(true);
	}

	// Action performed method
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == provinceButton) {
			isProvince = true;
			onboardScreenSetup(); // set up the GUI
			selectionPanel.setVisible(false);
			onboardPanel.setVisible(true);
			add(onboardPanel);
		} else if (event.getSource() == cityButton) {
			isProvince = false;
			onboardCityScreenSetup();
			selectionPanel.setVisible(false);
			onboardCityPanel.setVisible(true);
			add(onboardCityPanel);
		} else if (event.getSource() == resultsButtonProvince || event.getSource() == resultsButtonCity) {
			// VALIDATION CODE

			// Check if at least one province is selected
			if (isProvince) {
				if (!isSelectionValid(provinceCheckBoxes)) {
					JOptionPane.showMessageDialog(this, "Please select at least one province.", "Selection Required",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			} else {
				if (!isSelectionValid(cityCheckBoxes)) {
					JOptionPane.showMessageDialog(this, "Please select at least one city.", "Selection Required",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			// Check if a crime type is selected
			if (!isCrimeTypeSelected()) {
				JOptionPane.showMessageDialog(this, "Please select a type of crime.", "Selection Required",
						JOptionPane.WARNING_MESSAGE);
				return; // Prevent further action
			}
			// Check if the range slider selection is valid
			if (!checkRangeSlider()) {
				return; // Prevent further action
			}

			if (isProvince) {
				checkJCheckBoxes(provinceCheckBoxes, provinceSelectionList, provinces);
			} else {
				checkJCheckBoxes(cityCheckBoxes, citySelectionList, cities);
			}

			// Update data based on current selections
			updateRiskRanking();
			// Page navigation
			showResultsPanel();

		} else if (event.getSource() == backButton) {
			onboardPanel.setVisible(true);
			resultsPanel.setVisible(false);
			updateRiskRanking();
		} else if (event.getSource() == backButtonCity) {
			onboardCityPanel.setVisible(true);
			resultsPanel.setVisible(false);
			updateRiskRanking();
		} else if (event.getSource() == homeButton) {
			// Dispose of the current frame
			this.dispose();
			// Navigate to the home page
			new HomeFrame();
		}
	}

	// This method displays the results of the user's input
	private void showResultsPanel() {
		// Helper method to intialize the GUI components
		initializeHomicideResultsGUI();
		if (isProvince == true) {
			onboardPanel.setVisible(false);

		} else {
			onboardCityPanel.setVisible(false);
		}

		resultsPanel.setVisible(true);
	}

	// Initializes the GUI for the results - adds the TimeSeriesChart, adds the
	// panel to the frame
	private void initializeHomicideResultsGUI() {
		addTransparentButton(backButton, 61, 67, 34, 44, resultsScreenLabel);
		addTransparentButton(homeButton, 1067, 702, 216, 43, resultsScreenLabel);
		// Instantiate the TimeSeriesChart panel
		TimeSeriesChart timeSeriesChartPanel = new TimeSeriesChart();
		timeSeriesChartPanel.setBounds(147, 298, 855, 432);
		resultsScreenLabel.add(timeSeriesChartPanel);

		// Add external onboarding label to the panel
		resultsPanel.add(resultsScreenLabel);
		add(resultsPanel);
	}

	// Get the range of values for the range slider and add it to the array of years
	// that must be displayed
	private boolean checkRangeSlider() {
		yearList.clear();
		int min = rangeSlider.getValue();
		int max = rangeSlider.getUpperValue();
		if (max - min < 1) {
			JOptionPane.showMessageDialog(this, "Please select a range of at least 1 year.", "Invalid Range",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		for (int year = min; year <= max; year++) {
			yearList.add(year);
		}
		return true;
	}

	// Ensure that at least one checkbox is selectied
	private boolean isSelectionValid(JCheckBox[] checkBoxes) {
		for (JCheckBox checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				return true;
			}
		}
		return false;
	}

	// Helper method to check if a crime type is selected
	private boolean isCrimeTypeSelected() {
		for (JRadioButton radioButton : crimeTypeRadioButtons) {
			if (radioButton.isSelected()) {
				selectedCrimeType = radioButton.getText();
				return true;
			}
		}
		return false;
	}

	// Helper method to check which JCheckBoxes are selected
	private void checkJCheckBoxes(JCheckBox[] checkBoxes, ArrayList<String> selectionList, String[] items) {
		selectionList.clear();
		for (int i = 0; i < checkBoxes.length; i++) {
			if (checkBoxes[i].isSelected()) {
				selectionList.add(items[i]);
			}
		}
	}

	// This method updates the JLabels with the calculated risk ranking
	private void updateRiskRanking() {
		if (isProvince == true) {
			HomicideFileInput.updateData(provinceSelectionList, selectedCrimeType);
			ArrayList<Province> top3Provinces = HomicideFileInput.getTop3Provinces(yearList);
			// Each time, the top 3 provinces with the most incidents are displayed.
			// However, if the user does not select 3 provinces, then only the number of
			// provinces selected will be ranked
			if (top3Provinces.size() > 0)
				highRisk1Label.setText("1. " + top3Provinces.get(0).getProvince());
			if (top3Provinces.size() > 1)
				highRisk2Label.setText("2. " + top3Provinces.get(1).getProvince());
			if (top3Provinces.size() > 2)
				highRisk3Label.setText("3. " + top3Provinces.get(2).getProvince());

		} else {
			HomicideFileInput.updateCityData(citySelectionList, selectedCrimeType);
			ArrayList<City> top3Cities = HomicideFileInput.getTop3Cities(yearList);
			// Each time, the top 3 provinces with the most incidents are displayed.
			// However, if the user does not select 3 cities, then only the number of
			// cities selected will be ranked
			if (top3Cities.size() > 0)
				highRisk1Label.setText("1. " + top3Cities.get(0).getCity());
			if (top3Cities.size() > 1)
				highRisk2Label.setText("2. " + top3Cities.get(1).getCity());
			if (top3Cities.size() > 2)
				highRisk3Label.setText("3. " + top3Cities.get(2).getCity());
		}

		updateChart(); // helper method
	}

	// Updates the GUI of the chart based on any changes made in selection
	private void updateChart() {
		resultsPanel.removeAll(); // Clear previous components
		resultsScreenLabel.removeAll(); // Clear previous components from the label

		// Create a new TimeSeriesChart with updated data
		TimeSeriesChart timeSeriesChartPanel = new TimeSeriesChart();
		timeSeriesChartPanel.setBounds(147, 298, 855, 432);
		resultsScreenLabel.add(timeSeriesChartPanel);

		// Initialize and set bounds for top area labels
		initializeHighRiskLabel(highRisk1Label, 424);
		initializeHighRiskLabel(highRisk2Label, 495);
		initializeHighRiskLabel(highRisk3Label, 566);

		if (isProvince == true) {
			// Add backButton to resultsScreenLabel
			addTransparentButton(backButton, 61, 67, 34, 44, resultsScreenLabel);

		} else {
			addTransparentButton(backButtonCity, 61, 67, 34, 44, resultsScreenLabel);
		}

		// Add resultsScreenLabel to resultsPanel
		resultsPanel.add(resultsScreenLabel);

		resultsPanel.revalidate();
		resultsPanel.repaint();
	}

	// Helped method to add the labels
	private void initializeHighRiskLabel(JLabel label, int y) {
		label.setBounds(1068, y, 221, 44);
		label.setFont(new Font("Arial", Font.BOLD, 24));
		label.setForeground(Color.BLACK);

		resultsScreenLabel.add(label);
	}

}