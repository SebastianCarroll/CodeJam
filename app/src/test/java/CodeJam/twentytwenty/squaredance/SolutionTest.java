package CodeJam.twentytwenty.squaredance;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static java.util.stream.Collectors.joining;

class SolutionTest {
    @Test
    void canRun() {
        String input = null;
        try {
            Path test_file = Paths.get("src/test/java/CodeJam/twentytwenty/squaredance/T-input.txt");
            System.out.println(test_file.toString());
            System.out.println("Looking at: " + System.getProperty("user.dir"));
            input = Files.lines(test_file).collect(joining("\n"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertNotNull(input);
    }
}
