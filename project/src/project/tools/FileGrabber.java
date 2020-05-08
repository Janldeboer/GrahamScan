package project.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

/**
 * A file grabber to fetch the file stored at our specific location
 * 
 * @author jan
 *
 */
public class FileGrabber {

	/**
	 * Reads the file and return the representation as string
	 * 
	 * @return representative string
	 */
	public static String loadFileWithReader(){
				
		try  {
			FileReader myFileReader = new FileReader("src/project/resources/testData.csv");
			BufferedReader myBufferedReader = new BufferedReader(myFileReader);
			String fileAsString = myBufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
			return fileAsString;
			
		} catch(Exception e) {

		}

		return null;

	}
}
