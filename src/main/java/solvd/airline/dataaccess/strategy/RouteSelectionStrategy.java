package solvd.airline.dataaccess.strategy;

public interface RouteSelectionStrategy {

	int[][] getDistanceMatrix();

	double[][] getPriceMatrix();

	public int[][] getNextLocationIdxMatrix(); 
	
}
