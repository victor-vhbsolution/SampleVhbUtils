package br.com.vhbsolution.modules.vhbutils;

import android.support.v7.app.AppCompatActivity;

import java.net.SocketTimeoutException;

/**
 * Created by Victor Bitencourt - vtbitencourt on 8/26/2018.
 */
public class VhbHttpUtils {

    public static boolean handleHttpRequestFailure(AppCompatActivity activity, Throwable t, boolean showDefaultHttpRequestError) {

        boolean result = false;

        if (activity != null && t != null) {

            if (t instanceof SocketTimeoutException) {
                result = true;
                VhbDialogUtils.slowConnectionAlertDialog(activity);
            }else if (showDefaultHttpRequestError){
                result = true;
                VhbDialogUtils.unexpectedErrorAlertDialog(activity);
            }
        }

        return result;
    }

    public static String getMessageHttpRequestFailure(AppCompatActivity activity, Throwable t) {

        StringBuilder result = new StringBuilder();

        if (activity != null && t != null) {

            if (t instanceof SocketTimeoutException) {
                result.append(activity.getString(R.string.vhbutils_http_string_dialog_title_slow_connection))
                        .append(", ")
                        .append(activity.getString(R.string.vhbutils_http_string_dialog_message_slow_connection));
            }else {
                result.append(activity.getString(R.string.vhbutils_http_error_dialog_title_http_request))
                        .append(", ")
                        .append(activity.getString(R.string.vhbutils_http_error_dialog_message_http_request));
            }
        }

        return result.toString();
    }

}
