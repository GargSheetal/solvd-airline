package solvd.airline.dataaccess.strategy;

public class RouteSelectionSystem {
	
	private RouteSelectionStrategy routeSelectionStrategy;
	
	public void setRouteSelectionStrategy(RouteSelectionStrategy routeSelectionStrategy) {
		this.routeSelectionStrategy = routeSelectionStrategy;
	}
	
	public int[][] getDistanceMatrix() {
		if(routeSelectionStrategy == null) {
			throw new Error("No Route Selection strategy is set. Please set a route strategy.");
		}
		return routeSelectionStrategy.getDistanceMatrix();
	}
	
	public double[][] getPriceMatrix() {
		if(routeSelectionStrategy == null) {
			throw new Error("No Route Selection strategy is set. Please set a route strategy.");
		}
		return routeSelectionStrategy.getPriceMatrix();
	}
	
	public int[][] getNextLocationIdxMatrix() {
		if(routeSelectionStrategy == null) {
			throw new Error("No Route Selection strategy is set. Please set a route strategy.");
		}
		return routeSelectionStrategy.getNextLocationIdxMatrix();
	}
}
