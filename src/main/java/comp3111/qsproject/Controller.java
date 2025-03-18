package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class Controller {

    /* T1 Controller */
    public TableView<QSItem> t1DataTable;

    @FXML
    public ChoiceBox<String> t1YearChoiceBox;

    @FXML
    public BarChart<String, Double> t1BarChart;

    @FXML
    public TableColumn<QSItem, String> t1Rank;

    @FXML
    public TableColumn<QSItem, String> t1University;

    @FXML
    public TableColumn<QSItem, String> t1Score;

    @FXML
    public TableColumn<QSItem, String> t1Country;

    @FXML
    public TableColumn<QSItem, String> t1City;

    @FXML
    public TableColumn<QSItem, String> t1Type;

    @FXML
    public PieChart t1PieChart;

    @FXML
    public ChoiceBox<String> t1PieChartChoiceBox;

    @FXML
    public Label t1PieChartLabel;

    @FXML
    public ChoiceBox<String> t1BarChartChoiceBox;

    @FXML
    public Label t1BarChartLabel;

    @FXML
    public CategoryAxis t1BarChartTypeXaxis;

    /* T2 Controller */
    @FXML
    public ChoiceBox<String> t2University1ChoiceBox;
    @FXML
    public ChoiceBox<String> t2University2ChoiceBox;
    @FXML
    public ChoiceBox<String> t2CountryRegion1ChoiceBox;
    @FXML
    public ChoiceBox<String> t2CountryRegion2ChoiceBox;

    @FXML
    public CheckBox t22017CheckBox;
    @FXML
    public CheckBox t22018CheckBox;
    @FXML
    public CheckBox t22019CheckBox;
    @FXML
    public CheckBox t22020CheckBox;
    @FXML
    public CheckBox t22021CheckBox;
    @FXML
    public CheckBox t22022CheckBox;
    @FXML
    public CheckBox t22017CheckBox2;
    @FXML
    public CheckBox t22018CheckBox2;
    @FXML
    public CheckBox t22019CheckBox2;
    @FXML
    public CheckBox t22020CheckBox2;
    @FXML
    public CheckBox t22021CheckBox2;
    @FXML
    public CheckBox t22022CheckBox2;

    @FXML
    public BarChart<Double, String> t21RankBarChart;
    @FXML
    public BarChart<Double, String> t21ScoreBarChart;
    @FXML
    public BarChart<Double, String> t21FacultyBarChart;
    @FXML
    public BarChart<Double, String> t21InternationalBarChart;
    @FXML
    public BarChart<Double, String> t21SFRBarChart;
    @FXML
    public LineChart<String, Double> t21LineChart;

    @FXML
    public BarChart<Double, String> t22RankBarChart;
    @FXML
    public BarChart<Double, String> t22ScoreBarChart;
    @FXML
    public BarChart<Double, String> t22FacultyBarChart;
    @FXML
    public BarChart<Double, String> t22InternationalBarChart;
    @FXML
    public BarChart<Double, String> t22SFRBarChart;
    @FXML
    public LineChart<String, Double> t22LineChart;

    /* T3 Controller */

    @FXML
    public TextField t3TopRankTextField;
    @FXML
    public TextField t3BottomRankTextField;
    @FXML
    public ChoiceBox<String> t3TypeChoiceBox;
    @FXML
    public ChoiceBox<String> t3RegionChoiceBox;
    @FXML
    public TableView<RecommendItem> t3TableView;

    @FXML
    public TableColumn<RecommendItem, String> t3University;

    @FXML
    public TableColumn<RecommendItem, String> t3BestYear;

    @FXML
    public TableColumn<RecommendItem, String> t3BestRank;

    @FXML
    public TableColumn<RecommendItem, String> t3RecentYear;

    @FXML
    public TableColumn<RecommendItem, String> t3RecentRank;

    ObservableList<String> yearList = FXCollections.observableArrayList("2017", "2018", "2019", "2020", "2021", "2022");  // ovserved什么的是一个特定的数据类型，可观察list，即可记录list的变化情况
    ObservableList<String> stringPropertyList = FXCollections.observableArrayList("country", "region", "size", "type", "researchOutput");


    @FXML
    void initialize() {
        // Whole Program Information
        QSList.initialize();
//        QSList.initMap();
        // T1
        t1YearChoiceBox.setItems(yearList);
        t1YearChoiceBox.setValue("");
        t1PieChartChoiceBox.setItems(stringPropertyList);
        t1PieChartChoiceBox.setValue("size");
        t1PieChartLabel.setText("");
        t1BarChartChoiceBox.setItems(stringPropertyList);
        t1BarChartChoiceBox.setValue("size");
        t1BarChartLabel.setText("");
        // T2
        /*
            Your Code Here.
            1. Initialize the Choice boxes of university.
            2. Initialize the Choice boxes of country/region.
            3. For choice boxes of country/region,
                you need to add a blank or "All" option representing selection of all the country/region.
         */
        Set<String> CountrySet = new LinkedHashSet<>(QSList.country);
        ObservableList<String> country_without_duplicate = FXCollections.observableArrayList(CountrySet);
        Set<String> UniversitySet = new LinkedHashSet<>(QSList.university);
        ObservableList<String> university_without_duplicate = FXCollections.observableArrayList(UniversitySet);
        t2University1ChoiceBox.setItems(university_without_duplicate);
        t2University2ChoiceBox.setItems(university_without_duplicate);
        t2CountryRegion1ChoiceBox.setItems(country_without_duplicate);
        t2CountryRegion2ChoiceBox.setItems(country_without_duplicate);


        // T3
        /*
            Your Code Here.
            1. Initialize the Choice boxes of type.
            2. Initialize the Choice boxes of region.
            3. For choice boxes of region,
                you need to add a blank or "All" option representing selection of all the region.
         */
        ObservableList<String> regionsWithAll = FXCollections.observableArrayList("All", "Africa", "Asia", "Europe", "Latin America", "North America", "Oceania"); // Start with "All" option
        ObservableList<String> typelist = FXCollections.observableArrayList("Public", "Private"); // Start with "All" option
        t3RegionChoiceBox.setItems(regionsWithAll); // Assume QSList.region is already an ObservableList<String>
        t3TypeChoiceBox.setItems(typelist);
    }


    @FXML
    private void T1_onClickClear() {
        /*
            Your Code Here.
            Reset the Page Task1. (including the choice box, labels and charts)
         */
        t1YearChoiceBox.setValue("");
        t1PieChartChoiceBox.setValue("size");
        t1PieChartLabel.setText("");
        t1BarChartChoiceBox.setValue("size");
        t1BarChartLabel.setText("");
        t1DataTable.getItems().clear();
        t1PieChart.setData(FXCollections.observableArrayList());
        t1BarChart.setData(FXCollections.observableArrayList());
    }


    @FXML
    void T1_onClickSearch() {
        /*
            Your Code Here.
            When click search on Task1:
                1. Fetch the year from the choice box.
                2. Clear previous data.
                3. Make an Analyser.
                4. Update the Table view, which shows Information about universities.
                5. Update the Pie Chart, which shows the sum score of selected property (t1PieChartChoiceBox).
                6. Update the Bar Chart, which shows the average score of selected property (t1BarChartChoiceBox).
            Please notice that we need listeners for monitoring the changes of choice box in pie chart and bar chart.
         */
        String year = t1YearChoiceBox.getValue();
        String PieChoice = t1PieChartChoiceBox.getValue();
        String BarChoice = t1BarChartChoiceBox.getValue();
        if ((year == null) || (year.isEmpty())) {
                Stage newstage = new Stage();
                StackPane root = new StackPane();
                Scene scene = new Scene(root, 820, 400);
                Label label = new Label("               Hey come on, man! \nYou haven't decide which year to plot! ");
                label.setFont(new Font(24));
                root.getChildren().add(label);
                newstage.setScene(scene);
                newstage.setTitle("Input Error");
                newstage.show();
            } else {
            T1Analysis T1 = new T1Analysis(year);
            t1DataTable.setItems(T1.tableList);
            t1PieChart.setData(T1.getPieChartData(PieChoice));
            ObservableList<XYChart.Series<String, Double>> seriesList = FXCollections.observableArrayList();
            seriesList.add(T1.getBarChartData(BarChoice));
            t1BarChart.setData(seriesList);
            t1Rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
            t1University.setCellValueFactory(new PropertyValueFactory<>("name"));
            t1Score.setCellValueFactory(new PropertyValueFactory<>("score"));
            t1Country.setCellValueFactory(new PropertyValueFactory<>("country"));
            t1City.setCellValueFactory(new PropertyValueFactory<>("city"));
            t1Type.setCellValueFactory(new PropertyValueFactory<>("type"));
            t1PieChartChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                t1PieChart.setData(T1.getPieChartData(newValue));
            });
            t1BarChartChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                ObservableList<XYChart.Series<String, Double>> BarseriesList = FXCollections.observableArrayList();
                BarseriesList.add(T1.getBarChartData(newValue));
                t1BarChart.setData(BarseriesList);
            });
        }
    }

    @FXML
    private void T21_onClickClear() {
        /*
            Your Code Here.
            Reset the Page Task 2.1. (including the choice boxes, check boxes and charts)
         */
        t2University1ChoiceBox.setValue("");
        t2University2ChoiceBox.setValue("");
        t22017CheckBox.setSelected(false);
        t22018CheckBox.setSelected(false);
        t22019CheckBox.setSelected(false);
        t22020CheckBox.setSelected(false);
        t22021CheckBox.setSelected(false);
        t22022CheckBox.setSelected(false);
        t21RankBarChart.setData(FXCollections.observableArrayList());
        t21ScoreBarChart.setData(FXCollections.observableArrayList());
        t21FacultyBarChart.setData(FXCollections.observableArrayList());
        t21InternationalBarChart.setData(FXCollections.observableArrayList());
        t21SFRBarChart.setData(FXCollections.observableArrayList());
        t21LineChart.setData(FXCollections.observableArrayList());

    }

    @FXML
    void T21_onClickCompare() {
        /*
            Your Code Here.
            When click search on Task2.1:
                1. Fetch the two universities from the choice box.
                2. Fetch the selected years.
                3. Clear previous data.
                4. Make an Analyser.
                5. Update the Bar Charts, which shows the average of selected property.
                6. Update the line Chart, which shows two lines of score of each year.
         */
        String uni_1 = t2University1ChoiceBox.getValue();
        String uni_2 = t2University2ChoiceBox.getValue();
        List<String> yearList = new ArrayList<>();
        if (t22017CheckBox.isSelected()) {
            yearList.add("2017");
        }
        if (t22018CheckBox.isSelected()) {
            yearList.add("2018");
        }
        if (t22019CheckBox.isSelected()) {
            yearList.add("2019");
        }
        if (t22020CheckBox.isSelected()) {
            yearList.add("2020");
        }
        if (t22021CheckBox.isSelected()) {
            yearList.add("2021");
        }
        if (t22022CheckBox.isSelected()) {
            yearList.add("2022");
        }

        if ((uni_1 == null) || (uni_1.isEmpty()) || (uni_2 == null) || (uni_2.isEmpty()) || (!t22017CheckBox.isSelected() &&
            !t22018CheckBox.isSelected() &&
            !t22019CheckBox.isSelected() &&
            !t22020CheckBox.isSelected() &&
            !t22021CheckBox.isSelected() &&
            !t22022CheckBox.isSelected())) {
            Stage newstage = new Stage();
            StackPane root = new StackPane();
            Scene scene = new Scene(root, 820, 400);
            Label label = new Label("                                   Hey come on, man! \nYou haven't input the whole information and how do I compare? ");
            label.setFont(new Font(24));
            root.getChildren().add(label);
            newstage.setScene(scene);
            newstage.setTitle("Input Error");
            newstage.show();
        }
        else {
            T21Analysis T2 = new T21Analysis(uni_1, uni_2, yearList);
            ObservableList<XYChart.Series<Double, String>> rankseriesList = FXCollections.observableArrayList();
            rankseriesList.add(T2.getBarChartData("rank"));
            //        System.out.print("rank is" + T2.getBarChartData("rank"));
            t21RankBarChart.setData(rankseriesList);
            ObservableList<XYChart.Series<Double, String>> scoreseriesList = FXCollections.observableArrayList();
            scoreseriesList.add(T2.getBarChartData("score"));
            t21ScoreBarChart.setData(scoreseriesList);
            ObservableList<XYChart.Series<Double, String>> facultycountseriesList = FXCollections.observableArrayList();
            facultycountseriesList.add(T2.getBarChartData("facultyCount"));
            t21FacultyBarChart.setData(facultycountseriesList);
            ObservableList<XYChart.Series<Double, String>> internationalstudentseriesList = FXCollections.observableArrayList();
            internationalstudentseriesList.add(T2.getBarChartData("internationalStudents"));
            t21InternationalBarChart.setData(internationalstudentseriesList);
            ObservableList<XYChart.Series<Double, String>> ratioseriesList = FXCollections.observableArrayList();
            ratioseriesList.add(T2.getBarChartData("studentFacultyRatio"));
            t21SFRBarChart.setData(ratioseriesList);
            ObservableList<XYChart.Series<String, Double>> T2lineChartData = FXCollections.observableArrayList(T2.getLineChartData("score"));
            t21LineChart.setData(T2lineChartData);
        }
    }

    @FXML
    private void T22_onClickClear() {
        /*
            Your Code Here.
            Reset the Page Task 2.2. (including the choice boxes, check boxes and charts)
         */
        t2CountryRegion1ChoiceBox.setValue("");
        t2CountryRegion2ChoiceBox.setValue("");
        t22017CheckBox2.setSelected(false);
        t22018CheckBox2.setSelected(false);
        t22019CheckBox2.setSelected(false);
        t22020CheckBox2.setSelected(false);
        t22021CheckBox2.setSelected(false);
        t22022CheckBox2.setSelected(false);
        t22RankBarChart.setData(FXCollections.observableArrayList());
        t22ScoreBarChart.setData(FXCollections.observableArrayList());
        t22FacultyBarChart.setData(FXCollections.observableArrayList());
        t22InternationalBarChart.setData(FXCollections.observableArrayList());
        t22SFRBarChart.setData(FXCollections.observableArrayList());
        t22LineChart.setData(FXCollections.observableArrayList());
    }

    @FXML
    void T22_onClickCompare() {
        /*
            Your Code Here.
            When click search on Task2.2:
                1. Fetch the two country/region from the choice box.
                2. Fetch the selected years.
                3. Clear previous data.
                4. Make an Analyser.
                5. Update the Bar Charts, which shows the average of selected property.
                6. Update the line Chart, which shows two lines of score of each year.
         */
        String CR1 = t2CountryRegion1ChoiceBox.getValue();
        String CR2 = t2CountryRegion2ChoiceBox.getValue();
        List<String> yearList = new ArrayList<>();
        if (t22017CheckBox2.isSelected()) {
            yearList.add("2017");
        }
        if (t22018CheckBox2.isSelected()) {
            yearList.add("2018");
        }
        if (t22019CheckBox2.isSelected()) {
            yearList.add("2019");
        }
        if (t22020CheckBox2.isSelected()) {
            yearList.add("2020");
        }
        if (t22021CheckBox2.isSelected()) {
            yearList.add("2021");
        }
        if (t22022CheckBox2.isSelected()) {
            yearList.add("2022");
        }
        if ((CR1 == null) || (CR1.isEmpty()) || (CR2 == null) || (CR2.isEmpty()) || (!t22017CheckBox2.isSelected() &&
                !t22018CheckBox2.isSelected() &&
                !t22019CheckBox2.isSelected() &&
                !t22020CheckBox2.isSelected() &&
                !t22021CheckBox2.isSelected() &&
                !t22022CheckBox2.isSelected())) {
            Stage newstage = new Stage();
            StackPane root = new StackPane();
            Scene scene = new Scene(root, 820, 400);
            Label label = new Label("                                   Hey come on, man! \nYou haven't input the whole information and how do I compare? ");
            label.setFont(new Font(24));
            root.getChildren().add(label);
            newstage.setScene(scene);
            newstage.setTitle("Input Error");
            newstage.show();
        } else {
            T22Analysis T2 = new T22Analysis(CR1, CR2, yearList);
            ObservableList<XYChart.Series<Double, String>> rankseriesList = FXCollections.observableArrayList();
            rankseriesList.add(T2.getBarChartData("rank"));
            t22RankBarChart.setData(rankseriesList);
            ObservableList<XYChart.Series<Double, String>> scoreseriesList = FXCollections.observableArrayList();
            scoreseriesList.add(T2.getBarChartData("score"));
            t22ScoreBarChart.setData(scoreseriesList);
            ObservableList<XYChart.Series<Double, String>> facultycountseriesList = FXCollections.observableArrayList();
            facultycountseriesList.add(T2.getBarChartData("facultyCount"));
            t22FacultyBarChart.setData(facultycountseriesList);
            ObservableList<XYChart.Series<Double, String>> internationalstudentseriesList = FXCollections.observableArrayList();
            internationalstudentseriesList.add(T2.getBarChartData("internationalStudents"));
            t22InternationalBarChart.setData(internationalstudentseriesList);
            ObservableList<XYChart.Series<Double, String>> ratioseriesList = FXCollections.observableArrayList();
            ratioseriesList.add(T2.getBarChartData("studentFacultyRatio"));
            t22SFRBarChart.setData(ratioseriesList);
            ObservableList<XYChart.Series<String, Double>> T2lineChartData = FXCollections.observableArrayList(T2.getLineChartData("score"));
            t22LineChart.setData(T2lineChartData);
        }
    }

    @FXML
    private void T3_onClickClear() {
        /*
            Your Code Here.
            Reset the Page Task 2.2. (including the text fields, choice boxes and the table view)
         */
        ObservableList<String> regionsWithAll = FXCollections.observableArrayList("All", "Africa", "Asia", "Europe", "Latin America", "North America", "Oceania"); // Start with "All" option
        ObservableList<String> typelist = FXCollections.observableArrayList("Public", "Private"); // Start with "All" option
        t3RegionChoiceBox.setItems(regionsWithAll); // Assume QSList.region is already an ObservableList<String>
        t3TypeChoiceBox.setItems(typelist);
        t3TopRankTextField.setText("");
        t3BottomRankTextField.setText("");
        t3TableView.getItems().clear();
    }

    @FXML
    void T3_onClickRecommend() {
        /*
            Your Code Here.
            When click search on Task3:
                1. Fetch the top and bottom boundary requirement of score.
                2. Fetch the type and region requirements.
                3. Clear previous data.
                4. Make an Analyser.
                5. Update the Table View.
         */
        String TopRank = t3TopRankTextField.getText();
        String BottomRank = t3BottomRankTextField.getText();
        String TypeChoice = t3TypeChoiceBox.getValue();
        String RegionChoice = t3RegionChoiceBox.getValue();
//        System.out.print(TopRank);
//        System.out.print(BottomRank);
//        System.out.print(TypeChoice);
//        System.out.print(RegionChoice);

        if ((TopRank.isEmpty()) || (BottomRank.isEmpty()) || (TypeChoice == null) || (TypeChoice.isEmpty()) || (RegionChoice == null) || (RegionChoice.isEmpty())) {
            Stage newstage = new Stage();
            StackPane root = new StackPane();
            Scene scene = new Scene(root, 820, 400);
            Label label = new Label("                                   Hey come on, man! \nYou haven't input the whole information and how do I recommend? ");
            label.setFont(new Font(24));
            root.getChildren().add(label);
            newstage.setScene(scene);
            newstage.setTitle("Input Error");
            newstage.show();
        }
            else {
            try {
                int top = Integer.parseInt(TopRank);
                int bottom = Integer.parseInt(BottomRank);
//                System.out.print(top);
//                System.out.print(bottom);
                T3Analysis T3 = new T3Analysis(TopRank, BottomRank, TypeChoice, RegionChoice);
                t3TableView.setItems(T3.getRecommendData());
                t3University.setCellValueFactory(new PropertyValueFactory<>("name"));
                t3BestYear.setCellValueFactory(new PropertyValueFactory<>("bestYear"));
                t3BestRank.setCellValueFactory(new PropertyValueFactory<>("bestRank"));
                t3RecentYear.setCellValueFactory(new PropertyValueFactory<>("recentYear"));
                t3RecentRank.setCellValueFactory(new PropertyValueFactory<>("recentRank"));
            } catch (NumberFormatException e) {
                Stage newstage = new Stage();
                StackPane root = new StackPane();
                Scene scene = new Scene(root, 820, 450);
                Label label = new Label("                                   Hey come on, man! \nYour inputs about Top and Bottom ranks should be integers. \n                                   What are you giving me? ");
                label.setFont(new Font(24));
                root.getChildren().add(label);
                newstage.setScene(scene);
                newstage.setTitle("Input Error");
                newstage.show();
            }
            //
        }

    }

}
