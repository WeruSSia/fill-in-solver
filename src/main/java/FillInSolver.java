import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FillInSolver {

    private List<String[]> blanks = new ArrayList<>();
    private List<String> words = new ArrayList<>();
    private List<String> wordsAssignedToBlanksAccordingToLength = new ArrayList<>();

    public void solve(String blanks, String wordsFileName) throws IOException {
        getCrosswordStructure(blanks);
        getWords(wordsFileName);
        printList(words);
        printArray(this.blanks.get(0));
        assignWordsToBlanksAccordingToLength();
        printList(wordsAssignedToBlanksAccordingToLength);
    }

    private void getCrosswordStructure(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();
        while (line != null) {
            blanks.add(line.split(" "));
            line = bufferedReader.readLine();
        }
    }

    private void getWords(String fileName) throws IOException {
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
            System.out.println(s);
        }
    }

    private void printArray(String[] arrayToBePrinted) {
        for (String s : arrayToBePrinted) {
            System.out.println(s);
        }
    }

    private void assignWordsToBlanksAccordingToLength() {
        for (int i = 0; i < blanks.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (blanks.get(i).length == words.get(j).length()) {
                    String joinedArray = String.join("", blanks.get(i));
                    wordsAssignedToBlanksAccordingToLength.add(joinedArray + " " + words.get(j));
                }
            }
        }
    }

}
