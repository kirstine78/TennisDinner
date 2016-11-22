package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TennisDinnerStorage tennisDinnerStorage = TennisDinnerStorageSQLite.getInstance(this);

        ListView listView = (ListView) findViewById(R.id.scoresList);

        final Score [] scores = tennisDinnerStorage.getScores().toArray(new Score[0]);

        // create adapter we use for the connection between data source and object on screen
        // the adapter will have the values array...
        ScoreAdapter adapter = new ScoreAdapter(this, scores);

        listView.setAdapter(adapter);

        // listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // position is which list item is chosen
                // start new activity
                Intent intent = new Intent(HistoryActivity.this, EditScoreActivity.class);

                // pass in the whole score object
                intent.putExtra("aScoreObject", scores[position]);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
            case R.id.action_settings:
                i = new Intent(this, SettingsActivity.class);
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
