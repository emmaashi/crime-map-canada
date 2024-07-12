package objects;

/*
 * Emma Shi
 * Group #3 - Justice and Public Safety
 * Course Code: ICS4U1
 * Date: May 26th, 2024
 * Teacher: Mr. Fernandes
 * City Object
 * Major Skills: OOP
 */
// This class extends the crime 
public class City extends Crime {

	// Fields
	public String city;

	// Constructor method
	public City(String city, String crimeType, boolean actualIncidents, double numCrimes2008, double numCrimes2009,
			double numCrimes2010, double numCrimes2011, double numCrimes2012) {
		super(crimeType, actualIncidents, numCrimes2008, numCrimes2009, numCrimes2010, numCrimes2011, numCrimes2012);
		this.city = city;
	}

	// Getters and setters
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// toString method
	@Override
	public String toString() {
		return "City [city=" + city + "]";
	}
}

