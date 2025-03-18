package comp3111.qsproject;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class T3AnalysisTest {

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        // Initialize the JavaFX platform
        final CountDownLatch latch = new CountDownLatch(1);
//        Platform.startup(() -> { latch.countDown(); });
//        latch.await(); // Wait until JavaFX is initialized
    }

    @BeforeEach
    public void setup() throws InterruptedException {
        CountDownLatch setupLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            // Setup code that initializes the test environment
            QSList.list.clear();
            QSList.list.add(new QSItem(new String[]{"University A", "2023", "1", "95", "", "USA", "CityA", "North America", "", "Public", "High", "15:1", "3000", "Large", "100"}));
            QSList.list.add(new QSItem(new String[]{"University B", "2023", "2", "90", "", "UK", "CityB", "Europe", "", "Private", "Medium", "20:1", "2000", "Medium", "200"}));
            QSList.list.add(new QSItem(new String[]{"University C", "2023", "3", "85", "", "China", "CityC", "Asia", "", "Public", "Low", "25:1", "1000", "Small", "300"}));
            QSList.list.add(new QSItem(new String[]{"University D", "2023", "4", "80", "", "Canada", "CityD", "North America", "", "Private", "High", "10:1", "2500", "Large", "400"}));
            QSList.list.add(new QSItem(new String[]{"University D", "2023", "4", "80", "", "Canada", "CityD", "North America", "", "Private", "High", "10:1", "2500", "Large", "400"}));

            setupLatch.countDown();
        });

        setupLatch.await(); // Wait for the setup to complete on the JavaFX thread
    }

    @Test
    public void testRecommendationFilteringAndSorting() throws InterruptedException {
        final CountDownLatch testLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                T3Analysis analysis = new T3Analysis("1", "3", "Public", "All");
                ObservableList<RecommendItem> results = analysis.getRecommendData();
                assertEquals(2, results.size(), "Should have two recommended items");
                assertEquals("University A", results.get(0).getName(), "First item should be University A");
                assertEquals("University C", results.get(1).getName(), "Second item should be University C");
            } finally {
                testLatch.countDown();
            }
        });

        testLatch.await(); // Wait for the test to complete on the JavaFX thread
    }

    @Test
    public void testInputErrorHandling() throws InterruptedException {
        final CountDownLatch testLatch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                T3Analysis analysis = new T3Analysis("300", "100", "Public", "All");
                assertTrue(analysis.getRecommendData().isEmpty(), "RecommendList should be empty due to input error");

                analysis = new T3Analysis("0", "400", "Public", "All");
                assertTrue(analysis.getRecommendData().isEmpty(), "RecommendList should be empty due to input error");
            } finally {
                testLatch.countDown();
            }
        });

        testLatch.await(); // Wait for the test to complete on the JavaFX thread
    }
//=======
//package comp3111.qsproject;
//
//import javafx.application.Platform;
//import javafx.collections.ObservableList;
//import org.junit.jupiter.api.*;
//
//import java.util.concurrent.CountDownLatch;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class T3AnalysisTest {
//
//    @BeforeAll
//    public static void initJavaFX() throws InterruptedException {
//        // Initialize the JavaFX platform
//        final CountDownLatch latch = new CountDownLatch(1);
////        Platform.startup(() -> { latch.countDown(); });
////        latch.await(); // Wait until JavaFX is initialized
//    }
//
//    @BeforeEach
//    public void setup() throws InterruptedException {
//        CountDownLatch setupLatch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            // Setup code that initializes the test environment
//            QSList.list.clear();
//            QSList.list.add(new QSItem(new String[]{"University A", "2023", "1", "95", "", "USA", "CityA", "North America", "", "Public", "High", "15:1", "3000", "Large", "100"}));
//            QSList.list.add(new QSItem(new String[]{"University B", "2023", "2", "90", "", "UK", "CityB", "Europe", "", "Private", "Medium", "20:1", "2000", "Medium", "200"}));
//            QSList.list.add(new QSItem(new String[]{"University C", "2023", "3", "85", "", "China", "CityC", "Asia", "", "Public", "Low", "25:1", "1000", "Small", "300"}));
//            QSList.list.add(new QSItem(new String[]{"University D", "2023", "4", "80", "", "Canada", "CityD", "North America", "", "Private", "High", "10:1", "2500", "Large", "400"}));
//            QSList.list.add(new QSItem(new String[]{"University D", "2023", "4", "80", "", "Canada", "CityD", "North America", "", "Private", "High", "10:1", "2500", "Large", "400"}));
//
//            setupLatch.countDown();
//        });
//
//        setupLatch.await(); // Wait for the setup to complete on the JavaFX thread
//    }
//
//    @Test
//    public void testRecommendationFilteringAndSorting() throws InterruptedException {
//        final CountDownLatch testLatch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            try {
//                T3Analysis analysis = new T3Analysis("1", "3", "Public", "All");
//                ObservableList<RecommendItem> results = analysis.getRecommendData();
//                assertEquals(2, results.size(), "Should have two recommended items");
//                assertEquals("University A", results.get(0).getName(), "First item should be University A");
//                assertEquals("University C", results.get(1).getName(), "Second item should be University C");
//            } finally {
//                testLatch.countDown();
//            }
//        });
//
//        testLatch.await(); // Wait for the test to complete on the JavaFX thread
//    }
//
//    @Test
//    public void testInputErrorHandling() throws InterruptedException {
//        final CountDownLatch testLatch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            try {
//                T3Analysis analysis = new T3Analysis("300", "100", "Public", "All");
//                assertTrue(analysis.getRecommendData().isEmpty(), "RecommendList should be empty due to input error");
//
//                analysis = new T3Analysis("0", "400", "Public", "All");
//                assertTrue(analysis.getRecommendData().isEmpty(), "RecommendList should be empty due to input error");
//            } finally {
//                testLatch.countDown();
//            }
//        });
//
//        testLatch.await(); // Wait for the test to complete on the JavaFX thread
    }
