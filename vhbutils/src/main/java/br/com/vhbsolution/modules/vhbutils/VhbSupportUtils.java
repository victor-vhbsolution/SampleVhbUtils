package br.com.vhbsolution.modules.vhbutils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatDrawableManager;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Victor Bitencourt - vtbitencourt on 5/19/2018.
 */

public class VhbSupportUtils {


    private static String TAG = VhbSupportUtils.class.getSimpleName();


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: formatToHTML()
     * VhbDateUtils: 06/22/2017
     * About: This function returns html object
     * Parameters: String text
     * return: Spanned
     *************************************************************************/
    public static Spanned formatToHTML(@NonNull String text) {

        Spanned spanned = null;

        text = text.replaceAll("(?:\n|\r\n)","<br>");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            spanned = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);

        } else {
            spanned = Html.fromHtml(text);
        }

        return spanned;

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getSpecificColor()
     * VhbDateUtils: 06/22/2016
     * About: This function returns the given color from resources of app
     * Parameters: Context context
     *             int specificColor
     * return: int - color
     *************************************************************************/
    @SuppressLint("RestrictedApi")
    public static Drawable getSpecificDrawable(Context context, int specificDrawable) {

        Drawable drawable = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getDrawable(specificDrawable);
        } else {
            drawable = AppCompatDrawableManager.get().getDrawable(context, specificDrawable);
        }

        return drawable;
    }


    public static int getPixelFromDp(Context context, float dpSize) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dpSize;

        return (int) fpixels;
    }


    public static int getPixelSizeFromSp(Context context, float spSize) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * spSize;
        return (int) (fpixels + 0.5f);
    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getSpecificColor()
     * VhbDateUtils: 06/22/2016
     * About: This function returns the given color from resources of app
     * Parameters: Context context
     *             int specificColor
     * return: int - color
     *************************************************************************/
    public static int getSpecificColor(Context context, int specificColor) {

        int color = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getResources().getColor(specificColor, null);
        } else {
            color = context.getResources().getColor(specificColor);
        }

        return color;
    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: isWifiConnected()
     * VhbDateUtils: 06/18/2016
     * About: This function check the connection wifi
     * Parameters: Context context
     * return: boolean - exist
     *************************************************************************/
    public static boolean isWifiConnected(Context context) {

        boolean result = false;

        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Network[] allNetworks = connectivityManager.getAllNetworks();

                for (Network netWork : allNetworks) {

                    if ((connectivityManager.getNetworkInfo(netWork).getType() == ConnectivityManager.TYPE_WIFI) && connectivityManager.getNetworkInfo(netWork).isConnected()) {
                        result = true;
                        break;
                    }
                    Log.d(TAG, "isWifiNetworkConnected:" + connectivityManager.getNetworkInfo(netWork).getTypeName() + ": " + connectivityManager.getNetworkInfo(netWork).isConnected());
                }


            } else {

                NetworkInfo[] allNetworks = connectivityManager.getAllNetworkInfo();

                for (NetworkInfo networkInfo : allNetworks) {

                    if ((networkInfo.getType() == ConnectivityManager.TYPE_WIFI) && networkInfo.isConnected()) {
                        result = true;
                        break;
                    }
                    Log.d(TAG, "isWifiNetworkConnected:" + networkInfo.getTypeName() + ": " + networkInfo.isConnected());
                }

            }

        } catch (Exception e) {
            Log.e(TAG, "isWifiNetworkConnected:" + e.getMessage());
            result = false;
        }

        return result;
    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: checkPlayServices()
     * VhbDateUtils: 06/18/2016
     * About: This function verify google play services in the device
     * Parameters: Context context
     *             int requestCode
     * return: boolean - exist
     *************************************************************************/
    /*public static boolean checkPlayServices(Context context, int requestCode) {

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(context);

        if (result != ConnectionResult.SUCCESS) {

            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog((AppCompatActivity) context, result, requestCode).show();
            }

            return false;
        }

        return true;
    }*/

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: cryptWithMD5()
     * VhbDateUtils: 06/18/2016
     * About: This function makes the encryption of String given with MD5.
     * Parameters: String text
     * return: String encrypted
     *************************************************************************/
    public static String cryptWithMD5(String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: getVetorDrawableWithColor()
     * VhbDateUtils: 08/02/2017
     * About: This function returns a vector drawable with color
     * Parameters: Context context, int specificDrawable, int specificColor
     * return: Drawable
     *************************************************************************/
    public static Drawable getVetorDrawableWithColor(Context context, int specificDrawable, int specificColor) {

        try {

            Drawable drawable = getSpecificDrawable(context, specificDrawable);

            if (drawable != null)
                drawable.setColorFilter(getSpecificColor(context, specificColor), PorterDuff.Mode.SRC_ATOP);

            return drawable;

        } catch (Exception ex) {

            Log.e(TAG, ex.getMessage());
            return null;
        }

    }



}
