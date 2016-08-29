package in.vaksys.generous.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;
import in.vaksys.generous.extras.MyApplication;

/**
 * Created by dell980 on 6/1/2016.
 */
public class MonitoringDonationFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.tv_helpMoocconName_one)
    TextView tvHelpMoocconNameOne;
    @Bind(R.id.tv_helpMoocconAmount_two)
    TextView tvHelpMoocconAmountTwo;
    @Bind(R.id.tv_helpMoocconDate_three)
    TextView tvHelpMoocconDateThree;
    @Bind(R.id.tv_helpMooccon_sosAfrica)
    TextView tvHelpMoocconSosAfrica;
    @Bind(R.id.tv_donor)
    TextView tvDonor;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_donorName_donorList)
    TextView tvDonorNameDonorList;
    @Bind(R.id.tv_dateDonor_donorList)
    TextView tvDateDonorDonorList;
    @Bind(R.id.tv_ammount_donorList)
    TextView tvAmmountDonorList;
    @Bind(R.id.tv_typeOfDonation)
    TextView tvTypeOfDonation;
    @Bind(R.id.tv_number)
    TextView tvNumber;
    @Bind(R.id.tv_cityAndCountry)
    TextView tvCityAndCountry;
    @Bind(R.id.tv_methodOfRecu)
    TextView tvMethodOfRecu;
    @Bind(R.id.tv_typeOfDonation_moroccon)
    TextView tvTypeOfDonationMoroccon;
    @Bind(R.id.tv_number_moroccon)
    TextView tvNumberMoroccon;
    @Bind(R.id.tv_city_moroccon)
    TextView tvCityMoroccon;
    @Bind(R.id.tv_country_moroccon)
    TextView tvCountryMoroccon;
    @Bind(R.id.tv_location)
    TextView tvLocation;
    @Bind(R.id.tv_locationMessage)
    TextView tvLocationMessage;
    private CardView card_main_one;
    View one, two, three;
    ImageView leftArrow;
    LinearLayout rightArrow;
    private TextView tv_compignDonation_foodList;

    MyApplication myApplication;
    Typeface fontType;

    // TODO: 7/26/2016 new changes as per client

    private ImageView ivUpdateLocation, ivDeleteDonation, ivEditDonation, ivExtra, img_methodAddDark_moroccon,
            img_methodAdd_moroccon, img_methodAdd_moroccon_one, img_methodAdd_moroccon_two, img_methodAddDark_moroccon_one,
            img_methodAddDark_moroccon_two;
    private Dialog dialog;
    private View four;

    private CardView cardOne, cardTwo, cardThree;

    private AppCompatSpinner spinnerApp;

    private TextView etStartDate, endDate, startTime;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    SimpleDateFormat sdf;

    private Spinner sp_forWhat_registerCampaign;

    String[] family = {"Type of beneficiary", "Families", "Children", "Women", "Handicaps", "Person"};
    String[] usd = {"USD", "EUR", "CAD", "GBP", "CNY", "INR", "AUD", "JPY", "SEK", "MYR", "IDY", "MAD", "SAR", "AED", "QAR", "PLN", "SGD", "BRL"};

    public static MonitoringDonationFragment newInstance(int index) {
        MonitoringDonationFragment fragment = new MonitoringDonationFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_view, container, false);
        ButterKnife.bind(this, rootView);

        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        /*fontType = myApplication.getGujFont(getActivity());
        tvHelpMoocconAmountTwo.setTypeface(fontType);
        tvHelpMoocconDateThree.setTypeface(fontType);
        tvHelpMoocconNameOne.setTypeface(fontType);
        tvHelpMoocconSosAfrica.setTypeface(fontType);
        tvAmount.setTypeface(fontType);
        tvDate.setTypeface(fontType);
        tvDonor.setTypeface(fontType);
        tvAmmountDonorList.setTypeface(fontType);
        tvDonorNameDonorList.setTypeface(fontType);
        tvDateDonorDonorList.setTypeface(fontType);
        tvTypeOfDonation.setTypeface(fontType);
        tvNumber.setTypeface(fontType);
        tvCityAndCountry.setTypeface(fontType);
        tvMethodOfRecu.setTypeface(fontType);
        tvTypeOfDonationMoroccon.setTypeface(fontType);
        tvNumberMoroccon.setTypeface(fontType);
        tvCityMoroccon.setTypeface(fontType);
        tvCountryMoroccon.setTypeface(fontType);
        tvLocation.setTypeface(fontType);
        tvLocationMessage.setTypeface(fontType);*/


        card_main_one = (CardView) rootView.findViewById(R.id.card_main_one);
        one = rootView.findViewById(R.id.one);
        two = rootView.findViewById(R.id.two);
        three = rootView.findViewById(R.id.three);
        four = rootView.findViewById(R.id.four);
        rightArrow = (LinearLayout) rootView.findViewById(R.id.iv_right_arrow);
        leftArrow = (ImageView) rootView.findViewById(R.id.iv_left_arrow);
        sp_forWhat_registerCampaign = (Spinner) four.findViewById(R.id.sp_forWhat_registerCampaign);
        SpinnerTextAdapter spinn = new SpinnerTextAdapter(getActivity(), family);
        sp_forWhat_registerCampaign.setAdapter(spinn);



        Button rightArrow1 = (Button) rootView.findViewById(R.id.iv_right_arrow1);

        ivUpdateLocation = (ImageView) three.findViewById(R.id.iv_locationUpdate);
        ivDeleteDonation = (ImageView) three.findViewById(R.id.iv_deleteDonation);
        ivEditDonation = (ImageView) three.findViewById(R.id.iv_editDonation);
        ivExtra = (ImageView) two.findViewById(R.id.iv_methodComment_moroccon);

        ivExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_extra_value);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        etStartDate = (TextView) four.findViewById(R.id.et_startDate);
        endDate = (TextView) four.findViewById(R.id.et_endDate);
        startTime = (TextView) four.findViewById(R.id.et_startTime);
        spinnerApp = (AppCompatSpinner) four.findViewById(R.id.sp_usd_screen);
        img_methodAddDark_moroccon = (ImageView) two.findViewById(R.id.img_methodAddDark_moroccon);
        img_methodAddDark_moroccon_one = (ImageView) two.findViewById(R.id.img_methodAddDark_moroccon_one);
        img_methodAddDark_moroccon_two = (ImageView) two.findViewById(R.id.img_methodAddDark_moroccon_two);
        img_methodAdd_moroccon = (ImageView) two.findViewById(R.id.img_methodAdd_moroccon);
        img_methodAdd_moroccon_one = (ImageView) two.findViewById(R.id.img_methodAdd_moroccon_one);
        img_methodAdd_moroccon_two = (ImageView) two.findViewById(R.id.img_methodAdd_moroccon_two);
        cardOne = (CardView) two.findViewById(R.id.card_one);
        cardTwo = (CardView) two.findViewById(R.id.card_two);
        cardThree = (CardView) two.findViewById(R.id.card_three);

        img_methodAddDark_moroccon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_methodAdd_moroccon.setVisibility(View.VISIBLE);
                img_methodAddDark_moroccon.setVisibility(View.GONE);
                cardOne.setVisibility(View.VISIBLE);
            }
        });

        img_methodAdd_moroccon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_methodAdd_moroccon.setVisibility(View.GONE);
                img_methodAddDark_moroccon.setVisibility(View.VISIBLE);
                cardOne.setVisibility(View.GONE);
            }
        });

        img_methodAddDark_moroccon_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_methodAdd_moroccon_one.setVisibility(View.VISIBLE);
                img_methodAddDark_moroccon_one.setVisibility(View.GONE);
                cardTwo.setVisibility(View.VISIBLE);
            }
        });

        img_methodAdd_moroccon_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_methodAdd_moroccon_one.setVisibility(View.GONE);
                img_methodAddDark_moroccon_one.setVisibility(View.VISIBLE);
                cardTwo.setVisibility(View.GONE);
            }
        });

        img_methodAddDark_moroccon_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_methodAdd_moroccon_two.setVisibility(View.VISIBLE);
                img_methodAddDark_moroccon_two.setVisibility(View.GONE);
                cardThree.setVisibility(View.VISIBLE);
            }
        });

        img_methodAdd_moroccon_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_methodAdd_moroccon_two.setVisibility(View.GONE);
                img_methodAddDark_moroccon_two.setVisibility(View.VISIBLE);
                cardThree.setVisibility(View.GONE);
            }
        });

        SpinnerTextAdapter usdValue = new SpinnerTextAdapter(getActivity(), usd);
        spinnerApp.setAdapter(usdValue);

        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData(etStartDate);
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

        one.setVisibility(View.GONE);
        two.setVisibility(View.GONE);
        four.setVisibility(View.GONE);

        tv_compignDonation_foodList = (TextView) three.findViewById(R.id.tv_compignDonation_foodList);

        tv_compignDonation_foodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setVisibility(View.VISIBLE);
                three.setVisibility(View.GONE);
            }
        });


        //card_main_one.setOnClickListener(this);
        rightArrow.setOnClickListener(this);
        leftArrow.setOnClickListener(this);
        rightArrow1.setOnClickListener(this);
        ivUpdateLocation.setOnClickListener(this);
        ivDeleteDonation.setOnClickListener(this);
        ivEditDonation.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right_arrow:
                two.setVisibility(View.VISIBLE);
                one.setVisibility(View.GONE);
                three.setVisibility(View.GONE);
                break;
            case R.id.iv_left_arrow:
                two.setVisibility(View.GONE);
                one.setVisibility(View.GONE);
                three.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_right_arrow1:
                two.setVisibility(View.VISIBLE);
                one.setVisibility(View.GONE);
                three.setVisibility(View.GONE);
                break;
            case R.id.iv_locationUpdate:
                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_update_location);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.iv_deleteDonation:
                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_campaign);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;

            case R.id.iv_editDonation:
                four.setVisibility(View.VISIBLE);
                three.setVisibility(View.GONE);
                break;

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
