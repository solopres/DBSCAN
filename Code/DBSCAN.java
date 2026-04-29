package Code;

import java.util.List;

public class DBSCAN {
  private double epsilon;
  private int minPoints;
  private DistanceMetric metric;

  DBSCAN(double eps, int minPts, String metric) {}

  public static List<Point> findNeighbors(Point p, List<Point> data) {}

  public static void expandCluster(Point p, List<Point> neighbors, Cluster c, List<Point> data) {}
}
