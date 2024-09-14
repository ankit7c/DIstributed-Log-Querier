package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class GrepExecutorTest {
    private static GrepExecutor grepExecutor;
    private static final Logger logger = Logger.getLogger(GrepExecutorTest.class.getName());
    private static FileHandler logFileHandler;
    private static final String filePath = "junitLogs.log";
    private static File logFile;

    /**
     * This method creates a dummy log file and initialize all the other necessary things.
     * @throws IOException
     */
    @BeforeAll
    static void setUp() throws IOException {
        grepExecutor = new GrepExecutor(filePath);
        logFileHandler = new FileHandler(filePath);
        logFileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(logFileHandler);
        logger.setLevel(Level.ALL);


        for (int i = 0; i < 30; i++) {
            if(i%2==0){
                logger.info("GET log Entry: " + i);
            }
            else if (i%3==0) {
                logger.severe("POST log EntRy new: " + i);
            } else{
                logger.warning("PUT log Entry old: " + i);
            }
        }
        logger.info("PuT case insensitive");
        logFile = new File(filePath);
        assertTrue(logFile.exists(),"Error while generating log file");

    }

    @AfterAll
    static void tearDown() {
        if (logFileHandler != null) {
            logFileHandler.close();
        }
        File logFile = new File(filePath).getAbsoluteFile();
        if (logFile.exists()) {
            boolean delete = logFile.delete();
        }
    }

    @Test
    public void grepparameter_n_Test() {
        String command = "grep -n \"GET\"";
        List<String> grepResult = grepExecutor.executeGrep(command);
        assertEquals(expectedResults.get_n, grepResult,"Grep command failed for Pattern GET and parameter n");

    }

    @Test
    public void grepParameter_c_Test() {
        String command = "grep -c \"GET\"";
        List<String> grepResult = grepExecutor.executeGrep(command);
        assertEquals("15", grepResult.get(0),"Grep command failed for Pattern GET and parameter c");

    }

    @Test
    public void grepParameter_i_Test() {
        String command = "grep -i \"PUT\"";
        List<String> grepResult = grepExecutor.executeGrep(command);
        assertEquals(expectedResults.put_i,grepResult,"Grep command failed for Pattern PUT and parameter i");

    }

    @Test
    public void grepParameter_nI_Test() {
        String command = "grep -ni \"PUT\"";
        List<String> grepResult = grepExecutor.executeGrep(command);
        assertEquals(expectedResults.put_ni,grepResult,"Grep command failed for Pattern PUT and parameter -ni");

    }

    @Test
    public void grepParameter_n_ITest() {
        String command = "grep -n -i \"PUT\"";
        List<String> grepResult = grepExecutor.executeGrep(command);
        assertEquals(expectedResults.put_ni,grepResult,"Grep command failed for Pattern GET and parameter -n -i");

    }

}