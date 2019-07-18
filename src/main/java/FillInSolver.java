import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FillInSolver {

    private List<Blank> blanks = new ArrayList<>();
    private List<String> words = new ArrayList<>();
    private Set<String> valuesFromBlanks = new HashSet<>();

    public void solve(String blanksFileName, String wordsFileName) throws IOException {
        getBlanksFromFile(blanksFileName);
        getWordsFromFile(wordsFileName);
        populateCandidatesSets();
        for (Blank blank : blanks) {
            System.out.println(blank.getCandidates().toString());
        }
        getSetOfAllValues();
        System.out.println(valuesFromBlanks.toString());
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

    private void populateCandidatesSets() {
        for (Blank blank : blanks) {
            for (String word : words) {
                if (blank.getBlankLength() == word.length()) {
                    blank.addWordToCandidatesSet(word);
                }
            }
        }
    }

    private void getSetOfAllValues() {
        for (Blank blank : blanks) {
            valuesFromBlanks.addAll(blank.getValues());
        }
    }

    private BlankPair getPairOfBlanksWithSameValue(String value) throws Exception {
        BlankPair blankPair = new BlankPair();
        for(Blank blank : blanks){
            if(blank.getValues().contains(value)){
                blankPair.addBlank(blank);
            }
        }
        return blankPair;
    }

    private void compareCharactersAndManageLists() throws Exception { //todo
        for(String value:valuesFromBlanks){
            BlankPair blankPair = getPairOfBlanksWithSameValue(value);
            List<Character> intersection = new ArrayList<>(blankPair.getFirstBlank().getCharactersAtIndexValue(value));
            intersection.retainAll(blankPair.getSecondBlank().getCharactersAtIndexValue(value));
            //blankPair.getFirstBlank().removeWordFromCandidatesSet(intersection,);
            //blankPair.getSecondBlank().removeWordFromCandidatesSet(intersection,);
        }
    }

    private void printList(List<String> listToBePrinted) {
        for (String s : listToBePrinted) {
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }
}
