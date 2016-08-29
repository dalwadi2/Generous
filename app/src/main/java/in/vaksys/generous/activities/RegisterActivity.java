package in.vaksys.generous.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;
import in.vaksys.generous.extras.MyApplication;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Bind(R.id.et_fname_register)
    EditText etFnameRegister;
    @Bind(R.id.et_name_register)
    EditText etNameRegister;
    @Bind(R.id.et_email_register)
    EditText etEmailRegister;
    @Bind(R.id.btn_registerClick_register)
    Button btnRegisterClickRegister;
    @Bind(R.id.tv_allReadyAccount_register)
    TextView tvAllReadyAccountRegister;
    @Bind(R.id.linear_register_layout)
    LinearLayout linearRegisterLayout;
    @Bind(R.id.et_password_register)
    EditText etPasswordRegister;
    @Bind(R.id.et_city_register)
    EditText etCityRegister;
    @Bind(R.id.sp_country_register)
    Spinner spCountryRegister;
    @Bind(R.id.toggle_individual)
    ToggleButton toggleIndividual;
    @Bind(R.id.toggle_charity_foundation)
    ToggleButton toggleCharityFoundation;
    //    @Bind(R.id.tv_grey_individual)
//    TextView tvGreyIndividual;
//    @Bind(R.id.tv_green_individual)
//    TextView tvGreenIndividual;
//    @Bind(R.id.tv_green_foundation)
//    TextView tvGreenFoundation;
//    @Bind(R.id.tv_grey_foundation)
//    TextView tvGreyFoundation;
    private Toolbar ToolbarRegister;
    private Button btnRegister;

    private Typeface font;

    MyApplication myApplication;
    private TextView alreadyAccount;

    String[] country = {"Select Country", "Albania", "Algeria", "Angola", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Belarus", "Belgium", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Bulgaria", "Burkina Faso", "Cameroon", "Canada", "Central African Republic (CAR)", "Chad", "Chile", "China", "Colombia", "Democratic Republic of the Congo", "Republic of the Congo", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Estonia", "Ethiopia", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Italy", "Jamaica", "Japan", "Jordan", "Kenya", "Kosovo", "Kuwait", "Lebanon", "Libya", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Mali", "Malta", "Mauritania", "Mauritius", "Mexico", "Monaco", "Mongolia", "Montenegro", "Morocco", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palestine", "Panama", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "St. Vincent and the Grenadines", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Somalia", "South Africa", "South Korea", "Spain", "Sri Lanka", "Sudan", "Swaziland", "Sweden", "Switzerland", "Syria", "Tanzania", "Thailand", "Togo", "Tunisia", "Turkey", "Uganda", "Ukraine", "United Arab Emirates (UAE)", "United Kingdom (UK)", "United States of America (USA)", "Uruguay", "Uzbekistan", "Vatican City (Holy See)", "Venezuela", "Vietnam", "Yemen", "Zambia"};
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String individual = "individual";
    public static final String foundation = "foundation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        myApplication = (MyApplication) getApplicationContext();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        ToolbarRegister = (Toolbar) findViewById(R.id.toolbar_register);
        setSupportActionBar(ToolbarRegister);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ToolbarRegister.setTitle("Register");

        alreadyAccount = (TextView) findViewById(R.id.tv_allReadyAccount_register);
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();

            }
        });

//        tvGreyIndividual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvGreenIndividual.setVisibility(View.VISIBLE);
//                tvGreyIndividual.setVisibility(View.GONE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                editor.putString(individual, "individual");
//                editor.commit();
//            }
//        });
//
//        tvGreenIndividual.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvGreyIndividual.setVisibility(View.VISIBLE);
//                tvGreenIndividual.setVisibility(View.GONE);
//            }
//        });
//
//        tvGreyFoundation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvGreenFoundation.setVisibility(View.VISIBLE);
//                tvGreyFoundation.setVisibility(View.GONE);
//            }
//        });
//
//        tvGreenFoundation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvGreenFoundation.setVisibility(View.GONE);
//                tvGreyFoundation.setVisibility(View.VISIBLE);
//            }
//        });


        SpinnerTextAdapter spinnerTextAdapter = new SpinnerTextAdapter(RegisterActivity.this, country);
        // attaching data adapter to spinner
        spCountryRegister.setAdapter(spinnerTextAdapter);


       /* font = myApplication.getGujFont(RegisterActivity.this);
        etFnameRegister.setTypeface(font);
        etNameRegister.setTypeface(font);
        etEmailRegister.setTypeface(font);
        etPasswordRegister.setTypeface(font);
        etCityRegister.setTypeface(font);*/

        toggleIndividual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (toggleCharityFoundation.isChecked()) {
                        toggleCharityFoundation.setChecked(false);
                    }
                    Toast.makeText(RegisterActivity.this, "Individual Selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Individual Cancel", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toggleCharityFoundation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (toggleIndividual.isChecked()) {
                        toggleIndividual.setChecked(false);
                        getSharedPreferences(MyPREFERENCES, 0).edit().clear().commit();
                    }
                    Toast.makeText(RegisterActivity.this, "charity foundation Selected", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RegisterActivity.this, "charity foundation Cancel", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister = (Button) findViewById(R.id.btn_registerClick_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (toggleIndividual.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(individual, "individual");
                    editor.commit();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }
        });

        tvAllReadyAccountRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
