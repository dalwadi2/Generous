package in.vaksys.generous.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;
import in.vaksys.generous.adapters.SpinnerTextAdapterArray;

public class SettingsSecondActivity extends AppCompatActivity {

    @Bind(R.id.sp_participated)
    Spinner spParticipated;
    @Bind(R.id.sp_days_settings)
    Spinner spDaysSettings;

    private ArrayList<String> days;
    String[] reminder = {"I participate", "Any Campaigns"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_second);
        ButterKnife.bind(this);

        days = new ArrayList<String>();
        for (int i = 1; i <= 60; i++) {
            days.add(i + "days");
        }

        SpinnerTextAdapterArray spinnerInterface = new SpinnerTextAdapterArray(SettingsSecondActivity.this, days);
        spDaysSettings.setAdapter(spinnerInterface);

        SpinnerTextAdapter sp = new SpinnerTextAdapter(SettingsSecondActivity.this, reminder);
        spParticipated.setAdapter(sp);
    }
}
