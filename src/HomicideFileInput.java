
//Updates to keyboard shortcuts … On Thursday, August 1, 2024, Drive keyboard shortcuts will be updated to give you first-letters navigation.Learn more
//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import objects.City;
import objects.Province;
/*
 * Emma Shi 
 * Group #3 - Justice and Public Safety
 * Course Code: ICS4U1
 * Date: May 26th, 2024
 * Teacher: Mr. Fernandes
 * Major Skills: Array Lists, Arrays, Control Structures, File Handling, OOP Concepts, Methods, Lambda Expression (NEW)
 * Homicide File Input
 */

// This class reads the files and is used to match user selection to data
public class HomicideFileInput {
	// Fields for storing the matched Provinces and Cities
	public static ArrayList<Province> matchedProvinces = new ArrayList<>();
	public static ArrayList<City> matchedCities = new ArrayList<>();

	// Reading the file of the provinces
	public static void readProvinces(ArrayList<String> selectedProvinces, String selectedCrimeType) {
		matchedProvinces.clear(); // Clear the list before populating it again
		try {
			Scanner inputFile = new Scanner(new File("files/HomicideProvinceStats.csv"));

			// Set the delimiting pattern of the scanner
			inputFile.useDelimiter(",|\n");

			// Generate the fields
			while (inputFile.hasNext()) {
				String province = inputFile.next(); // province
				String crimeType = inputFile.next(); // type of crime
				String statType = inputFile.next(); // statistic type
				double num2008 = inputFile.nextDouble();
				double num2009 = inputFile.nextDouble();
				double num2010 = inputFile.nextDouble();
				double num2011 = inputFile.nextDouble();
				double num2012 = inputFile.nextDouble();

				// Check if the current record matches the selected criteria
				if (selectedProvinces.contains(province) && crimeType.equals(selectedCrimeType)) {
					// Note: not displaying the data for the rate per 100,000 population
					if (statType.equals("Actual incidents")) {
						// Create a new instance of the Province object
						Province provinceData = new Province(province, crimeType, true, num2008, num2009, num2010,
								num2011, num2012);
						// Add to the array list
						matchedProvinces.add(provinceData);
						System.out.println("Matched Province: " + provinceData);
					}
				}
			}

			// close the file input
			inputFile.close();

		} catch (FileNotFoundException event) {
			System.out.println("File Error");
		}
	}

	// Update data based on current selections
	public static void updateData(ArrayList<String> selectedProvinces, String selectedCrimeType) {
		readProvinces(selectedProvinces, selectedCrimeType);
	}

	// NEW SKILL: Lambda expression
	// // Sorts the matchedProvinces list based on total incidents for the given
	// years and returns the top 3 provinces
	public static ArrayList<Province> getTop3Provinces(ArrayList<Integer> yearList) {
		// Sort the matchedProvinces list in descending order based on total incidents
		// for the given years
		matchedProvinces.sort((p1, p2) -> Double.compare(p2.getTotalIncidentsForYears(yearList),
				p1.getTotalIncidentsForYears(yearList)));
		// Create a new list to store the top 3 provinces
		ArrayList<Province> top3Provinces = new ArrayList<>();
		// Loop through the sorted list and add the top 3 provinces to the new list
		for (int i = 0; i < Math.min(3, matchedProvinces.size()); i++) {
			top3Provinces.add(matchedProvinces.get(i));
		}
		// Return the list of top 3 provinces
		return top3Provinces;
	}

	// Reading the file of the cities
	private static void readCities(ArrayList<String> selectedCities, String selectedCrimeType) {
		matchedCities.clear();
		try {
			// Allow access to the files
			Scanner inputFile = new Scanner(new File("files/HomicideCityStats.csv"));

			// Set the delimiting pattern of the scanner
			inputFile.useDelimiter(",|\r\n");

			// Generate the fields
			for (int index = 0; index < 140; index++) {
				String city = inputFile.next(); // province
				String crimeType = inputFile.next(); // type of crime
				String statType = inputFile.next(); // statistic type
				double num2008 = inputFile.nextDouble();
				double num2009 = inputFile.nextDouble();
				double num2010 = inputFile.nextDouble();
				double num2011 = inputFile.nextDouble();
				double num2012 = inputFile.nextDouble();

				// Check if the current record matches the selected criteria
				if (selectedCities.contains(city) && crimeType.equals(selectedCrimeType)) {
					// Note: not displaying the data for the rate per 100,000 population
					if (statType.equals("Actual incidents")) {
						// Create a new instance of the City object
						City cityData = new City(city, crimeType, true, num2008, num2009, num2010, num2011, num2012);
						// Add to the array list
						matchedCities.add(cityData);
					}
				}
			}

			// close the file input
			inputFile.close();

			// Catch any exceptions
		} catch (FileNotFoundException event) {
			System.out.println("File Error");

		}

	}

	// Update data based on current selections
	public static void updateCityData(ArrayList<String> selectedCities, String selectedCrimeType) {
		readCities(selectedCities, selectedCrimeType);
	}

	// NEW SKILL: Lambda expression
	// // Sorts the matchedCities list based on total incidents for the given
	// years and returns the top 3 provinces
	public static ArrayList<City> getTop3Cities(ArrayList<Integer> yearList) {
		// Sort the matchedCities list in descending order based on total incidents
		// for the given years
		matchedCities.sort((c1, c2) -> Double.compare(c2.getTotalIncidentsForYears(yearList),
				c1.getTotalIncidentsForYears(yearList)));
		// Create a new list to store the top 3 provinces
		ArrayList<City> top3Cities = new ArrayList<>();
		// Loop through the sorted list and add the top 3 provinces to the new list
		for (int i = 0; i < Math.min(3, matchedCities.size()); i++) {
			top3Cities.add(matchedCities.get(i));
		}

		// Return the list of top 3 cities
		return top3Cities;
	}
}