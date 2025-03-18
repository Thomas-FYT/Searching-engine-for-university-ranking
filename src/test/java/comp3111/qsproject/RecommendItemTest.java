package comp3111.qsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommendItemTest {

    private RecommendItem recommendItem;

    @BeforeEach
    void setUp() {
        // Setup with initial data using the new QSItem constructor
        String[] initialData = new String[15];
        initialData[0] = "University A"; // name
        initialData[1] = "2018";         // year
        initialData[2] = "10";           // rank
        initialData[3] = "75.5";         // score
        initialData[5] = "CountryX";     // country
        initialData[6] = "CityY";        // city
        initialData[7] = "RegionZ";      // region
        initialData[9] = "Public";       // type
        initialData[10] = "High";        // researchOutput
        initialData[11] = "30:1";        // studentFacultyRatio
        initialData[12] = "2000";        // internationalStudents
        initialData[13] = "Large";       // size
        initialData[14] = "1500";        // facultyCount

        QSItem initialItem = new QSItem(initialData);
        recommendItem = new RecommendItem(initialItem);
    }

    @Test
    void testInitialSetup() {
        assertEquals("University A", recommendItem.getName());
        assertEquals("2018", recommendItem.getBestYear());
        assertEquals("10", recommendItem.getBestRank());
        assertEquals("2018", recommendItem.getRecentYear());
        assertEquals("10", recommendItem.getRecentRank());
    }

    @Test
    void testUpdateWithBetterRank() {
        String[] newData = {"University A", "2019", "5", "80.0", "hello", "CountryX", "CityY", "RegionZ", "hi", "Public", "High", "25:1", "2500", "Large", "1600"};
        QSItem newItem = new QSItem(newData);
        recommendItem.update(newItem);
        assertEquals("5", recommendItem.getBestRank());
        assertEquals("2019", recommendItem.getBestYear());
        assertEquals("2019", recommendItem.getRecentYear());
        assertEquals("5", recommendItem.getRecentRank());
    }

    @Test
    void testUpdateWithWorseRank() {
        String[] newData = {"University A", "2020", "20", "70.0", "hello", "CountryX", "CityY", "RegionZ", "hi", "Public", "Medium", "35:1", "1500", "Medium", "1400"};
        QSItem newItem = new QSItem(newData);
        recommendItem.update(newItem);
        assertEquals("10", recommendItem.getBestRank());
        assertEquals("2018", recommendItem.getBestYear());
        assertEquals("2020", recommendItem.getRecentYear());
        assertEquals("20", recommendItem.getRecentRank());
    }

    @Test
    void testUpdateWithSameYearBetterRank() {
        String[] newData = {"University A", "2018", "8", "77.0", "hello", "CountryX", "CityY", "RegionZ", "hi", "Public", "Very High", "28:1", "2200", "Large", "1550"};
        QSItem newItem = new QSItem(newData);
        recommendItem.update(newItem);
        assertEquals("8", recommendItem.getBestRank());
        assertEquals("2018", recommendItem.getBestYear());
    }
}