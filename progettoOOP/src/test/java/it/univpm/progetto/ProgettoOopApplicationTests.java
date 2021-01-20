package it.univpm.progetto;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.TestCase;

import it.univpm.progetto.configuration.ReaderFromFile;
import it.univpm.progetto.parser.CurrentWeatherParser;

/**
 * classe che si occupa di effettuare i test
 * @author colleluori
 * @author camplese
 *
 */
@SpringBootTest
class ProgettoOopApplicationTests extends TestCase {
	
	private ReaderFromFile readerFromFile;
	private CurrentWeatherParser currentWeatherParser;
	
	
	@BeforeEach
	protected void setUp() throws Exception {
		readerFromFile = new ReaderFromFile();
		currentWeatherParser = new CurrentWeatherParser("Test");
	}
	
	@AfterEach
	protected void tearDown() throws Exception {}
	
	/**
	 * test per verificare se il nome del file viene ritornato correttamente
	 * (affinchè il test funzioni correttamente, cambiare il primo flag)
	 */
	@Test
	public void test1() {		
		assertEquals("dataset/dati-storici-Ortona.json", readerFromFile.nomeFile("Ortona", true, false));
	}
	
	/**
	 * test che verifica se il ritorno del metodo appidFromFile() è corretto
	 * @throws FileNotFoundException
	 */
	@Test
	void test2() throws FileNotFoundException {
		assertEquals("9adf71fdfda989d29e27f8c71fac0d03", currentWeatherParser.appidFromFile() );
	}
	
}
