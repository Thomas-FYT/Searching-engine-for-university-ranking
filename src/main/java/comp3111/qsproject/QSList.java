package comp3111.qsproject;

import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 * This file is a container of collected QS data
 */

public class QSList {
    public static ObservableList<QSItem> list = FXCollections.observableArrayList();
    public static ObservableList<String> university = FXCollections.observableArrayList();
    public static ObservableList<String> year = FXCollections.observableArrayList();
    public static ObservableList<String> rank_display = FXCollections.observableArrayList();
    public static ObservableList<String> score = FXCollections.observableArrayList();
    public static ObservableList<String> link = FXCollections.observableArrayList();
    public static ObservableList<String> country = FXCollections.observableArrayList();
    public static ObservableList<String> city = FXCollections.observableArrayList();
    public static ObservableList<String> region = FXCollections.observableArrayList();
    public static ObservableList<String> logo = FXCollections.observableArrayList();
    public static ObservableList<String> type = FXCollections.observableArrayList();
    public static ObservableList<String> research_output = FXCollections.observableArrayList();
    public static ObservableList<String> student_faculty_ratio = FXCollections.observableArrayList();
    public static ObservableList<String> international_students = FXCollections.observableArrayList();
    public static ObservableList<String> size = FXCollections.observableArrayList();
    public static ObservableList<String> faculty_count = FXCollections.observableArrayList();


    public static void initialize() {
        /*
            Your Code Here.
            1. Load the csv into list.
            2. Collect the set of university, type, region and country.
         */
        String csvFile = "qs.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
//                boolean yearflag = nextLine[1].matches("-?\\d{4}");
//                int number = Integer.parseInt(nextLine[2]);
//                boolean rankflag = (number >= 1 && number <= 400);
//                number = Integer.parseInt(nextLine[3]);
//                boolean scoreflag = (number >= 1 && number <= 100);
                List<String> regionlist = Arrays.asList("Asia", "Europe", "North America", "Oceania", "Latin America", "Africa");
                List<String> typelist = Arrays.asList("Public", "Private");
                List<String> sizelist = Arrays.asList("M", "L", "XL");
                List<String> outputlist = Arrays.asList("Very high", "High", "Medium");

                if (regionlist.contains(nextLine[7]) && (typelist.contains(nextLine[9])) && (sizelist.contains(nextLine[13])) && (outputlist.contains(nextLine[10]))) {
                    university.add(nextLine[0]);
                    year.add(nextLine[1]);
                    rank_display.add(nextLine[2]);
                    score.add(nextLine[3]);
                    link.add(nextLine[4]);
                    country.add(nextLine[5]);
                    city.add(nextLine[6]);
                    region.add(nextLine[7]);
                    logo.add(nextLine[8]);
                    type.add(nextLine[9]);
                    research_output.add(nextLine[10]);
                    student_faculty_ratio.add(nextLine[11]);
                    international_students.add(nextLine[12]);
                    size.add(nextLine[13]);
                    faculty_count.add(nextLine[14]);
                    list.add(new QSItem(nextLine));
                } else {
                    continue;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace(); // This will print more detailed error information
        }
    }
}
