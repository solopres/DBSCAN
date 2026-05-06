package dbscan;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Parser {
  /** Parse a comma separated file for use with the {@link DBSCAN} class. */
  public static List<Point> parseFile(String filePath) {
    List<Point> coordinates = new ArrayList<>();
    String line = "";
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
      bufferedReader.readLine();
      while ((line = bufferedReader.readLine()) != null) {
        String[] values = line.split(",");
        double[] coordinate = new double[values.length];
        for (int i = 0; i < coordinate.length; i++) {
          coordinate[i] = Double.parseDouble(values[i]);
        }
        coordinates.add(new Point(coordinate));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return coordinates;
  }

  /** Parses the results of {@link DBSCAN#cluster(List<Point>)}. */
  private static XYSeriesCollection parseResults(DBSCANResult results) {
    List<Cluster> clusters = results.getClusters();
    XYSeriesCollection dataClusters = new XYSeriesCollection();

    for (int i = 0; i < clusters.size(); i++) {
      Cluster cluster = clusters.get(i);

      XYSeries series = new XYSeries("Cluster " + (i + 1));

      // Add core points before border points.
      for (Point p : cluster.getCorePoints()) {
        series.add(p.getCoordinates()[0], p.getCoordinates()[1]);
      }

      for (Point p : cluster.getBorderPoints()) {
        series.add(p.getCoordinates()[0], p.getCoordinates()[1]);
      }
      dataClusters.addSeries(series);
    }

    XYSeries noiseSeries = new XYSeries("Noise");
    for (Point p : results.getNoise()) {
      noiseSeries.add(p.getCoordinates()[0], p.getCoordinates()[1]);
    }
    dataClusters.addSeries(noiseSeries);
    return dataClusters;
  }

  /**
   * Create a JFreeChart for DBSCAN visualization.<br>
   * Noise marked as X's.
   *
   * @param results - The result of the DBSCAN.
   * @param title - The title of the graph.
   * @param xLabel - The title of the x-axis.
   * @param yLabel - The title of the y-axis.
   */
  public static JFreeChart createDBSCANChart(
      DBSCANResult results, String title, String xLabel, String yLabel) {
    XYSeriesCollection dataClusters = parseResults(results);
    JFreeChart chart = ChartFactory.createScatterPlot(title, xLabel, yLabel, dataClusters);
    XYPlot plot = chart.getXYPlot();
    XYLineAndShapeRenderer renderer =
        new XYLineAndShapeRenderer(false, true) {
          @Override
          public Shape getItemShape(int row, int col) {
            if (row >= results.getClusters().size()) {
              return makeX(3.0);
            } else {
              Cluster cluster = results.getClusters().get(row);

              int coreCount = cluster.getCorePoints().size();
              if (col < coreCount) {
                return new Ellipse2D.Double(-4, -4, 8, 8);
              } else {
                return new Ellipse2D.Double(-2, -2, 4, 4);
              }
            }
          }
        };
    int noiseIndex = results.getClusters().size();
    renderer.setSeriesPaint(noiseIndex, Color.BLACK);
    plot.setRenderer(renderer);
    return chart;
  }

  /** Makes x as a shape for a JFreeChart renderer. */
  private static Shape makeX(double size) {
    Path2D shape = new Path2D.Double();
    // Make / line
    shape.moveTo(-size, -size);
    shape.lineTo(size, size);
    // Make \ line
    shape.moveTo(size, -size);
    shape.lineTo(-size, size);
    // Return X shape
    return shape;
  }
}
