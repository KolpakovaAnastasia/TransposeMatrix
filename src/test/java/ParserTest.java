import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    private static File testFile = new File("src/test/java/TransposeMatrix/1.txt");

    private static File outputFile = new File("src/test/java/TransposeMatrix/output.txt");

    private static FileReader testReader;

    private static FileWriter outputWriter;

    @Before
    public void before() throws IOException {
        testReader = new FileReader(testFile);
        outputWriter = new FileWriter(outputFile);
    }

    @After
    public void after() throws IOException {
        outputFile.delete();
    }

    private void assertFileContent(String expected, File ifile) throws FileNotFoundException {
        String reader = new BufferedReader(new FileReader(ifile)).lines().collect(Collectors.joining("\n"));
        assertEquals(expected, reader);
    }

    @Test
    //для отладки. можно не смотреть
    public void ParseTest() throws IOException {
        testReader = new FileReader(testFile);
        //String[] args = {"'src/test/java/TransposeMatrix/output.txt' -o  'src/test/java/TransposeMatrix/1.txt'"};
        String[] args = {"-in","src/test/java/TransposeMatrix/1.txt","-o",outputFile.toString(),"-a","4"};

        Parser firstParser = new Parser();
        firstParser.start(args);


    }
}