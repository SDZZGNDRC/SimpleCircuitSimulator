package test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

public class CircuitSimTest {
    @Test
    public void test1(){
    }
    @Test
    public void givenPythonScript_whenPythonProcessInvoked_thenSuccess() throws Exception {
        String netlist = "V0 1 0 10.0\n"
                        +"W0 1 2\n"
                        +"R0 2 3 3.0\n"
                        +"W1 3 4\n"
                        +"W2 4 5\n"
                        +"W3 5 0\n";
        String base64_netlist = Base64.getEncoder().encodeToString(netlist.getBytes("utf-8"));
        ProcessBuilder processBuilder = new ProcessBuilder("python", "./cal.py", base64_netlist);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = 
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();
        System.out.println(result);
        // List<String> results = readProcessOutput(process.getInputStream());

        // assertThat("Results should not be empty", results, is(not(empty())));
        // assertThat("Results should contain output of script: ", results, hasItem(
        // containsString("Hello Baeldung Readers!!")));

        // int exitCode = process.waitFor();
        // assertEquals("No errors should be detected", 0, exitCode);
    }
}
