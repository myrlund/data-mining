package no.ntnu.idi.dm.distancemetrics;

/**
 * Implements the L2 or Euclidean metric.
 * 
 */
public class EuclideanDistance {

	public double distance(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Vectors must be equally dimensioned.");
        }

		double dist = 0;
        for (int i = 0; i < vector1.length; i++) {
            dist += Math.pow(vector1[i] - vector2[i], 2);
        }
		return Math.sqrt(dist);
	}

}
