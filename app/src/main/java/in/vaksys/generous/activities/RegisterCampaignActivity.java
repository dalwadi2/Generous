package in.vaksys.generous.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerCheckboxAdapter;
import in.vaksys.generous.adapters.SpinnerTextAdapter;

public class RegisterCampaignActivity extends AppCompatActivity {

    Toolbar tool;
    Spinner sp_recu, sp_status_foundation, sp_typeBenef;
    String[] titles = {"Type of Donation accepted?", "Clothes", "Drugs or Health equipment", "Books", "Computer accessories",
            "Furniture or appliances", "Other Donations"};

    String[] status = {"Status of the foundation", "International organization", "State organization",
            "Organization approved by the state", "Other"};

    String[] typeBenef = {"Type of beneficiary", "One", "Two",
            "Three", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_campaign);

//        tool = (Toolbar) findViewById(R.id.regToolbar);
//        setSupportActionBar(tool);

        sp_recu = (Spinner) findViewById(R.id.sp_recu);
        sp_status_foundation = (Spinner) findViewById(R.id.sp_status_foundation);
        sp_typeBenef = (Spinner) findViewById(R.id.sp_typeBenef);

        SpinnerTextAdapter spinnerTextAdapter = new SpinnerTextAdapter(this, status);
        sp_status_foundation.setAdapter(spinnerTextAdapter);

        SpinnerCheckboxAdapter spinnerCheckboxAdapter = new SpinnerCheckboxAdapter(this, titles);
        sp_recu.setAdapter(spinnerCheckboxAdapter);

        SpinnerTextAdapter spinnerTextAdapter1 = new SpinnerTextAdapter(this, typeBenef);
        sp_typeBenef.setAdapter(spinnerTextAdapter1);
    }
}
