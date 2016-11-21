package au.edu.holmesglen.kirstine_n.tennisdinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button btnSubmitChanges = (Button) findViewById(R.id.btnEditScore);

        btnSubmitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // update the data for the specific score.
                // edit in  storage
//                tennisDinnerStorage.updateScore(score);

                // go to another Activity
                Intent intent = new Intent(EditScoreActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Changes were saved", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
