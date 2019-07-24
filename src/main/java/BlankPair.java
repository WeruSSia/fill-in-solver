/**
 * Represents a pair of blanks that has common value.
 */
class BlankPair {

    private Blank firstBlank;
    private Blank secondBlank;


    /**
     * Adds the blank to the pair.
     *
     * @param blank Blank that should be added to the pair.
     * @throws Exception If pair is already full.
     */
    void addBlank(Blank blank) throws Exception {
        if (firstBlank == null) {
            this.firstBlank = blank;
        } else if (secondBlank == null) {
            this.secondBlank = blank;
        } else {
            throw new Exception("Pair is full");
        }
    }

    /**
     * @return First blank from a pair.
     */
    Blank getFirstBlank() {
        return firstBlank;
    }

    /**
     * @return Second blank from a pair.
     */
    Blank getSecondBlank() {
        return secondBlank;
    }
}
