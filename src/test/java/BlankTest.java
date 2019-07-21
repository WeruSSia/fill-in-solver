import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BlankTest {

    private Blank blank;
    private final String blankString = ". 1 2 . . 3";
    private final String word1 = "animal";
    private final String word2 = "parrot";
    private final Set<Character> charactersAtIndexWithValue3 = new HashSet<>();

    @Before
    public void setUp() {
        blank = new Blank(blankString);
        blank.getCandidates().add(word1);
        blank.getCandidates().add(word2);
        charactersAtIndexWithValue3.add('l');
        charactersAtIndexWithValue3.add('t');
    }

    @Test
    public void test_toString() {
        assertEquals(blankString + " [" + word1 + ", " + word2 + "]", blank.toString());
    }

    @Test
    public void getIndexOfGivenValue() {
        final String value = "3";
        final Integer index = 5;

        assertEquals(index, blank.getIndexOfGivenValue(value));

    }

    @Test
    public void getCharactersAtIndexValue() {
        final String value = "3";

        assertEquals(charactersAtIndexWithValue3, blank.getCharactersAtIndexValue(value));
    }

    @Test
    public void addWordToCandidatesSet() {
        String word3 = "rabbit";
        blank.addWordToCandidatesSet(word3);
        Set<String> candidatesSet = new HashSet<>();
        candidatesSet.add(word1);
        candidatesSet.add(word2);
        candidatesSet.add(word3);

        assertEquals(candidatesSet, blank.getCandidates());
    }

    @Test
    public void removeWordFromCandidatesSet() {
        final Set<Character> intersection = new HashSet<>();
        intersection.add('t');
        final Integer index = 5;

        blank.removeWordFromCandidatesSet(intersection, index);

        Set<String> candidatesAfterRemoving = new HashSet<>();
        candidatesAfterRemoving.add(word2);
        assertEquals(candidatesAfterRemoving, blank.getCandidates());
    }

    @Test
    public void getCandidates() {
        final Set<String> candidates = new HashSet<>();
        candidates.add(word1);
        candidates.add(word2);

        assertEquals(candidates, blank.getCandidates());
    }

    @Test
    public void getBlankLength() {
        final int blankLength = 6;

        assertEquals(blankLength, blank.getBlankLength());
    }

    @Test
    public void getValues() {
        final Set<String> values = new HashSet<>();
        values.add("1");
        values.add("2");
        values.add("3");

        assertEquals(values, blank.getValues());
    }
}