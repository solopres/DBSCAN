package dbscan;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
  private List<Point> corePoints;
  private List<Point> borderPoints;

  /** Initialize list of core points and list of border points to empty lists. */
  Cluster() {
    corePoints = new ArrayList<>();
    borderPoints = new ArrayList<>();
  }

  /** Adds a point to the list of core points in the cluser. */
  public void addCorePoint(Point p) {
    this.corePoints.add(p);
  }

  /** Adds a point to the list of border points in the cluster. */
  public void addBorderPoint(Point p) {
    this.borderPoints.add(p);
  }

  /** Returns a list of all the points in the cluster. */
  public List<Point> getAllPoints() {
    List<Point> fullList = new ArrayList<>(this.getCorePoints());
    fullList.addAll(this.getBorderPoints());
    return fullList;
  }

  /** Returns the list of core points in the cluster. */
  public List<Point> getCorePoints() {
    return this.corePoints;
  }

  /** Returns the list of border points in the cluster. */
  public List<Point> getBorderPoints() {
    return this.borderPoints;
  }
}
