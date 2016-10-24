package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import au.edu.holmesglen.kirstine_n.tennisdinner.TennisDBSchema.TennisTable;

/**
 * Created by Kirsti on 24/10/2016.
 */

public class ScoreCursorWrapper extends CursorWrapper {
    public ScoreCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Score getScore() {
        String uuidString = getString(getColumnIndex(TennisTable.Cols.UUID));
        long date = getLong(getColumnIndex(TennisTable.Cols.DATE));
        int scoreHydro = getInt(getColumnIndex(TennisTable.Cols.HYDRO_SCORE));
        int scoreDynamite = getInt(getColumnIndex(TennisTable.Cols.DYNAMITE_SCORE));

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        Score score = new Score(UUID.fromString(uuidString), c, scoreHydro, scoreDynamite);
        return score;
    }
}
