package br.com.vhbsolution.modules.vhbutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Created by Victor Bitencourt - vtbitencourt on 5/19/2018.
 */


public class VhbDateUtils {


    public static String formatDateTimeToDataBase(String strDate) {
        try {

            if (strDate.length() < 16)
                strDate += " 00:00";

            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = df2.parse(strDate);

            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return fmtOut.format(date).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getOnlyDate(String fullDate) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = format.parse(fullDate);

            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
            return fmtOut.format(date).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getOnlyTime(String fullDate) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = format.parse(fullDate);

            SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm");
            return fmtOut.format(date).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String formatTime(String strTime) {

        if (strTime.contains("."))
            strTime = strTime.replace(".", ":");
        else if (strTime.contains("-"))
            strTime = strTime.replace("-", ":");

        return strTime;

    }

    public static String getWeekDayName(Calendar calendar) {

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

    }

    public static String displayDate(String strDate) {

        try {

            if (strDate.length() < 16)
                strDate += " 00:00";

            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = df2.parse(strDate);

            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
            return fmtOut.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String formatDateFromServer(String strDate) {

        try {

            strDate = strDate.replace("T"," ");

            if (strDate.length() < 16)
                strDate += " 00:00";

            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = df2.parse(strDate);

            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
            return fmtOut.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String displayDateTime(String strDate) {

        try {

            if (strDate.length() < 16)
                strDate += " 00:00";

            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = df2.parse(strDate);

            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return fmtOut.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getCurrentDate() {

        try {
            Calendar c = Calendar.getInstance(Locale.getDefault());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            return df.format(c.getTime());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static String getStringDateFromCalendar(Calendar calendar) {

        try {

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            return df.format(calendar.getTime());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getDatetimeToJson() {
        String date = null;

        TimeZone zone = TimeZone.getTimeZone("Etc/GMT");
        Calendar calendar = Calendar.getInstance(zone);

        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = df.format(calendar.getTime()).trim();
            date = date.replace(" ","T");
            return date;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static Calendar convertDateFromJsonDate(String date) {

        if (date == null) return null;

        TimeZone zone = TimeZone.getTimeZone("Etc/GMT");
        Calendar calendar = Calendar.getInstance(zone);

        String ackwardRipOff = date.replace("/Date(", "").replace(")/", "");
        Long timeInMillis = Long.valueOf(ackwardRipOff);

        calendar.setTimeInMillis(timeInMillis);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar;

    }

    public static String getDateTimeFromCalendar(Calendar calendar) {

        StringBuilder strDate = null;

        String year = String.valueOf(calendar.get(Calendar.YEAR));

        String auxMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String month = auxMonth.length() < 2 ? "0" + auxMonth : auxMonth;

        String auxDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String day = auxDay.length() < 2 ? "0" + auxDay : auxDay;

        String auxHour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String hour = auxHour.length() < 2 ? "0" + auxHour : auxHour;

        String auxMinute = String.valueOf(calendar.get(Calendar.MINUTE));
        String minute = auxMinute.length() < 2 ? "0" + auxMinute : auxMinute;

        strDate = new StringBuilder().append(day)
                .append("/")
                .append(month)
                .append("/")
                .append(year)
                .append(" ")
                .append(hour)
                .append(":")
                .append(minute);

        return strDate.toString();
    }

    public static String getDisplayDateWDM(long timeInMillis) {

        try {

            StringBuilder result = null;

            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(timeInMillis);

            String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
            String monthOfYear = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

            String auxDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            String day = auxDay.length() < 2 ? "0" + auxDay : auxDay;

            result = new StringBuilder().append(dayOfWeek).append(", ").append(day + " ").append(monthOfYear);

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static Date getDateFromString(String sdate) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(sdate);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Date getDateTimeFromString(String sdateTime) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return format.parse(sdateTime);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean checkTime(String hour) {

        try {

            SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm");
            fmtOut.parse(hour);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkDate(String date) {

        try {
            String[] aux = date.split("/");

            if (aux.length == 3) {

                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.set(Calendar.MONTH, Integer.valueOf(aux[1]));
                int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                if (Integer.valueOf(aux[0]) <= maxDay)
                    return true;
                else
                    return false;

            } else {

                String[] aux2 = date.split("-");

                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.set(Calendar.MONTH, Integer.valueOf(aux2[1]));
                int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                if (Integer.valueOf(aux2[2]) <= maxDay)
                    return true;
                else
                    return false;


            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean checkFullDate(String fullDate) {

        try {
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fmtOut.parse(fullDate);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
