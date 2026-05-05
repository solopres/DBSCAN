package dbscan;

import java.awt.geom.Ellipse2D;
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
  public static List<XYSeriesCollection> parseResults(DBSCANResult results) {
    List<Cluster> clusters = results.getClusters();
    List<XYSeriesCollection> dataClusters = new ArrayList<>();

    for (int i = 0; i < clusters.size(); i++) {
      Cluster cluster = clusters.get(i);
      XYSeriesCollection dataCluster = new XYSeriesCollection();

      XYSeries coreSeries = new XYSeries("Cluster " + i + " (Core)");
      XYSeries borderSeries = new XYSeries("Cluster " + i + " (Border)");

      List<Point> corePoints = cluster.getCorePoints();
      for (Point point : corePoints) {
        coreSeries.add(point.getCoordinates()[0], point.getCoordinates()[1]);
      }
      List<Point> borderPoints = cluster.getCorePoints();
      for (Point point : borderPoints) {
        borderSeries.add(point.getCoordinates()[0], point.getCoordinates()[1]);
      }

      dataCluster.addSeries(coreSeries);
      dataCluster.addSeries(borderSeries);
      dataClusters.add(dataCluster);
    }
    return dataClusters;
  }

  public static JFreeChart createDBSCANChart(
      List<XYSeriesCollection> dataClusters, String title, String xLabel, String yLabel) {
    JFreeChart chart = ChartFactory.createScatterPlot(title, xLabel, yLabel, null);
    XYPlot plot = chart.getXYPlot();
    for (int i = 0; i < dataClusters.size(); i++) {
      plot.setDataset(i, dataClusters.get(i));

      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);

      renderer.setSeriesShape(0, new Ellipse2D.Double(-4, -4, 8, 8));
      renderer.setSeriesShape(1, new Ellipse2D.Double(-2, -2, 4, 4));

      plot.setRenderer(renderer);
    }
    return chart;
  }
}
