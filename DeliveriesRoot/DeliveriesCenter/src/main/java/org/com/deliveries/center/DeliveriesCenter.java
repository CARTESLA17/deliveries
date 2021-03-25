package org.com.deliveries.center;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.com.deliveries.DeliveriesConfiguration;
import org.com.deliveries.repository.DeliveriesRepository;
import org.com.deliveries.repository.impl.DeliveriesRepositoryImpl;
import org.com.deliveries.service.DeliveriesService;
import org.com.deliveries.service.impl.DeliveriesServiceImpl;
import org.com.deliveries.threads.DeliveriesRunnable;
import org.com.deliveries.utils.Utils;

public class DeliveriesCenter {	
	
	public void activateDeliveriesProcess() throws IOException{
		int availabilityDrones = DeliveriesConfiguration.getNumberDrones();
		Path inputPath = Paths.get(DeliveriesConfiguration.getInputFilePath());
		List<Path> inputFileList = Utils.getFilesByPath(inputPath);
		inputFileList = Utils.filterFilesByNameAndExtension(inputFileList, 
				DeliveriesConfiguration.getInputFileName(), DeliveriesConfiguration.getInputFileExtension());
		availabilityDrones = (availabilityDrones < inputFileList.size()) ? availabilityDrones : inputFileList.size();
		ExecutorService executor = Executors.newFixedThreadPool(availabilityDrones); 
		inputFileList.forEach( inputFile ->{			
			String deliveriesId = Utils.getIdFromInputFileName(inputFile,
					DeliveriesConfiguration.getInputFileName(), DeliveriesConfiguration.getInputFileExtension());
			DeliveriesRepository deliveriesRepository = new DeliveriesRepositoryImpl();
			DeliveriesService deliveriesService = new DeliveriesServiceImpl(deliveriesRepository);
			DeliveriesRunnable deliveryRunnable = new DeliveriesRunnable(inputFile, deliveriesService, deliveriesId);
			executor.execute(deliveryRunnable);			
		});
		executor.shutdown();
	}

}
