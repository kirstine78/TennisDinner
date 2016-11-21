package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TennisDinnerStorage tennisDinnerStorage = TennisDinnerStorageSQLite.getInstance(this);

        final Button btnResetScore = (Button) findViewById(R.id.btnResetScore);

        // to reset score
        btnResetScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tennisDinnerStorage.deleteScores();

//                int[] scoreArr = setCurrentStanding(tennisDinnerStorage);
//                tvScoreTeamHydro.setText("" + scoreArr[0]);
//                tvScoreTeamDynamite.setText("" + scoreArr[1]);
//
//                clearInput(etScoreTeamDynamite, etScoreTeamHydro, tvDate);

                Toast.makeText(SettingsActivity.this, "Scores were reset", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
            case R.id.action_teams:
                i = new Intent(this, TeamsActivity.class);
                startActivity(i);
                return true;
        }
        return false;  // nothing happened  no menu items has been selected
    }

}
