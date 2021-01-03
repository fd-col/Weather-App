/**
 * 
 */
package it.univpm.progetto.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author colleluori
 * @author camplese
 *
 */
public class AppidFromFile {

	private String appid;
	
	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * costruttore di default
	 */
	public AppidFromFile() {}
	
	/**
	 * prende l'API key da un file e la inserisce nell'attributo "appid"
	 * @return the API key
	 * @throws FileNotFoundException
	 */
/**
	public String appidFromFile() throws FileNotFoundException {
		
		char[] appidChar = new char[33];
		try {
			int next;
			int i=0;
			BufferedReader reader = new BufferedReader(new FileReader("appid.txt"));
			do {
				next = reader.read();
				if(next!=-1) {
					char c = (char) next;
					appidChar[i] = c;
					i++;
				}
			}while(next!=-1);
			 //converto appidChar da char[] ad una String
			
			reader.close();
		}catch(IOException e) {
			System.out.println(e);
		}
		return new String(appidChar);
	}
*/
}
