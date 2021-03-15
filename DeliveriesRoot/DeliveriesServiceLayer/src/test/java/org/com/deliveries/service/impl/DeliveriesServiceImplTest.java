package org.com.deliveries.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.com.deliveries.configuration.ConfigurationProcess;
import org.com.deliveries.configuration.impl.ConfigurationProcessImpl;
import org.com.deliveries.models.Deliveries;
import org.com.deliveries.models.Drone;
import org.com.deliveries.repository.DeliveriesRepository;
import org.com.deliveries.repository.impl.DeliveriesRepositoryImpl;
import org.com.deliveries.service.DeliveriesService;
import org.junit.Test;

public class DeliveriesServiceImplTest {
	
	@Test
	public void whenExecuteManageDeliveriesWithElasticityInOFFDroneFinalInformationMustToBeOK() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);	
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAIAA");
		deliveryPaths.add("DDDAIAD");
		deliveryPaths.add("AAIADAD");
		Drone drone = new Drone();	
		String deliveriesId = "01";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert
		assertEquals("(-2, 4) dirección Occidente", deliveries.getDeliveryReports().get(0));
		assertEquals("(-1, 3) dirección Sur", deliveries.getDeliveryReports().get(1));	
		assertEquals("(0, 0) dirección Occidente", deliveries.getDeliveryReports().get(2));	
	}
	
	@Test
	public void whenExecuteManageDeliveriesWithElasticityInOFFDroneFinalInformationMustToBeDifferentToStatementResults() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAIAA");
		deliveryPaths.add("DDDAIAD");
		deliveryPaths.add("AAIADAD");
		Drone drone = new Drone();	
		String deliveriesId = "02";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert
		assertNotEquals("(-2, 4) dirección Norte", deliveries.getDeliveryReports().get(0));
		assertNotEquals("(-3, 3) dirección Sur", deliveries.getDeliveryReports().get(1));	
		assertNotEquals("(-4, 2) dirección Oriente", deliveries.getDeliveryReports().get(2));	
	}
	
	@Test
	public void whenExecuteManageDeliveriesWithElasticityInOFFAndAmountDeliveriesAreHigherThanConfigurationDroneFinalInformationMustToBeOK() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);	
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAIAA");
		deliveryPaths.add("DDDAIAD");
		deliveryPaths.add("AAIADAD");
		deliveryPaths.add("DDAADDA");
		deliveryPaths.add("IIAAAD");
		Drone drone = new Drone();	
		String deliveriesId = "03";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert
		assertEquals("(-2, 4) dirección Occidente", deliveries.getDeliveryReports().get(0));
		assertEquals("(-1, 3) dirección Sur", deliveries.getDeliveryReports().get(1));	
		assertEquals("(0, 0) dirección Occidente", deliveries.getDeliveryReports().get(2));	
	}
	
	@Test
	public void whenExecuteManageDeliveriesWithElasticityInONDroneFinalInformationMustToBeOK() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);
		properties.setProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION, "ON");
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);	
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAIAA");
		deliveryPaths.add("DDDAIAD");
		deliveryPaths.add("AAIADAD");
		Drone drone = new Drone();	
		String deliveriesId = "04";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert
		assertEquals("(-2, 4) dirección Occidente", deliveries.getDeliveryReports().get(0));
		assertEquals("(-1, 3) dirección Sur", deliveries.getDeliveryReports().get(1));	
		assertEquals("(0, 0) dirección Occidente", deliveries.getDeliveryReports().get(2));	
	}
	
	@Test
	public void whenExecuteManageDeliveriesWithElasticityInONAndAmountDeliveriesAreHigherThanConfigurationDroneFinalInformationMustToBeOK() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);
		properties.setProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION, "ON");
		properties.setProperty(ConfigurationProcessImpl.NUMBER_DELIVERIES_BY_DRONE_IN_ONE_OUTPUT, "1");
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAIAA");
		deliveryPaths.add("DDDAIAD");
		deliveryPaths.add("AAIADAD");
		Drone drone = new Drone();	
		String deliveriesId = "05";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert
		assertEquals("(-2, 4) dirección Occidente", deliveries.getDeliveryReports().get(0));
		assertEquals("(-1, 3) dirección Sur", deliveries.getDeliveryReports().get(1));	
		assertEquals("(0, 0) dirección Occidente", deliveries.getDeliveryReports().get(2));	
	}
	
	@Test
	public void whenExecuteManageDeliveriesWithElasticityInONAndAmountStreetsAreHigherThanConfigurationDroneFinalInformationMustToBeOK() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);
		properties.setProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION, "ON");
		properties.setProperty(ConfigurationProcessImpl.NUMBER_AVAILABILITIES_STREETS, "1");
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAIAA");
		deliveryPaths.add("DDDAIAD");
		deliveryPaths.add("AAIADAD");
		deliveryPaths.add("DDDD");
		Drone drone = new Drone();	
		String deliveriesId = "06";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert
		assertEquals("(-2, 4) dirección Occidente", deliveries.getDeliveryReports().get(0));
		assertEquals("(-1, 3) dirección Sur", deliveries.getDeliveryReports().get(1));	
		assertEquals("(0, 0) dirección Occidente", deliveries.getDeliveryReports().get(2));	
	}
	
	@Test(expected = Exception.class)
	public void whenExecuteManageDeliveriesWithElasticityInOFFAndNorthStreetNotAllowedMustToThrowExpeption() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("AAAAAAAAAAA");		
		Drone drone = new Drone();	
		String deliveriesId = "01";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert		
	}
	
	@Test(expected = Exception.class)
	public void whenExecuteManageDeliveriesWithElasticityInOFFAndSouthStreetNotAllowedSouthStreetMustToThrowExpeption() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("DDAAAAAAAAAAA");		
		Drone drone = new Drone();	
		String deliveriesId = "01";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert		
	}
	
	@Test(expected = Exception.class)
	public void whenExecuteManageDeliveriesWithElasticityInOFFAndEastStreetNotAllowedMustToThrowExpeption() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("DAAAAAAAAAAA");		
		Drone drone = new Drone();	
		String deliveriesId = "01";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert		
	}
	
	@Test(expected = Exception.class)
	public void whenExecuteManageDeliveriesWithElasticityInOFFAndWestStreetNotAllowedMustToThrowExpeption() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("IAAAAAAAAAAA");		
		Drone drone = new Drone();	
		String deliveriesId = "01";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert		
	}
	
	@Test(expected = Exception.class)
	public void whenExecuteManageDeliveriesWithElasticityInOFFAndMoveNotAllowedMustToThrowExpeption() throws Exception {
		Properties properties = new Properties();
		chargePropertiesWithStandarConfiguration(properties);		
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);		
		List<String> deliveryPaths = new ArrayList<>();
		deliveryPaths.add("DAIAXA");		
		Drone drone = new Drone();	
		String deliveriesId = "01";
		Deliveries deliveries = new Deliveries(deliveryPaths, drone, deliveriesId);
		DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
		DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
		//act
		deliveriesService.manageDeliveries(deliveries);
		//assert		
	}
	
	private static void chargePropertiesWithStandarConfiguration(Properties properties){
		properties.setProperty(ConfigurationProcessImpl.NUMBER_DRONES, "20");
		properties.setProperty(ConfigurationProcessImpl.ELASTIC_CONFIGURATION, "OFF");
		properties.setProperty(ConfigurationProcessImpl.INPUT_FILE_EXTENSION_PROPERTY, "txt");
		properties.setProperty(ConfigurationProcessImpl.INPUT_FILE_NAME_PROPERTY, "in");
		properties.setProperty(ConfigurationProcessImpl.INPUT_FILE_PATH_PROPERTY, "./file_paths/inputs/");
		properties.setProperty(ConfigurationProcessImpl.NUMBER_AVAILABILITIES_STREETS, "10");
		properties.setProperty(ConfigurationProcessImpl.NUMBER_DELIVERIES_BY_DRONE_IN_ONE_OUTPUT, "3");
		properties.setProperty(ConfigurationProcessImpl.OUTPUT_FILE_EXTENSION_PROPERTY,"txt");
		properties.setProperty(ConfigurationProcessImpl.OUTPUT_FILE_NAME_PROPERTY, "out");
		properties.setProperty(ConfigurationProcessImpl.OUTPUT_FILE_PATH_PROPERTY, "../file_paths/outputs/");
	}

}
