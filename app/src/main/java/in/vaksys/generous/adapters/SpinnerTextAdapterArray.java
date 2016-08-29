package in.vaksys.generous.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.vaksys.generous.R;

/**
 * Created by dell980 on 6/27/2016.
 */
public class SpinnerTextAdapterArray extends BaseAdapter {

    private Context context;
    private ArrayList<String> title;
    private static LayoutInflater inflater;

    public SpinnerTextAdapterArray(Context context,ArrayList<String> title) {
        this.context = context;
        this.title = title;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.size();
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

        title.get(position);

        if (convertView == null) {
            rootView = inflater.inflate(R.layout.text_spinner, null);
            mItemHolder = new ItemHolder();
            mItemHolder.textOne = (TextView) rootView.findViewById(R.id.spin_text_without);
            rootView.setTag(mItemHolder);
        } else {
            mItemHolder = (ItemHolder) rootView.getTag();
        }
        Typeface font = Typeface.createFromAsset(context.getAssets(), "gothammedium.ttf");
        mItemHolder.textOne.setTypeface(font);
        mItemHolder.textOne.setText(title.get(position));

        return rootView;
    }

    public class ItemHolder {
        TextView textOne;
    }
}
