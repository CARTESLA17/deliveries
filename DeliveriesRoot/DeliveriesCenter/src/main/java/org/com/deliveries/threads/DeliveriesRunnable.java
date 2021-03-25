package org.com.deliveries.threads;

import java.nio.file.Path;
import java.util.List;

import org.com.deliveries.models.Deliveries;
import org.com.deliveries.models.Drone;
import org.com.deliveries.service.DeliveriesService;
import org.com.deliveries.utils.Utils;

public class DeliveriesRunnable implements Runnable{
	
	private Path inputFile;
	private DeliveriesService deliveriesService;
	private String deliveriesId;
	
	public DeliveriesRunnable(Path inputFile, DeliveriesService deliveriesService, String deliveriesId) {
		super();
		this.inputFile = inputFile;
		this.deliveriesService = deliveriesService;
		this.deliveriesId = deliveriesId;
	}

	@Override
	public void run() {		
		try {
			List<String> deliveryPaths = Utils.getInformationFromFile(this.inputFile);
			Drone drone = new Drone();			
			Deliveries deliveries = new Deliveries(deliveryPaths, drone, this.deliveriesId);			
			this.deliveriesService.manageDeliveries(deliveries);
		} catch (Exception exception) {			
			exception.printStackTrace();
		}
		
	}

}
