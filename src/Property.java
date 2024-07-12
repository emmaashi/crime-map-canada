public class Property {

    private String geography;
    private String crime;
    private double year2008;
    private double year2009;
    private double year2010;
    private double year2011;
    private double year2012;

    public Property(String geography, String crime, double year2008, double year2009, double year2010, double year2011, double year2012) {
        this.geography = geography;
        this.crime = crime;
        this.year2008 = year2008;
        this.year2009 = year2009;
        this.year2010 = year2010;
        this.year2011 = year2011;
        this.year2012 = year2012;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public double getYear2008() {
        return year2008;
    }

    public void setYear2008(double year2008) {
        this.year2008 = year2008;
    }

    public double getYear2009() {
        return year2009;
    }

    public void setYear2009(double year2009) {
        this.year2009 = year2009;
    }

    public double getYear2010() {
        return year2010;
    }

    public void setYear2010(double year2010) {
        this.year2010 = year2010;
    }

    public double getYear2011() {
        return year2011;
    }

    public void setYear2011(double year2011) {
        this.year2011 = year2011;
    }

    public double getYear2012() {
        return year2012;
    }

    public void setYear2012(double year2012) {
        this.year2012 = year2012;
    }

    @Override
    public String toString() {
        return "Property [geography=" + geography + ", crime=" + crime + ", year2008=" + year2008 + ", year2009="
                + year2009 + ", year2010=" + year2010 + ", year2011=" + year2011 + ", year2012=" + year2012 + "]";
    }
}