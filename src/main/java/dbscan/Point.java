package dbscan;

/**
 * Point is the class used to hold the coordinates of points in the implementation of the DBSCAN
 * class.
 */
public class Point {
  private double[] coordinates;
  private Status status;

  /** This initializer sets the coordinates variable to coordinates and status to UNIVISITED. */
  Point(double[] coordinates) {
    this.setCoordinates(coordinates);
    this.setStatus(Status.UNVISITED);
  }

  /** Get coordinates from point. */
  public double[] getCoordinates() {
    return this.coordinates;
  }

  /** Get the status of the point. */
  public Status geStatus() {
    return this.status;
  }

  /** Sets the coordinates of the point. */
  public void setCoordinates(double[] coords) {
    this.coordinates = coords;
  }

  /**
   * Sets the status of the point. Status options:
   *
   * <ul>
   *   <li>UNVISITED
   *   <li>VISITED
   *   <li>NOITSE
   *   <li>CLUSTERED
   * </ul>
   */
  public void setStatus(Status s) {
    this.status = s;
  }
}
