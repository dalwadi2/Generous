package in.vaksys.generous.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import in.vaksys.generous.R;

public class ListCampaignActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_campaign);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.list_campaign_toolbar);
        setSupportActionBar(mToolbar);


    }
}
