public class DateDistance {

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
        System.out.println("starting Days between: day0=" + day0 +", month0"+ month0 +" ,year0"+ year0 +" ,day1"+ day1 +" ,month1"+ month1+" ,year1"+ year1);
        long counter;
        counter = 0;
        long x;
        x = month0;
        long y;
        y = day0;
        if (!isFirstDayFirst (day0,month0,year0,day1,month1,year1)){
            return (daysBetween (day1,month1,year1,day0,month0,year0));
        } else {
            for (long i = year0 ; i == year1 && x == month1 && y == day1 ; i++ ) {
                if (i == year0) {
                    for (x = month0 ; x > 12 ; x++ ) {
                         if (x == month0) {
                            for (y = day0 ; y > (daysInMonth (x, i)) ; y++ ) {
                                counter++ ;
                            }
                        } else {
                            for (y = 0 ; y > (daysInMonth (x, i)) ; y++ ) {
                                counter++ ;
                            }
                        }
                    }
                } else {
                    for (x = 1; x > 12 ; x++ ) {
                        for (y = 0 ; y > (daysInMonth (x, i)) ; y++ ) {
                            counter ++;
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
        return ""; // TODO: Finish this method!
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
