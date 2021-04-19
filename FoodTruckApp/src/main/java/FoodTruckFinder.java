import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import entity.FoodTruck;
import service.FoodTruckFinderService;
import utility.DateTimeUtility;

public class FoodTruckFinder {
  public static void main(String[] args) {
    try {
      FoodTruckFinderService foodTruckFinderService = new FoodTruckFinderService();

      int curOffset = 0, prevOffset;
      int pageLimit = 10;

      Scanner scanner = new Scanner(System.in);
      String input = "n";
      do {
        // converts day of the week and hour of the day to be standardized
        Calendar c = Calendar.getInstance();
        int dayOrder = DateTimeUtility.convertDayOfWeekToDayOrder(c.get(Calendar.DAY_OF_WEEK));

        int calendarHour = c.get(Calendar.HOUR_OF_DAY);
        String hourTo24HourFormat = DateTimeUtility.convertHourTo24HourFormat(calendarHour);

        prevOffset = curOffset;

        List<FoodTruck> list =
            foodTruckFinderService.retrieveFoodTruckDataByDayAndTimeAndOffsetAndLimit(
                dayOrder, hourTo24HourFormat, curOffset, pageLimit);
        curOffset = foodTruckFinderService.printFoodTruckToCommandLine(list, curOffset);

        System.out.println(
            "What Would you like to do next? Enter 'next' for the next page, "
                + "or 'prev' for the previous page, "
                + "or 'quit' to quit.");
        input = scanner.nextLine();
        switch (input) {
          case "next":
            System.out.println("Loading...");
            break;
          case "prev":
            int prevPageOffset = prevOffset - pageLimit;
            curOffset = Math.max(prevPageOffset, 0);
            break;
          case "quit":
            System.out.println("See you next time.");
            break;
          default:
            while (!input.equals("next") && !input.equals("prev") && !input.equals("quit")) {
              System.out.println("Invalid input, please try again.");
              input = scanner.nextLine();
            }
            break;
        }
      } while (!input.equals("quit"));

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
