import java.io.*;
import java.util.*;
public class Transposition {
    private int numAligned;
    private boolean isCut;
    private boolean isRightAligned;

    Transposition(int numAligned, boolean isCut, boolean isRightAligned) {
        try {
            if (numAligned != 0) {
                this.numAligned = numAligned;
            }
            else if (isCut || isRightAligned) {
                this.numAligned = 10;
            }
            this.isCut = isCut;
            this.isRightAligned = isRightAligned;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }


    private String edit(String str, String rightAlign, String strNum, Boolean isCut) {
        String editedStr;
        editedStr = String.format("%" + rightAlign + strNum + "s", str);
        if (isCut) {
            editedStr = editedStr.substring(0, numAligned);
        }
        return editedStr;
    }

    private String transpose(List<List<String>> oldLines,int n) {
        String inform = "";
        List<String> newLine = new LinkedList<String>();
        for (String str : oldLines.get(n)) {
            newLine.add(str);
            inform = String.join(" ", newLine);
        }
        return inform;
    }

    public List<List<String>> matrix(Reader reader) throws IOException {
        String rightAlign;
        String thisLine;
        String strNum;
        List<List<String>> inMatrix = new ArrayList();
        BufferedReader buff = new BufferedReader(reader);
        strNum = Integer.toString(numAligned);
        if ((this.isRightAligned) || !(this.numAligned > 0)) rightAlign = "";
        else rightAlign = strNum;
        while ((thisLine = buff.readLine()) != null) {
            if (this.numAligned <= 0) strNum = Integer.toString(numAligned);
            else strNum = "";
            int n =0;
            for (String str : thisLine.split("[ ]+")) {
                str = edit(str, rightAlign, strNum, isCut);
                while (inMatrix.size() <= n){
                    inMatrix.add(new ArrayList());
                }
                inMatrix.get(n).add(str);
                n++;
            }
        }
        return inMatrix;
    }


public void writeMatrix(List<List<String>> oldLines, Writer writer) throws IOException {
        int n = oldLines.size();
        BufferedWriter buff = new BufferedWriter(writer);
        for (int i = 0; i < n; i++) {
            buff.write(transpose(oldLines, i));
            if (i < oldLines.size()){
                buff.write("\n");
            }
        }
        buff.close();
        writer.close();
}








}