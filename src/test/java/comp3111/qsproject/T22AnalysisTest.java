package comp3111.qsproject;

import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class T22AnalysisTest {

    @BeforeEach
    void setUp() {
        QSList.list.clear();  // Ensure the list is empty before each test

        // Add test data with the expected format of 15 elements per array
        QSList.list.add(new QSItem(new String[] {"University A", "2021", "#1", "88.0", "", "CountryX", "CityX", "RegionX", "", "Public", "High", "10:1", "2000", "Large", "500"}));
        QSList.list.add(new QSItem(new String[] {"University B", "2021", "#2", "90.0", "", "CountryY", "CityY", "RegionY", "", "Private", "Medium", "15:1", "1500", "Medium", "300"}));
        QSList.list.add(new QSItem(new String[] {"University C", "2022", "#3", "85.0", "", "CountryX", "CityX", "RegionX", "", "Public", "Low", "20:1", "1000", "Small", "200"}));
        QSList.list.add(new QSItem(new String[] {"University D", "2022", "#4", "92.0", "", "CountryY", "CityY", "RegionY", "", "Private", "Very High", "5:1", "2500", "Very Large", "800"}));
        QSList.list.add(new QSItem(new String[] {"University A", "2021", "#1", "88.0", "", "CountryX", "CityX", "RegionX", "", "Public", "High", "10:1", "2000", "Large", "500"}));

    }

    @AfterEach
    void tearDown() {
        QSList.list.clear();  // Clear the list after each test
    }

    @Test
    void testConstructorFillsListsCorrectly() {
        List<String> years = Arrays.asList("2021", "2022");
        T22Analysis analysis = new T22Analysis("CountryX", "CountryY", years);

        // Assert that the correct items are in each list
        assertEquals(3, analysis.CountryRegion1List.size());
        assertEquals(2, analysis.CountryRegion2List.size());
        assertTrue(analysis.CountryRegion1List.stream().allMatch(item -> item.country.equals("CountryX")));
        assertTrue(analysis.CountryRegion2List.stream().allMatch(item -> item.country.equals("CountryY")));

        // Check if sorting by years, if implemented
        assertTrue(analysis.CountryRegion1List.get(0).year.compareTo(analysis.CountryRegion1List.get(1).year) <= 0);
        assertTrue(analysis.CountryRegion2List.get(0).year.compareTo(analysis.CountryRegion2List.get(1).year) <= 0);
    }
    @Test
    void testGetLineChartData() {
        List<String> years = Arrays.asList("2021", "2022");
        T22Analysis analysis = new T22Analysis("CountryX", "CountryY", years);
        List<XYChart.Series<String, Double>> chartData = analysis.getLineChartData("score");

        // Assert there are two lines in the chart
        assertEquals(2, chartData.size());

        // Check names of the series
        assertEquals("CountryX", chartData.get(0).getName());
        assertEquals("CountryY", chartData.get(1).getName());

        // Check the data points for CountryX
        assertEquals(2, chartData.get(0).getData().size()); // Check the number of data points
        XYChart.Data<String, Double> data2021CountryX = chartData.get(0).getData().stream().filter(d -> d.getXValue().equals("2021")).findFirst().orElse(null);
        XYChart.Data<String, Double> data2022CountryX = chartData.get(0).getData().stream().filter(d -> d.getXValue().equals("2022")).findFirst().orElse(null);

        // Assert the values considering the dirty data "3,143" should be treated as 3143.0
        assertEquals(88.0, data2021CountryX.getYValue(), 0.1);
        assertEquals(85.0, data2022CountryX.getYValue(),0.1);

        // Check the data points for CountryY
        assertEquals(2, chartData.get(1).getData().size()); // Check the number of data points
        XYChart.Data<String, Double> data2021CountryY = chartData.get(1).getData().stream().filter(d -> d.getXValue().equals("2021")).findFirst().orElse(null);
        XYChart.Data<String, Double> data2022CountryY = chartData.get(1).getData().stream().filter(d -> d.getXValue().equals("2022")).findFirst().orElse(null);

        // Assert the values
        assertEquals(90.0, data2021CountryY.getYValue(), 0.1);
        assertEquals(92.0, data2022CountryY.getYValue(), 0.1);
    }
    @Test
    void testGetBarChartData() {
        List<String> years = Arrays.asList("2021", "2022");
        T22Analysis analysis = new T22Analysis("CountryX", "CountryY", years);
        XYChart.Series<Double, String> barData = analysis.getBarChartData("score");

        assertEquals(2, barData.getData().size());  // Expect two data points, one for each country/region
        assertEquals("Country/Region 2", barData.getData().get(0).getYValue());  // First data point should be for Country/Region 2
        assertEquals("Country/Region 1", barData.getData().get(1).getYValue());  // Second data point should be for Country/Region 1

        // Test for correct averages
        double expectedAverage1 = (88.0 + 85.0 + 88.0) / 3;  // Average for CountryX
        double expectedAverage2 = (90.0 + 92.0) / 2;  // Average for CountryY
        assertEquals(expectedAverage2, barData.getData().get(0).getXValue(), 0.001);  // Check average score for Country/Region 2
        assertEquals(expectedAverage1, barData.getData().get(1).getXValue(), 0.001);  // Check average score for Country/Region 1
    }
}

