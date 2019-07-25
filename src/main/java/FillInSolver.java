import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class solves a fill-in puzzle. One input file contains of words, and the second input file contains of blanks,
 * that words should be put into.<br><br>
 * In words file each word must be in a different line.<br><br>
 * Crossword structure file must meet these requirements:<br>
 * - each blank must be in a different line,<br>
 * - blank contains of '.' and numbers, in which '.' is an empty field, and a number is a field that crosses with another blank,<br>
 * - between each '.' and number must be a space.<br><br>
 * User can input more words that there are blanks. The program will try to match as many correct words to blanks as possible considering every option,
 * but there always must be a correct answer, otherwise program won't be able to show correct solution.
 */
@SuppressWarnings("WeakerAccess")
public class FillInSolver {

    private final List<Blank> blanks = new ArrayList<>();
    private final List<String> words = new ArrayList<>();
    private final Set<String> valuesFromBlanks = new HashSet<>();

    /**
     * Solves the fill-in puzzle, based on two input files - blanks and words, then populates each blank's candidates set
     * with words of the same length. Then, for each value got from blanks it compares character at a specific index.
     * In the end it writes solution to the output file.
     *
     * @param blanksFileName   Name of a file containing crossword structure (blanks).
     * @param wordsFileName    Name of a file containing words.
     * @param solutionFileName Name of a file that solution is written to.
     * @throws Exception If something goes wrong with reading/writing to file.
     */
    public void solve(String blanksFileName, String wordsFileName, String solutionFileName) throws Exception {
        getBlanksFromFile(blanksFileName);
        getWordsFromFile(wordsFileName);
        populateCandidatesSets();
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
