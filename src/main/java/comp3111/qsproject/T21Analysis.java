package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code T21Analysis} class provides methods to analyze and compare
 * university data over a series of years.
 */
public class T21Analysis {
    public ObservableList<QSItem> University1List = FXCollections.observableArrayList();
    public ObservableList<QSItem> University2List = FXCollections.observableArrayList();
    private String University1Name;
    private String University2Name;

    /**
     * Constructs a {@code T21Analysis} object for two universities, initializing data filtering based on the provided years.
     *
     * @param uni_1 the name of the first university
     * @param uni_2 the name of the second university
     * @param years the list of years for which the QS data should be analyzed
     */
    T21Analysis(String uni_1, String uni_2, List<String> years) {
        /*
            Your Code Here.
            Collect the QSItem with corresponding years and university into two university lists.
            Sort university lists by the years.
            Hint: QSList.list is a static property.
         */
        University1Name = uni_1;
        University2Name = uni_2;
        for (QSItem item : QSList.list) {
            if ((item.name.equals(University1Name)) && (years.contains(item.year))) {
                University1List.add(item);
            }
        }
        for (QSItem item : QSList.list) {
            if ((item.name.equals(University2Name)) && (years.contains(item.year))) {
                University2List.add(item);
            }
        }
    }

    /**
     * Retrieves bar chart data for a specified property of the universities.
     * This method calculates and returns the average value of the specified property for each university.
     *
     * @param searchName the name of the property to search and calculate average for (e.g., "score")
     * @return a {@link XYChart.Series} object containing the average values for the specified property
     */
    XYChart.Series<Double, String> getBarChartData(String searchName) {
        XYChart.Series<Double, String> barData= new XYChart.Series<>();
        /*
            Your Code Here.
            Return the Bar Chart Data.
            Bar Chart shows the Avg. of the selected property.
            For example, when the user chooses "score", which means the searchName will be "score"
            And Return an XYChart.Series with XYChart.Data
            [
              Average score of university2, "University 2"
              Average score of university1, "University 1"
            ]

            There are some "dirty data" in csv.
            For example, the string "3,143" or "3.143" can not transfer to Integer or Double directly.
            Careful process these data.
         */
        // dirty data处理了一部分，但不知道有无例外，仅处理了,和.
        List<String> string1List = new ArrayList<>();
        for (QSItem item : University1List) {
            string1List.add(item.getProperty(searchName));
        }

        double sum1 = 0;
        int count1 = string1List.size();
        for (String numberString : string1List) {
            try {
                numberString = numberString.replace(",", "");
                double number = Double.parseDouble(numberString);
                sum1 += number;
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for string \"" + numberString + "\"");
            }
        }
        double average1 = sum1 / count1;

        List<String> string2List = new ArrayList<>();
        for (QSItem item : University2List) {
            string2List.add(item.getProperty(searchName));
        }
        double sum2 = 0;
        int count2 = string2List.size();
        for (String numberString : string2List) {
            try {
                numberString = numberString.replace(",", "");
                double number = Double.parseDouble(numberString);
                sum2 += number;
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for string \"" + numberString + "\"");
            }
        }
        double average2 = sum2 / count2;
        barData.getData().add(new XYChart.Data<>(average2, "University 2"));
        barData.getData().add(new XYChart.Data<>(average1, "University 1"));
        return barData;
    }

    /**
     * Retrieves line chart data showing the evolution of a specified property over time for both universities.
     *
     * @param searchName the property to analyze over time (e.g., "score")
     * @return a list of {@link XYChart.Series} objects, each representing one of the universities
     */
    List<XYChart.Series<String, Double>> getLineChartData(String searchName) {
        List<XYChart.Series<String, Double>> lineData = new ArrayList<>();
        /*
            Your Code Here.
            Fill the lineData1 and lineData2.
            Line Chart shows two lines. Each line shows the number of searchName each year.
            In our cases, the searchName will be "score"
            And Return an XYChart.Series with XYChart.Data
            [
              Series[Data<year,score>],
            ]

            There are some "dirty data" in csv.
            For example, the string "3,143" or "3.143" can not transfer to Integer or Double directly.
            Careful process these data.
         */
        XYChart.Series<String, Double> lineData1 = new XYChart.Series<String, Double>();
        XYChart.Series<String, Double> lineData2 = new XYChart.Series<String, Double>();
//        List<String> string1List = new ArrayList<>();

        for (QSItem item : University1List) {
            String numberString = item.getProperty(searchName);
            try {
                numberString = numberString.replace(",", "");
                double number = Double.parseDouble(numberString);
                lineData1.getData().add(new XYChart.Data<>(item.year, number));
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for string \"" + numberString + "\"");
            }
        }

        for (QSItem item : University2List) {
            String numberString = item.getProperty(searchName);
            try {
                numberString = numberString.replace(",", "");
                double number = Double.parseDouble(numberString);
                lineData2.getData().add(new XYChart.Data<>(item.year, number));
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for string \"" + numberString + "\"");
            }
        }
        lineData1.setName(University1Name);
        lineData2.setName(University2Name);
        lineData.add(lineData1);
        lineData.add(lineData2);
        return lineData;
    }
}
