import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetOperationsTest {
    SetOperations test;
    Processor proc;
    File spamfolder;
    File hamfolder;
    Set<String> spam;
    Set<String> ham;

    @BeforeEach
    void setUp() {
        test = new SetOperations();
        proc = new Processor();
        spamfolder = new File("spam");
        spam = new HashSet<>();
        hamfolder = new File("ham");
        ham = new HashSet<>();
        for (String filename: spamfolder.list()) {
            proc.setFilename("spam/" + filename);
            spam.addAll(proc.parseFile());
        }
        for (String filename: hamfolder.list()) {
            proc.setFilename("ham/" + filename);
            ham.addAll(proc.parseFile());
        }
        System.out.println(spam);
        System.out.println(ham);
    }

    @Test
    void union() {
        System.out.println(test.Union(spam, ham));
    }

    @Test
    void intersection() {
        System.out.println(test.Intersection(ham, spam));
    }

    @Test
    void difference() {
        System.out.println(test.Difference(ham, spam));
    }
}