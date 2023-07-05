package solvd.airline.dataaccess.strategy;

public class ShortestRouteSelectionStrategy implements RouteSelectionStrategy {

	private final int INF = Integer.MAX_VALUE;	// Infinity value for unreachable routes
	private int numLocations;
	private int[][] distance;
	private double[][] price;
	private int[][] nextLocationIdx;
	
	public ShortestRouteSelectionStrategy(int numLocations, int[][] distance, double[][] price,
			int[][] nextLocationIdx) {
		this.numLocations = numLocations;
		this.distance = distance;
		this.price = price;
		this.nextLocationIdx = nextLocationIdx;
		runAlgorithm();
	}

	private void runAlgorithm() {
		
		// Apply floyd-warshall algorithm
		for(int k=0; k<numLocations; k++) {
			for(int i=0; i<numLocations; i++) {
				for(int j=0; j<numLocations; j++) {
					if(distance[i][k] != INF && distance[k][j] != INF) {
						if(distance[i][j] > distance[i][k] + distance[k][j]) {
							distance[i][j] = distance[i][k] + distance[k][j];
							price[i][j] = price[i][k] + price[k][j];
							nextLocationIdx[i][j] = nextLocationIdx[i][k];
						}
					}
				}
			}
		}
	}

	public int[][] getDistanceMatrix() {
		return distance;
	}

	public double[][] getPriceMatrix() {
		return price;
	}

	public int[][] getNextLocationIdxMatrix() {
		return nextLocationIdx;
	}
}
