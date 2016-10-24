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
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    public static final String LOGGING_TAG = "KIRSTI: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TennisDinnerStorage tennisDinnerStorage = TennisDinnerStorageSQLite.getInstance(this);

        final Button btnAddNewScore = (Button) findViewById(R.id.btnAddNewScore);
        final Button btnResetScore = (Button) findViewById(R.id.btnResetScore);

//        int totalHydro = 0;
//        int totalDynamite = 0;
//
//        Collection<Score> scores = tennisDinnerStorage.getScores();
//
//        for (Score currentScore: scores) {
//            Log.v(LOGGING_TAG, "score: " + currentScore.display());
//            totalHydro += currentScore.getScoreHydro();
//            totalDynamite += currentScore.getScoreDynamite();
//        }

        final TextView tvScoreTeamHydro = (TextView) findViewById(R.id.tvTeamHydroScore);
        final TextView tvScoreTeamDynamite = (TextView) findViewById(R.id.tvTeamDynamiteScore);
        final EditText etScoreTeamHydro = (EditText) findViewById(R.id.etTeamHydro);
        final EditText etScoreTeamDynamite = (EditText) findViewById(R.id.etTeamDynamite);
        final TextView tvDate = (TextView) findViewById(R.id.tvDisplayDate);

        int[] scoreArr = setCurrentStanding(tennisDinnerStorage);
        tvScoreTeamHydro.setText("" + scoreArr[0]);
        tvScoreTeamDynamite.setText("" + scoreArr[1]);

        btnAddNewScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    // get text view and edit text
                    String tvScoreTeamHydroValue = tvScoreTeamHydro.getText().toString();
                    String etScoreTeamHydroValue = etScoreTeamHydro.getText().toString();

                    String tvScoreTeamDynamiteValue = tvScoreTeamDynamite.getText().toString();
                    String etScoreTeamDynamiteValue = etScoreTeamDynamite.getText().toString();

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal  = Calendar.getInstance();
                    cal.setTime(df.parse(tvDate.getText().toString()));

                    // create Score object
                    Score score = new Score(cal, (Integer) Integer.parseInt(etScoreTeamHydroValue), (Integer) Integer.parseInt(etScoreTeamDynamiteValue));

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

//                    Collection<Score> scores = tennisDinnerStorage.getScores();
//
//                    for (Score currentScore: scores) {
//                        Log.v(LOGGING_TAG, "score: " + currentScore.display());
//                    }

                    clearInput(etScoreTeamDynamite, etScoreTeamHydro, tvDate);

                    Toast.makeText(MainActivity.this, "Scores were added", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException ex) {
                    Toast.makeText(MainActivity.this, "Enter scores", Toast.LENGTH_SHORT).show();

                } catch (ParseException parseEx) {
                    Log.v(LOGGING_TAG, "parseexeption");
                    Toast.makeText(MainActivity.this, "Pick a date", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnResetScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tennisDinnerStorage.deleteScores();

                int[] scoreArr = setCurrentStanding(tennisDinnerStorage);
                tvScoreTeamHydro.setText("" + scoreArr[0]);
                tvScoreTeamDynamite.setText("" + scoreArr[1]);

                clearInput(etScoreTeamDynamite, etScoreTeamHydro, tvDate);

                Toast.makeText(MainActivity.this, "Scores were reset", Toast.LENGTH_SHORT).show();
            }
        });
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

    public int[] setCurrentStanding(TennisDinnerStorage tennisDinnerStorage) {
        int totalHydro = 0;
        int totalDynamite = 0;

        Collection<Score> scores = tennisDinnerStorage.getScores();

        for (Score currentScore: scores) {
            Log.v(LOGGING_TAG, "score: " + currentScore.display());
            totalHydro += currentScore.getScoreHydro();
            totalDynamite += currentScore.getScoreDynamite();
        }

        int[] arr = new int[2];
        arr[0] = totalHydro;
        arr[1] = totalDynamite;

        return arr;
    }

    public void clearInput(EditText etScoreTeamDynamite, EditText etScoreTeamHydro, TextView date) {
        date.setText("");
        etScoreTeamDynamite.setText("");
        etScoreTeamHydro.setText("");
    }
}
