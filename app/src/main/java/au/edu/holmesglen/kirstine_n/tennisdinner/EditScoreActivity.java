package au.edu.holmesglen.kirstine_n.tennisdinner;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class EditScoreActivity extends AppCompatActivity {

    private TextView tvDate;
    private EditText etHydroScore;
    private EditText etDynamiteScore;
    private Score score;

    private String scoreTeamHydroValue;
    private String scoreTeamDynamiteValue;

    private TennisDinnerStorage tennisDinnerStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        score = (Score) intent.getSerializableExtra("aScoreObject");

        tennisDinnerStorage = TennisDinnerStorageSQLite.getInstance(this);

//        String aDate = intent.getStringExtra("aDate");
//        Integer scoreHydro = intent.getIntExtra("scoreHydro", 0);
//        Integer scoreDynamite = intent.getIntExtra("scoreDynamite", 0);

        tvDate = (TextView) findViewById(R.id.tvDisplayDate);
        tvDate.setText(score.getDateForTextView());

        etHydroScore = (EditText) findViewById(R.id.etTeamHydro);
        etHydroScore.setText(score.getScoreHydro().toString());

        etDynamiteScore = (EditText) findViewById(R.id.etTeamDynamite);
        etDynamiteScore.setText(score.getScoreDynamite().toString());

        final Button btnSubmitChanges = (Button) findViewById(R.id.btnEditScore);
        final Button btnDeleteScore = (Button) findViewById(R.id.btnDeleteScore);
        final Button btnCancelToMakeChanges = (Button) findViewById(R.id.btnCancelEditScore);

        btnSubmitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                // get text view and edit text
                scoreTeamHydroValue = etHydroScore.getText().toString();
                scoreTeamDynamiteValue = etDynamiteScore.getText().toString();

                if (scoreTeamHydroValue.equals("") || scoreTeamDynamiteValue.equals(""))
                {
                    Toast.makeText(EditScoreActivity.this, "Enter scores", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // show alert pop up
                    alertMessage();
                }
            }
        });

        btnDeleteScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // show alert pop up
                alertMessageDelete();
            }
        });

        btnCancelToMakeChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // go to another Activity
                Intent intent = new Intent(EditScoreActivity.this, HistoryActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "No changes were made", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent i;

        switch (item.getItemId())  // which menu item has been selected
        {
            case R.id.action_main:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.action_history:
                i = new Intent(this, HistoryActivity.class);
                startActivity(i);
                return true;
            case R.id.action_settings:
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_teams:
                i = new Intent(this, TeamsActivity.class);
                startActivity(i);
                return true;
        }
        return false;  // nothing happened, no menu items has been selected
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        // update the data for the specific score.

                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cal  = Calendar.getInstance();

                        try {
                            cal.setTime(df.parse(tvDate.getText().toString()));

                            // set the values for the score object. Date, score hydro, and score dynamite
                            score.setDate(cal);
                            score.setScoreHydro(Integer.parseInt(scoreTeamHydroValue));
                            score.setScoreDynamite(Integer.parseInt(scoreTeamDynamiteValue));

                            // save the changes for the score object in storage
                            tennisDinnerStorage.updateScore(score);

                            // wipe fields
                            emptyFields();

                            // go to another Activity
                            Intent intent = new Intent(EditScoreActivity.this, HistoryActivity.class);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "Changes were saved", Toast.LENGTH_SHORT).show();

                        } catch (NumberFormatException ex) {
                            Toast.makeText(EditScoreActivity.this, "Enter valid scores", Toast.LENGTH_SHORT).show();

                        } catch (ParseException parseEx) {
//                        Log.v(LOGGING_TAG, "parseexeption");
                            Toast.makeText(EditScoreActivity.this, "Pick a date", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.prompt_edit_score)
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.cancel, dialogClickListener).show();
    }

    public void alertMessageDelete() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        // delete record for this score
                        tennisDinnerStorage.deleteOneScore(score);

                        // wipe fields
                        emptyFields();

                        // go to another Activity
                        Intent intent = new Intent(EditScoreActivity.this, HistoryActivity.class);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Record were deleted", Toast.LENGTH_SHORT).show();

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.prompt_delete_score)
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.cancel, dialogClickListener).show();
    }

    private void emptyFields() {
        // get text view and edit text
        etHydroScore.setText("");
        etDynamiteScore.setText("");
        tvDate.setText("");
    }
}
