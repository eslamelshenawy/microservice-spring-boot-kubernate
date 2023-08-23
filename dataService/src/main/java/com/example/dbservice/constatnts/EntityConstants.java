package com.example.dbservice.constatnts;

/**
 * Contain all constants related to Entities.
 */
public final class EntityConstants {
    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,6}$";
    public static final String Name_PATTERN = "^[a-zA-z0-9 \'&-]+$";
    public static final String DATE_TIME_PATTERN =
                    "^20[1-9][0-9]-" +  // YYYY
                    "(0{1}[1-9]|1[0-2])-" + // MM
                    "(0{1}[1-9]|1[0-9]|2[0-9]|3[0-1])" + // DD
                    "(T|:)(0{1}[0-9]|1[0-9]|2[0-3]):" + // HH
                    "(0{1}[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]):?" + // MM
                    "(0{1}[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])?.?[\\d]*" + // SS.MMMM
                    "Z?(\\[UTC\\])?$"; // Z[UTC]

    public static final String TOKEN_HEADER = "User-Token";

}
