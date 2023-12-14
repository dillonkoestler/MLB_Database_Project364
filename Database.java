/* Author: Dillon Koestler
* Date: 10/30/23
* 
* The purpose of this class is to connect to the database and encapsulate database operations.
* 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Database {
	
//	private String url = "jdbc:mysql://<ip address>:<port>/<db name>?user=<mysql username>&password=<mysql password>";
	private String url = "jdbc:mysql://138.49.184.47:3306/koestler6518FinalProject?user=koestler6518&password=F>K_8^mz6U^Q6M3";

	private Connection connection;
	
	public Database() {
		
	}
	
	public void connect() {
		try {
			/* To make sure you successfully connected to the database. */
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Cannot connect!");
			System.out.println(e);
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Cannot disconnect!");
		}
	}
	
	/* Basic code to run a query on a database. */
	public ResultSet runQuery(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	/* Set of methods for accessing data in the database for common actions. */
	
	public ResultSet playerLookup(int playerID) throws SQLException {
		String query = "SELECT Player.PlayerID, Player.Name, Team.Name AS Team, Position, Age, Games, AtBats, Runs, Hits, Doubles, Triples, HomeRuns, RBI, StolenBases, CaughtStealing, "
				+ "	Walks, StrikeOuts, SacrificeHits, SacrificeFlies, HitByPitch, Average, OnBase, Slugging, OPS, Salary, ContractBegin, ContractEnd "
				+ "FROM Player JOIN PLAYS_FOR JOIN Team "
				+ "	ON Player.PlayerID = PLAYS_FOR.PlayerID "
				+ " AND PLAYS_FOR.TeamID = Team.TeamID "
				+ "WHERE Player.PlayerID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, playerID);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public int getNextPlayerID() throws SQLException {
		String sql = "SELECT max(PlayerID) AS id FROM Player";
		ResultSet results = runQuery(sql);
		while(results.next()) {
			int nextID = results.getInt("id") + 1;
			return nextID;
		}
		return 10000;
	}
	
	public void insertPlayer(Player p) throws SQLException {
		String sql = "INSERT INTO Player "
				+ "(PlayerID, Name, Position, Age, Games, AtBats, Runs, Hits, Doubles, Triples,"
				+ "HomeRuns, RBI, StolenBases, CaughtStealing, Walks, StrikeOuts, SacrificeHits,"
				+ "SacrificeFlies, HitByPitch, Average, OnBase, Slugging, OPS) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, p.getPlayerID());
		stmt.setString(2, p.getName());
		stmt.setString(3, p.getPosition());
		stmt.setInt(4, p.getAge());
		stmt.setInt(5, p.getGames());
		stmt.setInt(6, p.getAb());
		stmt.setInt(7, p.getR());
		stmt.setInt(8, p.getH());
		stmt.setInt(9, p.getDoubles());
		stmt.setInt(10, p.getTriples());
		stmt.setInt(11, p.getHr());
		stmt.setInt(12, p.getRbi());
		stmt.setInt(13, p.getSb());
		stmt.setInt(14, p.getCs());
		stmt.setInt(15, p.getBb());
		stmt.setInt(16, p.getSo());
		stmt.setInt(17, p.getSh());
		stmt.setInt(18, p.getSf());
		stmt.setInt(19, p.getHbp());
		stmt.setDouble(20, p.getAverage());
		stmt.setDouble(21, p.getOnBase());
		stmt.setDouble(22, p.getSlugging());
		stmt.setDouble(23, p.getOps());
		int numRowsAffected = stmt.executeUpdate();
		System.out.println("Number of rows affected: " + numRowsAffected);
	}
	
	public void insertPLAYS_FOR(Player p, int teamID, int salary, int conB, int conE) throws SQLException {
		String sql = "INSERT INTO PLAYS_FOR "
				+ "(PlayerID, TeamID, Salary, ContractBegin, ContractEnd) "
				+ "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, p.getPlayerID());
		stmt.setInt(2, teamID);
		stmt.setInt(3, salary);
		stmt.setInt(4, conB);
		stmt.setInt(5, conE);
		int numRowsAffected = stmt.executeUpdate();
		System.out.println("Number of rows affected: " + numRowsAffected);
	}
	
	public ResultSet getPlayerName(int playerID) throws SQLException {
		String sql = "SELECT Name FROM Player WHERE PlayerID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, playerID);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
//	public boolean deletePlayer(Player p) throws SQLException {
//		String sql = "DELETE FROM Player WHERE PlayerID = ?";
//		PreparedStatement stmt = connection.prepareStatement(sql);
//		stmt.setInt(1, p.getPlayerID());
//		int numRowsAffected = stmt.executeUpdate();
//		return numRowsAffected > 0;
//	}
	
	public boolean deletePlayerByID(int playerID) throws SQLException {
		String sqlPLAYS_FOR = "DELETE FROM PLAYS_FOR WHERE PlayerID = ?";
		PreparedStatement stmtPLAYS_FOR = connection.prepareStatement(sqlPLAYS_FOR);
		stmtPLAYS_FOR.setInt(1, playerID);
		stmtPLAYS_FOR.executeUpdate();
		
		String sqlPlayer = "DELETE FROM Player WHERE PlayerID = ?";
		PreparedStatement stmtPlayer = connection.prepareStatement(sqlPlayer);
		stmtPlayer.setInt(1, playerID);
		int numRowsAffectedPlayer = stmtPlayer.executeUpdate();
		return numRowsAffectedPlayer > 0;
	}
	
//	public void updatePlayerAverage(Player p, double average) throws SQLException {
//		String sql = "UPDATE Player SET Average = ? WHERE PlayerID = ?";
//		PreparedStatement stmt = connection.prepareStatement(sql);
//		stmt.setDouble(1, average);
//		stmt.setInt(2, p.getPlayerID());
//		stmt.executeUpdate();
//		p.setAverage(average);
//	}
	
	public void updatePlayerPrompt(int id) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Which stat would you like to update?");
		System.out.println();
		System.out.println("Available options include:");
		System.out.println("Age, Games, AtBats, Runs, Hits, Doubles, Triples,");
		System.out.println("HomeRuns, RBI, StolenBases, CaughtStealing, Walks,");
		System.out.println("StrikeOuts, SacrificeHits, SacrificeFlies, HitByPitch,");
		System.out.println("Average, OnBase, Slugging, OPS");
		System.out.println();
		String stat = scan.next();
		System.out.print("What is the updated value: ");
		double val = scan.nextDouble();
		
		/* update player based on statistic desired */
		updatePlayerStat(id, stat, val);
		System.out.println("Player updated");
		System.out.println("Would you like to update another value for the same player?");
		System.out.println("1 - Yes, 2 - No");
		
		int confirm = scan.nextInt();
		
		/* check for valid input */
		while(confirm != 1 && confirm != 2) {
			System.out.print("Please enter a valid input: ");
			confirm = scan.nextInt();
		}
		
		/* if confirmed */
		if(confirm == 1) {
			/* update */
			updatePlayerPrompt(id);
		}
	}
	
	public void updatePlayerStat(int playerID, String stat, double value) throws SQLException {
		String sql = "UPDATE Player SET " + stat + " = ? WHERE PlayerID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setDouble(1, value);
		stmt.setInt(2, playerID);
		stmt.executeUpdate();
	}
	
	public void teamSumData(String stat, String order) throws SQLException {
		String sql = "SELECT Team.Name, "
				+ "sum(AtBats) AS AtBats, sum(Runs) AS Runs, sum(Hits) AS Hits, sum(Doubles) AS Doubles, sum(Triples) AS Triples, sum(HomeRuns) AS HomeRuns, "
				+ "sum(RBI) AS RBI, sum(StolenBases) AS StolenBases, sum(CaughtStealing) AS CaughtStealing, sum(Walks) AS Walks, sum(StrikeOuts) AS StrikeOuts, "
				+ "sum(SacrificeHits) AS SacrificeHits, sum(SacrificeFlies) AS SacrificeFlies, sum(HitByPitch) AS HitByPitch, "
				+ "avg(Average) AS Average, avg(OnBase) AS OnBase, avg(Slugging) AS Slugging, avg(OPS) AS OPS, sum(PLAYS_FOR.Salary) AS Payroll "
				+ "FROM Team JOIN PLAYS_FOR JOIN Player "
				+ "ON Team.TeamID = PLAYS_FOR.TeamID "
				+ "AND PLAYS_FOR.PlayerID = Player.PlayerID "
				+ "GROUP BY Team.Name "
				+ "ORDER BY " + stat + " " + order;
		ResultSet results = runQuery(sql);
		
		System.out.println("Team Summary Statistics:");
		System.out.println("Sorted by " + stat + " " + order);	
		while(results.next()) {
			String name = results.getString("Name");
			int ab = results.getInt("AtBats");
			int r = results.getInt("Runs");
			int h = results.getInt("Hits");
			int doubles = results.getInt("Doubles");
			int triples = results.getInt("Triples");
			int hr = results.getInt("HomeRuns");
			int rbi = results.getInt("RBI");
			int sb = results.getInt("StolenBases");
			int cs = results.getInt("CaughtStealing");
			int bb = results.getInt("Walks");
			int so = results.getInt("StrikeOuts");
			int sh = results.getInt("SacrificeHits");
			int sf = results.getInt("SacrificeFlies");
			int hbp = results.getInt("HitByPitch");
			double avg = results.getDouble("Average");
			double obp = results.getDouble("OnBase");
			double slg = results.getDouble("Slugging");
			double ops = results.getDouble("OPS");
			int payroll = results.getInt("Payroll");
			
			/* set the rounding limit for doubles */
			DecimalFormat df3 = new DecimalFormat("#.###");
			String print = "Team: ";
			/* if/else for handling word/data length differences and aligning columns */
			if(name.equals("Diamondbacks")) {
				print+= name + "	| "
						+ "AB: " + ab + "	| "
						+ "R: " + r + "	| "
						+ "H: " + h + "	| "
						+ "2B: " + doubles + "	| "
						+ "3B: " + triples + "	| "
						+ "HR: " + hr + "	| "
						+ "RBI: " + rbi + "	| "
						+ "SB: " + sb + "	| "
						+ "CS: " + cs + "	| "
						+ "BB: " + bb + "	| "
						+ "SO: " + so + "	| ";
			} else {
				print+= name + "		| "
						+ "AB: " + ab + "	| "
						+ "R: " + r + "	| "
						+ "H: " + h + "	| "
						+ "2B: " + doubles + "	| "
						+ "3B: " + triples + "	| "
						+ "HR: " + hr + "	| "
						+ "RBI: " + rbi + "	| "
						+ "SB: " + sb + "	| "
						+ "CS: " + cs + "	| "
						+ "BB: " + bb + "	| "
						+ "SO: " + so + "	| ";
			} if(sh > 9) {
				print+= "SH: " + sh + "	| "
						+ "SF: " + sf + "	| "
						+ "HBP: " + hbp + "	| "
						+ "AVG: " + df3.format(avg) + "	| "
						+ "OBP: " + df3.format(obp) + "	| "
						+ "SLG: " + df3.format(slg) + "	| "
						+ "OPS: " + df3.format(ops) + "	| "
						+ "Payroll: $" + payroll;
				
			} else {
				print+= "SH: " + sh + "		| "
						+ "SF: " + sf + "	| "
						+ "HBP: " + hbp + "	| "
						+ "AVG: " + df3.format(avg) + "	| "
						+ "OBP: " + df3.format(obp) + "	| "
						+ "SLG: " + df3.format(slg) + "	| "
						+ "OPS: " + df3.format(ops) + "	| "
						+ "Payroll: $" + payroll;
			}
			System.out.println(print);
		}
	}
	
	public void awEffect(ResultSet results) throws SQLException {
		DecimalFormat df3 = new DecimalFormat("#.###");
		DecimalFormat df4 = new DecimalFormat("##.##");
		
		while(results.next()) {
			String  aw = results.getString("AwardWinner");
			String team = results.getString("Team");
			int att = results.getInt("Attendance");
			int cap = results.getInt("Capacity");
			int tp = results.getInt("TicketPrice");
			int w = results.getInt("Wins");
			
			String print = "Award Winner: ";
			if(aw.length() > 9) {
				print+= aw + "	| "
						+ "Team: ";
			} else {
				print+= aw + "		| "
						+ "Team: ";
			} if(team.length() > 7 ) {
				print+= team + "	| "
						+ "Win Percent: " + df3.format((double)w/162) + "	| "
						+ "Percent of Stadium Full: ";
			} else {
				print+= team + "		| "
						+ "Win Percent: " + df3.format((double)w/162) + "	| "
						+ "Percent of Stadium Full: ";
			} if(df4.format((double)att/cap*100).length() > 4) {
				print+= df4.format((double)att/cap*100) + "	| "
						+ "Average Game Earnings: $" + att*tp;
			} else {
				print+= df4.format((double)att/cap*100) + "		| "
						+ "Average Game Earnings: $" + att*tp;
			}
			System.out.println(print);
		}
	}
	
	public void awEffectAll(ResultSet results) throws SQLException {
		DecimalFormat df3 = new DecimalFormat("#.###");
		DecimalFormat df4 = new DecimalFormat("##.##");
		
		while(results.next()) {
			String team = results.getString("Team");
			int att = results.getInt("Attendance");
			int cap = results.getInt("Capacity");
			int tp = results.getInt("TicketPrice");
			int w = results.getInt("Wins");
			
			String print = "Award Winner: ";
			if(team.equals("Angels")) {
				print+= "Shohei Ohtani	| Team: ";
			} else if(team.equals("Braves")) {
				print+= "Ronald Acuna	| Team: ";
			} else if(team.equals("Orioles")) {
				print+= "Gunnar Henderson	| Team: ";
			} else if(team.equals("Diamondbacks")) {
				print+= "Corbin Carroll	| Team: ";
			} else {
				print+= "None		| Team: ";	
			}
			if(team.length() > 7 ) {
				print+= team + "	| "
						+ "Win Percent: " + df3.format((double)w/162) + "	| "
						+ "Percent of Stadium Full: ";
			} else {
				print+= team + "		| "
						+ "Win Percent: " + df3.format((double)w/162) + "	| "
						+ "Percent of Stadium Full: ";
			} if(df4.format((double)att/cap*100).length() > 4) {
				print+= df4.format((double)att/cap*100) + "	| "
						+ "Average Game Earnings: $" + att*tp;
			} else {
				print+= df4.format((double)att/cap*100) + "		| "
						+ "Average Game Earnings: $" + att*tp;
			}
			System.out.println(print);
		}
	}
}
