/*
 * Author: Dillon Koestler
 * Date: 10/18/2023
 * 
 * This class is for capturing data from the Player table.
 * 
 */

public class Player {

	private int playerID;
	private String name;
	private String position;
	private int age;
	private int games;
	private int ab;
	private int r;
	private int h;
	private int doubles;
	private int triples;
	private int hr;
	private int rbi;
	private int sb;
	private int cs;
	private int bb;
	private int so;
	private int sh;
	private int sf;
	private int hbp;
	private double average;
	private double onBase;
	private double slugging;
	private double ops;
	
	//Constructor	
	public Player(int playerID, String name, String position, int age, int games, int ab, int r, int h, int doubles,
			int triples, int hr, int rbi, int sb, int cs, int bb, int so, int sh, int sf, int hbp, double average,
			double onBase, double slugging, double ops) {
		super();
		this.playerID = playerID;
		this.name = name;
		this.position = position;
		this.age = age;
		this.games = games;
		this.ab = ab;
		this.r = r;
		this.h = h;
		this.doubles = doubles;
		this.triples = triples;
		this.hr = hr;
		this.rbi = rbi;
		this.sb = sb;
		this.cs = cs;
		this.bb = bb;
		this.so = so;
		this.sh = sh;
		this.sf = sf;
		this.hbp = hbp;
		this.average = average;
		this.onBase = onBase;
		this.slugging = slugging;
		this.ops = ops;
	}

	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public int getGames() {
		return games;
	}



	public void setGames(int games) {
		this.games = games;
	}



	public int getAb() {
		return ab;
	}



	public void setAb(int ab) {
		this.ab = ab;
	}



	public int getR() {
		return r;
	}



	public void setR(int r) {
		this.r = r;
	}



	public int getH() {
		return h;
	}



	public void setH(int h) {
		this.h = h;
	}



	public int getDoubles() {
		return doubles;
	}



	public void setDoubles(int doubles) {
		this.doubles = doubles;
	}



	public int getTriples() {
		return triples;
	}



	public void setTriples(int triples) {
		this.triples = triples;
	}



	public int getHr() {
		return hr;
	}



	public void setHr(int hr) {
		this.hr = hr;
	}



	public int getRbi() {
		return rbi;
	}



	public void setRbi(int rbi) {
		this.rbi = rbi;
	}



	public int getSb() {
		return sb;
	}



	public void setSb(int sb) {
		this.sb = sb;
	}



	public int getCs() {
		return cs;
	}



	public void setCs(int cs) {
		this.cs = cs;
	}



	public int getBb() {
		return bb;
	}



	public void setBb(int bb) {
		this.bb = bb;
	}



	public int getSo() {
		return so;
	}



	public void setSo(int so) {
		this.so = so;
	}



	public int getSh() {
		return sh;
	}



	public void setSh(int sh) {
		this.sh = sh;
	}



	public int getSf() {
		return sf;
	}



	public void setSf(int sf) {
		this.sf = sf;
	}



	public int getHbp() {
		return hbp;
	}



	public void setHbp(int hbp) {
		this.hbp = hbp;
	}



	public double getAverage() {
		return average;
	}



	public void setAverage(double average) {
		this.average = average;
	}



	public double getOnBase() {
		return onBase;
	}



	public void setOnBase(double onBase) {
		this.onBase = onBase;
	}



	public double getSlugging() {
		return slugging;
	}



	public void setSlugging(double slugging) {
		this.slugging = slugging;
	}



	public double getOps() {
		return ops;
	}



	public void setOps(double ops) {
		this.ops = ops;
	}



	public int getPlayerID() {
		return playerID;
	}

	@Override
	public String toString() {
		String print = "Player ID: " + playerID + "	| "
				+ "Name: ";
		
		if(name.length() > 15) {
			print+= name + "	| "
					+ " Position: " + position + "	 |"
					+ " Age: " + age	+ "	| "
					+ " Games: " + games + "	| "
					+ " AB: " + ab + "	| "
					+ "R: ";
		} else {
			print+= name + "		| "
					+ " Position: " + position + "	 |"
					+ " Age: " + age	+ "	| "
					+ " Games: " + games + "	| "
					+ " AB: " + ab + "	| "
					+ "R: ";
		} if(r > 99) {
			print+= r + "	| "
					+ " H: ";
		} else {
			print+= r + "		| "
					+ " H: ";
		} if(h > 9) {
			print+=	h + "	| "
					+ " 2B: " + doubles + "	| "
					+ " 3B: " + triples + "	| "
					+ " HR: " + hr + "	| "
					+ " RBI: " + rbi + "	| "
					+ " SB: " + sb + "	| "
					+ " CS: " + cs + "	| "
					+ " BB: " + bb + "	| "
					+ " SO: " + so + "	| "
					+ " SH: " + sh + "	| "
					+ " SF: " + sf + "	| "
					+ " HBP: " + hbp + "	| "
					+ " AVG: " + average + "	| "
					+ " OBP: " + onBase + "	| "
					+ " SLG: " + slugging + "	| "
					+ " OPS: " + ops;
		} else {
			print+=	h + "		| "
					+ " 2B: " + doubles + "	| "
					+ " 3B: " + triples + "	| "
					+ " HR: " + hr + "	| "
					+ " RBI: " + rbi + "	| "
					+ " SB: " + sb + "	| "
					+ " CS: " + cs + "	| "
					+ " BB: " + bb + "	| "
					+ " SO: " + so + "	| "
					+ " SH: " + sh + "	| "
					+ " SF: " + sf + "	| "
					+ " HBP: " + hbp + "	| "
					+ " AVG: " + average + "	| "
					+ " OBP: " + onBase + "	| "
					+ " SLG: " + slugging + "	| "
					+ " OPS: " + ops;
		}
		return print;
	}

	public String toStringVert() {
		return "Player ID: " + playerID + "\n"
					+ " Name: " + name + "\n"
					+ " Position: " + position + "\n"
					+ " Age: " + age	+ "\n"
					+ " Games: " + games + "\n"
					+ " AB: " + ab + "\n"
					+ " R: " + r + "\n"
					+ " H: " + h + "\n"
					+ " 2B: " + doubles + "\n"
					+ " 3B: " + triples + "\n"
					+ " HR: " + hr + "\n"
					+ " RBI: " + rbi + "\n"
					+ " SB: " + sb + "\n"
					+ " CS: " + cs + "\n"
					+ " BB: " + bb + "\n"
					+ " SO: " + so + "\n"
					+ " SH: " + sh + "\n"
					+ " SF: " + sf + "\n"
					+ " HBP: " + hbp + "\n"
					+ " AVG: " + average + "\n"
					+ " OBP: " + onBase + "\n"
					+ " SLG: " + slugging + "\n"
					+ " OPS: " + ops;
	}

	

}
