/**
 * 
 */
package p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author - Daithi O hAnluain - 15621049
 */
public class Game {

	private static final int MAX_NAME_LENGTH = 80;

	private static final int MIN_NAME_LENGTH = 1;

	private static final int MAX_PUBLISHSER_LENGTH = 40;

	private static final int MIN_PUBLISHSER_LENGTH = 1;
	// Instance Variables

	private String name;
	private String platform;
	private int realeaseYear;
	private Genre genre;
	private String publisher;
	private double northAmericanSales;
	private double europeanUnionSales;
	private double japaneseMarketSales;
	private double otherMarketsSales;

	// Constructors

	/**
	 * Default constructor
	 */
	public Game() {

	}

	/**
	 * 
	 * @param name
	 * @param platform
	 * @param realeaseYear
	 * @param genre
	 * @param publisher
	 * @param northAmericanSales
	 * @param europeanUnionSales
	 * @param japaneseMarketSales
	 * @param otherMarketsSales
	 * @throws IllegalArgumentException
	 */
	public Game(String name, String platform, int realeaseYear, Genre genre, String publisher,
			double northAmericanSales, double europeanUnionSales, double japaneseMarketSales, double otherMarketsSales)
			throws IllegalArgumentException {
		super();
		this.setName(name);
		this.setPlatform(platform);
		this.setRealeaseYear(realeaseYear);
		this.setGenre(genre);
		this.setPublisher(publisher);
		this.setNorthAmericanSales(northAmericanSales);
		this.setEuropeanUnionSales(europeanUnionSales);
		this.setJapaneseMarketSales(japaneseMarketSales);
		this.setOtherMarketsSales(otherMarketsSales);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name.length() >= MIN_NAME_LENGTH && name.length() <= MAX_NAME_LENGTH) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("INVALID NAME");
		}

	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * 
	 * @param platform
	 * @throws IllegalArgumentException
	 */
	public void setPlatform(String platform) throws IllegalArgumentException {
		if (platform.equalsIgnoreCase("3DS") || platform.equalsIgnoreCase("PC") || platform.equalsIgnoreCase("PS3")
				|| platform.equalsIgnoreCase("PS4") || platform.equalsIgnoreCase("PSV")
				|| platform.equalsIgnoreCase("WiiU") || platform.equalsIgnoreCase("X360")
				|| platform.equalsIgnoreCase("XOne") || platform.equalsIgnoreCase("Any")) {
			this.platform = platform;
		} else {
			throw new IllegalArgumentException("INVALID PLATFORM");
		}

	}

	/**
	 * @return the realeaseYear
	 */
	public int getRealeaseYear() {
		return realeaseYear;
	}

	/**
	 * 
	 * @param realeaseYear
	 * @throws IllegalArgumentException
	 */
	public void setRealeaseYear(int realeaseYear) throws IllegalArgumentException {
		if (realeaseYear >= 1980) {
			this.realeaseYear = realeaseYear;
		} else {
			throw new IllegalArgumentException("INVALID RELEASE YEAR");
		}

	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * 
	 * @param genre
	 * @throws IllegalArgumentException
	 */
	public void setGenre(Genre genre) throws IllegalArgumentException {
		if (genre == null) {
			throw new IllegalArgumentException("INVALID GENRE");
		} else {
			this.genre = genre;
		}

	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * 
	 * @param publisher
	 * @throws IllegalArgumentException
	 */
	public void setPublisher(String publisher) throws IllegalArgumentException {
		if (publisher.length() >= MIN_PUBLISHSER_LENGTH && publisher.length() <= MAX_PUBLISHSER_LENGTH) {
			this.publisher = publisher;
		} else {
			throw new IllegalArgumentException("INVALID PUBLISHER");
		}

	}

	/**
	 * @return the northAmericanSales
	 */
	public double getNorthAmericanSales() {
		return northAmericanSales;
	}

	/**
	 * 
	 * @param northAmericanSales
	 * @throws IllegalArgumentException
	 */
	public void setNorthAmericanSales(double northAmericanSales) throws IllegalArgumentException {
		if (northAmericanSales >= 0) {
			this.northAmericanSales = northAmericanSales;
		} else {
			throw new IllegalArgumentException("INVALID SALES");
		}

	}

	/**
	 * @return the europeanUnionSales
	 */
	public double getEuropeanUnionSales() {
		return europeanUnionSales;
	}

	/**
	 * 
	 * @param europeanUnionSales
	 * @throws IllegalArgumentException
	 */
	public void setEuropeanUnionSales(double europeanUnionSales) throws IllegalArgumentException {
		if (europeanUnionSales >= 0) {
			this.europeanUnionSales = europeanUnionSales;
		} else {
			throw new IllegalArgumentException("INVALID SALES");
		}

	}

	/**
	 * @return the japaneseMarketSales
	 */
	public double getJapaneseMarketSales() {
		return japaneseMarketSales;
	}

	/**
	 * @param japaneseMarketSales the japaneseMarketSales to set
	 */
	public void setJapaneseMarketSales(double japaneseMarketSales) throws IllegalArgumentException {
		if (japaneseMarketSales >= 0) {
			this.japaneseMarketSales = japaneseMarketSales;
		} else {
			throw new IllegalArgumentException("INVALID SALES");
		}
	}

	/**
	 * @return the otherMarketsSales
	 */
	public double getOtherMarketsSales() {
		return otherMarketsSales;
	}

	/**
	 * @param otherMarketsSales the otherMarketsSales to set
	 */
	public void setOtherMarketsSales(double otherMarketsSales) throws IllegalArgumentException {
		if (otherMarketsSales >= 0) {
			this.otherMarketsSales = otherMarketsSales;
		} else {
			throw new IllegalArgumentException("INVALID SALES");
		}

	}

	// Methods

	public double getGlobalSales() {

		double globalSales = northAmericanSales + europeanUnionSales + japaneseMarketSales + otherMarketsSales;

		return globalSales;

	}

	public String getBestMarket() {

		List<Double> markets = new ArrayList<Double>();
		Collections.addAll(markets, northAmericanSales, europeanUnionSales, japaneseMarketSales, otherMarketsSales);

		int maxRegion = markets.indexOf(Collections.max(markets));

		String toReturn = "";

		switch (maxRegion) {
		case 0:
			toReturn = "North America";
		case 1:
			toReturn = "Europe";
		case 2:
			toReturn = "Japan";
		case 3:
			toReturn = "Other";
		}

		return toReturn;

	}

	public void displayAllDetails() {

		System.out.println("name:             " + getName());
		System.out.println("platform:         " + getPlatform());
		System.out.println("release:          " + getRealeaseYear());
		System.out.println("genre:            " + getGenre());
		System.out.println("publisher:        " + getPublisher());
		System.out.println("Sales             ");
		System.out.printf("North America:    %.2f\n", getNorthAmericanSales());
		System.out.printf("Europe:           %.2f\n", getEuropeanUnionSales());
		System.out.printf("Japan:            %.2f\n", getJapaneseMarketSales());
		System.out.printf("Other:            %.2f\n", getOtherMarketsSales());
		System.out.printf("Total:            %.2f\n", getGlobalSales());
	}

	public void displaySummaryDetails() {
		String line = String.format("%s - %s (%d)", getName(), getPlatform(), getRealeaseYear());
		String line2 = String.format("NA: %.2f | EU: %.2f | JP: %.2f | OTH: %.2f | Total: %.2f",
				getNorthAmericanSales(), getEuropeanUnionSales(), getJapaneseMarketSales(), getOtherMarketsSales(),
				getGlobalSales());
		System.out.println(line);
		System.out.println(line2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(europeanUnionSales, genre, japaneseMarketSales, name, northAmericanSales, otherMarketsSales,
				platform, publisher, realeaseYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Double.doubleToLongBits(europeanUnionSales) == Double.doubleToLongBits(other.europeanUnionSales)
				&& genre == other.genre
				&& Double.doubleToLongBits(japaneseMarketSales) == Double.doubleToLongBits(other.japaneseMarketSales)
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(northAmericanSales) == Double.doubleToLongBits(other.northAmericanSales)
				&& Double.doubleToLongBits(otherMarketsSales) == Double.doubleToLongBits(other.otherMarketsSales)
				&& Objects.equals(platform, other.platform) && Objects.equals(publisher, other.publisher)
				&& realeaseYear == other.realeaseYear;
	}
	
	

}
