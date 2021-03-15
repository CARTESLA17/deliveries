package org.com.deliveries.center;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.com.deliveries.DeliveriesConfiguration;
import org.com.deliveries.repository.DeliveriesRepository;
import org.com.deliveries.repository.impl.DeliveriesRepositoryImpl;
import org.com.deliveries.service.DeliveriesService;
import org.com.deliveries.service.impl.DeliveriesServiceImpl;
import org.com.deliveries.threads.DeliveriesThread;
import org.com.deliveries.utils.Utils;

public class DeliveriesCenter {	
	
	public void activateDeliveriesProcess() throws IOException{
		int [] availabilityDrones = new int [1];
		availabilityDrones[0] = DeliveriesConfiguration.getNumberDrones();
		Path inputPath = Paths.get(DeliveriesConfiguration.getInputFilePath());
		List<Path> inputFileList = Utils.getFilesByPath(inputPath);
		inputFileList = Utils.filterFilesByNameAndExtension(inputFileList, 
				DeliveriesConfiguration.getInputFileName(), DeliveriesConfiguration.getInputFileExtension());
		inputFileList.forEach( inputFile ->{			
			if( availabilityDrones[0]>0 ){
				availabilityDrones[0]-=1;
				String deliveriesId = Utils.getIdFromInputFileName(inputFile,
						DeliveriesConfiguration.getInputFileName(), DeliveriesConfiguration.getInputFileExtension());
				DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
				DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
				DeliveriesThread deliveryThread = new DeliveriesThread(inputFile, deliveriesService, deliveriesId);
				deliveryThread.start();
			}
		});
	}

}
