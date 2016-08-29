package in.vaksys.generous.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.activities.LocationActivity;
import in.vaksys.generous.adapters.SpinnerCheckboxAdapter;
import in.vaksys.generous.adapters.SpinnerTextAdapter;
import in.vaksys.generous.extras.MyApplication;
import in.vaksys.generous.model.Picselected;

/**
 * Created by dell980 on 6/1/2016.
 */
public class CampaignFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.tv_food)
    TextView tvFood;
    @Bind(R.id.tv_clothes)
    TextView tvClothes;
    @Bind(R.id.tv_schooling)
    TextView tvSchooling;
    @Bind(R.id.tv_health)
    TextView tvHealth;
    @Bind(R.id.tv_animals)
    TextView tvAnimals;
    @Bind(R.id.tv_nature)
    TextView tvNature;
    @Bind(R.id.tv_globalCrisis)
    TextView tvGlobalCrisis;
    @Bind(R.id.tv_compignName_foodList)
    TextView tvCompignNameFoodList;
    @Bind(R.id.tv_compignAmount_foodList)
    TextView tvCompignAmountFoodList;
    @Bind(R.id.tv_compignDate_foodList)
    TextView tvCompignDateFoodList;
    //    @Bind(R.id.tv_compignDonation_foodList1)
//    TextView tvCompignDonationFoodList;
    @Bind(R.id.tv_compignView_foodList)
    TextView tvCompignViewFoodList;
    @Bind(R.id.tv_compignComment_foodList)
    TextView tvCompignCommentFoodList;
    @Bind(R.id.tv_compignStar_foodList)
    TextView tvCompignStarFoodList;
    @Bind(R.id.linear_schooling_compaign)
    LinearLayout linearSchoolingCompaign;
    @Bind(R.id.linear_health_compaign)
    LinearLayout linearHealthCompaign;
    @Bind(R.id.linear_animals_compaign)
    LinearLayout linearAnimalsCompaign;
    @Bind(R.id.linear_nature_compaign)
    LinearLayout linearNatureCompaign;
    @Bind(R.id.linear_globalCrisis_compaign)
    LinearLayout linearGlobalCrisisCompaign;
    private ImageView ivFood;


    private LinearLayout one, two, three;
    private RadioGroup rgOne;
    private Spinner spOne, sp_type_dona, sp_month, sp_year, sp_forWhat_registerCampaign;
    private EditText tvOne, tv_terms;
    private View clickDonation, clickClothes, clickCommentClothes;
    private RadioButton rb_donation, rb_otherDonation, rvVisa;
    private RadioGroup myRadioGroup, visaGroup;

    LinearLayout tvTerms;

    private FoodFragment foodFragment;
    private ImageView commentClothes;
    private View foodView, click_btn_giveDonation, click_btn_campaign, clickRegisterCampaign;
    private LinearLayout linearMainCampaign, linear_clothes_compaign;

    private Button btnGiveDonation;
    private EditText etNamePaypal, etEmailPaypal, etPasswordPaypal, et_name_visa;

    private Typeface fontType;

    private FloatingActionButton fabList, fabCampaign;
    private CardView cardView, cardView1;

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String individual = "individual";

    String[] typeDonation = {"Type Of donation", "Clothes", "Drugs or Health equipment", "Books",
            "Computer accessories", "Other donations"};


    String[] typeRecuperation = {"Method of recuperation", "At home", "Send by post", "Delivered directly to the foundation", "Others"};

    String[] month = {"Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    String[] year = {"Year", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};


    String[] typeBenef = {"Type of beneficiary", "Families", "Children",
            "Women", "Handicaps", "Handicaps"};

    String[] usd = {"USD", "EUR", "CAD", "GBP", "CNY", "INR", "AUD", "JPY", "SEK", "MYR", "IDY", "MAD", "SAR", "AED", "QAR", "PLN", "SGD", "BRL"};

    String[] titles = {"Type of Donation accepted?", "Clothes", "Drugs or Health equipment", "Books", "Computer accessories",
            "Furniture or appliances", "Other Donations"};

    String[] status = {"Status of the foundation", "International organization", "State organization",
            "Organization approved by the state", "Other"};

    private EditText etOther, et_fname;
    private TextView tvOK, later, tv_validateOne, tvView, tvFollowClothes, tv_compignDonation_foodList1, paypal, tv_visa, tv_donation_clothes;
    private Dialog dialog;
    private AppCompatSpinner spinnerApp, sp_usd_user, sp_usd_user1;
    private Spinner spinnerUsd, sp_typeBenef, spRecu, spStatus, sp_type_dona1;
    private SwitchButton switchRegCampaign;
    private LinearLayout linearRegCampaign, linear_estimated_donation, linear_card_cvv, linear_month_year;
    private Button btnRegCampaign, btn_visa_paypal;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    SimpleDateFormat sdf;
    private TextView startDate, endDate, startTime, tvCreateDate;
    String temp;
    private View campaign_user;
    private MyApplication myApplication;
    RadioGroup rg_visaPaypal, radioGroup3;
    RadioButton rbVisa, rbPaypal;
    private EditText et_visa_select, et_paypal_select, etOrganization;

    public static CampaignFragment newInstance(int index) {
        CampaignFragment fragment = new CampaignFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_screen, container, false);
        ButterKnife.bind(this, rootView);

        myApplication = MyApplication.getInstance();

        sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        temp = sharedPreferences.getString(individual, "");
        Log.e("SHAREDPREFERENCE", "onCreateView: " + temp);

        campaign_user = rootView.findViewById(R.id.campaign_user);
        campaign_user.setVisibility(View.GONE);


        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        ivFood = (ImageView) rootView.findViewById(R.id.iv_food);
        foodView = rootView.findViewById(R.id.click_food);
        tv_compignDonation_foodList1 = (TextView) foodView.findViewById(R.id.tv_compignDonation_foodList1);

        tv_compignDonation_foodList1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campaign_user.setVisibility(View.VISIBLE);
                foodView.setVisibility(View.GONE);
            }
        });

        sp_usd_user = (AppCompatSpinner) campaign_user.findViewById(R.id.sp_usd_user);
        SpinnerTextAdapter spUser = new SpinnerTextAdapter(getActivity(), usd);
        sp_usd_user.setAdapter(spUser);

        sp_type_dona1 = (Spinner) campaign_user.findViewById(R.id.sp_type_dona1);
        SpinnerTextAdapter typeDona = new SpinnerTextAdapter(getActivity(), typeDonation);
        sp_type_dona1.setAdapter(typeDona);

        linearMainCampaign = (LinearLayout) rootView.findViewById(R.id.linear_main_campaign);
        //fabCampaign = (FloatingActionButton) rootView.findViewById(R.id.fab_campaign_confirm);
        click_btn_campaign = rootView.findViewById(R.id.click_btn_campaign);


        tvView = (TextView) foodView.findViewById(R.id.tv_compignView_foodList);
        tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.equalsIgnoreCase("individual")) {
                    clickClothes.setVisibility(View.VISIBLE);
                    tv_donation_clothes.setVisibility(View.VISIBLE);
                    foodView.setVisibility(View.GONE);
                } else {
                    clickClothes.setVisibility(View.VISIBLE);
                    foodView.setVisibility(View.GONE);
                }

            }
        });


        clickRegisterCampaign = rootView.findViewById(R.id.register_campaign);
        clickRegisterCampaign.setVisibility(View.GONE);

        rg_visaPaypal = (RadioGroup) clickRegisterCampaign.findViewById(R.id.rg_visaPaypal);
        rbVisa = (RadioButton) clickRegisterCampaign.findViewById(R.id.rbVisa);
        rbPaypal = (RadioButton) clickRegisterCampaign.findViewById(R.id.rbPaypal);
        et_visa_select = (EditText) clickRegisterCampaign.findViewById(R.id.et_visa_select);
        et_paypal_select = (EditText) clickRegisterCampaign.findViewById(R.id.et_paypal_select);

        radioGroup3 = (RadioGroup) campaign_user.findViewById(R.id.myRadioGroup3);
        etOrganization = (EditText) campaign_user.findViewById(R.id.et_organization);

        rg_visaPaypal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbVisa) {
                    et_paypal_select.setEnabled(false);
                    et_visa_select.setEnabled(true);
                }

                if (checkedId == R.id.rbPaypal) {
                    et_visa_select.setEnabled(false);
                    et_paypal_select.setEnabled(true);
                }
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio111) {
                    etOrganization.setVisibility(View.GONE);
                } else {
                    etOrganization.setVisibility(View.VISIBLE);
                }
            }
        });

        clickCommentClothes = rootView.findViewById(R.id.click_btn_clothes_comment);

        cardView = (CardView) clickCommentClothes.findViewById(R.id.cardview_clothes_list);
        cardView1 = (CardView) clickCommentClothes.findViewById(R.id.cardview_clothes_list_second);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new Dialog(getActivity());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.interface_one);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                spinnerApp = (AppCompatSpinner) dialog.findViewById(R.id.spinner_interface_one);
//                tv_validateOne = (TextView) dialog.findViewById(R.id.tv_validateOne);
//                String[] days = {"42 days", "52 days", "60 days"};
//
//                SpinnerTextAdapter spinnerInterface = new SpinnerTextAdapter(getActivity(), days);
//                spinnerApp.setAdapter(spinnerInterface);
//
//                tv_validateOne.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Dialog dialog1 = new Dialog(getActivity());
//                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog1.setContentView(R.layout.interface_four);
//                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog1.show();
//                    }
//                });
//
//
//                dialog.show();
//            }
//        });

        linearSchoolingCompaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("school"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("school"));
                }
            }
        });

        linearHealthCompaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("helth"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("helth"));
                }
            }
        });

        linearAnimalsCompaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("animal"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("animal"));
                }
            }
        });

        linearNatureCompaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("nature"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("nature"));
                }
            }
        });

        linearGlobalCrisisCompaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("global"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("global"));
                }
            }
        });

//        cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new Dialog(getActivity());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.interface_one);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                spinnerApp = (AppCompatSpinner) dialog.findViewById(R.id.spinner_interface_one);
//                tv_validateOne = (TextView) dialog.findViewById(R.id.tv_validateOne);
//                String[] days = {"42 days", "52 days", "60 days"};
//
//                SpinnerTextAdapter spinnerInterface = new SpinnerTextAdapter(getActivity(), days);
//                spinnerApp.setAdapter(spinnerInterface);
//
//                tv_validateOne.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Dialog dialog1 = new Dialog(getActivity());
//                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog1.setContentView(R.layout.interface_four);
//                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog1.show();
//                    }
//                });
//
//
//                dialog.show();
//            }
//        });


//        tvOK = (TextView) clickCommentClothes.findViewById(R.id.tv_ok_comment);
//
//        tvOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog = new Dialog(getActivity());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.interface_one);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                spinnerApp = (AppCompatSpinner) dialog.findViewById(R.id.spinner_interface_one);
//                tv_validateOne = (TextView) dialog.findViewById(R.id.tv_validateOne);
//                String[] days = {"42 days", "52 days", "60 days"};
//
//                SpinnerTextAdapter spinnerInterface = new SpinnerTextAdapter(getActivity(), days);
//                spinnerApp.setAdapter(spinnerInterface);
//
//                tv_validateOne.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Dialog dialog1 = new Dialog(getActivity());
//                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        dialog1.setContentView(R.layout.interface_four);
//                        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog1.show();
//                    }
//                });
//
//
//                dialog.show();
//            }
//        });

        LinearLayout list = (LinearLayout) rootView.findViewById(R.id.fabList);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), RegisterCampaignActivity.class));

                clickRegisterCampaign.setVisibility(View.VISIBLE);
                foodView.setVisibility(View.GONE);
            }
        });

        startDate = (TextView) clickRegisterCampaign.findViewById(R.id.et_startDate_registerCampaign);

        endDate = (TextView) clickRegisterCampaign.findViewById(R.id.et_endTime_registerCampaign);

        startTime = (TextView) clickRegisterCampaign.findViewById(R.id.et_startTime_registerCampaign);

        tvCreateDate = (TextView) clickRegisterCampaign.findViewById(R.id.tv_create_date);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData(startDate);
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData(endDate);
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        tvCreateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData(tvCreateDate);
            }
        });

        btnRegCampaign = (Button) clickRegisterCampaign.findViewById(R.id.campaignOne);
        btnRegCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRegisterCampaign.setVisibility(View.GONE);
                foodView.setVisibility(View.VISIBLE);
            }
        });

        spinnerUsd = (Spinner) clickRegisterCampaign.findViewById(R.id.sp_usd);
        SpinnerTextAdapter usdValue = new SpinnerTextAdapter(getActivity(), usd);
        spinnerUsd.setAdapter(usdValue);

        sp_typeBenef = (Spinner) clickRegisterCampaign.findViewById(R.id.sp_typeBenef);
        SpinnerTextAdapter typeOfBeni = new SpinnerTextAdapter(getActivity(), typeBenef);
        sp_typeBenef.setAdapter(typeOfBeni);

        spRecu = (Spinner) clickRegisterCampaign.findViewById(R.id.sp_recu);
        spStatus = (Spinner) clickRegisterCampaign.findViewById(R.id.sp_status_foundation);

        SpinnerCheckboxAdapter spinnerCheckboxAdapter = new SpinnerCheckboxAdapter(getActivity(), titles);
        spRecu.setAdapter(spinnerCheckboxAdapter);

        SpinnerTextAdapter typeStatus = new SpinnerTextAdapter(getActivity(), status);
        spStatus.setAdapter(typeStatus);

        switchRegCampaign = (SwitchButton) clickRegisterCampaign.findViewById(R.id.switch_reg_campaign);
        linearRegCampaign = (LinearLayout) clickRegisterCampaign.findViewById(R.id.linear_reg_campaign);

        switchRegCampaign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearRegCampaign.setVisibility(View.VISIBLE);
                } else {
                    linearRegCampaign.setVisibility(View.GONE);
                }
            }
        });

        sp_month = (Spinner) rootView.findViewById(R.id.sp_month);
        SpinnerTextAdapter spMonth = new SpinnerTextAdapter(getActivity(), month);
        sp_month.setAdapter(spMonth);


        sp_year = (Spinner) rootView.findViewById(R.id.sp_year);
        SpinnerTextAdapter spYear = new SpinnerTextAdapter(getActivity(), year);
        sp_year.setAdapter(spYear);


        clickDonation = rootView.findViewById(R.id.click_donation);
        clickClothes = rootView.findViewById(R.id.click_clothes);
        tv_donation_clothes = (TextView) clickClothes.findViewById(R.id.tv_donation_clothes);

        tv_donation_clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campaign_user.setVisibility(View.VISIBLE);
                clickClothes.setVisibility(View.GONE);
            }
        });

        commentClothes = (ImageView) clickClothes.findViewById(R.id.clothes_comment);

        tvFollowClothes = (TextView) clickClothes.findViewById(R.id.tv_follow_clothes);
        tvFollowClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LocationActivity.class));
            }
        });

//        commentClothes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), NotificationActivity.class));
//            }
//        });

        btnGiveDonation = (Button) campaign_user.findViewById(R.id.btn_give_donation_campaign_people);

        visaGroup = (RadioGroup) rootView.findViewById(R.id.rg_visa_paypal);

        linear_clothes_compaign = (LinearLayout) rootView.findViewById(R.id.linear_clothes_compaign);
        myRadioGroup = (RadioGroup) campaign_user.findViewById(R.id.myRadioGroup);
        one = (LinearLayout) campaign_user.findViewById(R.id.linear_person_number);
        three = (LinearLayout) campaign_user.findViewById(R.id.ll_spinner);
        two = (LinearLayout) campaign_user.findViewById(R.id.linear_condition);
        rgOne = (RadioGroup) campaign_user.findViewById(R.id.myRadioGroup_amount);
        spOne = (Spinner) campaign_user.findViewById(R.id.sp_method_recuputation1);
        tvOne = (EditText) campaign_user.findViewById(R.id.tv_estimated_value_donation);
        tvTerms = (LinearLayout) campaign_user.findViewById(R.id.tv_terms);
        linear_estimated_donation = (LinearLayout) campaign_user.findViewById(R.id.linear_estimated_donation);
        sp_usd_user1 = (AppCompatSpinner) campaign_user.findViewById(R.id.sp_usd_user1);
        SpinnerTextAdapter spinUserOne = new SpinnerTextAdapter(getActivity(), usd);
        sp_usd_user1.setAdapter(spinUserOne);

        etOther = (EditText) campaign_user.findViewById(R.id.et_other_other);
        etOther.setEnabled(false);

        rb_otherDonation = (RadioButton) campaign_user.findViewById(R.id.rb_otherDonation);
        rb_donation = (RadioButton) campaign_user.findViewById(R.id.rb_donation);


        sp_type_dona = (Spinner) rootView.findViewById(R.id.sp_type_dona1);

        etNamePaypal = (EditText) rootView.findViewById(R.id.et_name_paypal);
        etEmailPaypal = (EditText) rootView.findViewById(R.id.et_email_paypal);
        //etPasswordPaypal = (EditText) rootView.findViewById(R.id.et_password_paypal);
        // et_name_visa = (EditText) rootView.findViewById(R.id.et_name_visa);

        click_btn_giveDonation = rootView.findViewById(R.id.click_btn_giveDonation);
        click_btn_giveDonation.setVisibility(View.GONE);
        etNamePaypal.setVisibility(View.GONE);
        etEmailPaypal.setVisibility(View.GONE);
        //etPasswordPaypal.setVisibility(View.GONE);
        foodView.setVisibility(View.GONE);
        clickDonation.setVisibility(View.GONE);
        clickClothes.setVisibility(View.GONE);

        one.setVisibility(View.GONE);
        two.setVisibility(View.GONE);
        spOne.setVisibility(View.GONE);
        linear_estimated_donation.setVisibility(View.GONE);
        three.setVisibility(View.GONE);
        click_btn_campaign.setVisibility(View.GONE);
        //clickCommentClothes.setVisibility(View.GONE);

        SpinnerTextAdapter spinnerTextAdapter = new SpinnerTextAdapter(getActivity(), typeDonation);
        // attaching data adapter to spinner
        sp_type_dona.setAdapter(spinnerTextAdapter);

        SpinnerTextAdapter spinnerTextAdapter1 = new SpinnerTextAdapter(getActivity(), typeRecuperation);
        spOne.setAdapter(spinnerTextAdapter1);

        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_donation) {
                    one.setVisibility(View.GONE);
                    two.setVisibility(View.GONE);
                    spOne.setVisibility(View.GONE);
                    linear_estimated_donation.setVisibility(View.GONE);
                    three.setVisibility(View.GONE);

                    tvTerms.setVisibility(View.VISIBLE);
                    rgOne.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rb_otherDonation) {
                    one.setVisibility(View.VISIBLE);
                    two.setVisibility(View.VISIBLE);
                    tvTerms.setVisibility(View.GONE);
                    rgOne.setVisibility(View.GONE);
                    spOne.setVisibility(View.VISIBLE);
                    linear_estimated_donation.setVisibility(View.VISIBLE);
                    tvTerms.setVisibility(View.GONE);
                    three.setVisibility(View.VISIBLE);
                }
            }
        });

        rgOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_other) {
                    etOther.setEnabled(true);
                } else {
                    etOther.setEnabled(false);
                }
            }
        });

        et_fname = (EditText) rootView.findViewById(R.id.et_fname);
        et_fname.setVisibility(View.GONE);
        linear_card_cvv = (LinearLayout) rootView.findViewById(R.id.linear_card_cvv);
        linear_month_year = (LinearLayout) rootView.findViewById(R.id.linear_month_year);
        paypal = (TextView) rootView.findViewById(R.id.paypal);
        tv_visa = (TextView) rootView.findViewById(R.id.tv_visa);
        rvVisa = (RadioButton) rootView.findViewById(R.id.rb_visa);
        btn_visa_paypal = (Button) rootView.findViewById(R.id.btn_visa_paypal);
        btn_visa_paypal.setOnClickListener(this);

        visaGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_visa) {
                    etNamePaypal.setVisibility(View.GONE);
                    etEmailPaypal.setVisibility(View.GONE);
                    linear_card_cvv.setVisibility(View.VISIBLE);
                    linear_month_year.setVisibility(View.VISIBLE);
                    paypal.setVisibility(View.GONE);
                    tv_visa.setVisibility(View.VISIBLE);
                    et_fname.setVisibility(View.GONE);
                    // etPasswordPaypal.setVisibility(View.GONE);
                    //et_name_visa.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rb_paypal) {
                    //et_name_visa.setVisibility(View.GONE);
                    etNamePaypal.setVisibility(View.VISIBLE);
                    etEmailPaypal.setVisibility(View.VISIBLE);
                    et_fname.setVisibility(View.VISIBLE);
                    linear_card_cvv.setVisibility(View.GONE);
                    linear_month_year.setVisibility(View.GONE);
                    paypal.setVisibility(View.VISIBLE);
                    tv_visa.setVisibility(View.GONE);
                    //etPasswordPaypal.setVisibility(View.VISIBLE);

                }
            }
        });


        ivFood.setOnClickListener(this);
        //tvCompignDonationFoodList.setOnClickListener(this);
        linear_clothes_compaign.setOnClickListener(this);
        btnGiveDonation.setOnClickListener(this);
        //fabCampaign.setOnClickListener(this);

        tvFood.setText("Food");
        tvClothes.setText("Clothes");
        tvSchooling.setText("Schooling");
        tvNature.setText("Nature");
        tvGlobalCrisis.setText("Global Crisis");
        tvAnimals.setText("Animals");
        tvHealth.setText("Health");


        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_food:

                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("food"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("food"));
                }
                break;

//            case R.id.tv_compignDonation_foodList1:
//                clickDonation.setVisibility(View.VISIBLE);
//                foodView.setVisibility(View.GONE);
//                linearMainCampaign.setVisibility(View.GONE);
//                break;
            case R.id.linear_clothes_compaign:
                if (temp.equalsIgnoreCase("individual")) {
                    foodView.setVisibility(View.VISIBLE);
                    tv_compignDonation_foodList1.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("clothes"));
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    linearMainCampaign.setVisibility(View.GONE);
                    EventBus.getDefault().post(new Picselected("clothes"));
                }
                break;
            case R.id.btn_give_donation_campaign_people:

                if (rb_donation.isChecked()) {
                    click_btn_giveDonation.setVisibility(View.VISIBLE);
                    campaign_user.setVisibility(View.GONE);
                    //foodView.setVisibility(View.GONE);
                    //linearMainCampaign.setVisibility(View.GONE);
                } else {
                    foodView.setVisibility(View.VISIBLE);
                    campaign_user.setVisibility(View.GONE);
                }

                break;

            case R.id.btn_visa_paypal:
                if (rvVisa.isChecked()) {
                    foodView.setVisibility(View.VISIBLE);
                    click_btn_giveDonation.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), "PayPal", Toast.LENGTH_SHORT).show();
                }

                break;
//            case R.id.fab_campaign_confirm:
//                click_btn_campaign.setVisibility(View.VISIBLE);
//                foodView.setVisibility(View.GONE);
//                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void SetData(final TextView date) {
        Date d = null;
        final Calendar newCalendar = Calendar.getInstance();

        newCalendar.add(Calendar.DAY_OF_MONTH, 0);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        String formattedDate = sdf.format(newCalendar.getTime());
        try {
            d = sdf.parse(formattedDate);
        } catch (ParseException e) {
//            Log.e(TAG, "SelectfromDate: " + e);
        }
        fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar newDate = Calendar.getInstance();
                newCalendar.set(year, monthOfYear, dayOfMonth);
                //  newCalendar.add(Calendar.DATE, -5);
                date.setText(dateFormatter.format(newCalendar.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        fromDatePickerDialog.getDatePicker().setMinDate(d.getTime());

        fromDatePickerDialog.show();
    }


}
