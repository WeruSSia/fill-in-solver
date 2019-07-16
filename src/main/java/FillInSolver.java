import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FillInSolver {

    private List<Blank> blanks = new ArrayList<>();
    private List<String> words = new ArrayList<>();

    public void solve(String blanksFileName, String wordsFileName) throws IOException {
        getBlanksFromFile(blanksFileName);
        getWordsFromFile(wordsFileName);
    }

    private void getBlanksFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        while (line != null) {
            blanks.add(new Blank(line));
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    private void getWordsFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        while (line != null) {
            words.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    private void printList(List<String> listToBePrinted) {
        for (String s : listToBePrinted) {
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }
}
