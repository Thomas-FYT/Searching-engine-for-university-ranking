package comp3111.qsproject;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class T22ControllerTest {

    @Test
    public void T22_testOnClickCompare() throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
        Platform.runLater(() -> {
            try {
                fxmlLoader.load();
                Controller controller = fxmlLoader.getController();

                // Setup
                controller.t2CountryRegion1ChoiceBox.setValue("Country1");
                controller.t2CountryRegion2ChoiceBox.setValue("Country2");
                controller.t22017CheckBox2.setSelected(true);
                controller.t22018CheckBox2.setSelected(false);
                controller.t22019CheckBox2.setSelected(true);
                controller.t22020CheckBox2.setSelected(false);
                controller.t22021CheckBox2.setSelected(true);
                controller.t22022CheckBox2.setSelected(false);

                // Action
                controller.T22_onClickCompare();

                // Verify
                Assertions.assertEquals("Country1", controller.t2CountryRegion1ChoiceBox.getValue());
                Assertions.assertEquals("Country2", controller.t2CountryRegion2ChoiceBox.getValue());
                Assertions.assertTrue(controller.t22017CheckBox2.isSelected());
                Assertions.assertFalse(controller.t22018CheckBox2.isSelected());
                Assertions.assertTrue(controller.t22019CheckBox2.isSelected());
                Assertions.assertFalse(controller.t22020CheckBox2.isSelected());
                Assertions.assertTrue(controller.t22021CheckBox2.isSelected());
                Assertions.assertFalse(controller.t22022CheckBox2.isSelected());

                // Verify charts are updated
                Assertions.assertFalse(controller.t22RankBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t22ScoreBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t22FacultyBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t22InternationalBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t22SFRBarChart.getData().isEmpty());
                Assertions.assertFalse(controller.t22LineChart.getData().isEmpty());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}