package br.com.vhbsolution.modules.vhbutils;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.INPUT_METHOD_SERVICE;
import static br.com.vhbsolution.modules.vhbutils.VhbDialogUtils.noConnectionAlertDialog;


/**
 * Created by Victor Bitencourt - vtbitencourt on 5/19/2018.
 */


public class VhbUtils {

    private static final int WIFI_SETTINGS_RESOLUTION_REQUEST = 130;

    private static String TAG = VhbUtils.class.getSimpleName();
    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: setupUiTouchListenerForKeyboardDimiss()
     * DateUtils: 06/18/2016
     * About: This function returns the app version code
     * Parameters: AppCompatActivity activity
     * Parameters: View view
     * return: void
     *************************************************************************/
    public static void setupUiTouchListenerForKeyboardDimiss(final AppCompatActivity activity, View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                    return false;
                }
            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUiTouchListenerForKeyboardDimiss(activity, innerView);
            }
        }
    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getAppVersionCode()
     * DateUtils: 06/18/2016
     * About: This function returns the app version code
     * Parameters: Context context
     * return: int - version code
     *************************************************************************/
    public static boolean existsActivityInBackStatcy(Context context, String className) {
        ActivityManager mngr = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(2);

        if (taskList.get(0).numActivities == 1 && taskList.get(0).topActivity.getClassName().equals(className))
            return true;


        return false;
    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getAppVersionCode()
     * DateUtils: 06/18/2016
     * About: This function returns the app version code
     * Parameters: Context context
     * return: int - version code
     *************************************************************************/
    public static int getAppVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getFormattedString()
     * DateUtils: 06/18/2017
     * About: This function format the string
     * Parameters: String text,  String Formatter
     * return: String - String Formated
     *************************************************************************/
    public static String getFormattedString(String text, String mask) {

        try {

            StringBuilder out = new StringBuilder();

            char[] input = text.toCharArray();
            char[] aux = mask.toCharArray();

            int j = 0;

            for (int i = 0; i < mask.length(); i++) {
                if (aux[i] == '#') {
                    out.append(input[j]);
                    j++;
                } else {
                    out.append(aux[i]);
                }
            }

            return out.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return text;
        }


    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getAppVersionName()
     * DateUtils: 06/18/2016
     * About: This function returns the app version name
     * Parameters: Context context
     * return: String - version
     *************************************************************************/
    public static String getAppVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: isNetworkConnected()
     * DateUtils: 06/18/2016
     * About: This function check the network connection
     * Parameters: Context context
     * return: boolean - exist
     *************************************************************************/
    public static boolean isNetworkConnected(AppCompatActivity activity, boolean showDefaultMessage) {

        boolean result = false;

        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Network[] allNetworks = connectivityManager.getAllNetworks();

                for (Network netWork : allNetworks) {

                    if ((connectivityManager.getNetworkInfo(netWork).getType() == ConnectivityManager.TYPE_MOBILE) && connectivityManager.getNetworkInfo(netWork).isConnected()) {
                        result = true;
                        break;
                    }

                    if ((connectivityManager.getNetworkInfo(netWork).getType() == ConnectivityManager.TYPE_WIFI) && connectivityManager.getNetworkInfo(netWork).isConnected()) {
                        result = true;
                        break;
                    }
                    Log.d(VhbUtils.class.getName(), "isNetworkConnected:" + connectivityManager.getNetworkInfo(netWork).getTypeName() + ": " + connectivityManager.getNetworkInfo(netWork).isConnected());
                }

            } else {

                NetworkInfo[] allNetworks = connectivityManager.getAllNetworkInfo();

                for (NetworkInfo networkInfo : allNetworks) {

                    if ((networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) && networkInfo.isConnected()) {
                        result = true;
                        break;
                    }

                    if ((networkInfo.getType() == ConnectivityManager.TYPE_WIFI) && networkInfo.isConnected()) {
                        result = true;
                        break;
                    }
                    Log.d(VhbUtils.class.getName(), "isNetworkConnected:" + networkInfo.getTypeName() + ": " + networkInfo.isConnected());
                }

            }

        } catch (Exception e) {
            Log.e(VhbUtils.class.getName(), "isNetworkConnected:" + e.getMessage());
            result = false;
        }

        if (showDefaultMessage && !result)
            noConnectionAlertDialog(activity);


        return result;
    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: formatCurrency()
     * DateUtils: 07/18/2017
     * About: This function is used to format currency values
     * Parameters: float value
     * return: void
     *************************************************************************/
    public static String formatCurrency(float value) {

        NumberFormat mCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        return mCurrencyFormat.format(value).replace("R$", "");

    }


    public static String removeAccents(String text) {
        return text.replaceAll("[áàãâä]", "a")
                .replaceAll("[éèê]", "e")
                .replaceAll("[í]", "i")
                .replaceAll("[óô]", "o")
                .replaceAll("[ú]", "u");
    }


    public static class Mask {

        public static String unmask(String s) {
            return s.replaceAll("[.]", "").replaceAll("[-]", "")
                    .replaceAll("[/]", "").replaceAll("[(]", "")
                    .replaceAll("[)]", "").replaceAll(" ", "");
        }

        public static TextWatcher insert(String fullMask, final EditText ediTxt, final boolean keepWatcher) {

            final String mask = fullMask.replace("@", ""); // alterar metodo para usar espaços na mascara

            return new TextWatcher() {
                boolean isUpdating;
                String old = "";

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String str = Mask.unmask(s.toString());
                    String mascara = "";
                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }
                    int i = 0;
                    for (char m : mask.toCharArray()) {

                        if (m != '#' && str.length() > old.length()) {
                            mascara += m;
                            continue;
                        }

                        try {
                            mascara += str.charAt(i);
                        } catch (Exception e) {
                            break;
                        }
                        i++;
                    }
                    isUpdating = true;
                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());

                    if (mascara.isEmpty() && !keepWatcher)
                        ediTxt.removeTextChangedListener(this);

                }

                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                public void afterTextChanged(Editable s) {
                }

            };
        }
    }

    public static SpannableString makeLinks(String str, ArrayList<String> links, ArrayList<ClickableSpan> clickableSpans) {

        SpannableString spannableString = new SpannableString(str);

        for (int i = 0; i < links.size(); i++) {
            ClickableSpan clickableSpan = clickableSpans.get(i);
            String link = links.get(i);

            int startIndexOfLink = str.indexOf(link);
            spannableString.setSpan(clickableSpan, startIndexOfLink,
                    startIndexOfLink + link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

}




