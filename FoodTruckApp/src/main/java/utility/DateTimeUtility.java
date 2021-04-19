package utility;

public final class DateTimeUtility {

  /** Private instructor to prevent instantiation. */
  private DateTimeUtility() {}

  /**
   * Converts Calendar's day of week to Socrata's day order.
   *
   * @param dayOfWeek - Calendar's day of week
   * @return dayOrder - Socrata's day order
   */
  public static int convertDayOfWeekToDayOrder(final int dayOfWeek) {
    return dayOfWeek % 7;
  }

  /**
   * Converts hour of day to the standard hour in 24 hour format.
   *
   * @param hourOfDay - Socrata's hour of day
   * @return hour - standardized hour
   */
  public static String convertHourTo24HourFormat(final int hourOfDay) {
    StringBuilder hour = new StringBuilder();
    if (hourOfDay < 10) {
      hour.append("0");
    }
    hour.append(hourOfDay).append(":00");
    return hour.toString();
  }
}
