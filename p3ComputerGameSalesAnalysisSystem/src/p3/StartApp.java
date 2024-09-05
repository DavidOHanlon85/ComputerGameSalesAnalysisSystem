package p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * PUT YOUR NAME HERE AND YOUR STUDENT NUMBER
 * 
 *
 */
public class StartApp {
	
	public static List<Game> copyForThread = new ArrayList<Game>();

	/**
	 * Entry point of program - no need to modify this method
	 * 
	 * @param args
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		showMenu();
	}

	// TODO modify readData method to populate List appropriately - method partially
	// completed already
	// TODO add static methods to this class as required to achieve tasks outlined
	// in menu
	// TODO modify showMenu method to add calls to new methods you write to
	// accomplish the tasks outlined in the menu

	/**
	 * Launches menu system which in turn calls appropriate methods based on user
	 * choices Partially implemented already requires updating to add calls to other
	 * methods written to achieve the tasks described
	 */
	public static void showMenu() {
		
		List<Game> gameSalesData = readData();

		System.out.println();
		Scanner scanner = new Scanner(System.in);
		int option;
		System.out.println("Video Game Sales Data Processing");
		do {
			System.out.println("1. (Re)read Data From File (use to restore sales data for example)");
			System.out.println("2. Display the number of Games in the Current List");
			System.out.println("3. Display full details for all Games in the current List");
			System.out.println(
					"4. Display summary details of the top 3 best selling Games in the European Region from the current List");
			System.out.println(
					"5. Display summary details of Platform Games in the List with total global sales of 0.2 million or more, sorted descending by total sales");
			System.out.println(
					"6. Display the name, genre and publisher of the game/games with the longest name in the current List");
			System.out.println(
					"7. Multiplatform: Display summary details sorted by name of any games available for more than one platform");
			System.out.println(
					"8. Display in descending sales order, summary details of any games which sold only in the Japanese market");
			System.out.println(
					"9. Update all Games in the list so platform is 'Any' and combine data for any duplicates into single objects");
			System.out.println("10. Quit");
			System.out.println("Enter option ...");
			option = scanner.nextInt();
			System.out.println();
			switch (option) {

			case 1:
				gameSalesData = readData();
				break;
			case 2:
				System.out.println("Number of games in current list: " + gameSalesData.size());
				// TODO add required method call(s)
				break;
			case 3:
				displayAllItemsInList(gameSalesData);
				// TODO add required method call(s)
				break;
			case 4:
				Collections.sort(gameSalesData, new CompareByEuroSales().reversed());
				printSummaryOfItemsInList(XTopInList(gameSalesData, 3));
				break;
			case 5:
				Collections.sort(gameSalesData, new CompareByTotalSales().reversed());
				printSummaryOfItemsInList(filterBySales(filterByGenre(gameSalesData, Genre.PLATFORM), 0.2));
				break;
			case 6:
				Game game = Collections.max(gameSalesData, new CompareByNameLength());

				String line = String.format("%s %s %s", game.getName(), game.getGenre(), game.getPublisher());
				System.out.println(line);

				// TODO add required method call(s)
				break;
			case 7:

				Collections.sort(gameSalesData, new CompareByName());

				Map<String, Integer> multiPlatformGames = mapMultiPlatformToFrequency(gameSalesData);

				displaySummariesForMultiplatformGames(gameSalesData, multiPlatformGames);

				// TODO add required method call(s)
				break;
			case 8:
				Collections.sort(gameSalesData, new CompareByJapaneseSales().reversed());
				displaySummariesForList(soldOnlyInJapan(gameSalesData));
				// TODO add required method call(s)
				break;
			case 9:
				settingAllPlatformsToAny(gameSalesData, "Any");

				Map<String, Game> uniqueGames = new HashMap<String, Game>();

				for (Game g : gameSalesData) {
					String gameName = g.getName();

					if (uniqueGames.containsKey(gameName)) {
						Game existingGame = uniqueGames.get(gameName);
						existingGame.setNorthAmericanSales(existingGame.getNorthAmericanSales() + g.getNorthAmericanSales());
						existingGame.setEuropeanUnionSales(existingGame.getEuropeanUnionSales() + g.getEuropeanUnionSales());
						existingGame.setJapaneseMarketSales(existingGame.getJapaneseMarketSales() + g.getJapaneseMarketSales());
						existingGame.setOtherMarketsSales(existingGame.getOtherMarketsSales() + g.getOtherMarketsSales());
					} else {
						uniqueGames.put(gameName, g);
					}
					
				}
				
				gameSalesData.clear();
				gameSalesData.addAll(uniqueGames.values());

				// mappingGamesToSales(gameSalesData);
				// removingDuplicates(gameSalesData);
				Collections.sort(gameSalesData, new CompareByName());
				displaySummariesForList(gameSalesData);
				break;
			case 10:
				System.out.println("Quitting");
				break;
			default:
				System.out.println("Try options again...");
			}
		} while (option != 10);
		scanner.close();
	}

	/**
	 * This method removes all duplicates, clear the initial array list and adds
	 * them back in
	 * 
	 * @param gameSalesData
	 */
	public static void removingDuplicates(List<Game> gameSalesData) {

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		Set<Game> duplicatesRemoved = new HashSet<Game>(gameSalesData);

		gameSalesData.clear();
		gameSalesData.addAll(duplicatesRemoved);
	}

	/**
	 * This method maps Region sales with name
	 * 
	 * @param gameSalesData
	 */

	public static void mappingGamesToSales(List<Game> gameSalesData) {
		Map<String, Double> addingNASales = new HashMap<String, Double>();

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		double currentNASales;
		double newNASales;

		for (Game g : gameSalesData) {

			if (addingNASales.containsKey(g.getName())) {
				currentNASales = addingNASales.get(g.getName());
				newNASales = g.getNorthAmericanSales();
				addingNASales.put(g.getName(), currentNASales + newNASales);

			} else {
				addingNASales.put(g.getName(), g.getNorthAmericanSales());
			}

		}

		for (Game g : gameSalesData) {
			if (addingNASales.containsKey(g.getName())) {
				g.setNorthAmericanSales(addingNASales.get(g.getName()));
			}

		}

		Map<String, Double> addingEUSales = new HashMap<String, Double>();

		double currentEUSales;
		double newEUSales;

		for (Game g : gameSalesData) {

			if (addingEUSales.containsKey(g.getName())) {
				currentEUSales = addingEUSales.get(g.getName());
				newEUSales = g.getEuropeanUnionSales();
				addingEUSales.put(g.getName(), currentEUSales + newEUSales);

			} else {
				addingEUSales.put(g.getName(), g.getEuropeanUnionSales());
			}

		}

		for (Game g : gameSalesData) {
			if (addingEUSales.containsKey(g.getName())) {
				g.setEuropeanUnionSales(addingEUSales.get(g.getName()));
			}

		}

		Map<String, Double> addingJPSales = new HashMap<String, Double>();

		double currentJPSales;
		double newJPSales;

		for (Game g : gameSalesData) {

			if (addingJPSales.containsKey(g.getName())) {
				currentJPSales = addingJPSales.get(g.getName());
				newJPSales = g.getJapaneseMarketSales();
				addingJPSales.put(g.getName(), currentJPSales + newJPSales);

			} else {
				addingJPSales.put(g.getName(), g.getJapaneseMarketSales());
			}

		}

		for (Game g : gameSalesData) {
			if (addingJPSales.containsKey(g.getName())) {
				g.setJapaneseMarketSales(addingJPSales.get(g.getName()));
			}

		}

		Map<String, Double> addingOMSales = new HashMap<String, Double>();

		double currentOMSales;
		double newOMSales;

		for (Game g : gameSalesData) {

			if (addingOMSales.containsKey(g.getName())) {
				currentOMSales = addingOMSales.get(g.getName());
				newOMSales = g.getOtherMarketsSales();
				addingOMSales.put(g.getName(), currentOMSales + newOMSales);

			} else {
				addingOMSales.put(g.getName(), g.getOtherMarketsSales());
			}

		}

		for (Game g : gameSalesData) {
			if (addingOMSales.containsKey(g.getName())) {
				g.setOtherMarketsSales(addingOMSales.get(g.getName()));
			}

		}
	}

	/**
	 * This sets platforms in list to target
	 * 
	 * @param gameSalesData
	 * @param target
	 * @throws IllegalArgumentException
	 */
	public static void settingAllPlatformsToAny(List<Game> gameSalesData, String target)
			throws IllegalArgumentException {

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		for (Game g : gameSalesData) {
			g.setPlatform(target);
		}
	}

	/**
	 * Displays summary details for all games in a list
	 * 
	 * @param list
	 */
	public static void displaySummariesForList(List<Game> list) throws IllegalArgumentException {

		if (list == null || list.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		for (Game g : list) {
			g.displaySummaryDetails();
			System.out.println();
		}

	}

	/**
	 * Returns as a list those games that were only sold in Japan
	 * 
	 * @param gameSalesData
	 * @return
	 */
	public static List<Game> soldOnlyInJapan(List<Game> gameSalesData) throws IllegalArgumentException {

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		List<Game> results = new ArrayList<Game>();

		for (Game g : gameSalesData) {
			if (g.getNorthAmericanSales() == 0.0 && g.getEuropeanUnionSales() == 0.0 && g.getOtherMarketsSales() == 0
					&& g.getJapaneseMarketSales() > 0.0) {
				results.add(g);
			}

		}

		return results;

	}

	/**
	 * @param gameSalesData
	 * @param multiPlatformGames
	 */
	public static void displaySummariesForMultiplatformGames(List<Game> gameSalesData,
			Map<String, Integer> multiPlatformGames) throws IllegalArgumentException {

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		for (Game g : gameSalesData) {
			if (multiPlatformGames.get(g.getName()) > 1) {
				g.displaySummaryDetails();
				System.out.println();
			}
		}
	}

	/**
	 * This method maps multiplatform to frequency
	 * 
	 * @param gameSalesData
	 * @return
	 * @throws
	 */
	public static Map<String, Integer> mapMultiPlatformToFrequency(List<Game> gameSalesData)
			throws IllegalArgumentException {

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		Map<String, Integer> multiPlatformGames = new TreeMap<String, Integer>();

		for (Game g : gameSalesData) {
			if (multiPlatformGames.containsKey(g.getName())) {
				int freq = multiPlatformGames.get(g.getName());
				multiPlatformGames.put(g.getName(), freq + 1);
			} else {
				multiPlatformGames.put(g.getName(), 1);
			}
		}
		return multiPlatformGames;
	}

	/**
	 * This method filters by sales
	 * 
	 * @param gameSalesData
	 * @param threshold
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static List<Game> filterBySales(List<Game> gameSalesData, double threshold)
			throws IllegalArgumentException {

		List<Game> results = new ArrayList<Game>();

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		for (Game g : gameSalesData) {
			if (g.getGlobalSales() >= 0.2) {
				results.add(g);
			}
		}

		return results;

	}

	/**
	 * This method filters list by Genre
	 * 
	 * @param gameSalesData
	 * @param platform
	 * @return
	 */
	public static List<Game> filterByGenre(List<Game> gameSalesData, Genre platform) throws IllegalArgumentException {
		List<Game> results = new ArrayList<Game>();

		if (gameSalesData == null || gameSalesData.isEmpty()) {
			throw new IllegalArgumentException("AL NULL OF EMPTY");
		}

		for (Game g : gameSalesData) {
			if (g.getGenre() == Genre.PLATFORM) {
				results.add(g);
			}
		}

		return results;

	}

	/**
	 * This method prints summaries of a list
	 * 
	 * @param input
	 */
	public static void printSummaryOfItemsInList(List<Game> input) {
		for (Game g : input) {
			g.displaySummaryDetails();
			System.out.println();
		}

	}

	/**
	 * This method return a list with the top X requested number of places
	 * 
	 * @param gameSalesData
	 * @param target
	 * @return
	 */
	public static List<Game> XTopInList(List<Game> gameSalesData, int target) {

		List<Game> results = new ArrayList<Game>();

		for (int i = 0; i < target; i++) {
			results.add(gameSalesData.get(i));
		}

		return results;

	}

	/**
	 * This method displays all items in the list to console
	 * 
	 * @param gameSalesData
	 */
	public static void displayAllItemsInList(List<Game> gameSalesData) {

		for (Game g : gameSalesData) {
			g.displayAllDetails();
			System.out.println();
		}

	}

	/**
	 * Reads in the data from the provided csv and returns a list of Game objects
	 * for further processing - requires updating for full functionality
	 */
	public static List<Game> readData() {

		List<Game> listFromFile = new ArrayList<Game>();

		File file = new File("videogamesalesdata.csv"); // hard coded to specific file

		// try with resources - auto closes reader resources when finished/exception
		// occurs
		try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr);) {

			String line = reader.readLine(); // discard header
			line = reader.readLine(); // read first line

			while (line != null) {

				// TODO Code to process current line

				try {
					String[] splitDetails = line.split(",");

					Game g = createGame(splitDetails);

					// TODO and add to list
					listFromFile.add(g);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				line = reader.readLine();// attempt to read next line and loop again
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (Exception e) {
			System.out.println("Exception occured");
			System.out.println(listFromFile.size() + " lines read successfully");
			System.out.println(e.getMessage());
		}
		System.out.println(listFromFile.size() + " lines read successfully");
		return listFromFile;
	}

	/**
	 * This method creates a Game object
	 * 
	 * @param splitDetails
	 * @return
	 * @throws Exception
	 */
	public static Game createGame(String[] splitDetails) throws Exception {
		Game g = new Game();

		g.setName(splitDetails[0]);

		g.setPlatform(splitDetails[1]);

		g.setRealeaseYear(Integer.parseInt(splitDetails[2]));

//		if (splitDetails[3].equalsIgnoreCase("Role-Playing")) {
//			g.setGenre(Genre.ROLE_PLAYING);
//		} else if (splitDetails[3].equalsIgnoreCase("Sports")) {
//			g.setGenre(Genre.SPORTS);
//		} else if (splitDetails[3].equalsIgnoreCase("Shooter")) {
//			g.setGenre(Genre.SHOOTER);
//		} else if (splitDetails[3].equalsIgnoreCase("Platform")) {
//			g.setGenre(Genre.PLATFORM);
//		} else {
//			throw new Exception("INVALID PLATFORM");
//		}
		
		if (splitDetails[3].equalsIgnoreCase("Role-Playing")) {
			g.setGenre(Genre.ROLE_PLAYING);
		} else if (!splitDetails[3].equalsIgnoreCase("Role-Playing")) {
			g.setGenre(Genre.valueOf(splitDetails[3].toUpperCase()));
		} else {
			throw new Exception("INVALID PLATFORM");
		}

		g.setPublisher(splitDetails[4]);

		g.setNorthAmericanSales(Double.parseDouble(splitDetails[5]));
		g.setEuropeanUnionSales(Double.parseDouble(splitDetails[6]));
		g.setJapaneseMarketSales(Double.parseDouble(splitDetails[7]));
		g.setOtherMarketsSales(Double.parseDouble(splitDetails[8]));
		return g;
	}

}
