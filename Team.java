/*
 * Author: Dillon Koestler
 * Date: 10/18/2023
 * 
 * This class is for capturing data from the Team table.
 * 
 */

public class Team {
	
	private int teamID;
	private String name;
	private String city;
	private String state;
	private String league;
	private int capacity;
	private int attendance;
	private double ticketPrice;
	private int championships;
	
	public Team(int teamID, String name, String city, String state, String league, int capacity, int attendance,
			double ticketPrice, int championships) {
		super();
		this.teamID = teamID;
		this.name = name;
		this.city = city;
		this.state = state;
		this.league = league;
		this.capacity = capacity;
		this.attendance = attendance;
		this.ticketPrice = ticketPrice;
		this.championships = championships;
	}

	//teamID
	public int getTeamID() {
		return teamID;
	}

	//name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//city
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	//state
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	//league
	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	//capacity
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	//attendance
	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	//ticketPrice
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	//championships
	public int getChampionships() {
		return championships;
	}

	public void setChampionships(int championships) {
		this.championships = championships;
	}

	@Override
	public String toString() {
		return "Team [teamID=" + teamID + ", name=" + name + ", city=" + city + ",\n state=" + state + ", league="
				+ league + ", capacity=" + capacity + ",\n attendance=" + attendance + ", ticketPrice=" + ticketPrice
				+ ", championships=" + championships + "]";
	}
	
}
