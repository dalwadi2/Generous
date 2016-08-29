package in.vaksys.generous.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.kyleduo.switchbutton.SwitchButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.adapters.SpinnerTextAdapter;

/**
 * Created by dell980 on 6/2/2016.
 */
public class NotificationFragment extends Fragment {

    @Bind(R.id.toggle_acceptMoney_fragmentNotification)
    SwitchButton toggleAcceptMoneyFragmentNotification;
    @Bind(R.id.linear_msg_thanks)
    LinearLayout linearMsgThanks;
    private Spinner spNotiNameOfCompaign, spotificationRecieptDonations;
    String[] status = {"All the donations", "Other donations than money", "Only money", "Any donation"};
    private String[] dates = {"2 Days", "3 Days", "4 Days", "5 Days", "7 Days", "10 Days"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, rootView);

        linearMsgThanks.setVisibility(View.GONE);

        spNotiNameOfCompaign = (Spinner) rootView.findViewById(R.id.sp_notification_nameOfFoundation);
        spotificationRecieptDonations = (Spinner) rootView.findViewById(R.id.sp_notification_receiptDonation);

        SpinnerTextAdapter one = new SpinnerTextAdapter(getActivity(), dates);
        spNotiNameOfCompaign.setAdapter(one);

        SpinnerTextAdapter two = new SpinnerTextAdapter(getActivity(), status);
        spotificationRecieptDonations.setAdapter(two);

        toggleAcceptMoneyFragmentNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearMsgThanks.setVisibility(View.VISIBLE);
                } else {
                    linearMsgThanks.setVisibility(View.GONE);
                }
            }
        });
//
//        List<String> categories = new ArrayList<String>();
//        categories.add("3 Days");
//        categories.add("5 Days");
//        categories.add("10 Days");
//        categories.add("9 Days");
//        categories.add("7 Days");
//        categories.add("2 Days");
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spNotiNameOfCompaign.setAdapter(dataAdapter);
//
//
//        List<String> donation = new ArrayList<String>();
//        donation.add("All Donations");
//        donation.add("1 month");
//        donation.add("6 month");
//
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, donation);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spotificationRecieptDonations.setAdapter(dataAdapter1);


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
