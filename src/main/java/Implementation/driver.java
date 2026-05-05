package Implementation;

import dbscan.*;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

public class driver {
  public static void main(String[] args) {
    List<Point> coordinates = Parser.parseFile("../Data/dataset.csv");
    DBSCAN db = new DBSCAN(0.5, 5, "euclidean");
    DBSCANResult results = db.cluster(coordinates);

    List<XYSeriesCollection> dataClusters = Parser.parseResults(results);
    JFreeChart chart =
        Parser.createDBSCANChart(dataClusters, "DBSCAN Clustering Results", "X-Axis", "Y-Axis");
    display(chart);
  }

  public static void display(JFreeChart chart) {
    JFrame frame = new JFrame("DBSCAN Visualization");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new ChartPanel(chart));
    frame.pack();
    frame.setVisible(true);
  }
}
