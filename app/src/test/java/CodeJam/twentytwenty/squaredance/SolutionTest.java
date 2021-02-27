package CodeJam.twentytwenty.squaredance;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void canRunFromStdINStdOut() throws IOException {
        // Set Stdin to file input
        String dir = "src/test/java/CodeJam/twentytwenty/squaredance/";
        String testFile = dir + "T-input.txt";
        System.setIn(new FileInputStream(new File(testFile)));

        // Set stdout to output file
        String outActual = dir + "T-output.txt";
        System.setOut(new PrintStream(new File(outActual)));

        Solution sol = new Solution();
        sol.run();

        String outExpected = dir + "T-output-expected.txt";
        List<String> actual = Files.readAllLines(Paths.get(outActual));
        Path outExpectedPath = Paths.get(outExpected);
        List<String> expected = Files.readAllLines(outExpectedPath);
        assertIterableEquals(actual, expected, "Expected output files to match");

        Files.delete(outExpectedPath);
    }
}
