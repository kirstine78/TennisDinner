package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import au.edu.holmesglen.kirstine_n.tennisdinner.TennisDBSchema.TennisTable;

/**
 * Created by Kirsti on 21/10/2016.
 */

public class TennisSQLiteHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tennisDinner.db";

    public TennisSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TennisTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        TennisTable.Cols.UUID + ", " +
                        TennisTable.Cols.DATE + ", " +
                        TennisTable.Cols.HYDRO_SCORE + ", " +
                        TennisTable.Cols.DYNAMITE_SCORE +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
