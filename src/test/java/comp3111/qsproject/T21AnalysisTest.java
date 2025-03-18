package comp3111.qsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class T21AnalysisTest {
    @BeforeEach
    void setUp() {
        QSList.list.clear();
        QSList.list.add(new QSItem(new String[]{"University A", "2021", "1", "95", "", "Country A", "City A", "Region A", "", "Type A", "High", "10:1", "2000", "Large", "1000"}));
        QSList.list.add(new QSItem(new String[]{"University B", "2021", "2", "94", "", "Country B", "City B", "Region B", "", "Type B", "Medium", "15:1", "3000", "Medium", "1500"}));
        QSList.list.add(new QSItem(new String[]{"University A", "2022", "1", "96", "", "Country A", "City A", "Region A", "", "Type A", "High", "9:1", "2100", "Large", "1005"}));
        QSList.list.add(new QSItem(new String[]{"University B", "2023", "2", "93", "", "Country B", "City B", "Region B", "", "Type B", "Medium", "14:1", "3100", "Medium", "1505"}));
        QSList.list.add(new QSItem(new String[]{"University A", "2023", "1", "97", "", "Country A", "City A", "Region A", "", "Type A", "High", "8:1", "2200", "Large", "1010"}));
    }

    @Test
    void testT21Analysis() {
        T21Analysis analysis = new T21Analysis("University A", "University B", Arrays.asList("2021", "2023"));
        assertEquals(2, analysis.University1List.size());
        assertEquals(2, analysis.University2List.size());

        assertTrue(analysis.University1List.get(0).year.equals("2021"));
        assertTrue(analysis.University1List.get(1).year.equals("2023"));
        assertTrue(analysis.University2List.get(0).year.equals("2021"));
        assertTrue(analysis.University2List.get(1).year.equals("2023"));
    }
//    @Test
//    void testGetBarChartData() {
//        // Use Collections.unmodifiableList to create an immutable list
//        List<String> years = Collections.unmodifiableList(Arrays.asList("2021", "2023"));
//
//        T21Analysis analysis = new T21Analysis("University A", "University B", years);
//        XYChart.Series<Double, String> barData = analysis.getBarChartData("score");
//
//        // Check if the data series has the correct size
//        assertEquals(2, barData.getData().size(), "Data series should contain two entries");
//
//        // Extract data points
//        XYChart.Data<Double, String> dataForUni2 = barData.getData().get(0);
//        XYChart.Data<Double, String> dataForUni1 = barData.getData().get(1);
//
//        // Check labels
//        assertEquals("University 2", dataForUni2.getYValue(), "Label for university 2 is incorrect");
//        assertEquals("University 1", dataForUni1.getYValue(), "Label for university 1 is incorrect");
//
//        // Check calculated averages
//        assertEquals(93.5, dataForUni2.getXValue(), 0.01, "Average score for University 2 is incorrect");
//        assertEquals((95.0 + 97.0) / 2, dataForUni1.getXValue(), 0.01, "Average score for University 1 is incorrect");
//    }
//    @Test
//    void testGetLineChartData() {
//        T21Analysis analysis = new T21Analysis("University A", "University B", Arrays.asList("2021", "2023"));
//        XYChart.Series<Double, String> barData = analysis.getBarChartData("score");
//        List<XYChart.Series<String, Double>> result = analysis.getLineChartData("score");
//
//        // Check if two series are returned
//        assertEquals(2, result.size(), "Should return two series");
//
//        // Check series names
//        assertEquals("University A", result.get(0).getName());
//        assertEquals("University B", result.get(1).getName());
//
//        // Check data points in first series
//        assertEquals("2021", result.get(0).getData().get(0).getXValue());
//        assertEquals(95.0, result.get(0).getData().get(0).getYValue());
//
//        assertEquals("2023", result.get(0).getData().get(1).getXValue());
//        assertEquals(97.0, result.get(0).getData().get(1).getYValue());
//
//        // Check data points in second series
//        assertEquals("2021", result.get(1).getData().get(0).getXValue());
//        assertEquals(94.0, result.get(1).getData().get(0).getYValue());
//
//        assertEquals("2023", result.get(1).getData().get(1).getXValue());
//        assertEquals(93.0, result.get(1).getData().get(1).getYValue());
//    }
}