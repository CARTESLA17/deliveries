package org.com.deliveries;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.com.deliveries.configuration.ConfigurationProcess;
import org.com.deliveries.configuration.impl.ConfigurationProcessImpl;
import org.junit.Test;

public class DeliveriesConfigurationTest {
	
	@Test
	public void whenChargeConfigurationWithElasticityInOFFTheInformationMustToBeOKInDeliveriesConfiguration() throws Exception {
		//arranges
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();			
		//act
		configurationProcess.chargeConfigurationFromProperties(properties);	
		//assert
		assertEquals(
				Integer.parseInt(properties.getProperty(ConfigurationProcessImpl.NUMBER_DRONES)), DeliveriesConfiguration.getNumberDrones());	
		assertEquals(properties.getProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION), DeliveriesConfiguration.getElasticConfiguration());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.INPUT_FILE_EXTENSION_PROPERTY), DeliveriesConfiguration.getInputFileExtension());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.INPUT_FILE_NAME_PROPERTY), DeliveriesConfiguration.getInputFileName());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.INPUT_FILE_PATH_PROPERTY), DeliveriesConfiguration.getInputFilePath());
		assertEquals(
				Integer.parseInt(properties.getProperty(ConfigurationProcessImpl.NUMBER_AVAILABILITIES_STREETS)), DeliveriesConfiguration.getNumberAvailabilitiesStreets());
		assertEquals(
				Integer.parseInt(properties.getProperty(ConfigurationProcessImpl.NUMBER_DELIVERIES_BY_DRONE_IN_ONE_OUTPUT)), DeliveriesConfiguration.getNumberDeliveriesByDroneInOneOutput());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.OUTPUT_FILE_EXTENSION_PROPERTY), DeliveriesConfiguration.getOutPutFileExtension());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.OUTPUT_FILE_NAME_PROPERTY), DeliveriesConfiguration.getOutPutFileName());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.OUTPUT_FILE_PATH_PROPERTY), DeliveriesConfiguration.getOutPutFilePath());
	}
	
	@Test
	public void whenChargeConfigurationWithElasticityInONTheInformationMustToBeOKInDeliveriesConfiguration() throws Exception {
		//arranges
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		//act
		configurationProcess.chargeConfigurationFromProperties(properties);	
		//assert
		assertEquals(
				Integer.parseInt(properties.getProperty(ConfigurationProcessImpl.NUMBER_DRONES)), DeliveriesConfiguration.getNumberDrones());	
		assertEquals(properties.getProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION), DeliveriesConfiguration.getElasticConfiguration());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.INPUT_FILE_EXTENSION_PROPERTY), DeliveriesConfiguration.getInputFileExtension());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.INPUT_FILE_NAME_PROPERTY), DeliveriesConfiguration.getInputFileName());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.INPUT_FILE_PATH_PROPERTY), DeliveriesConfiguration.getInputFilePath());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.OUTPUT_FILE_EXTENSION_PROPERTY), DeliveriesConfiguration.getOutPutFileExtension());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.OUTPUT_FILE_NAME_PROPERTY), DeliveriesConfiguration.getOutPutFileName());
		assertEquals(properties.getProperty(ConfigurationProcessImpl.OUTPUT_FILE_PATH_PROPERTY), DeliveriesConfiguration.getOutPutFilePath());
	}
	
	private static void chargePropertiesWithStandarConfiguration(Properties properties){
		properties.setProperty(ConfigurationProcessImpl.NUMBER_DRONES, "20");
		properties.setProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION, "OFF");
		properties.setProperty(ConfigurationProcessImpl.INPUT_FILE_EXTENSION_PROPERTY, "txt");
		properties.setProperty(ConfigurationProcessImpl.INPUT_FILE_NAME_PROPERTY, "in");
		properties.setProperty(ConfigurationProcessImpl.INPUT_FILE_PATH_PROPERTY, "inputFilePath");
		properties.setProperty(ConfigurationProcessImpl.NUMBER_AVAILABILITIES_STREETS, "10");
		properties.setProperty(ConfigurationProcessImpl.NUMBER_DELIVERIES_BY_DRONE_IN_ONE_OUTPUT, "3");
		properties.setProperty(ConfigurationProcessImpl.OUTPUT_FILE_EXTENSION_PROPERTY,"txt");
		properties.setProperty(ConfigurationProcessImpl.OUTPUT_FILE_NAME_PROPERTY, "out");
		properties.setProperty(ConfigurationProcessImpl.OUTPUT_FILE_PATH_PROPERTY, "outFilePath");
	}


}
