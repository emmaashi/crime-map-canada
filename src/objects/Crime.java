package objects;

import java.util.ArrayList;

/*
 * Emma Shi
 * Group #3 - Justice and Public Safety
 * Course Code: ICS4U1
 * Date: May 26th, 2024
 * Teacher: Mr. Fernandes
 * Major Skills: OOP
 * Crime Object
 */

// This class creates the common properties/data of a crime
public class Crime {
	public String crimeType;
	public boolean actualIncidents; // true == actual, false = rate per 100,000
	public double numCrimes2008;
	public double numCrimes2009;
	public double numCrimes2010;
	public double numCrimes2011;
	public double numCrimes2012;

	// Constructor method
	public Crime(String crimeType, boolean actualIncidents, double numCrimes2008, double numCrimes2009,
			double numCrimes2010, double numCrimes2011, double numCrimes2012) {
		super();
		this.crimeType = crimeType;
		this.actualIncidents = actualIncidents;
		this.numCrimes2008 = numCrimes2008;
		this.numCrimes2009 = numCrimes2009;
		this.numCrimes2010 = numCrimes2010;
		this.numCrimes2011 = numCrimes2011;
		this.numCrimes2012 = numCrimes2012;
	}

	// Getters and setters
	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	public boolean isActualIncidents() {
		return actualIncidents;
	}

	public void setActualIncidents(boolean actualIncidents) {
		this.actualIncidents = actualIncidents;
	}

	public double getNumCrimes2008() {
		return numCrimes2008;
	}

	public void setNumCrimes2008(double numCrimes2008) {
		this.numCrimes2008 = numCrimes2008;
	}

	public double getNumCrimes2009() {
		return numCrimes2009;
	}

	public void setNumCrimes2009(double numCrimes2009) {
		this.numCrimes2009 = numCrimes2009;
	}

	public double getNumCrimes2010() {
		return numCrimes2010;
	}

	public void setNumCrimes2010(double numCrimes2010) {
		this.numCrimes2010 = numCrimes2010;
	}

	public double getNumCrimes2011() {
		return numCrimes2011;
	}

	public void setNumCrimes2011(double numCrimes2011) {
		this.numCrimes2011 = numCrimes2011;
	}

	public double getNumCrimes2012() {
		return numCrimes2012;
	}

	public void setNumCrimes2012(double numCrimes2012) {
		this.numCrimes2012 = numCrimes2012;
	}

	// Utility method - to be used by the Province and City object
	// This method adds up the total crimes that occured for each province or city
	// in the passed in range of years
	public double getTotalIncidentsForYears(ArrayList<Integer> yearList) {
		double total = 0;
		for (int year : yearList) {
			switch (year) {
			case 2008:
				total += numCrimes2008;
				break;
			case 2009:
				total += numCrimes2009;
				break;
			case 2010:
				total += numCrimes2010;
				break;
			case 2011:
				total += numCrimes2011;
				break;
			case 2012:
				total += numCrimes2012;
				break;
			}
		}
		return total;
	}

	// toString method
	@Override
	public String toString() {
		return "Crime [crimeType=" + crimeType + ", actualIncidents=" + actualIncidents + ", numCrimes2008="
				+ numCrimes2008 + ", numCrimes2009=" + numCrimes2009 + ", numCrimes2010=" + numCrimes2010
				+ ", numCrimes2011=" + numCrimes2011 + ", numCrimes2012=" + numCrimes2012 + "]";
	}

}