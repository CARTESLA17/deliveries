package org.com.deliveries.repository.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.com.deliveries.DeliveriesConfiguration;
import org.com.deliveries.models.Deliveries;
import org.com.deliveries.repository.DeliveriesRepository;

public class DeliveriesRepositoryImpl implements DeliveriesRepository {

	@Override
	public void saveReports(Deliveries deliveries) throws IOException {
		List<String> reportList = deliveries.getDeliveryReports();
		String repositoryFolder = DeliveriesConfiguration.getOutPutFilePath();		
		String outPutFileName = DeliveriesConfiguration.getOutPutFileName()+deliveries.getId()+
				"."+DeliveriesConfiguration.getOutPutFileExtension();
		String outPutFilePath = repositoryFolder+outPutFileName;		
		Path path = Paths.get(outPutFilePath).toAbsolutePath().normalize();
		Files.deleteIfExists(path);
		PrintWriter writer = new PrintWriter(outPutFilePath, "UTF-8");
		reportList.forEach( report -> writer.println(report) );		
		writer.close();		
	}

}
