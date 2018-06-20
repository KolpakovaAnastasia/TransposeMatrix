import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class TranspositionTest {
    private static File test1 = new File("src/test/java/TransposeMatrix/1.txt");
    private static File test2 = new File("src/test/java/TransposeMatrix/2.txt");
    private static File test3 = new File("src/test/java/TransposeMatrix/3.txt");
    private static File test4 = new File("src/test/java/TransposeMatrix/4.txt");
    private static File outFile = new File("src/test/java/TransposeMatrix/output.txt");
    private static FileReader test1Reader;
    private static FileReader test2Reader;
    private static FileReader test3Reader;
    private static FileReader test4Reader;
    private static FileWriter outputWriter;
    private static FileReader outputReader;

    @Before
    public void before() throws IOException {
        test1Reader = new FileReader(test1);
        test2Reader = new FileReader(test2);
        test3Reader = new FileReader(test3);
        test4Reader = new FileReader(test4);
        outputWriter = new FileWriter(outFile);
        outputReader = new FileReader(outFile);
    }

    public void after() throws IOException {
        outFile.delete();
    }

    @Test
    public void numTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(5, false, false);
        List<List<String>> matrixContent = matrix.matrix(test4Reader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("  bum   pam    fr\n" +
                "bubum param    mr\n" +
                "bububum parampampam    br\n", outputWriter.toString());
    }

    @Test
    public void transposeTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(4, false, false);
        List<List<String>> matrixContent = matrix.matrix(test1Reader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals(" muu  muu  muu\n" +
                " kar  kar  kar\n" +
                " mur  mur kiss\n", outputWriter.toString());
    }


    @Test
    public void cuttingTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(2, true, false);
        List<List<String>> matrixContent = matrix.matrix(test2Reader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("aa aa aa\n" +
                "aa aa aa\n" +
                "aa aa aa\n" +
                "aa aa\n", outputWriter.toString());
    }

    @Test
    public void rightAlignTest() throws IOException {
        StringWriter outputWriter = new StringWriter();
        Transposition matrix = new Transposition(7, false, true);
        List<List<String>> matrixContent = matrix.matrix(test3Reader);
        matrix.writeMatrix(matrixContent, outputWriter);
        assertEquals("end line like are oracle\n" +
                "begin again kidding me actually\n" +
                "commute class kek around i\n", outputWriter.toString());
    }
}