package p3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import p3.Game;
import p3.Genre;

class TestGame {

	// input data

	String validNameLow, validNameHigh, invalidNameLow, invalidNameHigh;

	String validPlatformName, invalidPlatformName;

	int validReleaseYearEarly, validReleaseYearLate, invalidReleaseYear;

	Genre platfrom, rolePlaying, shooter, sports;

	String validPublisherLow, validPublisherHigh, invalidPublisherLow, invalidPublisherHigh;

	double validSalesLow, validSalesHigh, invalidSalesLow;

	Game g;

	@BeforeEach
	void setUp() throws Exception {

		validNameLow = "X";
		validNameHigh = "A".repeat(80);
		invalidNameLow = "";
		invalidNameHigh = "a".repeat(81);

		validPlatformName = "3DS";
		invalidPlatformName = "A".repeat(40);

		validReleaseYearEarly = 1980;
		validReleaseYearLate = 2000;
		invalidReleaseYear = 1979;

		platfrom = Genre.PLATFORM;
		rolePlaying = Genre.ROLE_PLAYING;
		shooter = Genre.SHOOTER;
		sports = Genre.SPORTS;

		validPublisherLow = "X";
		validPublisherHigh = "A".repeat(40);
		invalidPublisherLow = "";
		invalidPublisherHigh = "A".repeat(41);

		validSalesLow = 0.2;
		validSalesHigh = 10.2;
		invalidSalesLow = -3;

		g = new Game(validNameHigh, validPlatformName, validReleaseYearLate, platfrom, validPublisherHigh,
				validSalesHigh, validSalesHigh, validSalesHigh, validSalesHigh);

	}

	@Test
	void testGameConstructorNoArgs() {
		assertNotNull(g);
	}

	@Test
	void testGameConstructorWithArgsValid() {
		assertEquals(validNameHigh, g.getName());
		assertEquals(validPlatformName, g.getPlatform());
		assertEquals(validReleaseYearLate, g.getRealeaseYear());
		assertEquals(platfrom, g.getGenre());
		assertEquals(validPublisherHigh, g.getPublisher());
		assertEquals(validSalesHigh, g.getNorthAmericanSales());
		assertEquals(validSalesHigh, g.getEuropeanUnionSales());
		assertEquals(validSalesHigh, g.getJapaneseMarketSales());
		assertEquals(validSalesHigh, g.getOtherMarketsSales());
		
	}
	
	@Test
	void testGameConstructorWithArgsInValid() {
		
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setName(invalidNameLow);
		});
		
		assertEquals("INVALID NAME", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setName(invalidNameHigh);
		});
		
		assertEquals("INVALID NAME", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setPlatform(invalidPlatformName);
		});
		
		assertEquals("INVALID PLATFORM", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setRealeaseYear(invalidReleaseYear);
		});
		
		assertEquals("INVALID RELEASE YEAR", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setGenre(null);
		});
		
		assertEquals("INVALID GENRE", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setPublisher(invalidPublisherLow);
		});
		
		assertEquals("INVALID PUBLISHER", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setPublisher(invalidPublisherHigh);
		});
		
		assertEquals("INVALID PUBLISHER", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setNorthAmericanSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setEuropeanUnionSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setJapaneseMarketSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setOtherMarketsSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
		
	}

	@Test
	void testSetGetNameValid() {
		g.setName(validNameLow);
		assertEquals(validNameLow, g.getName());
		
		g.setName(validNameHigh);
		assertEquals(validNameHigh, g.getName());
		
		
	}

	@Test
	void testSetGetNameInvalid() {
		
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setName(invalidNameLow);
		});
		
		assertEquals("INVALID NAME", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setName(invalidNameHigh);
		});
		
		assertEquals("INVALID NAME", exp.getMessage());
	}

	@Test
	void testSetGetPlatformValid() {
		g.setPlatform(validPlatformName);
		assertEquals(validPlatformName, g.getPlatform());
	
	}

	@Test
	void testSetGetPlatformInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setPlatform(invalidPlatformName);
		});
		
		assertEquals("INVALID PLATFORM", exp.getMessage());
	}

	@Test
	void testGetRealeaseYear() {
		g.setRealeaseYear(validReleaseYearEarly);
		assertEquals(validReleaseYearEarly, g.getRealeaseYear());
		
		g.setRealeaseYear(validReleaseYearLate);
		assertEquals(validReleaseYearLate, g.getRealeaseYear());
	
	}

	@Test
	void testSetGetRealeaseYearInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setRealeaseYear(invalidReleaseYear);
		});
		
		assertEquals("INVALID RELEASE YEAR", exp.getMessage());
	}

	@Test
	void testSetGetGenreValid() {
		g.setGenre(Genre.PLATFORM);
		assertEquals(platfrom, g.getGenre());
		
		g.setGenre(Genre.ROLE_PLAYING);
		assertEquals(rolePlaying, g.getGenre());
		
		g.setGenre(Genre.SHOOTER);
		assertEquals(shooter, g.getGenre());
		
		g.setGenre(Genre.SPORTS);
		assertEquals(sports, g.getGenre());
	}

	@Test
	void testSetGetGenreInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setGenre(null);
		});
		
		assertEquals("INVALID GENRE", exp.getMessage());
	}

	@Test
	void testSetGetPublisherValid() {
		g.setPublisher(validPublisherLow);
		assertEquals(validPublisherLow, g.getPublisher());
		
		g.setPublisher(validPublisherHigh);
		assertEquals(validPublisherHigh, g.getPublisher());
	
	}

	@Test
	void testSetGetPublisherInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setPublisher(invalidPublisherLow);
		});
		
		assertEquals("INVALID PUBLISHER", exp.getMessage());
		
		exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setPublisher(invalidPublisherHigh);
		});
		
		assertEquals("INVALID PUBLISHER", exp.getMessage());
	}

	@Test
	void testSetGetNorthAmericanSalesValid() {
		g.setNorthAmericanSales(validSalesLow);
		assertEquals(validSalesLow, g.getNorthAmericanSales());
		
		g.setNorthAmericanSales(validSalesHigh);
		assertEquals(validSalesHigh, g.getNorthAmericanSales());
	}

	@Test
	void testSetNorthAmericanSales() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setNorthAmericanSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
	
	}

	@Test
	void testSetGetEuropeanUnionSalesValid() {
		g.setEuropeanUnionSales(validSalesLow);
		assertEquals(validSalesLow, g.getEuropeanUnionSales());
		
		g.setEuropeanUnionSales(validSalesHigh);
		assertEquals(validSalesHigh, g.getEuropeanUnionSales());
	}

	@Test
	void testSetGetEuropeanUnionSalesInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setEuropeanUnionSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
	}

	@Test
	void testSetGetJapaneseMarketSalesValid() {
		g.setJapaneseMarketSales(validSalesLow);
		assertEquals(validSalesLow, g.getJapaneseMarketSales());
		
		g.setJapaneseMarketSales(validSalesHigh);
		assertEquals(validSalesHigh, g.getJapaneseMarketSales());
	}

	@Test
	void testGetSetJapaneseMarketSalesInvalid() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setJapaneseMarketSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
	}

	@Test
	void testSetGetOtherMarketsSalesValid() {
		g.setOtherMarketsSales(validSalesLow);
		assertEquals(validSalesLow, g.getOtherMarketsSales());
		
		g.setOtherMarketsSales(validSalesHigh);
		assertEquals(validSalesHigh, g.getOtherMarketsSales());
	}

	@Test
	void testSetOtherMarketsSales() {
		Exception exp = assertThrows(IllegalArgumentException.class, ()->{
			g.setOtherMarketsSales(invalidSalesLow);
		});
		
		assertEquals("INVALID SALES", exp.getMessage());
	}

	@Test
	void testGetGlobalSales() {
		g.setNorthAmericanSales(1.2);
		g.setEuropeanUnionSales(1.3);
		g.setJapaneseMarketSales(1.0);
		g.setOtherMarketsSales(2.0);
		
		assertEquals(5.5, g.getGlobalSales());
	}

	@Test
	void testGetBestMarket() {
		g.setNorthAmericanSales(1.2);
		g.setEuropeanUnionSales(1.3);
		g.setJapaneseMarketSales(1.0);
		g.setOtherMarketsSales(2.0);
		
		assertEquals("Other", g.getBestMarket());
	}

}
