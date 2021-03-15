package org.com.deliveries.configuration.impl;

import java.io.IOException;
import java.util.Properties;

import org.com.deliveries.DeliveriesConfiguration;
import org.com.deliveries.configuration.ConfigurationProcess;

public class ConfigurationProcessImpl implements ConfigurationProcess {
	
	public static final String INPUT_FILE_PATH_PROPERTY = "input_file_path";
	public static final String INPUT_FILE_NAME_PROPERTY = "input_file_name";
	public static final String INPUT_FILE_EXTENSION_PROPERTY = "input_file_extension";
	public static final String OUTPUT_FILE_PATH_PROPERTY = "output_file_path";
	public static final String OUTPUT_FILE_NAME_PROPERTY = "output_file_name";
	public static final String OUTPUT_FILE_EXTENSION_PROPERTY = "output_file_extension";
	public static final String NUMBER_DRONES = "number_drones";
	public static final String ELASTIC_CONFIGURATION = "elastic_configuration";
	public static final String NUMBER_AVAILABILITIES_STREETS = "number_availabilities_streets";
	public static final String NUMBER_DELIVERIES_BY_DRONE_IN_ONE_OUTPUT = "number_deliveries_by_drone_in_one_output";
	
	@Override
	public void chargeConfigurationFromProperties(Properties properties) throws IOException {		
		DeliveriesConfiguration.setNumberDrones(
				Integer.parseInt(properties.getProperty(NUMBER_DRONES).trim()));
		DeliveriesConfiguration.setInputFilePath(properties.getProperty(INPUT_FILE_PATH_PROPERTY).trim());
		DeliveriesConfiguration.setOutPutFilePath(properties.getProperty(OUTPUT_FILE_PATH_PROPERTY).trim());
		DeliveriesConfiguration.setInputFileName(properties.getProperty(INPUT_FILE_NAME_PROPERTY).trim());
		DeliveriesConfiguration.setOutPutFileName(properties.getProperty(OUTPUT_FILE_NAME_PROPERTY).trim());
		DeliveriesConfiguration.setInputFileExtension(properties.getProperty(INPUT_FILE_EXTENSION_PROPERTY).trim());
		DeliveriesConfiguration.setOutPutFileExtension(properties.getProperty(OUTPUT_FILE_EXTENSION_PROPERTY).trim());
		DeliveriesConfiguration.setElasticConfiguration(properties.getProperty(ELASTIC_CONFIGURATION).trim());
		if( "OFF".equals(DeliveriesConfiguration.getElasticConfiguration()) ){ 
			DeliveriesConfiguration.setNumberAvailabilitiesStreets(
					Integer.parseInt(properties.getProperty(NUMBER_AVAILABILITIES_STREETS).trim()));
			DeliveriesConfiguration.setNumberDeliveriesByDroneInOneOutput(
					Integer.parseInt(properties.getProperty(NUMBER_DELIVERIES_BY_DRONE_IN_ONE_OUTPUT).trim()));
		}
	}	

}
