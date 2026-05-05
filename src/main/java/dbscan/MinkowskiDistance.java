package dbscan;

public class MinkowskiDistance implements DistanceMetric {
  private double p;

  /**
   * This initializer takes in a metric to determine the value of p. <br>
   *
   * <ul>
   *   Possible entries and resultant p value.
   *   <li>"manhattan": p = 1
   *   <li>"euclidean": p = 2
   * </ul>
   *
   * @throws IllegalArgumentException
   */
  MinkowskiDistance(String metric) throws IllegalArgumentException {
    if (metric.toLowerCase().equals("euclidean")) {
      this.p = 2;
    } else if (metric.toLowerCase().equals("manhattan")) {
      this.p = 1;
    } else {
      throw new IllegalArgumentException(
          String.format(
              "You entered: %s as an argument! Please use either euclidean or manhattan.",
              metric));
    }
  }

  /**
   * {@inheritDoc} <br>
   * Uses Manhattan or Euclidean based on the value of p.
   */
  public double calculate(double[] x, double[] y) {
    double sum = 0;
    for (int i = 0; i < x.length; i++) {
      sum += Math.pow(Math.abs(x[i] - y[i]), this.getP());
    }
    return Math.pow(sum, 1 / this.getP());
  }

  /** Returns the value of p. */
  public double getP() {
    return this.p;
  }
}
