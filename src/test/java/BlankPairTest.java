import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BlankPairTest {

    private BlankPair blankPair;

    @Before
    public void setUp() {
        blankPair = new BlankPair();
    }

    @Test
    public void addBlank_success() throws Exception {
        Blank firstBlank = mock(Blank.class);
        Blank secondBlank = mock(Blank.class);
        blankPair.addBlank(firstBlank);
        blankPair.addBlank(secondBlank);

        assertEquals(firstBlank, blankPair.getFirstBlank());
        assertEquals(secondBlank, blankPair.getSecondBlank());
    }

    @Test(expected = Exception.class)
    public void addBlank_fail() throws Exception {
        blankPair.addBlank(mock(Blank.class));
        blankPair.addBlank(mock(Blank.class));
        blankPair.addBlank(mock(Blank.class));
    }
}