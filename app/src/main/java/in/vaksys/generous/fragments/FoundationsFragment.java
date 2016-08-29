package in.vaksys.generous.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;

/**
 * Created by dell980 on 6/2/2016.
 */
public class FoundationsFragment extends Fragment implements View.OnClickListener {

    private Spinner spDonationAccept, spFoundation, spReminder, spReminderCampaigns;
    private LinearLayout linear_One;
    View foundation_new;
    Button btnSave;
    private SwitchButton btnOne;
    private LinearLayout linear_spin_donationAccept;

    private TextView et_date_fragmentFoundation;
    private SimpleDateFormat dateFormatter;

    SimpleDateFormat sdf;
    private DatePickerDialog fromDatePickerDialog;

    String[] titles = {"Type of Donation accepted?", "Cash", "Drugs or health equipment", "Books", "Computer accessories"
            , "Furniture or appliances", "Other Donations"};
    String[] status = {"Status of the foundation", "International organization", "State organization",
            "Organization approved by the state", "Other"};

    String[] reminder = {"I participate", "I participate", "I participate", "I participate"};
    String[] reminderFraq = {"I participate", "I participate", "I participate", "I participate"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_foundations, container, false);

        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        et_date_fragmentFoundation = (TextView) rootView.findViewById(R.id.et_date_fragmentFoundation);
        et_date_fragmentFoundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData(et_date_fragmentFoundation);
            }
        });

        spDonationAccept = (Spinner) rootView.findViewById(R.id.sp_donationAccept);
        spFoundation = (Spinner) rootView.findViewById(R.id.sp_status_foundation_settings);

        SpinnerTextAdapter spinnerTextAdapter1 = new SpinnerTextAdapter(getActivity(), status);
        spFoundation.setAdapter(spinnerTextAdapter1);

        btnOne = (SwitchButton) rootView.findViewById(R.id.toggle_acceptMoney_fragmentFoundation);
        linear_spin_donationAccept = (LinearLayout) rootView.findViewById(R.id.linear_spin_donationAccept);
        linear_spin_donationAccept.setVisibility(View.GONE);

        btnOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linear_spin_donationAccept.setVisibility(View.VISIBLE);
                }

                if (!isChecked) {
                    linear_spin_donationAccept.setVisibility(View.GONE);
                }
            }
        });

        spReminder = (Spinner) rootView.findViewById(R.id.reminder_campaign);
        spReminderCampaigns = (Spinner) rootView.findViewById(R.id.requently_reminder);

        SpinnerTextAdapter spinnerReminder = new SpinnerTextAdapter(getActivity(), reminder);
        spReminder.setAdapter(spinnerReminder);

        SpinnerTextAdapter spinnerReminderFraq = new SpinnerTextAdapter(getActivity(), reminderFraq);
        spReminderCampaigns.setAdapter(spinnerReminderFraq);

        linear_One = (LinearLayout) rootView.findViewById(R.id.linear_one);
        foundation_new = rootView.findViewById(R.id.foundation_new);
        btnSave = (Button) rootView.findViewById(R.id.btn_acceptAndSave_fragmentFoundation);

        foundation_new.setVisibility(View.GONE);

        //btnSave.setOnClickListener(this);

      /*  ArrayList<String> donationType = new ArrayList<String>();
        donationType.add("Type of Donation accepted?");
        donationType.add("Cash");
        donationType.add("Check");
        donationType.add("Debit-card");*/


//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.text_spinner, R.id.spin_text, donationType);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerTextAdapter spinnerTextAdapter = new SpinnerTextAdapter(getActivity(), titles);

        // attaching data adapter to spinner
        spDonationAccept.setAdapter(spinnerTextAdapter);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_acceptAndSave_fragmentFoundation:
                foundation_new.setVisibility(View.VISIBLE);
                linear_One.setVisibility(View.GONE);
                break;
        }
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
