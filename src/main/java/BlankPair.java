public class BlankPair {

    private Blank firstBlank;
    private Blank secondBlank;
    private String value;

    public void addBlank(Blank blank) throws Exception {
        if(firstBlank!=null){
            this.firstBlank=blank;
        }else if(secondBlank!=null){
            this.secondBlank=blank;
        }else{
            throw new Exception("Pair is full");
        }
    }

    public Blank getFirstBlank() {
        return firstBlank;
    }

    public Blank getSecondBlank() {
        return secondBlank;
    }
}
