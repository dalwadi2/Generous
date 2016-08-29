package in.vaksys.generous.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapterArray;


public class NotificationActivity extends AppCompatActivity {

    @Bind(R.id.tv_notification_cancel)
    TextView tvNotificationCancel;
    @Bind(R.id.tv_notification_confirm)
    TextView tvNotificationConfirm;
    @Bind(R.id.tv_span)
    TextView tvSpan;
    private Toolbar toolbar;
    private Dialog dialog;
    private AppCompatSpinner spinnerApp;
    private ArrayList<String> days;
    private RadioGroup rg_cancel_donation;
    private LinearLayout linear_spin_one;
    private TextView tv_noti_one, tv_noti_two;
    private View individual_noti;
    private LinearLayout linear_noti_noti;

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String individual = "individual";
    private String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_list);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        temp = sharedPreferences.getString(individual, "");

        toolbar = (Toolbar) findViewById(R.id.notificationTool_bar);
        setSupportActionBar(toolbar);

        linear_noti_noti = (LinearLayout) findViewById(R.id.linear_noti_noti);

        individual_noti = findViewById(R.id.individual_noti);
        individual_noti.setVisibility(View.GONE);

        if (temp.equalsIgnoreCase("individual")) {
            individual_noti.setVisibility(View.VISIBLE);
            linear_noti_noti.setVisibility(View.GONE);
        } else {
            linear_noti_noti.setVisibility(View.VISIBLE);
            individual_noti.setVisibility(View.GONE);
        }

        tv_noti_one = (TextView) findViewById(R.id.tv_noti_one);
        tv_noti_two = (TextView) findViewById(R.id.tv_noti_two);


        Spannable wordtoSpan = new SpannableString("The campaign doesn’t achieve the minimum goal < 50% ,please click here to Postpone the start date campaign or Canceled campaign \n");

        wordtoSpan.setSpan(new ForegroundColorSpan(Color.GREEN), 66, 71, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvSpan.setText(wordtoSpan);

        Spannable wordtoSpan1 = new SpannableString("Do you accept to give a few of your time to assiste and evaluate the  campaign named . Breakfast for  families in Iraq ? thanks to your help we can ensure that donations are given to the persons whose really needs View\n");

        wordtoSpan1.setSpan(new ForegroundColorSpan(Color.GREEN), 214, 218, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_noti_one.setText(wordtoSpan1);


        Spannable wordtoSpan2 = new SpannableString("Meals for refugees in Syria will begin In March 25, 2016 View\n");

        wordtoSpan2.setSpan(new ForegroundColorSpan(Color.GREEN), 57, 61, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_noti_two.setText(wordtoSpan2);

        tvSpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(NotificationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.interface_one);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                spinnerApp = (AppCompatSpinner) dialog.findViewById(R.id.spinner_interface_one);
                rg_cancel_donation = (RadioGroup) dialog.findViewById(R.id.rg_cancel_donation);
                linear_spin_one = (LinearLayout) dialog.findViewById(R.id.linear_spin_one);
                //String[] days = {"42days", "52days", "60days"};
                days = new ArrayList<String>();
                for (int i = 1; i <= 59; i++) {
                    if (i == 1) {
                        days.add(i + "day");
                    }
                    days.add(i + 1 + "days");
                }

                SpinnerTextAdapterArray spinnerInterface = new SpinnerTextAdapterArray(NotificationActivity.this, days);
                spinnerApp.setAdapter(spinnerInterface);

                rg_cancel_donation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rb_yes) {
                            linear_spin_one.setVisibility(View.VISIBLE);
                        }

                        if (checkedId == R.id.rb_no) {
                            linear_spin_one.setVisibility(View.GONE);
                        }
                    }
                });

                dialog.show();
            }
        });

        tvNotificationCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(NotificationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.interface_one);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                spinnerApp = (AppCompatSpinner) dialog.findViewById(R.id.spinner_interface_one);
                rg_cancel_donation = (RadioGroup) dialog.findViewById(R.id.rg_cancel_donation);
                linear_spin_one = (LinearLayout) dialog.findViewById(R.id.linear_spin_one);
                //String[] days = {"42days", "52days", "60days"};
                days = new ArrayList<String>();
                for (int i = 1; i <= 59; i++) {
                    if (i == 1) {
                        days.add(i + "day");
                    }
                    days.add(i + 1 + "days");
                }

                SpinnerTextAdapterArray spinnerInterface = new SpinnerTextAdapterArray(NotificationActivity.this, days);
                spinnerApp.setAdapter(spinnerInterface);

                rg_cancel_donation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rb_yes) {
                            linear_spin_one.setVisibility(View.VISIBLE);
                        }

                        if (checkedId == R.id.rb_no) {
                            linear_spin_one.setVisibility(View.GONE);
                        }
                    }
                });

                dialog.show();
            }
        });

        tvNotificationConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(NotificationActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.interface_four);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

    }
}
