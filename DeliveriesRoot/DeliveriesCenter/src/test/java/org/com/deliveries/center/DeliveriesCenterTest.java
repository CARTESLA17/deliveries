package org.com.deliveries.center;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.com.deliveries.DeliveriesConfiguration;
import org.com.deliveries.configuration.ConfigurationProcess;
import org.com.deliveries.configuration.impl.ConfigurationProcessImpl;
import org.com.deliveries.utils.Utils;
import org.junit.Test;

public class DeliveriesCenterTest {
	
	@Test
	public void whenActivateDeliveriesProcessFinalInformationMustToBeOK() throws Exception {
		Properties properties = Utils.getConfigurationProperties("../configuration.properties");
		ConfigurationProcess configurationProcess = new ConfigurationProcessImpl();
		configurationProcess.chargeConfigurationFromProperties(properties);
		DeliveriesCenter deliveriesCentral = new DeliveriesCenter();		
		//act
		deliveriesCentral.activateDeliveriesProcess();
		//assert
		List<Path> inputFileList = Utils.getFilesByPath(Paths.get(DeliveriesConfiguration.getOutPutFilePath()));
		int tries = 1;
		while( inputFileList.size()<=2 && tries<=3){
			Thread.sleep(5000);
			tries++;
			inputFileList = Utils.getFilesByPath(Paths.get(DeliveriesConfiguration.getOutPutFilePath()));
		}
		List<String> reportsFileOne = Utils.getInformationFromFile(inputFileList.get(0));
		List<String> reportsFileTwo = Utils.getInformationFromFile(inputFileList.get(1));
		assertEquals("out01.txt", inputFileList.get(0).getFileName().toString());
		assertEquals("out04.txt", inputFileList.get(1).getFileName().toString());
		assertEquals("(-2, 4) dirección Occidente", reportsFileOne.get(0));
		assertEquals("(-1, 3) dirección Sur", reportsFileOne.get(1));	
		assertEquals("(0, 0) dirección Occidente", reportsFileOne.get(2));
		assertEquals("(-2, 4) dirección Occidente", reportsFileTwo.get(0));
		assertEquals("(-1, 3) dirección Sur", reportsFileTwo.get(1));	
	}
		
}
