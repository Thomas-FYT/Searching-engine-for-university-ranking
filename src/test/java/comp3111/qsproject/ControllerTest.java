//package comp3111.qsproject;
//
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.Label;
//import org.junit.Before;
//import org.junit.Test;
//
//public class ControllerTest {
//
//    private Controller controller;
//
//    @Before
//    public void setUp() throws Exception {
//        // Initialize the JavaFX toolkit
//        Platform.startup(() -> {});
//
//        controller = new Controller();
//
//        // Manually instantiate JavaFX components
//        controller.t1YearChoiceBox = new ChoiceBox<>();
//        controller.t1PieChartChoiceBox = new ChoiceBox<>();
//        controller.t1PieChartLabel = new Label();
//        controller.t1BarChartChoiceBox = new ChoiceBox<>();
//        controller.t1BarChartLabel = new Label();
//
//        controller.t2University1ChoiceBox = new ChoiceBox<>();
//        controller.t2University2ChoiceBox = new ChoiceBox<>();
//        controller.t2CountryRegion1ChoiceBox = new ChoiceBox<>();
//        controller.t2CountryRegion2ChoiceBox = new ChoiceBox<>();
//
//        controller.t3RegionChoiceBox = new ChoiceBox<>();
//        controller.t3TypeChoiceBox = new ChoiceBox<>();
//
//        // Simulate QSList data
//        QSList.country = FXCollections.observableArrayList("USA", "Canada");
//        QSList.university = FXCollections.observableArrayList("MIT", "Harvard");
//        QSList.initialize();
//    }
//
//    @Test
//    public void testInitialize() {
//        controller.initialize();
//
//        // Check if the choice boxes are set with the expected values
//        assert controller.t1PieChartChoiceBox.getValue().equals("size");
//        assert controller.t1BarChartChoiceBox.getValue().equals("size");
//
//        assert !controller.t2University1ChoiceBox.getItems().isEmpty();
//        assert !controller.t2CountryRegion1ChoiceBox.getItems().isEmpty();
//
//        assert controller.t3RegionChoiceBox.getItems().contains("All");
//        assert controller.t3TypeChoiceBox.getItems().contains("Public");
//    }
//}

package comp3111.qsproject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class ControllerTest {

    @BeforeAll
    public static void startJavaFXRuntime() {
        Platform.startup(() -> {});
    }

    @Test
    public void T3_test1() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                // Set text fields safely on the FX thread
                controller.t3TopRankTextField.setText("");
                controller.t3BottomRankTextField.setText("50");
                controller.t3TypeChoiceBox.setValue("Public");
                controller.t3RegionChoiceBox.setValue("Asia");

                controller.T3_onClickRecommend();

                // Use assertions within Platform.runLater if possible or use another synchronization mechanism
                Assertions.assertEquals("", controller.t3TopRankTextField.getText());
                Assertions.assertEquals("50", controller.t3BottomRankTextField.getText());
                Assertions.assertEquals("Public", controller.t3TypeChoiceBox.getValue());
                Assertions.assertEquals("Asia", controller.t3RegionChoiceBox.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Wait for Platform.runLater to finish
        waitForRunLater();
    }

    @Test
    public void T3_test2() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                controller.t3TopRankTextField.setText("a");
                controller.t3BottomRankTextField.setText("b");
                controller.t3TypeChoiceBox.setValue("Public");
                controller.t3RegionChoiceBox.setValue("Asia");

                controller.T3_onClickRecommend();

                Assertions.assertEquals("a", controller.t3TopRankTextField.getText());
                Assertions.assertEquals("b", controller.t3BottomRankTextField.getText());
                Assertions.assertEquals("Public", controller.t3TypeChoiceBox.getValue());
                Assertions.assertEquals("Asia", controller.t3RegionChoiceBox.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Wait for all JavaFX operations to complete before concluding the test
        waitForRunLater();
    }

    @Test
    public void T3_test3() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                controller.t3TopRankTextField.setText("-5");
                controller.t3BottomRankTextField.setText("-1");
                controller.t3TypeChoiceBox.setValue("Public");
                controller.t3RegionChoiceBox.setValue("Asia");

                controller.T3_onClickRecommend();

                // Assertions to check the state after all interactions
                Assertions.assertEquals("-5", controller.t3TopRankTextField.getText());
                Assertions.assertEquals("-1", controller.t3BottomRankTextField.getText());
                Assertions.assertEquals("Public", controller.t3TypeChoiceBox.getValue());
                Assertions.assertEquals("Asia", controller.t3RegionChoiceBox.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Wait for all JavaFX operations to complete before concluding the test
        waitForRunLater();
    }

    @Test
    public void T3_test4() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                controller.t3TopRankTextField.setText("50");
                controller.t3BottomRankTextField.setText("3");
                controller.t3TypeChoiceBox.setValue("Public");
                controller.t3RegionChoiceBox.setValue("Asia");

                controller.T3_onClickRecommend();

                Assertions.assertEquals("50", controller.t3TopRankTextField.getText());
                Assertions.assertEquals("3", controller.t3BottomRankTextField.getText());
                Assertions.assertEquals("Public", controller.t3TypeChoiceBox.getValue());
                Assertions.assertEquals("Asia", controller.t3RegionChoiceBox.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Wait for all JavaFX operations to complete before concluding the test
        waitForRunLater();
    }

    @Test
    public void T3_test5() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                controller.t3TopRankTextField.setText("5");
                controller.t3BottomRankTextField.setText("60");
                controller.t3TypeChoiceBox.setValue("Public");
                controller.t3RegionChoiceBox.setValue("Asia");

                controller.T3_onClickRecommend();

                // Assertions to check the state after all interactions
                Assertions.assertEquals("5", controller.t3TopRankTextField.getText());
                Assertions.assertEquals("60", controller.t3BottomRankTextField.getText());
                Assertions.assertEquals("Public", controller.t3TypeChoiceBox.getValue());
                Assertions.assertEquals("Asia", controller.t3RegionChoiceBox.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Wait for all JavaFX operations to complete before concluding the test
        waitForRunLater();
    }
    private void waitForRunLater() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await(5, TimeUnit.SECONDS); // waits for the actions to be completed
    }
}