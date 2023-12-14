/*
 * Author: Dillon Koestler
 * Date: 10/18/2023
 * 
 * This class is for capturing data from the Season table.
 * 
 */

public class Season {

	private int year;
	private String wsChamp;
	private String alChamp;
	private String nlChamp;
	private String alMVP;
	private String nlMVP;
	private String alROTY;
	private String nlROTY;
	
	public Season(int year, String wsChamp, String alChamp, String nlChamp, String alMVP, String nlMVP, String alROTY,
			String nlROTY) {
		super();
		this.year = year;
		this.wsChamp = wsChamp;
		this.alChamp = alChamp;
		this.nlChamp = nlChamp;
		this.alMVP = alMVP;
		this.nlMVP = nlMVP;
		this.alROTY = alROTY;
		this.nlROTY = nlROTY;
	}

	public String getWsChamp() {
		return wsChamp;
	}

	public void setWsChamp(String wsChamp) {
		this.wsChamp = wsChamp;
	}

	public String getAlChamp() {
		return alChamp;
	}

	public void setAlChamp(String alChamp) {
		this.alChamp = alChamp;
	}

	public String getNlChamp() {
		return nlChamp;
	}

	public void setNlChamp(String nlChamp) {
		this.nlChamp = nlChamp;
	}

	public String getAlMVP() {
		return alMVP;
	}

	public void setAlMVP(String alMVP) {
		this.alMVP = alMVP;
	}

	public String getNlMVP() {
		return nlMVP;
	}

	public void setNlMVP(String nlMVP) {
		this.nlMVP = nlMVP;
	}

	public String getAlROTY() {
		return alROTY;
	}

	public void setAlROTY(String alROTY) {
		this.alROTY = alROTY;
	}

	public String getNlROTY() {
		return nlROTY;
	}

	public void setNlROTY(String nlROTY) {
		this.nlROTY = nlROTY;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Season [year=" + year + ", wsChamp=" + wsChamp + ", alChamp=" + alChamp + ",\n nlChamp=" + nlChamp
				+ ", alMVP=" + alMVP + ", nlMVP=" + nlMVP + ",\n alROTY=" + alROTY + ", nlROTY=" + nlROTY + "]";
	}
	
}
