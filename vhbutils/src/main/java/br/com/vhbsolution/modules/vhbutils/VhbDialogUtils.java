package br.com.vhbsolution.modules.vhbutils;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Victor Bitencourt - vtbitencourt on 5/19/2018.
 */
public class VhbDialogUtils {


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: unexpectedErrorAlertDialog()
     * DateUtils: 08/02/2017
     * About: This function helps to build an dialof for unexpected erros in the app
     * Parameters: AppCompatActivity activity
     * return: void
     *************************************************************************/
    public static void unexpectedErrorAlertDialog(AppCompatActivity activity) {

        int icon = R.drawable.vhbutils_ic_dialog_error;
        String title = "Problema";
        String Message = activity.getString(R.string.vhbutils_error_info_http_request_message);

        createNeutralAppDialog(activity, icon, title, Message);

    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: unexpectedErrorAlertDialog()
     * DateUtils: 06/18/2016
     * About: This function helps to build an dialof for unexpected erros in the app
     * Parameters: AppCompatActivity activity
     * return: void
     *************************************************************************/
    public static void warningMessageDialog(AppCompatActivity activity, String message) {

        int icon = R.drawable.vhbutils_ic_dialog_alert;
        createNeutralAppDialog(activity, icon, activity.getString(R.string.vhbutils_title_warning), message);

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: noConnectionAlertDialog()
     * DateUtils: 08/02/2017
     * About: This function helps to build an dialof for unexpected erros in the app
     * Parameters: AppCompatActivity activity
     * return: void
     *************************************************************************/
    public static void noConnectionAlertDialog(AppCompatActivity activity) {

        int icon = R.drawable.vhbutils_ic_dialog_connection_off;
        String title = activity.getString(R.string.vhbutils_title_warning_no_connection);
        String Message = activity.getString(R.string.vhbutils_info_warning_no_connection_message);

        createNeutralAppDialog(activity, icon, title, Message);

    }

    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: unexpectedErrorAlertDialog()
     * DateUtils: 08/02/2017
     * About: This function helps to build an dialof for unexpected erros in the app
     * Parameters: AppCompatActivity activity
     * return: void
     *************************************************************************/
    public static void slowConnectionAlertDialog(AppCompatActivity activity) {

        int icon = R.drawable.vhbutils_ic_dialog_slow_connection;
        String title = activity.getString(R.string.vhbutils_title_slow_connection);
        String Message = activity.getString(R.string.vhbutils_info_slow_connection_message);

        createNeutralAppDialog(activity, icon, title, Message);

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: createNeutralAppDialog()
     * DateUtils: 06/18/2017
     * About:
     * Parameters:
     * return: void
     *************************************************************************/
    public static void createNeutralAppDialog(AppCompatActivity activity, int icon, String title, String message) {

        createActionAppDialog(activity,
                icon,
                title,
                message,
                activity.getString(R.string.vhbutils_action_dialog_neutral),
                null,
                null,
                null,
                null,
                null);

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: createSingleActionAppDialog()
     * DateUtils: 06/18/2017
     * About:
     * Parameters:
     * return: void
     *************************************************************************/
    public static void createSingleActionAppDialog(AppCompatActivity activity,
                                                   int icon,
                                                   String title,
                                                   String message,
                                                   String positiveButton,
                                                   DialogInterface.OnClickListener action) {

        createActionAppDialog(activity,
                icon,
                title,
                message,
                positiveButton,
                null,
                null,
                action,
                null,
                null);


    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: createMultipleActionAppDialog()
     * DateUtils: 06/18/2016
     * About:
     * Parameters:
     * return: void
     *************************************************************************/
    public static void createMultipleActionAppDialog(AppCompatActivity activity,
                                                     int icon,
                                                     String title,
                                                     String message,
                                                     String positiveButton,
                                                     String negativeButton,
                                                     DialogInterface.OnClickListener positiveAction,
                                                     DialogInterface.OnClickListener negativeAction) {

        createActionAppDialog(activity,
                icon,
                title,
                message,
                positiveButton,
                negativeButton,
                null,
                positiveAction,
                negativeAction,
                null);

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: createTripleActionAppDialog()
     * DateUtils: 06/18/2016
     * About:
     * Parameters:
     * return: void
     *************************************************************************/
    public static void createTripleActionAppDialog(AppCompatActivity activity,
                                                   int icon,
                                                   String title,
                                                   String message,
                                                   String positiveButton,
                                                   String negativeButton,
                                                   String neutralButton,
                                                   DialogInterface.OnClickListener positiveAction,
                                                   DialogInterface.OnClickListener negativeAction,
                                                   DialogInterface.OnClickListener neutralAction) {

        createActionAppDialog(activity,
                icon,
                title,
                message,
                positiveButton,
                negativeButton,
                neutralButton,
                positiveAction,
                negativeAction,
                neutralAction);

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: createAppDialogAction()
     * DateUtils: 06/18/2016
     * About:
     * Parameters: Context context
     * return: boolean - exist
     *************************************************************************/
    private static void createActionAppDialog(AppCompatActivity activity,
                                              int icon,
                                              String title,
                                              String message,
                                              String positiveButton,
                                              String negativeButton,
                                              String neutralButton,
                                              DialogInterface.OnClickListener positiveAction,
                                              DialogInterface.OnClickListener negativeAction,
                                              DialogInterface.OnClickListener neutralAction) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(activity);

        builderSingle.setIcon(icon);
        builderSingle.setTitle(title);
        builderSingle.setCancelable(false);

        TextView txtMessage = new TextView(activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

            if (message.contains("\n"))
                txtMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            else
                txtMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        } else {

            if (message.contains("\n"))
                txtMessage.setGravity(Gravity.LEFT);
            else
                txtMessage.setGravity(Gravity.CENTER);

        }

        txtMessage.setPadding(VhbSupportUtils.getPixelFromDp(activity, 10)
                , VhbSupportUtils.getPixelFromDp(activity, 10)
                , VhbSupportUtils.getPixelFromDp(activity, 10)
                , 0);

        txtMessage.setText(message);

        builderSingle.setView(txtMessage);

        if (neutralButton != null)
            builderSingle.setNeutralButton(neutralButton, neutralAction);

        if (positiveButton != null)
            builderSingle.setPositiveButton(positiveButton, positiveAction);

        if (negativeButton != null)
            builderSingle.setNegativeButton(negativeButton, negativeAction);

        builderSingle.show();

    }


    /**********************************************************************
     * Author: Victor Bitencourt
     * Method: internetActionSnackBar()
     * DateUtils: 06/18/2016
     * About: This function helps to build an actionbar with customized action
     * Parameters: AppCompatActivity activity
     *             View view
     *             int duration
     * return: void
     *************************************************************************/
    public static void internetAlertDialog(AppCompatActivity activity) {

        int icon = R.drawable.vhbutils_ic_dialog_connection_off;
        String title = activity.getString(R.string.vhbutils_title_warning_no_connection);
        String Message = activity.getString(R.string.vhbutils_info_warning_no_connection_message);

        createNeutralAppDialog(activity, icon, title, Message);

    }

}
