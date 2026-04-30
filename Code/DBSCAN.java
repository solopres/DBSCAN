package Code;

import java.util.List;

public class DBSCAN {
  private double epsilon;
  private int minPoints;
  private DistanceMetric metric;

  /**
   * Initializes DBSCAN based on hyperparameters provided by the user.<br>
   * Variables:
   *
   * <ul>
   *   <li>eps: Maximum distance between a point and others to be within its neighborhood.
   *   <li>minPts: Minimum number of points within a point's neighborhood to consider it a core
   *       point.
   *   <li>metric: euclidean or manhattan. Used to determine distance calculation.
   * </ul>
   */
  DBSCAN(double eps, int minPts, String metric) {
    this.epsilon = eps;
    this.minPoints = minPts;
    this.metric = new MinkowskiDistance(metric);
  }

  public DBSCANResult cluster(List<Point> data) {}

  public static List<Point> findNeighbors(Point p, List<Point> data) {}

  public static void expandCluster(Point p, List<Point> neighbors, Cluster c, List<Point> data) {}
}
