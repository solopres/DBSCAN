package Implementation;

import dbscan.*;
import java.util.List;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class driver {
  public static void main(String[] args) {
    List<Point> coordinates = Parser.parseFile("Data/dataset.csv");
    DBSCAN db = new DBSCAN(0.6, 3, "euclidean");
    DBSCANResult results = db.cluster(coordinates);

    JFreeChart chart =
        Parser.createDBSCANChart(results, "DBSCAN Clustering Results", "X-Axis", "Y-Axis");
    display(chart);
  }

  /** Takes in a JFreeChart to display. */
  public static void display(JFreeChart chart) {
    JFrame frame = new JFrame("DBSCAN Visualization");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new ChartPanel(chart));
    frame.pack();
    frame.setVisible(true);
  }
}
