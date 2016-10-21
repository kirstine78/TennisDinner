package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Collection;

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

    public static TennisDinnerStorage get(Context context) {
        if (tennisDinnerStorageSQLite == null) {
            tennisDinnerStorageSQLite = new TennisDinnerStorageSQLite(context);
        }
        return tennisDinnerStorageSQLite;
    }

    @Override
    public void addScore(Score score) {

    }

    @Override
    public Collection<Score> getScores() {
        return null;
    }
}
