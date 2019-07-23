import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class FillInSolverTest {

    private FillInSolver fillInSolver;
    private File wordsFile;
    private File crosswordStructureFile;

    @Before
    public void setUp() {
        fillInSolver = new FillInSolver();
        wordsFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("wordsFile.txt")).getFile());
        crosswordStructureFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("crosswordStructureFile.txt")).getFile());
    }

    @Test
    public void solve_success() throws Exception {
        final String solutionFileName = "solutionFile.txt";
        fillInSolver.solve(crosswordStructureFile.toString(),wordsFile.toString(),solutionFileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(solutionFileName));
        StringBuilder solutionFileContent = new StringBuilder();
        String line = bufferedReader.readLine();
        while(line!=null){
            solutionFileContent.append(line);
            line = bufferedReader.readLine();
        }

        assertEquals("1 . . [cat]. . . 2 . [snail]. 3 . . 4 . [monkey]. . 2 . 3 [rhino]1 . . . . 4 . [chicken]",solutionFileContent.toString());
    }

    @Test(expected = Exception.class)
    public void solve_wrongInputFiles() throws Exception {
        String nonExistingWordsFileName = "nonExistingWordsFile.txt";

        fillInSolver.solve(crosswordStructureFile.toString(),nonExistingWordsFileName,"solutionFile.txt");
    }
}