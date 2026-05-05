package dbscan;

public interface DistanceMetric {
  /**
   * Calculates the distance between two coordinate arrays. Both arrays must have the same length.
   */
  double calculate(double[] x, double[] y);
}
