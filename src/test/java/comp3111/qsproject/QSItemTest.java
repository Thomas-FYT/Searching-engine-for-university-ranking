package comp3111.qsproject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class QSItemTest {
    @Test
    public void testConstructor() {
        String[] data = {
                "University X", "2023", "10", "85", "", "CountryX", "CityX",
                "RegionX", "", "Public", "High", "20:1", "3000", "Large", "200"
        };
        QSItem item = new QSItem(data);

        assertAll("constructor",
                () -> assertEquals("University X", item.name),
                () -> assertEquals("2023", item.year),
                () -> assertEquals("10", item.rank),
                () -> assertEquals("85", item.score),
                () -> assertEquals("CountryX", item.country),
                () -> assertEquals("CityX", item.city),
                () -> assertEquals("RegionX", item.region),
                () -> assertEquals("Public", item.type),
                () -> assertEquals("High", item.researchOutput),
                () -> assertEquals("20:1", item.studentFacultyRatio),
                () -> assertEquals("3000", item.internationalStudents),
                () -> assertEquals("Large", item.size),
                () -> assertEquals("200", item.facultyCount)
        );
    }

    @Test
    public void testGetters() {
        String[] data = {
                "University X", "2023", "10", "85", "", "CountryX", "CityX",
                "RegionX", "", "Public", "High", "20:1", "3000", "Large", "200"
        };
        QSItem item = new QSItem(data);

        assertAll("getters",
                () -> assertEquals("10", item.getRank()),
                () -> assertEquals("University X", item.getName()),
                () -> assertEquals("85", item.getScore()),
                () -> assertEquals("CountryX", item.getCountry()),
                () -> assertEquals("CityX", item.getCity()),
                () -> assertEquals("Public", item.getType()),
                () -> assertEquals("High", item.getResearchOutput()),
                () -> assertEquals("20:1", item.getStudentFacultyRatio()),
                () -> assertEquals("3000", item.getInternationalStudents()),
                () -> assertEquals("200", item.getFacultyCount())
        );
    }

    @Test
    public void testGetProperty() {
        String[] data = {
                "University X", "2023", "10", "85", "", "CountryX", "CityX",
                "RegionX", "", "Public", "High", "20:1", "3000", "Large", "200"
        };
        QSItem item = new QSItem(data);


//        assertAll("getProperty",
//                () -> assertEquals("10", item.getProperty("rank")),
//                () -> assertEquals("University X", item.getProperty("name")),
//                () -> assertEquals("85", item.getProperty("score")),
//                () -> assertEquals("CountryX", item.getProperty("country")),
//                () -> assertNull(item.getProperty("nonexistentProperty"), "Should handle invalid properties")
//        );
    }
    @Test
    public void testGetPropertyHandlingIllegalAccessException() {
        // Providing a complete, valid set of data
        String[] data = {"University of Test", "2023", "10", "95.0", "http://test.edu", "Testland", "Testville", "Europe", "logo.png", "Public", "Very high", "15:1", "5000", "XL", "1000"};
        QSItem item = new QSItem(data);

//         Attempt to get a property where access might be illegal
//        String result = item.getProperty("somePrivateField"); // Hypothetical private field

        // Verify that the result is empty or null as the field access is illegal
//        assertTrue(result.isEmpty(), "The result should be empty or null due to inaccessible field.");
    }
}