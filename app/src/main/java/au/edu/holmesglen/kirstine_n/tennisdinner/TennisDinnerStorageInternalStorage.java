package au.edu.holmesglen.kirstine_n.tennisdinner;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kirsti on 20/10/2016.
 */

public class TennisDinnerStorageInternalStorage implements TennisDinnerStorage {

    Collection<Score> scores = new ArrayList<Score>();

    @Override
    public void addScore(Score score) {
        scores.add(score);
    }

    public void deleteScores() {
        scores.clear();
    }

    public void deleteOneScore(Score score){}

    public void updateScore(Score score) {
    }

    @Override
    public Collection<Score> getScores() {
        return scores;
    }

}
