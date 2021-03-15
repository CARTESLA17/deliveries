package org.com.deliveries.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.com.deliveries.DeliveriesConfiguration;
import org.com.deliveries.models.Deliveries;
import org.com.deliveries.models.Drone;
import org.com.deliveries.repository.DeliveriesRepository;
import org.com.deliveries.service.DeliveriesService;
import org.com.deliveries.utils.Utils;

public class DeliveriesServiceImpl implements DeliveriesService {
	
	public static final String NORTH_MOVE = "N";
	public static final String SOUTH_MOVE = "S";
	public static final String WEST_MOVE = "W";
	public static final String EAST_MOVE = "E";
	
	public static final Map<String, String> RIGHT_MOVES_MAP = getMapWithRightMoves();
	public static final Map<String, String> LEFT_MOVES_MAP = getMapWithLefttMoves();
	public static final Map<String, Consumer<Drone>> FORWARD_MOVES_MAP = getMapWithForwardMoves();
	public static final Map<String, BiFunction<Drone,Integer,String>> CONDITIONAL_FORWARD_MOVES_MAP = getMapWithConditionalForwardMoves();

	private DeliveriesRepository deliveriesRepository;
	
	public DeliveriesServiceImpl(DeliveriesRepository deliveriesRepository) {
		super();
		this.deliveriesRepository = deliveriesRepository;
	}
	
	private static Map<String, BiFunction<Drone,Integer,String>> getMapWithConditionalForwardMoves() {
		Map<String, BiFunction<Drone,Integer,String>> map = new HashMap<>();		
		map.put(NORTH_MOVE, (drone, availabilityStreets) -> {
			if( drone.getY()+1 <= availabilityStreets ){
				drone.setY(drone.getY()+1);
				return "OK";
			} else {
				return "Invalid street in north direction, street: "+(drone.getY()+1);
			}
		});
		map.put(SOUTH_MOVE, (drone, availabilityStreets) -> {
			if( drone.getY()-1 >= (availabilityStreets*(-1)) ){
				drone.setY(drone.getY()-1);
				return "OK";
			} else {
				return "Invalid street in south direction, street: "+(drone.getY()-1);
			}
		});
		map.put(WEST_MOVE, (drone, availabilityStreets) -> {
			if( drone.getX()-1 >= (availabilityStreets*(-1)) ){
				drone.setX(drone.getX()-1);
				return "OK";
			} else {
				return "Invalid street in west direction, street: "+(drone.getX()-1);
			}
		});
		map.put(EAST_MOVE, (drone, availabilityStreets) -> { 
			if( drone.getX()+1 <= availabilityStreets ){
				drone.setX(drone.getX()+1);
				return "OK";
			} else {
				return "Invalid street in east direction, street:"+(drone.getX()+1);
			}
		});
		return map;
	}

	private static Map<String, Consumer<Drone>> getMapWithForwardMoves() {
		Map<String, Consumer<Drone>> map = new HashMap<>();		
		map.put(NORTH_MOVE, drone -> drone.setY(drone.getY()+1) );
		map.put(SOUTH_MOVE, drone -> drone.setY(drone.getY()-1) );
		map.put(WEST_MOVE, drone -> drone.setX(drone.getX()-1) );
		map.put(EAST_MOVE, drone -> drone.setX(drone.getX()+1) );
		return map;
	}

	private static Map<String, String> getMapWithLefttMoves() {
		Map<String, String> map = new HashMap<>();
		map.put(NORTH_MOVE, WEST_MOVE);
		map.put(SOUTH_MOVE, EAST_MOVE);
		map.put(WEST_MOVE, SOUTH_MOVE);
		map.put(EAST_MOVE, NORTH_MOVE);
		return map;
	}

	private static Map<String, String> getMapWithRightMoves() {
		Map<String, String> map = new HashMap<>();
		map.put(NORTH_MOVE, EAST_MOVE);
		map.put(SOUTH_MOVE, WEST_MOVE);
		map.put(WEST_MOVE, NORTH_MOVE);
		map.put(EAST_MOVE, SOUTH_MOVE);
		return map;
	}

	@Override
	public void manageDeliveries(Deliveries deliveries) throws Exception {				
		int numberOfDeliveries = deliveries.getDeliveryPaths().size();
		int deliveriesByDrone = Utils.getDroneAvailabilyDeliveriesAboutConfiguration(
				DeliveriesConfiguration.getElasticConfiguration(), DeliveriesConfiguration.getNumberDeliveriesByDroneInOneOutput(), 
				numberOfDeliveries);		
		sendDrones(deliveries, deliveriesByDrone);
		this.deliveriesRepository.saveReports(deliveries);
	}
	
	private void sendDrones(Deliveries deliveries, int deliveriesByDrone) throws Exception{
		Drone drone = deliveries.getDrone();
		List<String> deliveryPaths = deliveries.getDeliveryPaths();
		for( int i=0; i < deliveriesByDrone; i++ ){
			String path = deliveryPaths.get(i).trim();
			for( int j=0; j < path.length(); j++ ){			
				String move = path.charAt(j)+"";				
				if( Drone.FORWARD_MOVE.equals(move) ){
					setForwardMove(drone, DeliveriesConfiguration.getElasticConfiguration(),
							DeliveriesConfiguration.getNumberAvailabilitiesStreets());
				} else if( Drone.LEFT_MOVE.equals(move) ){
					String newDirection = LEFT_MOVES_MAP.get(drone.getCurrentDirection()); 
					drone.setCurrentDirection(newDirection);	
				} else if( Drone.RIGHT_MOVE.equals(move) ){
					String newDirection = RIGHT_MOVES_MAP.get(drone.getCurrentDirection()); 
					drone.setCurrentDirection(newDirection);					
				} else {
					throw new Exception("Invalid move, the drone does not recognize this move: "+move);
				}				
			}
			String report = Utils.doReport(drone.getX(), drone.getY(), drone.getCurrentDirection());
			deliveries.getDeliveryReports().add(report);
		}
	}
	
	private void setForwardMove(Drone drone, String elasticConfiguration, int availabilityStreets) throws Exception{
		if( "OFF".equals(elasticConfiguration) ){
			String response =CONDITIONAL_FORWARD_MOVES_MAP.get(drone.getCurrentDirection()).apply(drone, availabilityStreets);				
			if( !"OK".equals(response) ){
				throw new Exception(response);
			}
		} else {
			FORWARD_MOVES_MAP.get(drone.getCurrentDirection()).accept(drone);			
		}		
	}

}
