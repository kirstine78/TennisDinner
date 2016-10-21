package au.edu.holmesglen.kirstine_n.tennisdinner;

import java.util.Calendar;

/**
 * Created by Kirsti on 20/10/2016.
 */

public class Score {
    Calendar date;
    Integer scoreHydro;
    Integer scoreDynamite;

    public Score(Calendar aDate, Integer scoreHydro, Integer scoreDynamite) {
        this.date = aDate;
        this.scoreHydro = scoreHydro;
        this.scoreDynamite = scoreDynamite;
    }
}
