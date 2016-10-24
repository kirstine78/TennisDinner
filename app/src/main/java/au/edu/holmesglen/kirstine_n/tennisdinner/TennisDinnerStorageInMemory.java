package au.edu.holmesglen.kirstine_n.tennisdinner;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kirsti on 20/10/2016.
 */

public class TennisDinnerStorageInMemory implements TennisDinnerStorage {

    Collection<Score> scores = new ArrayList<Score>();

    @Override
    public void addScore(Score score) {
        scores.add(score);
    }

    public void deleteScores() {scores.clear();}

    @Override
    public Collection<Score> getScores() {
        return scores;
    }
}
