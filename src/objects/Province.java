package objects;

/*
 * Emma Shi
 * Group #3 - Justice and Public Safety
 * Course Code: ICS4U1
 * Date: May 26th, 2024
 * Teacher: Mr. Fernandes
 * Major Skills: OOP
 * Province Object
 */

// This class creates a province objects
public class Province extends Crime {

	// Fields
	public String province;

	// Constructor method - inherits common properties from the crime object
	public Province(String province, String crimeType, boolean actualIncidents, double numCrimes2008,
			double numCrimes2009, double numCrimes2010, double numCrimes2011, double numCrimes2012) {
		super(crimeType, actualIncidents, numCrimes2008, numCrimes2009, numCrimes2010, numCrimes2011, numCrimes2012);
		this.province = province;
	}

	// Getters and setters
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Province [province=" + province + "]";
	}

}