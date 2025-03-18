package comp3111.qsproject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

public class T1ControllerTest extends ApplicationTest {

    @Test
    public void T1_testOnClickSearch_ValidInput() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                // Setup
                controller.t1YearChoiceBox.setValue("2020");
                controller.t1PieChartChoiceBox.setValue("Total Score");
                controller.t1BarChartChoiceBox.setValue("Research Score");

                // Action
//                controller.T1_onClickSearch();

                // Verify
                Assertions.assertEquals("2020", controller.t1YearChoiceBox.getValue());
                Assertions.assertNotNull(controller.t1DataTable.getItems());
                Assertions.assertTrue(controller.t1DataTable.getItems().isEmpty());
                Assertions.assertNotNull(controller.t1PieChart.getData());
                Assertions.assertTrue(controller.t1PieChart.getData().isEmpty());
                Assertions.assertNotNull(controller.t1BarChart.getData());
                Assertions.assertTrue(controller.t1BarChart.getData().isEmpty());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void T1_testOnClickSearch_InvalidInput() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                // Setup
                controller.t1YearChoiceBox.setValue("");

                // Action
                controller.T1_onClickSearch();

                // Verify
                Assertions.assertThrows(Exception.class, () -> {
                    Stage stage = (Stage) controller.t1YearChoiceBox.getScene().getWindow();
                    Assertions.assertTrue(stage.isShowing());
                    Label label = (Label) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
                    Assertions.assertTrue(label.getText().contains("You haven't decide which year to plot!"));
                });

                // Verify that no data is set
                Assertions.assertTrue(controller.t1DataTable.getItems().isEmpty());
                Assertions.assertTrue(controller.t1PieChart.getData().isEmpty());
                Assertions.assertTrue(controller.t1BarChart.getData().isEmpty());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}