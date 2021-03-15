package org.com.deliveries.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deliveries implements Serializable{

	private static final long serialVersionUID = -4676000037557420104L;
	
	private List<String> deliveryPaths;
	private Drone drone;
	private List<String> deliveryReports = new ArrayList<>();
	private String id;

	public Deliveries() {
		super();
	}

	public Deliveries(List<String> deliveryPaths, Drone drone, String id) {
		this.deliveryPaths = deliveryPaths;
		this.drone = drone;
		this.id = id;
	}

	public List<String> getDeliveryPaths() {
		return deliveryPaths;
	}

	public void setDeliveryPaths(List<String> deliveryPaths) {
		this.deliveryPaths = deliveryPaths;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public List<String> getDeliveryReports() {
		return deliveryReports;
	}

	public void setDeliveryReports(List<String> deliveryReports) {
		this.deliveryReports = deliveryReports;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
