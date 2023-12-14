import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Baseball {

	public static void main(String[] args) {
		/* create a database object to encapsulate the connection details */
		Database db = new Database();
		
		/* connect to the database */
		db.connect();
		
		System.out.println("Welcome to my MLB Database project!");
		
		try {
			boolean flag = true;
			
			while(flag) {
				
				/* begin user prompting */
				System.out.println("Which action would you like to perform?");
				System.out.println();
				System.out.println("1. Look up a player's 2023 statistics");
				System.out.println("2. Add a player and his statistics");
				System.out.println("3. Delete a player and his statistics");
				System.out.println("4. Update a player and his statistics");
				System.out.println("5. Find team summary data");
				System.out.println("6. Learn about free agent players (those without contracts)");
				System.out.println("7. Investigate award winner effect");
				System.out.println();
			
				Scanner scan = new Scanner(System.in);
				int selection = scan.nextInt();
				
				if(selection == 1) {
					System.out.println("You selected \"Look up a player\"");
					System.out.println();
					
					System.out.print("Enter the Player ID you want to lookup: ");
					int id = scan.nextInt();
					
					/* check for valid input */
					while(id >= db.getNextPlayerID() || id < 1) {
						System.out.print("That Player ID is not valid. Please enter another value: ");
						id = scan.nextInt();
					}
					System.out.println();
					System.out.println("You want to look up the player with id: \"" + id + "\"\n");
			
					/* lookup player */
					ResultSet result1 = db.playerLookup(id);
					
					/* display player*/
					if(result1.next()) {
						int playerID = result1.getInt("PlayerID");
						String name = result1.getString("Name");
						String team = result1.getString("Team");
						String pos = result1.getString("Position");
						int age = result1.getInt("Age");
						int g = result1.getInt("Games");
						int ab = result1.getInt("AtBats");
						int r = result1.getInt("Runs");
						int h = result1.getInt("Hits");
						int doubles = result1.getInt("Doubles");
						int triples = result1.getInt("Triples");
						int hr = result1.getInt("HomeRuns");
						int rbi = result1.getInt("RBI");
						int sb = result1.getInt("StolenBases");
						int cs = result1.getInt("CaughtStealing");
						int bb = result1.getInt("Walks");
						int so = result1.getInt("StrikeOuts");
						int sh = result1.getInt("SacrificeHits");
						int sf = result1.getInt("SacrificeFlies");
						int hbp = result1.getInt("HitByPitch");
						double avg = result1.getDouble("Average");
						double obp = result1.getDouble("OnBase");
						double slg = result1.getDouble("Slugging");
						double ops = result1.getDouble("OPS");
						int salary = result1.getInt("Salary");
						int conB = result1.getInt("ContractBegin");
						int conE = result1.getInt("ContractEnd");
						
						Player p1 = new Player(playerID, name, pos, age, g, ab, r, h, doubles, 
							triples, hr, rbi, sb, cs, bb, so, sh, sf, hbp, avg, obp, slg, ops);
						
						System.out.println(p1.toStringVert()
								+ "\n Team: " + team + "\n"
								+ " Salary: " + salary + "\n"
								+ " Beginning of Contract: " + conB + "\n"
								+ " End of Contract: " + conE);
					}
					
					flag = false;
				} else if(selection == 2) {
					System.out.println("You selected \"Add a player\"");
					System.out.println();
					
					/* get the next available playerID*/
					int nextID = db.getNextPlayerID();
					
					/* prompt user for player information */
					System.out.print("First Name: ");
					String fName = scan.next();
					System.out.print("Last Name: ");
					String lName = scan.next();
					String name = fName + " " + lName;
					System.out.print("Team ID: ");
					int teamID = scan.nextInt();
					System.out.print("Position: ");
					String pos = scan.next();
					System.out.print("Age: ");
					int age = scan.nextInt();
					System.out.print("Games: ");
					int g = scan.nextInt();
					System.out.print("At Bats: ");
					int ab = scan.nextInt();
					System.out.print("Runs: ");
					int r = scan.nextInt();
					System.out.print("Hits: ");
					int h = scan.nextInt();
					System.out.print("Doubles: ");
					int doubles = scan.nextInt();
					System.out.print("Triples: ");
					int triples = scan.nextInt();
					System.out.print("Home Runs: ");
					int hr = scan.nextInt();
					System.out.print("RBI: ");
					int rbi = scan.nextInt();
					System.out.print("Stolen Bases: ");
					int sb = scan.nextInt();
					System.out.print("Caught Stealing: ");
					int cs = scan.nextInt();
					System.out.print("Walks: ");
					int bb = scan.nextInt();
					System.out.print("Strike Outs: ");
					int so = scan.nextInt();
					System.out.print("Sacrifice Hits: ");
					int sh = scan.nextInt();
					System.out.print("Sacrifice Flies: ");
					int sf = scan.nextInt();
					System.out.print("Hit By Pitch: ");
					int hbp = scan.nextInt();
					System.out.print("Batting Average (Between 0.000 and 1.000): ");
					double avg = scan.nextDouble();
					System.out.print("On-Base Percentage (Between 0.000 and 1.000): ");
					double obp = scan.nextDouble();
					System.out.print("Slugging Percentage (Between 0.000 and 4.000): ");
					double slg = scan.nextDouble();
					double ops = obp + slg;
					System.out.print("Salary: ");
					int salary = scan.nextInt();
					System.out.print("Beginning of Contract: ");
					int conB = scan.nextInt();
					System.out.print("End of Contract: ");
					int conE = scan.nextInt();
					
					/* create player object */
					Player p2 = new Player(nextID, name, pos, age, g, ab, r, h, doubles, 
							triples, hr, rbi, sb, cs, bb, so, sh, sf, hbp, avg, obp, slg, ops);
					
					/* insert Player and PLAYS_FOR data into database */
					db.insertPlayer(p2);
					db.insertPLAYS_FOR(p2, teamID, salary, conB, conE);
					
					System.out.println();
					System.out.println(name +  " added with Player ID: " + nextID + ".");
					
					flag = false;
				} else if(selection == 3) {
					System.out.println("You selected \"Delete a player\"");
					System.out.println();
					System.out.print("Enter the Player ID of the player you wish to delete: ");
					int entered = scan.nextInt();
					
					/* check for valid input */
					while(entered >= db.getNextPlayerID() || entered < 1) {
						System.out.print("That Player ID is not valid. Please enter another value: ");
						entered = scan.nextInt();
					}
					
					/* confirm user's desire to delete a player */
					ResultSet result3 = db.getPlayerName(entered);
					while(result3.next()) {
						System.out.println("You are choosing to delete \"" + result3.getString("Name") + "\"\n"
								+ "Confirm? This cannot be undone.\n"
								+ "1 - Yes, 2 - No");
					}
					System.out.println();
					int confirm = scan.nextInt();
					
					/* check for valid input */
					while(confirm != 1 && confirm != 2) {
						System.out.print("Please enter a valid input: ");
						confirm = scan.nextInt();
					}
					
					/* if confirmed */
					if(confirm == 1) {
						/* delete player */
						boolean success = db.deletePlayerByID(entered);
						if(success) {
							System.out.println("Player deleted");
						} else {
							System.out.println("Player could not be deleted. Please try a different Player ID.");
						}
					}
					
					flag = false;
				} else if(selection == 4) {
					System.out.println("You selected \"Update a player\"");
					System.out.println();
					System.out.println("Please provide information on what to update.");
					System.out.print("What is the player's ID: ");
					int id = scan.nextInt();
					
					/* check for valid input */
					while(id >= db.getNextPlayerID() || id < 1) {
						System.out.print("That Player ID is not valid. Please enter another value: ");
						id = scan.nextInt();
					}
					
					/* display player name */
					ResultSet result4 = db.getPlayerName(id);
					while(result4.next()) {
						System.out.println("You have decided to update \"" + result4.getString("Name") + "\"");
					}
					System.out.println();
					
					db.updatePlayerPrompt(id);
					
					flag = false;
				} else if(selection == 5) {
					System.out.println("You selected \"Find team summary data\"");
					System.out.println();
					System.out.print("Which statistic would you like to view? ");
					System.out.println();
					System.out.println("Available options include:");
					System.out.println("AtBats, Runs, Hits, Doubles, Triples,");
					System.out.println("HomeRuns, RBI, StolenBases, CaughtStealing, Walks,");
					System.out.println("StrikeOuts, SacrificeHits, SacrificeFlies, HitByPitch,");
					System.out.println("Average, OnBase, Slugging, OPS, Payroll");
					System.out.println();
					String stat = scan.next();
					System.out.println("ASC - Ascending, DESC - Descending");
					String order = scan.next();
					
					/* check for valid input */
					while(!order.equals("ASC") && !order.equals("DESC")) {
						System.out.print("Please enter a valid input: ");
						order = scan.next();
					}
					
					db.teamSumData(stat, order);
					
					flag = false;
				} else if(selection == 6) {
					System.out.println("You selected \"Learn about free agent players\"");
					System.out.println();
					
					String query6 = "SELECT * "
							+ "FROM Player RIGHT JOIN PLAYS_FOR "
							+ "	ON Player.PlayerID = PLAYS_FOR.PlayerID "
							+ "WHERE ContractBegin IS NULL";
					ResultSet result6 = db.runQuery(query6);
					
					ArrayList<Player> freeAgents = new ArrayList<>();
					
					while(result6.next()) {
						int playerID = result6.getInt("PlayerID");
						String name = result6.getString("Name");
						String pos = result6.getString("Position");
						int age = result6.getInt("Age");
						int g = result6.getInt("Games");
						int ab = result6.getInt("AtBats");
						int r = result6.getInt("Runs");
						int h = result6.getInt("Hits");
						int doubles = result6.getInt("Doubles");
						int triples = result6.getInt("Triples");
						int hr = result6.getInt("HomeRuns");
						int rbi = result6.getInt("RBI");
						int sb = result6.getInt("StolenBases");
						int cs = result6.getInt("CaughtStealing");
						int bb = result6.getInt("Walks");
						int so = result6.getInt("StrikeOuts");
						int sh = result6.getInt("SacrificeHits");
						int sf = result6.getInt("SacrificeFlies");
						int hbp = result6.getInt("HitByPitch");
						double avg = result6.getDouble("Average");
						double obp = result6.getDouble("OnBase");
						double slg = result6.getDouble("Slugging");
						double ops = result6.getDouble("OPS");
						
						/* Use constructor to create a new object */
						Player p6 = new Player(playerID, name, pos, age, g, ab, r, h,
								doubles, triples, hr, rbi, sb, cs, bb, so, sh, sf, hbp,
								avg, obp, slg, ops);
						
						/* add it to our ArrayList */
						freeAgents.add(p6);
					}
					
					/* Foreach loop to print all the elements. */
					for(Player p6 : freeAgents) {
						System.out.println(p6);
					}
					
					flag = false;
				} else if(selection == 7) {
					System.out.println("You selected \"Investigate award winner effect\"");
					System.out.println("These teams had players that won awards last year. Did they feel the impact?");
					System.out.println();
					
					String query7a = "SELECT Player.Name AS AwardWinner, Team.Name AS Team, Attendance, Capacity, TicketPrice, Wins "
							+ "FROM Player JOIN PLAYS_FOR JOIN Team JOIN PLAYS_DURING JOIN Season "
							+ "	ON Player.PlayerID = PLAYS_FOR.PlayerID "
							+ "    AND PLAYS_FOR.TeamID = Team.TeamID "
							+ "    AND Team.TeamID = PLAYS_DURING.TeamID "
							+ "    AND PLAYS_DURING.Year = Season.Year "
							+ "WHERE Player.Name = (SELECT alMVP "
							+ "					FROM Season "
							+ "					WHERE Year = 2023) "
							+ "	AND Season.Year = 2023 "
							+ "UNION "
							+ "SELECT Player.Name AS AwardWinner, Team.Name AS Team, Attendance, Capacity, TicketPrice, Wins "
							+ "FROM Player JOIN PLAYS_FOR JOIN Team JOIN PLAYS_DURING JOIN Season "
							+ "	ON Player.PlayerID = PLAYS_FOR.PlayerID "
							+ "    AND PLAYS_FOR.TeamID = Team.TeamID "
							+ "    AND Team.TeamID = PLAYS_DURING.TeamID "
							+ "    AND PLAYS_DURING.Year = Season.Year "
							+ "WHERE Player.Name = (SELECT nlMVP "
							+ "					FROM Season "
							+ "					WHERE Year = 2023) "
							+ "	AND Season.Year = 2023 "
							+ "UNION "
							+ "SELECT Player.Name AS AwardWinner, Team.Name AS Team, Attendance, Capacity, TicketPrice, Wins "
							+ "FROM Player JOIN PLAYS_FOR JOIN Team JOIN PLAYS_DURING JOIN Season "
							+ "	ON Player.PlayerID = PLAYS_FOR.PlayerID "
							+ "    AND PLAYS_FOR.TeamID = Team.TeamID "
							+ "    AND Team.TeamID = PLAYS_DURING.TeamID "
							+ "    AND PLAYS_DURING.Year = Season.Year "
							+ "WHERE Player.Name = (SELECT alROTY "
							+ "					FROM Season "
							+ "					WHERE Year = 2023) "
							+ "	AND Season.Year = 2023 "
							+ "UNION "
							+ "SELECT Player.Name AS AwardWinner, Team.Name AS Team, Attendance, Capacity, TicketPrice, Wins "
							+ "FROM Player JOIN PLAYS_FOR JOIN Team JOIN PLAYS_DURING JOIN Season "
							+ "	ON Player.PlayerID = PLAYS_FOR.PlayerID "
							+ "    AND PLAYS_FOR.TeamID = Team.TeamID "
							+ "    AND Team.TeamID = PLAYS_DURING.TeamID "
							+ "    AND PLAYS_DURING.Year = Season.Year "
							+ "WHERE Player.Name = (SELECT nlROTY "
							+ "					FROM Season "
							+ "					WHERE Year = 2023) "
							+ "	AND Season.Year = 2023";
					ResultSet result7a = db.runQuery(query7a);
					db.awEffect(result7a);
					System.out.println();
										
					String query7b = "SELECT DISTINCT Team.Name AS Team, Attendance, Capacity, TicketPrice, Wins "
								+ "FROM Player JOIN PLAYS_FOR JOIN Team JOIN PLAYS_DURING JOIN Season " 
									+ "ON Player.PlayerID = PLAYS_FOR.PlayerID " 
									+ "AND PLAYS_FOR.TeamID = Team.TeamID "
									+ "AND Team.TeamID = PLAYS_DURING.TeamID " 
									+ "AND PLAYS_DURING.Year = Season.Year "
								+ "WHERE Season.Year = 2023";
					ResultSet result7b = db.runQuery(query7b);
					db.awEffectAll(result7b);					
					
					flag = false;
				} else {
					System.out.println(selection + " is not valid. Please enter a valid response.");
					selection = scan.nextInt();
				}
				
				System.out.println();
				System.out.println("Perform another action?");
				System.out.println("1 - Yes, 2 - No");
				int redo = scan.nextInt();
				if(redo != 1 && redo != 2) {
					System.out.println(redo + " is not a valid response. Please try again.");
					redo = scan.nextInt();
				} else if(redo == 1) {
					flag = true;
				}
			}
		} catch(SQLException e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Thank you for using my program!");
		
		/* Don't forget to disconnect! */
		db.disconnect();
	}

}
