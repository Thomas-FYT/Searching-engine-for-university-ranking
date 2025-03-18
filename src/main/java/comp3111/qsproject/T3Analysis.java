package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Comparator;


/**
 * The {@code T3Analysis} class is responsible for analyzing QS data items to recommend universities
 * based on specific criteria such as score range, type, and region. It also handles input validation
 * and displays error messages when necessary.
 */
public class T3Analysis {
    private final String topInput;
    private final String bottomInput;
    private final String type;
    private final String region;
    public ObservableList<RecommendItem> RecommendList = FXCollections.observableArrayList();
    public ObservableList<QSItem> draftRecommendList = FXCollections.observableArrayList();

    /**
     * Constructs a {@code T3Analysis} instance that filters and recommends QS data items based on the
     * given parameters. It also handles input errors by displaying appropriate messages.
     *
     * @param top_input The upper boundary of the desired ranking range.
     * @param bottom_input The lower boundary of the desired ranking range.
     * @param type The type of institution (e.g., "University").
     * @param region The geographical region of the institution or "All" for no regional filtering.
     */

     T3Analysis (String top_input, String bottom_input, String type, String region) {
        topInput = top_input;
        bottomInput = bottom_input;
        this.type = type;
        this.region = region;
        /*
            Your Code Here.
            Collect the QSItem which fit the score range, type and region into the RecommendItem.
            Sort the RecommendList by bestRank.
            Hint: QSList.list is a static property and you can use "update" function in RecommendItem.
         */
        Stage newstage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 599, 250);
        int top = Integer.parseInt(top_input);
        int bottom = Integer.parseInt(bottom_input);
        if (top >= bottom) {
            Label label = new Label("Number input error, top should be less than bottom");
            root.getChildren().add(label);
            newstage.setScene(scene);
            newstage.setTitle("Input Error");
            newstage.show();
        } else if ((top < 1) || (bottom > 400)) {
            Label label = new Label("Number input error, top and bottom should be among 1 to 399, both inclusive");
            root.getChildren().add(label);
            newstage.setScene(scene);
            newstage.setTitle("Input Error");
            newstage.show();
        } else {
            for (QSItem item : QSList.list) {
                // Access and use the item here
                if (region.equals("All")) {
                    if ((item.type.equals(type)) && (Integer.parseInt(item.rank) >= Integer.parseInt(top_input)) && (Integer.parseInt(item.rank) <= Integer.parseInt(bottom_input))) {
                        draftRecommendList.add(item);
                    }
                } else {
                    if ((item.type.equals(type)) && (item.region.equals(region)) && (Integer.parseInt(item.rank) >= Integer.parseInt(top_input)) && (Integer.parseInt(item.rank) <= Integer.parseInt(bottom_input))) {
                        draftRecommendList.add(item);
                    }
                }
            }
            for (QSItem item : draftRecommendList) {
                boolean found = false;
                for (int i = 0; i < RecommendList.size(); i++) {
                    RecommendItem existingItem = RecommendList.get(i);
                    if (existingItem.getName().equals(item.getName())) {
                        RecommendList.get(i).update(item);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    RecommendList.add(new RecommendItem(item));
                }
            }
        }
    }

    /**
     * Retrieves the recommended data sorted by the best rank.
     *
     * @return An observable list of {@code RecommendItem} sorted by the best rank of each institution.
     */
    ObservableList<RecommendItem> getRecommendData() {
        // Show the most valuable university
        FXCollections.sort(RecommendList, Comparator.comparingInt(item -> Integer.parseInt(item.getBestRank())));
        return RecommendList;
    }
}
