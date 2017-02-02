public class DateDistance {

    private static String MON = "Monday";
    private static String TUES = "Tuesday";
    private static String WED = "Wednesday";
    private static String THURS = "Thursday";
    private static String FRI = "Friday";
    private static String SAT = "Saturday";
    private static String SUN = "Sunday";

    /**
     * Returns whether the given year is a leap year.
     */
    public static boolean isLeapYear ( long year ) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of days in the given month in the given year.
     */
    public static long daysInMonth ( long month, long year ) {
        switch ((int)month) {
            case 1: return 31;
            case 2: if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            default: return -1;
        }
    }

    /**
     * Returns whether the given date is a valid date.
     */
    public static boolean isValidDate ( long day, long month, long year ) {
        if (month < 1 || month > 12 || year < 0 || day < 1 || day > daysInMonth(month, year)){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the number of days between the two provided dates, regardless of
     * the order they were provided.
     */
    public static long daysBetween ( long day0, long month0, long year0,
            long day1, long month1, long year1 ) {
        int counter;
        counter = 0;
        if (!isFirstDayFirst (day0,month0,year0,day1,month1,year1)){
            return (daysBetween (day1,month1,year1,day0,month0,year0));
        } else {
            for (long i = year0 ; i <= year1; i++ ) {
                if (i == year0) {
                    for (long x = month0 ; x <= 12 ; x++ ) {
                         if (x == month0) {
                            for (long y = day0 ; y <= (daysInMonth (x, i)) ; y++ ) {
                                counter = counter + 1 ;
                            }
                        } else {
                            for (long y = 1 ; y <= (daysInMonth (x, i)) ; y++ ) {
                                counter = counter + 1 ;
                            }
                        }
                    }
                } else if (i == year1) {
                    for (long x = 1; x <= month1  ; x++ ) {
                        if (x == month1) {
                            for (long y = 1 ; y < day1 ; y++ ) {
                                counter = counter + 1;
                            }
                        } else {
                            for (long y = 1 ; y <= (daysInMonth (x, i)) ; y++ ) {
                                counter = counter + 1 ;
                            }
                        }
                    }
                } else {
                    for (long x = 1; x <= 12  ; x++ ) {
                        for (long y = 1 ; y <= (daysInMonth (x, i)) ; y++ ) {
                            counter = counter + 1 ;
                        }
                    }
                }
            }
            System.out.println(counter);
            return counter;
        }
    }
    public static boolean isFirstDayFirst ( long day0, long month0, long year0, long day1, long month1, long year1 ){
        if (year0 < year1){
            return true;
        } else if (year0 == year1 && month0 < month1) {
            return true;
        } else if (year0 == year1 && month0 == month1 && day0 < day1) {
            return true;
        } else if (year0 == year1 && month0 == month1 && day0 == day1) {
            System.out.println("This is the Same Day");
            return false;
        } else {
            return false;
        }
    }


    /**
     * Returns the day of the week the given date occured on as a String.
     */
    public static String dayOfTheWeek ( long day, long month, long year ) {
       // Febuary 1st 2017 is a Wednesday
        long x = daysBetween (1, 2, 2017, day, month, year);
        long y = x % 7;
        boolean future = isFirstDayFirst (1, 2, 2017, day, month, year);
        if (y == 0) {
            return WED;
        } else if (y == 1) {
            if (future) {
                return THURS;
            } else {
                return TUES;
            }
        } else if (y == 2) {
            if (future) {
                return FRI;
            } else {
                return MON;
            }
        } else if (y == 3) {
            if (future) {
                return SAT;
            } else {
                return SUN;
            }
        } else if (y == 4) {
            if (future) {
                return SUN;
            } else {
                return SAT;
            }
        } else if (y == 5) {
            if (future) {
                return MON;
            } else {
                return FRI;
            }
        } else if (y == 6) {
            if (future) {
                return TUES;
            } else {
                return THURS;
            }
        }
        return "Unexpected Error";
    }


    /**
     * Returns the long form of the given date as a String.
     */
    public static String longformDate ( long day, long month, long year ) {
        return ""; // TODO: Finish this method!
    }

    public static void main ( String[] args ) {
        // TODO: Finish this method!
    }
}
