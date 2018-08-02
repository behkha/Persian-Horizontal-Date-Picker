package model;

import java.util.ArrayList;

public class Calendar {

    private String[] persianMonthNames = new String[]{"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
    private String[] persianWeekDays = new String[]{"شنبه", "یک\u200cشنبه", "دوشنبه", "سه\u200cشنبه", "چهارشنبه", "پنج\u200cشنبه", "جمعه"};
    private int[] daysInMonth = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private ArrayList<YearMonth> yearMonths = new ArrayList<>();

    public Calendar(String today) {
        init(today);
    }
    private void init(String today) {
        String[] dateSplit = today.split(" ");
        int monthNameIndex = findMonthNameIndex(this.persianMonthNames, dateSplit[4]);
        int dayInWeekIndex = findDayInWeekIndex(this.persianWeekDays, dateSplit[0]);
        int dayInMonthIndex = findDayInMonthIndex(this.daysInMonth, Integer.valueOf(dateSplit[2]));

        int year = Integer.valueOf(dateSplit[dateSplit.length - 1]);
        for (int i = 0; i < 13 ; i++) {

            if (monthNameIndex == 12) {
                monthNameIndex = 0;
                year++;
            }

            String month = this.persianMonthNames[monthNameIndex];
            ArrayList<Day> days = new ArrayList<>();

            for (int j = 0; j < 32; j++) {

                if (monthNameIndex > 5)
                    if (monthNameIndex == 11) {
                        if (!isPersianLeapYear(year)) {
                            if (dayInMonthIndex == 29)
                                dayInMonthIndex = 31;
                        } else {
                            if (dayInMonthIndex == 30)
                                dayInMonthIndex = 31;
                        }
                    } else if (dayInMonthIndex == 30)
                        dayInMonthIndex = 31;

                if (dayInMonthIndex == 31) {
                    dayInMonthIndex = 0;
                    break;
                }

                if (dayInWeekIndex == 7)
                    dayInWeekIndex = 0;

                days.add(new Day(this.daysInMonth[dayInMonthIndex], this.persianWeekDays[dayInWeekIndex]));
                dayInWeekIndex++;
                dayInMonthIndex++;

            }
            this.yearMonths.add(new YearMonth(year, month, days));
            monthNameIndex++;
        }
    }

    private int findDayInMonthIndex(int[] daysInMonth, int day) {
        for (int i = 0; i < daysInMonth.length; i++) {
            if (daysInMonth[i] == day) {
                return i;
            }
        }
        return -1;
    }
    private int findDayInWeekIndex(String[] daysInWeek, String day) {
        for (int i = 0; i < daysInWeek.length; i++) {
            if (daysInWeek[i].equals(day)) {
                return i;
            }
        }
        return -1;
    }
    private int findMonthNameIndex(String[] monthNames, String day) {
        for (int i = 0; i < monthNames.length; i++) {
            if (monthNames[i].equals(day)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<YearMonth> getYearMonths() {
        return yearMonths;
    }

    private static long ceil(double double1, double double2) {
        return (long) (double1 - double2 * Math.floor(double1 / double2));
    }
    private static boolean isPersianLeapYear(int persianYear) {
        return ceil((38.0D + (double) (ceil((double) ((long) persianYear - 474L), 2820.0D) + 474L)) * 682.0D, 2816.0D) < 682L;
    }
}
