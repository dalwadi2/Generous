package in.vaksys.generous.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;

public class LoginActivity extends AppCompatActivity implements TextWatcher {

    @Bind(R.id.et_fname_email)
    EditText etFnameFragmentProfile;
    @Bind(R.id.et_pasword_login)
    EditText etPaswordLogin;
    private Toolbar loginToolbar;
    private Button btnSign, btn_login;

    private LinearLayout linerNew, linearNew1;
    View viewone;
    TextView campaign, tvForgotPassword;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginToolbar = (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(loginToolbar);
        loginToolbar.setTitle("Login");

        viewone = findViewById(R.id.viewNew);
        linearNew1 = (LinearLayout) findViewById(R.id.linearNew1);

        btn_login = (Button) findViewById(R.id.btn_login);

        etFnameFragmentProfile.addTextChangedListener(this);
        etPaswordLogin.addTextChangedListener(this);

        checkFieldsForEmptyValues();

        tvForgotPassword = (TextView) findViewById(R.id.tv_forgotpassword);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(LoginActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.interface_two);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnSign = (Button) findViewById(R.id.btn_sign);
        campaign = (TextView) findViewById(R.id.campaign);
        campaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ListCampaignActivity.class));
                finish();
            }
        });

        viewone.setVisibility(View.GONE);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewone.setVisibility(View.VISIBLE);
//                linearNew1.setVisibility(View.GONE);
                checkFieldsForEmptyValues();

            }
        });
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        checkFieldsForEmptyValues();
    }

    void checkFieldsForEmptyValues() {
        //Button b = (Button) findViewById(R.id.btn_login);

        String s1 = etFnameFragmentProfile.getText().toString();
        String s2 = etPaswordLogin.getText().toString();

        if (s1.equals("") || s2.equals("")) {
            btn_login.setBackgroundResource(R.drawable.btn_green_disable);
        } else {
            btn_login.setBackgroundResource(R.drawable.buttonshape_blue);
            btn_login.setTextColor(Color.WHITE);
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
