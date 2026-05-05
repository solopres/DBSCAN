package dbscan;

/**
 * This is the status of an initialized Point. Options:
 *
 * <ul>
 *   <li>UNVISITED
 *   <li>VISITED
 *   <li>NOITSE
 *   <li>CLUSTERED
 * </ul>
 */
public enum Status {
  UNVISITED,
  VISITED,
  NOISE,
  CLUSTERED
}