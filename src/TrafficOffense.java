
public class TrafficOffense {
	private String region;
	private String offense;
	private int y2008;
	private int y2009;
	private int y2010;
	private int y2011;
	private int y2012;
	public TrafficOffense(String region, String offense, int y2008, int y2009, int y2010, int y2011, int y2012
			) {
		super();
		this.region = region;
		this.offense = offense;
		this.y2008 = y2008;
		this.y2009 = y2009;
		this.y2010 = y2010;
		this.y2011 = y2011;
		this.y2012 = y2012;
		
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getOffense() {
		return offense;
	}
	public void setOffense(String offense) {
		this.offense = offense;
	}
	public int getY2008() {
		return y2008;
	}
	public void setY2008(int y2008) {
		this.y2008 = y2008;
	}
	public int getY2009() {
		return y2009;
	}
	public void setY2009(int y2009) {
		this.y2009 = y2009;
	}
	public int getY2010() {
		return y2010;
	}
	public void setY2010(int y2010) {
		this.y2010 = y2010;
	}
	public int getY2011() {
		return y2011;
	}
	public void setY2011(int y2011) {
		this.y2011 = y2011;
	}
	public int getY2012() {
		return y2012;
	}
	public void setY2012(int y2012) {
		this.y2012 = y2012;
	}
	
	@Override
	public String toString() {
		return "TrafficOffense [region=" + region + ", offense=" + offense + ", y2008=" + y2008 + ", y2009=" + y2009
				+ ", y2010=" + y2010 + ", y2011=" + y2011 + ", y2012=" + y2012 + "]";
	}
	


}
