package org.com.deliveries.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {	
	
	public static final String NORTH = "Norte";
	public static final String SOUTH = "Sur";
	public static final String WEST = "Occidente";
	public static final String EAST = "Oriente";
	
	public static final Map<String, String> DIRECTIONS_MAP = getMapWithDirections();
	
	private Utils(){
		super();
    }
	
    private static Map<String, String> getMapWithDirections() {
		Map<String, String> map = new HashMap<>();
		map.put("N", NORTH);
		map.put("S", SOUTH);
		map.put("W", WEST);
		map.put("E", EAST);
		return map;
	}

	public static Properties getConfigurationProperties(String configurationFilePath) throws IOException {
		Properties properties = null;		
		try( InputStream inputStream = new FileInputStream(configurationFilePath) ){			
	        properties = new Properties();		       
	        properties.load(inputStream);	
	    } catch (IOException exception) {
	        throw exception;
	    } 
		return properties;
	}

	public static List<Path> getFilesByPath(Path path) throws IOException{
		List<Path> result;		
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk.filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return result;
	}

	public static List<Path> filterFilesByNameAndExtension(List<Path> inputFileList, String inputFileName,
			String inputFileExtension) {
		return inputFileList.stream().filter( inputFile ->
				inputFile.getFileName().toString().matches(inputFileName+"[0-9]+\\."+inputFileExtension)
			).map(inputFile -> inputFile).collect(Collectors.toList());
	}

	public static List<String> getInformationFromFile(Path inputFile) throws Exception {
		List<String> deliveryPaths = new ArrayList<>();
		BufferedReader buffredReader = null;	    
		InputStream inputStream = null;		
		try {			
			inputStream = new FileInputStream( inputFile.toFile() );
			buffredReader = new BufferedReader(new InputStreamReader(inputStream));
	        String line;
	        while( (line = buffredReader.readLine()) != null ){
	            deliveryPaths.add(line);
	        }
		} catch( Exception exception){
			throw exception;
		} finally {
		    if( inputStream != null ){
		        try {
		            inputStream.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    if( buffredReader!=null ) buffredReader.close();
		}
		return deliveryPaths;
	}

	public static String doReport(int x, int y, String currentDirection) {
		StringBuilder report = new StringBuilder();
		report.append("(")
			  .append(x)
			  .append(", ")
			  .append(y)
			  .append(") dirección ")
			  .append(DIRECTIONS_MAP.get(currentDirection));		
		return report.toString();
	}

	public static String getIdFromInputFileName(Path inputFile, String fileName, String extensionName) {
		String pattern = fileName+"(.*?)\\."+extensionName;
		Pattern mainPattern = Pattern.compile(pattern);
        Matcher mainMatcher = mainPattern.matcher(inputFile.getFileName().toString());            
        mainMatcher.find();
        return mainMatcher.group(1);	
	}

	public static int getDroneAvailabilyDeliveriesAboutConfiguration(String elasticConfiguration, int numberAvailabityDeliveries, int numberRequestedDeliveries) {
		int availabityDeliveries = 0;
		if( "ON".equals(elasticConfiguration) ){
			availabityDeliveries = numberRequestedDeliveries;
		} else {
			availabityDeliveries = (numberRequestedDeliveries <= numberAvailabityDeliveries) ? numberRequestedDeliveries : numberAvailabityDeliveries; 
		}
		return availabityDeliveries;
	}
	
}
