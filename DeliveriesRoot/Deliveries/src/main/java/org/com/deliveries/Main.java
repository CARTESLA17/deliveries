package org.com.deliveries;

import java.util.Properties;

import org.com.deliveries.center.DeliveriesCenter;
import org.com.deliveries.configuration.ConfigurationProcess;
import org.com.deliveries.configuration.impl.ConfigurationProcessImpl;
import org.com.deliveries.utils.Utils;

public class Main {
	
	private static final String CONFIGURATION_FILE_PATH = "../configuration.properties";
	
	public static void main(String[] args) {		
		try {
			Properties properties = Utils.getConfigurationProperties(CONFIGURATION_FILE_PATH);
			ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
			configurationProcess.chargeConfigurationFromProperties(properties);
			DeliveriesCenter deliveriesCentral = new DeliveriesCenter();
			deliveriesCentral.activateDeliveriesProcess();
		} catch (Exception exception) {			
			exception.printStackTrace();
		} 
	}

}
