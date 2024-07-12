package objects;

//Object created to store name and severity values of a place
public class Place {

	String name;
	double year8;
	double year9;
	double year10;
	double year11;
	double year12;
	
	public Place(String name) {
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getYear8() {
		return year8;
	}

	public void setYear8(double year8) {
		this.year8 = year8;
	}

	public double getYear9() {
		return year9;
	}

	public void setYear9(double year9) {
		this.year9 = year9;
	}

	public double getYear10() {
		return year10;
	}

	public void setYear10(double year10) {
		this.year10 = year10;
	}

	public double getYear11() {
		return year11;
	}

	public void setYear11(double year11) {
		this.year11 = year11;
	}

	public double getYear12() {
		return year12;
	}

	public void setYear12(double year12) {
		this.year12 = year12;
	}
	
	public double getYear(int num) {
		
		if (num == 2008) {
			
			return year8;
		}
		
		if (num == 2009) {
			
			return year9;
		}
		
		if (num == 2010) {
			
			return year10;
		}
		
		if (num == 2011) {
			
			return year11;
		}
		
		if (num == 2012) {
			
			return year12;
		}
		
		else {
			
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Place [name=" + name + ", year8=" + year8 + ", year9=" + year9 + ", year10=" + year10 + ", year11="
				+ year11 + ", year12=" + year12 + "]";
	}
}