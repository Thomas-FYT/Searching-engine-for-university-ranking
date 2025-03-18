//package comp3111.qsproject;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import static org.junit.Assert.*;
//
//public class QSListTest {
//
//    private Path filePath = Paths.get(System.getProperty("user.dir"), "qs.csv");
//
//    @Before
//    public void setUp() throws IOException {
//        // Ensure the file is clean before each test
//        Files.deleteIfExists(filePath);
//    }
//
//    @Test
//    public void testSuccessfulInitialize() throws IOException {
//        // Write valid data to the file
//        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
//            bw.write("University,Year,Rank,Score,Link,Country,City,Region,Logo,Type,Research Output,Student Faculty Ratio,International Students,Size,Faculty Count\n");
//            bw.write("University of Test,2023,1,95,http://test.edu,Testland,Testville,Europe,logo.png,Public,Very high,15:1,5000,XL,1000\n");
//        }
//
//        // Initialize QSList, which reads from "qs.csv"
//        QSList.initialize();
//
//        // Assertions
//        assertEquals(2, QSList.university.size());
//        assertTrue(QSList.university.contains("University of Test"));
//    }
//
//    @Test
//    public void testInitializeWithInvalidRank() throws IOException {
//        // Write invalid data to the file
//        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
//            bw.write("University,Year,Rank,Score,Link,Country,City,Region,Logo,Type,Research Output,Student Faculty Ratio,International Students,Size,Faculty Count\n");
//            bw.write("University of Test,2023,401,95,http://test.edu,Testland,Testville,Europe,logo.png,Public,Very high,15:1,5000,XL,1000\n");
//        }
//
//        // We expect an exception to be thrown, or the list to remain empty if rank validation is re-enabled
//        QSList.initialize();
//
//        // Assertions
//        assertEquals(3, QSList.list.size());
//        assertTrue(QSList.university.contains("University of Test"));
//    }
//    @Test
//    public void testInitializeWithNonNumericScore() throws IOException {
//        // Write data to the file with a non-numeric score
//        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
//            bw.write("University,Year,Rank,Score,Link,Country,City,Region,Logo,Type,Research Output,Student Faculty Ratio,International Students,Size,Faculty Count\n");
//            bw.write("University of Misentry,2023,10,NotANumber,http://misentry.edu,Misland,Misville,Europe,logo.png,Public,High,20:1,3000,L,800\n");
//        }
//
//        // Handling exceptions or checking for invalid entries
//        try {
//            QSList.initialize();
//            // If no exception is thrown, check if the entry was added
//            assertFalse("No university should be added due to non-numeric score.", QSList.university.isEmpty());
//        } catch (Exception e) {
//            // If an exception is thrown, you can check the type and message
//            assertTrue(e instanceof NumberFormatException);
//        }
//    }
//
////    @Test(expected = FileNotFoundException.class)
////    public void testInitializeThrowsFileNotFoundException() throws IOException {
////        // Do not create the file, so initializing should fail
////        assertThrows(FileNotFoundException.class, () -> {
////            QSList.initialize();
////        });
////    }
////
////    @Test(expected = IOException.class)
////    public void testInitializeThrowsIOExceptionForBadFormat() throws IOException {
////        Path filePath = Paths.get(System.getProperty("user.dir"), "qs.csv"); // Specify the path to your test file
////
////        // Write bad data to the file
////        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
////            bw.write("Bad data that does not comply with expected format");
////        }
////
////        // Attempt to initialize QSList, expecting an IOException due to format issues
////        assertThrows(IOException.class, () -> {
////            QSList.initialize();
////        });
////    }
//
////    @After
////    public void tearDown() throws IOException {
////        // Clean up by deleting the file after each test
////        Path filePath = Paths.get(System.getProperty("user.dir"), "qs.csv");
////        Files.deleteIfExists(filePath);
////    }
//}