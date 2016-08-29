package in.vaksys.generous.extras;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by dell980 on 6/9/2016.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "gothammedium.ttf");
    }

    /*public static Typeface getGujFont(Context context) {

        return Typeface.createFromAsset(context.getAssets(), "museo.ttf");

    }*/

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void hideKeyboard(Activity context) {
        // Check if no view has focus:
        View view1 = context.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }
}
