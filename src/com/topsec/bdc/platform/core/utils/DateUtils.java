package com.topsec.bdc.platform.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author baiyanwei
 * 
 *         Jul 13, 2013
 * 
 *         This is the platform general utility class.
 */
final public class DateUtils {

    /**
     * default date format.
     */
    final private static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmSSsss");

    /**
     * Get the String type date
     * 
     * @param millis
     * @return
     */
    public static String getTime(long millis) {

        return DEFAULT_DATE_FORMAT.format(new Date(millis));

    }

    /**
     * Parse String to date
     * 
     * @param millis
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateText) throws ParseException {

        if (Assert.isEmptyString(dateText) == true) {
            return null;
        }
        return DEFAULT_DATE_FORMAT.parse(dateText);

    }

}
