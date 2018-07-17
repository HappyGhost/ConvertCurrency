package com.myapp.convertcurrency.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String YYYY_MM_DD_FORMAT = "yyyy-mm-dd";
    public static final String MM_DD_YYYY_FORMAT = "MMM dd, yyyy";

    public static String convertDateFormat(String value, String formatInput, String formatOutPut) {
        DateFormat dateFormatInput = new SimpleDateFormat(formatInput);
        DateFormat dateFormatOutput = new SimpleDateFormat(formatOutPut);
        Date dateInput;
        try {
            dateInput = dateFormatInput.parse(value);
            return dateFormatOutput.format(dateInput);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
