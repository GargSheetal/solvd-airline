package solvd.airline.dataaccess.strategy;

public class RouteSelectionSystem {
	
	private RouteSelectionStrategy routeSelectionStrategy;
	
	public void setRouteSelectionStrategy(RouteSelectionStrategy routeSelectionStrategy) {
		this.routeSelectionStrategy = routeSelectionStrategy;
	}
	
	public void getDistanceMatrix() {
		if(routeSelectionStrategy == null) {
			throw new Error("No Route Selection strategy is set. Please set a route strategy.");
		}
		routeSelectionStrategy.getDistanceMatrix();
	}
	
	public void getPriceMatrix() {
		if(routeSelectionStrategy == null) {
			throw new Error("No Route Selection strategy is set. Please set a route strategy.");
		}
		routeSelectionStrategy.getPriceMatrix();
	}
	
	public void getNextLocationIdxMatrix() {
		if(routeSelectionStrategy == null) {
			throw new Error("No Route Selection strategy is set. Please set a route strategy.");
		}
		routeSelectionStrategy.getNextLocationIdxMatrix();
	}
}
