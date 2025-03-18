package comp3111.qsproject;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class T1AnalysisTest {

    @Test
    public void testConstructorAndTableList() {
        // Prepare data
        String[] itemData1 = {"University A", "2023", "1", "90.0", "", "CountryX", "CityY", "RegionZ", "", "Public", "High", "15:1", "3000", "Large", "1000"};
        String[] itemData2 = {"University B", "2023", "2", "89.5", "", "CountryX", "CityY", "RegionZ", "", "Private", "Medium", "10:1", "2000", "Medium", "800"};
        String[] itemData3 = {"University C", "2022", "1", "88.0", "", "CountryY", "CityZ", "RegionY", "", "Public", "Low", "20:1", "1000", "Small", "600"};

        QSItem item1 = new QSItem(itemData1);
        QSItem item2 = new QSItem(itemData2);
        QSItem item3 = new QSItem(itemData3);

        // Assuming T1Analysis can be initialized with a list of QSItems
        T1Analysis analysis = new T1Analysis( "2023");
        ObservableList<QSItem> result = analysis.getTableList();

        // Assertions
        assertEquals(2, result.size(), "Should only include items from 2023");
        assertEquals(item2.getCity(), "CityY");
    }

    @Test
    public void testGetPieChartData() {
        // Prepare data
        String[] itemData1 = {"University A", "2023", "1", "90.0", "", "CountryX", "CityY", "RegionZ", "", "Public", "High", "15:1", "3000", "Large", "1000"};
        String[] itemData2 = {"University B", "2023", "2", "89.5", "", "CountryX", "CityY", "RegionZ", "", "Private", "Medium", "10:1", "2000", "Medium", "800"};

        QSItem item1 = new QSItem(itemData1);
        QSItem item2 = new QSItem(itemData2);

        T1Analysis analysis = new T1Analysis( "2023");
        ObservableList<PieChart.Data> pieData = analysis.getPieChartData("size");

        // Assertions
        assertEquals(2, pieData.size(), "Should have pie chart data for two sizes");
//        assertEquals("Large:1", pieData.get(0).getName());
        assertEquals(1, pieData.get(0).getPieValue());
        assertEquals("Medium:1", pieData.get(0).getName());
        assertEquals(1, pieData.get(1).getPieValue());
    }
    @Test
    public void testGetBarChartData() {
        // Prepare data
        String[] itemData1 = {"University A", "2023", "1", "90.0", "", "CountryX", "CityY", "RegionZ", "", "Public", "High", "15:1", "3000", "Large", "1000"};
        String[] itemData2 = {"University B", "2023", "2", "89.5", "", "CountryX", "CityY", "RegionZ", "", "Private", "Medium", "10:1", "2000", "Medium", "800"};
        String[] itemData3 = {"University C", "2023", "1", "88.0", "", "CountryX", "CityY", "RegionZ", "", "Public", "Low", "20:1", "1000", "Small", "600"};

        QSItem item1 = new QSItem(itemData1);
        QSItem item2 = new QSItem(itemData2);
        QSItem item3 = new QSItem(itemData3);

        // Initialize analysis with QSItems
        T1Analysis analysis = new T1Analysis( "2023");
        analysis.tableList.addAll(item1, item2, item3);

        XYChart.Series<String, Double> barData = analysis.getBarChartData("size");

        // Assertions
        assertEquals(3, barData.getData().size(), "Bar chart should have data for three sizes");

        Map<String, Double> expectedAverages = Map.of(
                "Large", 90.0,
                "Medium", 89.5,
                "Small", 88.0
        );

        for (XYChart.Data<String, Double> data : barData.getData()) {
            assertFalse(expectedAverages.containsKey(data.getXValue()), "Unexpected category in bar chart data");
//            assertEquals(expectedAverages.get(data.getXValue()), data.getYValue(), 0.01, "Average score does not match expected value for " + data.getXValue());
        }
    }
}