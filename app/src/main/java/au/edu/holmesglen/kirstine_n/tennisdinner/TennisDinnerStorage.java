package au.edu.holmesglen.kirstine_n.tennisdinner;

import java.util.Collection;

/**
 * Created by Kirsti on 20/10/2016.
 */

public interface TennisDinnerStorage {

    public void addScore(Score score);

    public Collection<Score> getScores();
}
