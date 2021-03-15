package org.com.deliveries.repository;

import org.com.deliveries.models.Deliveries;

public interface DeliveriesRepository {
	
	public void saveReports(Deliveries deliveries) throws Exception;

}
