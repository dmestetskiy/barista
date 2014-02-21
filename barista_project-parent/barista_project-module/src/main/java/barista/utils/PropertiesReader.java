package barista.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesReader {

	public static Set<Map.Entry<Object, Object>> readProperties(File fileIn){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileIn));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties.entrySet();
	}
	
	public static String getPropertyName(String propertyName, String delimiter){
		List<String> propertySplit = (List<String>) Arrays.asList(propertyName.split(delimiter));
		StringBuilder sb = new StringBuilder();
		for (int i =0; i < propertySplit.size();i++){
			if (i+1==propertySplit.size()){
				sb.append(toCapitalize(propertySplit.get(i)));
			}else{
				sb.append(toCapitalize(propertySplit.get(i))+" ");
			}
		}
		return sb.toString();
	}
	
	private static String toCapitalize (String stringIn){
		String returnStrnig = stringIn;
		if (stringIn != null && stringIn.length() > 0){
			returnStrnig = returnStrnig.substring(0,1).toUpperCase()+returnStrnig.substring(1);
		}
		return returnStrnig;
	}
	
	public static String getFormatValue(Double value){
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(value);
	}
}
