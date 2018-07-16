import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Calendar;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class GanttChartPanel extends JPanel {

   private static final long serialVersionUID = 1L;
   private MainPanel mainPanel;

   public GanttChartPanel(MainPanel mainPanel) {
     super(new BorderLayout());
     this.mainPanel = mainPanel;
      //super(title);
      // Create dataset
      IntervalCategoryDataset dataset = getCategoryDataset();

      // Create chart
      JFreeChart chart = ChartFactory.createGanttChart(
            "Scheduled Project", // Chart title
            "Tasks", // X-Axis Label
            "Timeline", // Y-Axis Label
            dataset);

      ChartPanel panel = new ChartPanel(chart);
      add(panel, BorderLayout.CENTER);
   }

   private IntervalCategoryDataset getCategoryDataset() {

      TaskSeries series1 = new TaskSeries("Scheduled Task");

      series1.add(new Task("Task1",
            Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.now().plus(7, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));


      series1.add(new Task("Task2",
            Date.from(LocalDate.of(2018, 7, 10).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2018, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));

      series1.add(new Task("Task3",
            Date.from(LocalDate.of(2018, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2018, 7, 21).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));

      series1.add(new Task("Task4",
            Date.from(LocalDate.of(2018, 7, 24).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2018, 7, 28).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));

      series1.add(new Task("Task5",
            Date.from(LocalDate.of(2018, 7, 31).atStartOfDay().toInstant(ZoneOffset.UTC)),
            Date.from(LocalDate.of(2018, 8, 4).atStartOfDay().toInstant(ZoneOffset.UTC))
         ));


      TaskSeriesCollection dataset = new TaskSeriesCollection();
      dataset.add(series1);
      return dataset;
   }
}
