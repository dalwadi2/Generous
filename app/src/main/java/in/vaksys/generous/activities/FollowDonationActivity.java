package in.vaksys.generous.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;

public class FollowDonationActivity extends AppCompatActivity {

    @Bind(R.id.sp_all)
    Spinner spAll;

    private Toolbar toolbar;

    String[] all = {"All", "Auditor", "Donor", "Visitor"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_donation);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.followToolbar);
        setSupportActionBar(toolbar);

        SpinnerTextAdapter spinnerTextAdapter = new SpinnerTextAdapter(FollowDonationActivity.this, all);
        spAll.setAdapter(spinnerTextAdapter);
    }
}
