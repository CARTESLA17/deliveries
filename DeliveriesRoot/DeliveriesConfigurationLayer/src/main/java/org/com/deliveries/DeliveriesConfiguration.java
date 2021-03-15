package org.com.deliveries;

public class DeliveriesConfiguration {
	
	private static int numberDrones;
	private static String inputFilePath = "";
	private static String outPutFilePath = "";
	private static String inputFileName = "";
	private static String inputFileExtension = "";
	private static String outPutFileName = "";
	private static String outPutFileExtension = "";
	private static String elasticConfiguration = "OFF";
	private static int numberAvailabilitiesStreets;
	private static int numberDeliveriesByDroneInOneOutput;
	
	private DeliveriesConfiguration(){
		super();
    }

	public static int getNumberDrones() {
		return numberDrones;
	}

	public static void setNumberDrones(int numberDrones) {
		DeliveriesConfiguration.numberDrones = numberDrones;
	}

	public static String getInputFilePath() {
		return inputFilePath;
	}

	public static void setInputFilePath(String inputFilePath) {
		DeliveriesConfiguration.inputFilePath = inputFilePath;
	}

	public static String getOutPutFilePath() {
		return outPutFilePath;
	}

	public static void setOutPutFilePath(String outPutFilePath) {
		DeliveriesConfiguration.outPutFilePath = outPutFilePath;
	}

	public static String getInputFileName() {
		return inputFileName;
	}

	public static void setInputFileName(String inputFileName) {
		DeliveriesConfiguration.inputFileName = inputFileName;
	}

	public static String getInputFileExtension() {
		return inputFileExtension;
	}

	public static void setInputFileExtension(String inputFileExtension) {
		DeliveriesConfiguration.inputFileExtension = inputFileExtension;
	}

	public static String getOutPutFileName() {
		return outPutFileName;
	}

	public static void setOutPutFileName(String outPutFileName) {
		DeliveriesConfiguration.outPutFileName = outPutFileName;
	}

	public static String getOutPutFileExtension() {
		return outPutFileExtension;
	}

	public static void setOutPutFileExtension(String outPutFileExtension) {
		DeliveriesConfiguration.outPutFileExtension = outPutFileExtension;
	}

	public static String getElasticConfiguration() {
		return elasticConfiguration;
	}

	public static void setElasticConfiguration(String elasticConfiguration) {
		DeliveriesConfiguration.elasticConfiguration = elasticConfiguration;
	}

	public static int getNumberAvailabilitiesStreets() {
		return numberAvailabilitiesStreets;
	}

	public static void setNumberAvailabilitiesStreets(int numberAvailabilitiesStreets) {
		DeliveriesConfiguration.numberAvailabilitiesStreets = numberAvailabilitiesStreets;
	}

	public static int getNumberDeliveriesByDroneInOneOutput() {
		return numberDeliveriesByDroneInOneOutput;
	}

	public static void setNumberDeliveriesByDroneInOneOutput(int numberDeliveriesByDroneInOneOutput) {
		DeliveriesConfiguration.numberDeliveriesByDroneInOneOutput = numberDeliveriesByDroneInOneOutput;
	}
	
}
