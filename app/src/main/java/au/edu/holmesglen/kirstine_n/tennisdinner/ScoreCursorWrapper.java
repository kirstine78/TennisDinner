package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String dateStr = getString(getColumnIndex(TennisTable.Cols.DATE));
        int scoreHydro = getInt(getColumnIndex(TennisTable.Cols.HYDRO_SCORE));
        int scoreDynamite = getInt(getColumnIndex(TennisTable.Cols.DYNAMITE_SCORE));

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
        Date date = null;

        Calendar c = null;

        try {
            date = (Date)formatter.parse(dateStr);

            c = Calendar.getInstance();
            c.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.v("Kirsti", dateStr);


        Score score = new Score(UUID.fromString(uuidString), c, scoreHydro, scoreDynamite);
        return score;
    }
}
