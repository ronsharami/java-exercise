package Files_Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	
	public String readFile(String sourceFilePath) throws IOException {
		String line;
		String result;
		FileReader sourceFile;
		BufferedReader br = null;
		StringBuilder sb;
		try {
			sourceFile = new FileReader(sourceFilePath);

			br = new BufferedReader(sourceFile);
			sb = new StringBuilder();
			line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
				if (line != null)
					sb.append(System.lineSeparator());

			}
			result = sb.toString();
		} 
		catch (FileNotFoundException ex) {
			throw ex;
		} 
		finally {
			if (br != null) {
				br.close();
			}
			
		}
		return result;
	}
	
	public void writeToFile(String str,String sourceFilePath)
			throws IOException {
		File toFile;
    	FileWriter outFileWriter;
    	BufferedWriter bw;
    	
    	toFile = new File(sourceFilePath);
	
		if(!toFile.exists())
		{
			toFile.createNewFile();
		}
		outFileWriter = new FileWriter(toFile);
		bw = new BufferedWriter(outFileWriter);
		bw.write(str);
		bw.close();
		outFileWriter.close();
	}




	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) {
	        	return false;
	        }
	    }
	    return true;
	}
}
