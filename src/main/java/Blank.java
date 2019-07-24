import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a blank from the crossword. As a blank contains of dots and values (on specified indexes)
 * the class has a set of values and their indexes as pairs. There is also a list of candidates - words that have the same length as blank.
 */
class Blank {
    final private int blankLength;
    private Set<IndexValuePair> indexValuePairs;
    private Set<String> candidates = new HashSet<>();
    private String blankAsString;

    Blank(String blank) {
        this.blankLength = blank.split(" ").length;
        this.indexValuePairs = generateIndexValuePairs(blank);
        blankAsString = blank;
    }

    /**
     * @return Blank and its list of candidates as String.
     */
    @Override
    public String toString() {
        return blankAsString + " " + this.getCandidates();
    }

    private Set<IndexValuePair> generateIndexValuePairs(String blank) {
        Set<IndexValuePair> indexValuePairs = new HashSet<>();
        String[] blankAsArray = blank.split(" ");
        for (int i = 0; i < blankAsArray.length; i++) {
            if (!blankAsArray[i].equals(".")) {
                indexValuePairs.add(new IndexValuePair(i, blankAsArray[i]));
            }
        }
        return indexValuePairs;
    }

    /**
     * Looks for the index of a given value in the corresponding indexValuePair.
     *
     * @param value Value of which the index will be returned.
     * @return Index of a given value.
     */
    Integer getIndexOfGivenValue(String value) {
        Integer index = null;
        for (IndexValuePair indexValuePair : indexValuePairs) {
            if (indexValuePair.getValue().equals(value)) {
                index = indexValuePair.getIndex();
                break;
            }
        }
        return index;
    }

    /**
     * Gets the set of characters at an index of a given value from words in candidates set.
     *
     * @param value Value that is on the same index as a character that needs to be read.
     * @return Set of characters at index of a given value.
     */
    Set<Character> getCharactersAtIndexValue(String value) {
        Integer index = getIndexOfGivenValue(value);
        Set<Character> charactersAtIndexValue = new HashSet<>();
        for (String word : candidates) {
            charactersAtIndexValue.add(word.charAt(index));
        }
        return charactersAtIndexValue;
    }

    /**
     * Adds same length word to candidates list of a blank.
     *
     * @param word Word that should be added to the candidates list.
     */
    void addWordToCandidatesSet(String word) {
        candidates.add(word);
    }

    /**
     * Removes a word from candidates set if the character at given index is not in the given set of characters.
     *
     * @param characters Set of characters that if words has at a given index should stay on candidates list.
     * @param index      Index of a character that is checked if it is in the given set of characters.
     * @return Whether it removed something from the list.
     */
    boolean removeWordFromCandidatesSet(Set<Character> characters, Integer index) {
        List<String> candidatesToBeRemoved = new ArrayList<>();
        for (String word : candidates) {
            if (!characters.contains(word.charAt(index))) {
                candidatesToBeRemoved.add(word);
            }
        }
        return candidates.removeAll(candidatesToBeRemoved);
    }

    /**
     * @return Set of candidates.
     */
    Set<String> getCandidates() {
        return candidates;
    }

    /**
     * @return Length of a blank.
     */
    int getBlankLength() {
        return blankLength;
    }

    /**
     * @return Set of values that blank contains.
     */
    Set<String> getValues() {
        Set<String> values = new HashSet<>();
        for (IndexValuePair indexValuePair : indexValuePairs) {
            values.add(indexValuePair.getValue());
        }
        return values;
    }

    /**
     * Represents a pair of a value from a blank and its index.
     */
    class IndexValuePair {
        private int index;
        private String value;

        private IndexValuePair(int index, String value) {
            this.index = index;
            this.value = value;
        }

        /**
         * @return Index.
         */
        int getIndex() {
            return index;
        }

        /**
         * @return Value.
         */
        String getValue() {
            return value;
        }
    }

}
