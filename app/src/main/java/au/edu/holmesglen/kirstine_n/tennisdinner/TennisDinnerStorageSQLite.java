package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import au.edu.holmesglen.kirstine_n.tennisdinner.TennisDBSchema.TennisTable;

/**
 * Created by Kirsti on 21/10/2016.
 */

public class TennisDinnerStorageSQLite implements TennisDinnerStorage {
    private static TennisDinnerStorageSQLite tennisDinnerStorageSQLite;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private TennisDinnerStorageSQLite (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TennisSQLiteHelper(mContext).getWritableDatabase();
    }

    public static TennisDinnerStorage getInstance(Context context) {
        if (tennisDinnerStorageSQLite == null) {
            tennisDinnerStorageSQLite = new TennisDinnerStorageSQLite(context);
        }
        return tennisDinnerStorageSQLite;
    }

    public Score getScore(UUID id) {
        String sqlQuery = "Select * from " + TennisTable.NAME + " where UUID=" + id + ";";
        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        String calendarStr = cursor.getString(cursor.getColumnIndex("DATE"));
        Integer scoreTeamHydro = Integer.parseInt(cursor.getString(cursor.getColumnIndex("HYDRO_SCORE")));
        Integer scoreTeamDynamite = Integer.parseInt(cursor.getString(cursor.getColumnIndex("DYNAMITE_SCORE")));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal  = Calendar.getInstance();
//        cal.setTime(df.parse(tvDate.getText().toString()));

        Score score = new Score(cal, scoreTeamHydro, scoreTeamDynamite);
        return score;
    }

    @Override
    public void addScore(Score score) {
        ContentValues values = getContentValues(score);

        mDatabase.insert(TennisTable.NAME, null, values);

//        try {
//            File sd = Environment.getExternalStorageDirectory();
//            File data = Environment.getDataDirectory();
//
//            if (sd.canWrite()) {
//                String currentDBPath = "//data//au.edu.holmesglen.kirstine_n.tennisdinner//databases//tennisDinner.db";
//                String backupDBPath = "tennisDinner.db";
//                File currentDB = new File(data, currentDBPath);
//                File backupDB = new File(sd, backupDBPath);
//
//                if (currentDB.exists()) {
//                    FileChannel src = new FileInputStream(currentDB).getChannel();
//                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                    dst.transferFrom(src, 0, src.size());
//                    src.close();
//                    dst.close();
//                }
//            } else {
//
//                Log.v(LOGGING_TAG, "sc cannot write");
//            }
//        } catch (Exception e) {
//            Log.v(LOGGING_TAG, "exception" + e.toString());
//
//        }
    }


    @Override
    public void updateScore(Score score) {
        String uuidString = score.getId().toString();
        ContentValues values = getContentValues(score);

        mDatabase.update(TennisTable.NAME, values,
                TennisTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }


    private void deleteScores(String whereClause, String[] whereArgs) {
        mDatabase.delete(TennisTable.NAME, whereClause, whereArgs);
    }


    public void deleteScores() {
        mDatabase.delete(TennisTable.NAME, null, null);
    }

    public void deleteOneScore(Score score) {
        mDatabase.delete(TennisTable.NAME, TennisTable.Cols.UUID + "='" + score.getId() + "'", null);
    }

    private static ContentValues getContentValues(Score score) {
        ContentValues values = new ContentValues();
        values.put(TennisTable.Cols.UUID, score.getId().toString());
        values.put(TennisTable.Cols.DATE, score.getDate().getTime().toString());
        values.put(TennisTable.Cols.HYDRO_SCORE, score.getScoreHydro().toString());
        values.put(TennisTable.Cols.DYNAMITE_SCORE, score.getScoreDynamite().toString());

        return values;
    }

    private ScoreCursorWrapper queryScores(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TennisTable.NAME,
                null,  // columns - null selects all columns
                whereClause,
                whereArgs,
                null,  // group by
                null,  // having
                TennisTable.Cols.DATE + " DESC"  // order by
        );

        return new ScoreCursorWrapper(cursor);
    }

    @Override
    public Collection<Score> getScores() {
        List<Score> scores = new ArrayList<>();

        ScoreCursorWrapper cursor = queryScores(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                scores.add(cursor.getScore());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        // sort the scores by date desc
        sortScoresByDateDesc(scores);

        return scores;
    }

    private void sortScoresByDateDesc(List<Score> scoresList) {

        Collections.sort(scoresList, new Comparator<Score>() {
            public int compare(Score score1, Score score2) {
                // sort desc by date
                return score2.getDate().compareTo(score1.getDate());
            }
        });
    }
}
