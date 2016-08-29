package in.vaksys.generous.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.vaksys.generous.R;

/**
 * Created by dell980 on 6/27/2016.
 */
public class SpinnerTextAdapter extends BaseAdapter {
    private static final String TAG = "SpinnerTextAdapter";
    private Context context;
    private String[] title;
    private static LayoutInflater inflater;

    public SpinnerTextAdapter(Context context, String[] title) {
        this.context = context;
        this.title = title;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView;
        final ItemHolder mItemHolder;

        rootView = convertView;

        if (convertView == null) {
            rootView = inflater.inflate(R.layout.text_spinner, null);
            mItemHolder = new ItemHolder();
            mItemHolder.textOne = (TextView) rootView.findViewById(R.id.spin_text_without);
            mItemHolder.layout = rootView.findViewById(R.id.layout);
            rootView.setTag(mItemHolder);
        } else {
            mItemHolder = (ItemHolder) rootView.getTag();
        }
//        mItemHolder.layout.setPadding(5, 0, 5, 0);
//        if (position == 0) {
//            mItemHolder.layout.setPadding(0, 0, 0, 0);
//        }
//        if (position == title.length - 1) {
//            mItemHolder.layout.setPadding(5, 0, 5, 5);
//        }
        Typeface font = Typeface.createFromAsset(context.getAssets(), "gothammedium.ttf");
        mItemHolder.textOne.setTypeface(font);
        mItemHolder.textOne.setText(title[position]);

        /*mItemHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
            }
        });*/
        return rootView;
    }

    public class ItemHolder {
        TextView textOne;
        View layout;
    }
}
