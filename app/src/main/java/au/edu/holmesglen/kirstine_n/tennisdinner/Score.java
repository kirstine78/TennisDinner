package au.edu.holmesglen.kirstine_n.tennisdinner;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Kirsti on 20/10/2016.
 */

public class Score {
    UUID id;
    Calendar date;
    Integer scoreHydro;
    Integer scoreDynamite;

    public Score(Calendar aDate, Integer scoreHydro, Integer scoreDynamite) {
        this(UUID.randomUUID(), aDate, scoreHydro, scoreDynamite);
    }


    public Score(UUID id, Calendar aDate, Integer scoreHydro, Integer scoreDynamite) {
        this.id = id;
        this.date = aDate;
        this.scoreHydro = scoreHydro;
        this.scoreDynamite = scoreDynamite;
    }


    public String display() {
        return "id: " + id + ", date: " + date.getTime() + ", hydro: " + scoreHydro + ", dynamite: " + scoreDynamite;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Integer getScoreHydro() {
        return scoreHydro;
    }

    public void setScoreHydro(Integer scoreHydro) {
        this.scoreHydro = scoreHydro;
    }

    public Integer getScoreDynamite() {
        return scoreDynamite;
    }

    public void setScoreDynamite(Integer scoreDynamite) {
        this.scoreDynamite = scoreDynamite;
    }
}
