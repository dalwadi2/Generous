package in.vaksys.generous.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vaksys.generous.R;
import in.vaksys.generous.activities.NotificationActivity;
import in.vaksys.generous.extras.MyApplication;

/**
 * Created by dell980 on 6/1/2016.
 */
public class FoundationFragment extends Fragment {
    @Bind(R.id.tv_name_foundation)
    TextView tvNameFoundation;
    @Bind(R.id.tv_comment_foundation)
    TextView tvCommentFoundation;
    @Bind(R.id.tv_like_foundation)
    TextView tvLikeFoundation;
    @Bind(R.id.tv_site_foundation)
    TextView tvSiteFoundation;
    @Bind(R.id.tv_contact_foundation)
    TextView tvContactFoundation;

    private MyApplication myApplication;
    private Typeface fontType;

    private TextView tv_comment_foundation;
    private Dialog dialog;

    public static FoundationFragment newInstance(int index) {
        FoundationFragment fragment = new FoundationFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_foundation_main, container, false);
        ButterKnife.bind(this, rootView);

        /*fontType = myApplication.getGujFont(getActivity());
        tvComapaignFoundation.setTypeface(fontType);
        tvCommentFoundation.setTypeface(fontType);
        tvContactFoundation.setTypeface(fontType);
        tvLikeFoundation.setTypeface(fontType);
        tvNameFoundation.setTypeface(fontType);
        tvSiteFoundation.setTypeface(fontType);*/

        tv_comment_foundation = (TextView) rootView.findViewById(R.id.tv_comment_foundation);

        tv_comment_foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_comment_foundations);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView tv_all_comments = (TextView) dialog.findViewById(R.id.tv_all_comments);
                tv_all_comments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), NotificationActivity.class));
                    }
                });

                dialog.show();
            }
        });


        return rootView;
    }


}
