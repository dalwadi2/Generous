package in.vaksys.generous.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import in.vaksys.generous.R;

/**
 * Created by dell980 on 6/29/2016.
 */
public class SpinnerCheckboxAdapter extends BaseAdapter {

    private Context context;
    private String[] title;
    private static LayoutInflater inflater;

    public SpinnerCheckboxAdapter(Context context, String[] title) {
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
        ItemHolder mItemHolder;

        rootView = convertView;

        if (convertView == null) {
            rootView = inflater.inflate(R.layout.spinner_type_donation, null);
            mItemHolder = new ItemHolder();
            mItemHolder.textOne = (TextView) rootView.findViewById(R.id.spin_text);
            mItemHolder.checkBox = (CheckBox) rootView.findViewById(R.id.checkboxDonation);
            rootView.setTag(mItemHolder);
        } else {
            mItemHolder = (ItemHolder) rootView.getTag();
        }

        mItemHolder.textOne.setText(title[position]);

        return rootView;
    }

    public class ItemHolder {
        TextView textOne;
        CheckBox checkBox;
    }
}
