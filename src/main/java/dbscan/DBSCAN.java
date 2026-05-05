package dbscan;

import java.util.ArrayList;
import java.util.List;

/** This class implements DBSCAN using either Manhattan or Euclidean distance. */
public class DBSCAN {
  private double epsilon;
  private int minPoints;
  private DistanceMetric metric;

  /**
   * Initializes DBSCAN based on hyperparameters provided by the user.<br>
   *
   * @param eps - Maximum distance between a point and others to be within its neighborhood.
   * @param minPts - Minimum number of points within a point's neighborhood to consider it a core
   *     point.
   * @param metric - euclidean or manhattan. Used to determine distance calculation.
   */
  public DBSCAN(double eps, int minPts, String metric) {
    this.epsilon = eps;
    this.minPoints = minPts;
    this.metric = new MinkowskiDistance(metric);
  }

  /**
   * Create clusters for the data provided based on epsilon, minPoints, and the distance metric.
   *
   * @param data - This is data obtained from a file, formatted into a list of Points. For ease of
   *     conversion, {@link Parser#parseFile(String)}.
   */
  public DBSCANResult cluster(List<Point> data) {
    List<Cluster> clusters = new ArrayList<>();
    for (Point p : data) {
      // Skip all points that have been visited/clustered
      if (p.geStatus() != Status.UNVISITED) {
        continue;
      }
      p.setStatus(Status.VISITED);
      List<Point> neighbors = findNeighbors(p, data);
      // If point has less than the minimum number of points required, it is noise.
      if (neighbors.size() < this.getMinPoints()) {
        p.setStatus(Status.NOISE);
      } else {
        // Otherwise make it a cluster.
        Cluster newCluster = new Cluster();
        this.expandCluster(p, neighbors, newCluster, data);
        clusters.add(newCluster);
      }
    }
    // From all points filter those that are NOISE and convert to list.
    List<Point> noise = data.stream().filter(p -> p.geStatus() == Status.NOISE).toList();
    return new DBSCANResult(clusters, noise);
  }

  /** Find all the neighbors of the target point and return them as a list. */
  public List<Point> findNeighbors(Point target, List<Point> data) {
    List<Point> neighbors = new ArrayList<>();
    for (Point p : data) {
      if (this.getMetric().calculate(target.getCoordinates(), p.getCoordinates())
          <= this.getEpsilon()) {
        neighbors.add(p);
      }
    }
    return neighbors;
  }

  /** Expands a given cluster based on the provided point and neighbors via density based search. */
  public void expandCluster(Point root, List<Point> neighbors, Cluster cluster, List<Point> data) {
    cluster.addCorePoint(root);
    root.setStatus(Status.CLUSTERED);

    List<Point> queue = new ArrayList<>(neighbors);
    for (int i = 0; i < queue.size(); i++) {
      Point currentPoint = queue.get(i);

      // Any point that was already visited and was classified as noise is a border point.
      if (currentPoint.geStatus() == Status.NOISE) {
        currentPoint.setStatus(Status.CLUSTERED);
        cluster.addBorderPoint(currentPoint);
      }

      // Skip points already visited and clustered.
      if (currentPoint.geStatus() != Status.UNVISITED) {
        continue;
      }

      currentPoint.setStatus(Status.VISITED);
      List<Point> currentNeighbors = findNeighbors(currentPoint, data);

      // If the current point has at least the minimum number of neighbors, it is a core point.
      if (currentNeighbors.size() >= this.getMinPoints()) {
        currentPoint.setStatus(Status.CLUSTERED);
        cluster.addCorePoint(currentPoint);
        // Expand queue to include the current point's neighbors.
        for (Point p : currentNeighbors) {
          queue.add(p);
        }
      } else {
        // Otherwise it is a border point.
        currentPoint.setStatus(Status.CLUSTERED);
        cluster.addBorderPoint(currentPoint);
      }
    }
  }

  /** Return the distance metric used. */
  public DistanceMetric getMetric() {
    return this.metric;
  }

  /** Return the value of epsilon. */
  public double getEpsilon() {
    return this.epsilon;
  }

  /** Return the minimum number of neighbors a point needs to become a core point. */
  public int getMinPoints() {
    return this.minPoints;
  }
}
