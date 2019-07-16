import java.util.HashSet;
import java.util.Set;

public class Blank {
    final private int blankLength;
    private Set<IndexValuePair> indexValuePairs;
    private Set<String> candidates = new HashSet<>();

    public Blank(String blank) {
        this.blankLength = blank.split(" ").length;
        this.indexValuePairs = generateIndexValuePairs(blank);
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

    public int getBlankLength() {
        return blankLength;
    }

    public Set<IndexValuePair> getIndexValuePairs() {
        return indexValuePairs;
    }

    private class IndexValuePair {
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
