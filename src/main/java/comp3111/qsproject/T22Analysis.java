package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.*;

/**
 * The {@code T22Analysis} class is used to analyze QS data for two countries or regions over specified years.
 * It supports generating both bar and line charts to compare various data properties between the two regions.
 */
public class T22Analysis {
    public ObservableList<QSItem> CountryRegion1List = FXCollections.observableArrayList();
    public ObservableList<QSItem> CountryRegion2List = FXCollections.observableArrayList();
    private String CountryRegion1Name;
    private String CountryRegion2Name;

    /**
     * Constructs a new {@code T22Analysis} instance for the specified countries or regions over given years.
     * Initializes lists with QS data items that match the specified regions and years.
     *
     * @param country_region_1 the name of the first country or region
     * @param country_region_2 the name of the second country or region
     * @param years the list of years for which the QS data should be analyzed
     */
    T22Analysis(String country_region_1, String country_region_2, List<String> years) {
        /*
            Your Code Here.
            Collect the QSItem with corresponding years and country/region into two country/region lists.
            Sort country/region lists by the years.
            Hint: QSList.list is a static property.
         */
        CountryRegion1Name = country_region_1;
        CountryRegion2Name = country_region_2;
        for (QSItem item : QSList.list) {
            if ((item.country.equals(CountryRegion1Name)) && (years.contains(item.year))) {
                CountryRegion1List.add(item);
            }
        }
        for (QSItem item : QSList.list) {
            if ((item.country.equals(CountryRegion2Name)) && (years.contains(item.year))) {
                CountryRegion2List.add(item);
            }
        }
    }

    /**
     * Computes and returns bar chart data for a specified property across the two regions.
     * The bar chart displays the average values of the specified property for each region.
     *
     * @param searchName the property name to be averaged and displayed (e.g., "score")
     * @return an {@code XYChart.Series} object containing the average values for the specified property for each region
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
              Average score of country/region2, "Country/Region 2"
              Average score of country/region1, "Country/Region 1"
            ]

            There are some "dirty data" in csv.
            For example, the string "3,143" or "3.143" can not transfer to Integer or Double directly.
            Careful process these data.
         */
        List<String> string1List = new ArrayList<>();
        for (QSItem item : CountryRegion1List) {
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
        for (QSItem item : CountryRegion2List) {
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
        barData.getData().add(new XYChart.Data<>(average2, "Country/Region 2"));
        barData.getData().add(new XYChart.Data<>(average1, "Country/Region 1"));
        return barData;
    }

    /**
     * Computes and returns line chart data for a specified property across multiple years for the two regions.
     * Each line in the chart represents the changes in the specified property over the selected years for one region.
     *
     * @param searchName the property name to be analyzed over time (e.g., "score")
     * @return a list containing two {@code XYChart.Series} objects, each representing the time series data for one region
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

        List<Double> sums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (QSItem item : CountryRegion1List) {
            String numberString = item.getProperty(searchName);
            try {
                // Remove commas and periods as specified
                numberString = numberString.replace(",", "");
                double number = Double.parseDouble(numberString);

                // Find existing data for the year
                int index = -1;
                for (int i = 0; i < lineData1.getData().size(); i++) {
                    if (lineData1.getData().get(i).getXValue().equals(item.year)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    // Update existing data
                    double newSum = sums.get(index) + number;
                    int newCount = counts.get(index) + 1;
                    sums.set(index, newSum);
                    counts.set(index, newCount);
                    lineData1.getData().get(index).setYValue(newSum / newCount);
                } else {
                    // Add new data point
                    lineData1.getData().add(new XYChart.Data<>(item.year, number));
                    sums.add(number);
                    counts.add(1);
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for string \"" + numberString + "\"");
            }
        }

        for (QSItem item : CountryRegion2List) {
            String numberString = item.getProperty(searchName);
            try {
                // Remove commas and periods as specified
                numberString = numberString.replace(",", "");
                double number = Double.parseDouble(numberString);

                // Find existing data for the year
                int index = -1;
                for (int i = 0; i < lineData2.getData().size(); i++) {
                    if (lineData2.getData().get(i).getXValue().equals(item.year)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    // Update existing data
                    double newSum = sums.get(index) + number;
                    int newCount = counts.get(index) + 1;
                    sums.set(index, newSum);
                    counts.set(index, newCount);
                    lineData2.getData().get(index).setYValue(newSum / newCount);
                } else {
                    // Add new data point
                    lineData2.getData().add(new XYChart.Data<>(item.year, number));
                    sums.add(number);
                    counts.add(1);
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid number format for string \"" + numberString + "\"");
            }
        }
        lineData1.setName(CountryRegion1Name);
        lineData2.setName(CountryRegion2Name);
        lineData.add(lineData1);
        lineData.add(lineData2);
        return lineData;
    }
}
