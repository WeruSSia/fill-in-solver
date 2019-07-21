import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blank {
    final private int blankLength;
    private Set<IndexValuePair> indexValuePairs;
    private Set<String> candidates = new HashSet<>();
    private String blankAsString;

    Blank(String blank) {
        this.blankLength = blank.split(" ").length;
        this.indexValuePairs = generateIndexValuePairs(blank);
        blankAsString = blank;
    }

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

    public Integer getIndexOfGivenValue(String value) {
        Integer index = null;
        for (IndexValuePair indexValuePair : indexValuePairs) {
            if (indexValuePair.getValue().equals(value)) {
                index = indexValuePair.getIndex();
                break;
            }
        }
        return index;
    }

    public Set<Character> getCharactersAtIndexValue(String value) {
        Integer index = getIndexOfGivenValue(value);
        Set<Character> charactersAtIndexValue = new HashSet<>();
        for (String word : candidates) {
            charactersAtIndexValue.add(word.charAt(index));
        }
        return charactersAtIndexValue;
    }

    public void addWordToCandidatesSet(String word) {
        candidates.add(word);
    }

    public boolean removeWordFromCandidatesSet(Set<Character> characters, Integer index) {
        List<String> candidatesToBeRemoved = new ArrayList<>();
        for (String word : candidates) {
            if (!characters.contains(word.charAt(index))) {
                candidatesToBeRemoved.add(word);
            }
        }
        return candidates.removeAll(candidatesToBeRemoved);
    }

    public Set<String> getCandidates() {
        return candidates;
    }

    public int getBlankLength() {
        return blankLength;
    }

    public Set<String> getValues() {
        Set<String> values = new HashSet<>();
        for (IndexValuePair indexValuePair : indexValuePairs) {
            values.add(indexValuePair.getValue());
        }
        return values;
    }

    public class IndexValuePair {
        private int index;
        private String value;

        private IndexValuePair(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public String getValue() {
            return value;
        }
    }

}
