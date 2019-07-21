import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FillInSolver {

    private List<Blank> blanks = new ArrayList<>();
    private List<String> words = new ArrayList<>();
    private Set<String> valuesFromBlanks = new HashSet<>();

    public void solve(String blanksFileName, String wordsFileName, String solutionFileName) throws Exception {
        getBlanksFromFile(blanksFileName);
        getWordsFromFile(wordsFileName);
        populateCandidatesSets();
        for (Blank blank : blanks) {
            System.out.println(blank.getCandidates().toString());
        }
        getSetOfAllValues();
        compareCharactersAndManageCandidates();
        writeSolutionToFile(solutionFileName);
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
        for (Blank blank : blanks) {
            if (blank.getValues().contains(value)) {
                blankPair.addBlank(blank);
            }
        }
        return blankPair;
    }

    private void compareCharactersAndManageCandidates() throws Exception {
        boolean solved = false;
        while (!solved) {
            solved = true;
            for (String value : valuesFromBlanks) {
                BlankPair blankPair = getPairOfBlanksWithSameValue(value);
                Set<Character> intersection = new HashSet<>(blankPair.getFirstBlank().getCharactersAtIndexValue(value));
                intersection.retainAll(blankPair.getSecondBlank().getCharactersAtIndexValue(value));
                boolean changedFirst = blankPair.getFirstBlank().removeWordFromCandidatesSet(intersection, blankPair.getFirstBlank().getIndexOfGivenValue(value));
                boolean changedSecond = blankPair.getSecondBlank().removeWordFromCandidatesSet(intersection, blankPair.getSecondBlank().getIndexOfGivenValue(value));
                if (changedFirst || changedSecond) {
                    solved = false;
                }
            }
        }
    }

    private void writeSolutionToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        StringBuilder finalSolution = new StringBuilder();
        for (Blank blank : blanks) {
            finalSolution.append(blank.toString());
            finalSolution.append("\n");
        }
        fileWriter.write(finalSolution.toString());
        fileWriter.close();
    }
}
