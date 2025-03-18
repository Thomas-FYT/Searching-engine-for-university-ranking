package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code T1Analysis} class performs analysis on QS data filtered by a specific year.
 * It supports generating data for pie charts and bar charts based on specified properties.
 */
public class T1Analysis {
    public ObservableList<QSItem> tableList = FXCollections.observableArrayList();
    /**
     * Constructs a {@code T1Analysis} instance, filtering the QS data for items matching the specified year.
     *
     * @param year The year to filter QS data items.
     */
    T1Analysis (String year) {
        /*
            Your Code Here.
            Collect the QSItem with corresponding years into tableList.
            Use static properties in QSList here.
            Hint: QSList.list is a static property.
         */
        for (QSItem item : QSList.list) {
            // Access and use the item here
            if (item.year.equals(year)) {
                tableList.add(item);
            }
        }
    }

    /**
     * Returns the list of QS data items filtered by year.
     *
     * @return An observable list of {@code QSItem} containing filtered data.
     */
    ObservableList<QSItem> getTableList() {
        return tableList;
    }

    /**
     * Generates pie chart data to visualize the sum of a specific property across QS data items.
     *
     * @param searchName The property name based on which the pie chart will aggregate data.
     * @return An {@code ObservableList<PieChart.Data>} containing the sum of values for each distinct property value.
     */
    ObservableList<PieChart.Data> getPieChartData(String searchName) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        /*
            Your Code Here.
            Return the Pie Chart Data.
            Pie Chart shows the SUM of the score.
            For example, when the user chooses "size", which means the searchName will be "size"
            And Return an ObservableList with PieChart.Data
            [
                key: "L", value: the Sum score of the Large size universities,
                key: "M", value: the Sum score of the Middle size universities,
                key: "S", value: the Sum score of the Small size universities,
            ]
         */
        List<String> stringList = new ArrayList<>();
        for (QSItem item : tableList) {
            stringList.add(item.getProperty(searchName));
        }
        // Get distinct items and their counts, then add to pie chart data
        List<String> distinctItems = stringList.stream().distinct().collect(Collectors.toList());
        for (String item : distinctItems) {
            long count = stringList.stream().filter(it -> it.equals(item)).count();
            String item_renamed = item + ":" + count;
            pieChartData.add(new PieChart.Data(item_renamed, count));
        }
        return pieChartData;

    }

    /**
     * Generates bar chart data to visualize the average of a specific property across QS data items.
     *
     * @param searchName The property name based on which the bar chart will average data.
     * @return An {@code XYChart.Series<String, Double>} containing the average of values for each distinct property value.
     */
    XYChart.Series<String, Double> getBarChartData(String searchName) {
        XYChart.Series<String, Double> barData= new XYChart.Series<>();
        /*
            Your Code Here.
            Return the Bar Chart Data.
            Bar Chart shows the Avg. of the score.
            For example, when the user chooses "size", which means the searchName will be "size"
            And Return an XYChart.Series with XYChart.Data
            [
                key: "L", value: the Average score of the Large size universities,
                key: "M", value: the Average score of the Middle size universities,
                key: "S", value: the Average score of the Small size universities,
            ]
         */

        List<String> stringList = new ArrayList<>();
        for (QSItem item : tableList) {
            stringList.add(item.getProperty(searchName));
        }
        List<String> distinctItems = stringList.stream().distinct().collect(Collectors.toList());
        for (String item : distinctItems) {
            long totalOccurrences = stringList.stream().filter(it -> it.equals(item)).count();
            double average = totalOccurrences / (double) stringList.size();  // Calculate the average

            // Format the label to include the item name and its average
            String modifiedItem = String.format("%s Avg: %.2f", item, average);

            // Add the new data with the average value
            barData.getData().add(new BarChart.Data(modifiedItem, average));
        }
        return barData;

    }

}
