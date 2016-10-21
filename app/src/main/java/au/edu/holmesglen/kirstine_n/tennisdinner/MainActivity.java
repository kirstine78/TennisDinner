package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String LOGGING_TAG = "KIRSTI: ";

//    public static final String MyTotalScores = "myTotalScores" ;
//    public static final String TotalScoreTeamHydro = "totalScoreTeamHydroKey";
//    public static final String TotalScoreTeamDynamite = "totalScoreTeamDynamiteKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TennisDinnerStorage tennisDinnerStorage = TennisDinnerStorageSQLite.get(this);

//        sharedpreferences = getSharedPreferences(MyTotalScores, 0);

        final Button btnAddNewScore = (Button) findViewById(R.id.btnAddNewScore);


//        String scoreHydro = sharedpreferences.getString("TotalScoreTeamHydro", "missing");
//        Log.v("Kirsti", "scoreHydro: " + scoreHydro);

        // set textview to current total score
//        tvScoreTeamHydro.setText(scoreHydro);

        final TextView tvScoreTeamHydro = (TextView) findViewById(R.id.tvTeamHydroScore);
        final TextView tvScoreTeamDynamite = (TextView) findViewById(R.id.tvTeamDynamiteScore);
        final EditText etScoreTeamHydro = (EditText) findViewById(R.id.etTeamHydro);
        final EditText etScoreTeamDynamite = (EditText) findViewById(R.id.etTeamDynamite);


        btnAddNewScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String FILENAME = "hello_file";
                String string = "hello world!";

//                FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
//                fos.write(string.getBytes());
//                fos.close();


//                SharedPreferences.Editor editor = sharedpreferences.edit();

                try {
                    // get text view and edit text
                    String tvScoreTeamHydroValue = tvScoreTeamHydro.getText().toString();
                    String etScoreTeamHydroValue = etScoreTeamHydro.getText().toString();

                    String tvScoreTeamDynamiteValue = tvScoreTeamDynamite.getText().toString();
                    String etScoreTeamDynamiteValue = etScoreTeamDynamite.getText().toString();

                    TextView tvDate = (TextView) findViewById(R.id.tvDisplayDate);

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal  = Calendar.getInstance();
                    cal.setTime(df.parse(tvDate.getText().toString()));

                    // create Score object
                    Score score = new Score(cal, (Integer) Integer.parseInt(tvScoreTeamHydroValue), (Integer) Integer.parseInt(tvScoreTeamDynamiteValue));

                    // add to storage
                    tennisDinnerStorage.addScore(score);

                    // add to total score
                    int intUpdatedScoreTeamHydro = ((Integer) Integer.parseInt(tvScoreTeamHydroValue)) +
                            ((Integer) Integer.parseInt(etScoreTeamHydroValue));

                    int intUpdatedScoreTeamDynamite = ((Integer) Integer.parseInt(tvScoreTeamDynamiteValue)) +
                            ((Integer) Integer.parseInt(etScoreTeamDynamiteValue));

                    // convert total score to string
                    String strUpdatedScoreTeamHydro = Integer.toString(intUpdatedScoreTeamHydro);
                    String strUpdatedScoreTeamDynamite = Integer.toString(intUpdatedScoreTeamDynamite);

                    // set textview to updated score
                    tvScoreTeamHydro.setText(strUpdatedScoreTeamHydro);
                    tvScoreTeamDynamite.setText(strUpdatedScoreTeamDynamite);


                    // update process team dynamite



                    // set textview to updated score

//                    editor.putString(TotalScoreTeamHydro, strUpdatedScoreTeamHydro);
//                    editor.putString(TotalScoreTeamDynamite, strUpdatedScoreTeamDynamite);
////                    editor.commit();
//                    editor.apply();

//                    String scoreHydro = sharedpreferences.getString("TotalScoreTeamHydro", "");


//                    TextView tvDisplayOfDate= (TextView) findViewById(R.id.tvDisplayDate);
//                    tvDisplayOfDate.setText("scoreHydro: " + scoreHydro);
                    Toast.makeText(MainActivity.this, "Scores were added", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Enter scores", Toast.LENGTH_SHORT).show();

                } catch (ParseException parseEx) {
                    Log.v(LOGGING_TAG, "parseexeption");
                    Toast.makeText(MainActivity.this, "Pick a date", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
