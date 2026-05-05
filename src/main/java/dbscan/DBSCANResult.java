package dbscan;

import java.util.List;

/** This class collects data from DBSCAN, including the clusters and noise. */
public class DBSCANResult {
  private List<Cluster> clusters;
  private List<Point> noise;

  /** Initialize the DBSCAN results with list of clusters and list of noise (outliers). */
  DBSCANResult(List<Cluster> clusters, List<Point> noise) {
    this.clusters = List.copyOf(clusters);
    this.noise = List.copyOf(noise);
  }

  /** Returns a list of the clusters found by DBSCAN. */
  public List<Cluster> getClusters() {
    return this.clusters;
  }

  /** Returns a list of the noise/outliers found by DBSCAN */
  public List<Point> getNoise() {
    return this.noise;
  }
}
