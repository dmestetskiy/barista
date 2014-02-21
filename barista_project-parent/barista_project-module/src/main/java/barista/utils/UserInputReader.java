package barista.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputReader {

	
	public static String readCommand() {
		String command = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String line = reader.readLine();
			if (line.length() > 0) {
				command = line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return command;

	}
	
	public static boolean checkIfInteger(String command){
		boolean isInteger = false;
		
		try{
			Integer.valueOf(command);
			isInteger = true;
		}catch (Exception e) {
			
		}
		
		return isInteger;
	}
	
	public static boolean checkIfValidCommand(String command){
		boolean isValid = false;
		if (command.toLowerCase().equals(Constants.R)){
			isValid = true;
		}
		
		return isValid;
	}
}
