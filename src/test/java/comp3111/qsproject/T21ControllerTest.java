package comp3111.qsproject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

public class T21ControllerTest extends ApplicationTest {

    @Test
    public void T21_testOnClickCompare_ValidInput() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                // Setup
                controller.t2University1ChoiceBox.setValue("University1");
                controller.t2University2ChoiceBox.setValue("University2");
                controller.t22017CheckBox.setSelected(true);
                controller.t22018CheckBox.setSelected(false);
                controller.t22019CheckBox.setSelected(true);
                controller.t22020CheckBox.setSelected(false);
                controller.t22021CheckBox.setSelected(true);
                controller.t22022CheckBox.setSelected(true);

                // Action
                controller.T21_onClickCompare();

                // Verify
                Assertions.assertEquals("University1", controller.t2University1ChoiceBox.getValue());
                Assertions.assertEquals("University2", controller.t2University2ChoiceBox.getValue());
                Assertions.assertTrue(controller.t22017CheckBox.isSelected());
                Assertions.assertTrue(controller.t22019CheckBox.isSelected());
                Assertions.assertTrue(controller.t22021CheckBox.isSelected());
                Assertions.assertTrue(controller.t22022CheckBox.isSelected());

                // Verify charts are updated
                Assertions.assertFalse(controller.t21RankBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t21ScoreBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t21FacultyBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t21InternationalBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t21SFRBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t21LineChart.getData().isEmpty());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void T21_testOnClickCompare_InvalidInput() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                // Setup
                controller.t2University1ChoiceBox.setValue("");
                controller.t2University2ChoiceBox.setValue("");
                // No years selected

                // Action
                controller.T21_onClickCompare();

                // Verify
                Assertions.assertTrue(controller.t2University1ChoiceBox.getValue().isEmpty());
                Assertions.assertTrue(controller.t2University2ChoiceBox.getValue().isEmpty());
                Assertions.assertFalse(controller.t22017CheckBox.isSelected());
                Assertions.assertFalse(controller.t22018CheckBox.isSelected());
                Assertions.assertFalse(controller.t22019CheckBox.isSelected());
                Assertions.assertFalse(controller.t22020CheckBox.isSelected());
                Assertions.assertFalse(controller.t22021CheckBox.isSelected());
                Assertions.assertFalse(controller.t22022CheckBox.isSelected());

                // Verify charts are not updated
                Assertions.assertTrue(controller.t21RankBarChart.getData().isEmpty());
                Assertions.assertTrue(controller.t21ScoreBarChart.getData().isEmpty());
                Assertions.assertTrue(controller.t21FacultyBarChart.getData().isEmpty());
                Assertions.assertTrue(controller.t21InternationalBarChart.getData().isEmpty());
                Assertions.assertTrue(controller.t21SFRBarChart.getData().isEmpty());
                Assertions.assertTrue(controller.t21LineChart.getData().isEmpty());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}