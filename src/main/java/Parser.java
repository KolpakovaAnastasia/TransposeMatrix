import org.kohsuke.args4j.*;
import java.io.*;
import java.util.List;

public class Parser {
    @Argument(  usage = "Read data from this file", metaVar = "file")
    private String inputFile;

    @Option(name = "-o",  usage = "Write data to this file", metaVar = "ofile")
    private String outputFile;

    @Option(name = "-a", usage = "Align data by num symbols in each word", metaVar = "num")
    private int numAlign;

    @Option(name = "-t", usage = "Cut data to num symbols (num is declare in -a num)", metaVar = "cutting")
    private boolean isCut;

    @Option(name = "-r", usage = "Right align", metaVar = "rightAlign")
    private boolean isRightAligned;

    public static void main(String[] args) {

        new Parser().start(args);
    }

    public void start(String[] args){
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (numAlign < 0) {
                throw new CmdLineException(parser, "invalid argument");
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar part2.jar -o ofile file -a numAlign -t isCut -r isRightAligned");
            parser.printUsage(System.err);
            System.out.println("numAlign = " + numAlign);
            return;
        }

        Transposition transpose = new Transposition(numAlign, isCut, isRightAligned);
        try {
            Reader reader;
            Writer writer;
            if (inputFile != null) {
                reader = new FileReader(inputFile);
            }
            else reader = new InputStreamReader(System.in);
            if (outputFile != null) {
                writer = new FileWriter(outputFile);
            }
            else writer = new OutputStreamWriter(System.out);
            List<List<String>> matrix = transpose.matrix(reader);
            transpose.writeMatrix(matrix,writer);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}