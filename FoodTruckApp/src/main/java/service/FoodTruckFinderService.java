package service;

import com.google.gson.Gson;
import entity.FoodTruck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FoodTruckFinderService {

  private static final String APP_TOKEN = "Q0MYjT87rKhRe3nOzNUPj3kFt";
  private static final String API_BASE_URL = "http://data.sfgov.org/resource/bbb8-hzi6.json";
  private static final String APP_TOKEN_PLACEHOLDER = "$$app_token";
  private static final String DAY_ORDER_PLACEHOLDER = "dayorder";
  private static final String START_TIME_PLACEHOLDER = "start24";
  private static final String END_TIME_PLACEHOLDER = "end24";
  private static final String NO_TRUCK_AVAILABLE_PLACEHOLDER =
      "Sorry, there is no more open food truck to be shown.";
  private static final String STRING_SPECIFIERS = "%-75s %-25s";

  private Map<String, List<FoodTruck>> truckListCache = new HashMap<>();

  /**
   * Retrieves sorted food truck data based on day order, hour of day, offset, and page limit from
   * Socrata. Will return early if already stored in cache before.
   *
   * @param dayOrder - day of the week
   * @param hourOfDay - hour of the day
   * @param offset - offset in the data list
   * @param pageLimit - number of entries allowed on each page
   * @return foodTruckList - list returned based on all criteria specified
   */
  public List<FoodTruck> retrieveFoodTruckDataByDayAndTimeAndOffsetAndLimit(
      final int dayOrder, final String hourOfDay, final int offset, final int pageLimit) {
    // utilizes hashmap to quickly retrieve page that has been visited before
    String key = dayOrder + " " + hourOfDay + " " + offset;
    if (truckListCache.containsKey(key)) {
      return truckListCache.get(key);
    }

    List<FoodTruck> foodTruckList = new ArrayList<>();
    try {
      StringBuilder result = new StringBuilder();
      StringBuilder query = new StringBuilder();

      query.append(APP_TOKEN_PLACEHOLDER + "=" + APP_TOKEN);
      query.append("&$SELECT=location,applicant");
      query.append("&" + DAY_ORDER_PLACEHOLDER + "=" + dayOrder);
      String where =
          START_TIME_PLACEHOLDER
              + " <= '"
              + hourOfDay
              + "'"
              + "AND "
              + END_TIME_PLACEHOLDER
              + " >= '"
              + hourOfDay
              + "'";
      query.append("&$WHERE=" + URLEncoder.encode(where, "UTF-8"));
      query.append("&$ORDER=applicant");
      query.append("&$OFFSET=" + offset);
      query.append("&$LIMIT=" + pageLimit);

      URL url = new URL(API_BASE_URL + "?" + query.toString());
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      rd.close();

      foodTruckList = Arrays.asList(new Gson().fromJson(result.toString(), FoodTruck[].class));
      truckListCache.put(key, foodTruckList);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return foodTruckList;
  }

  public int printFoodTruckToCommandLine(final List<FoodTruck> list, int offset) {
    if (list.size() == 0) {
      System.out.println(NO_TRUCK_AVAILABLE_PLACEHOLDER);
    } else {
      System.out.println(
          "----------------------------------------------------------------------------------------------------------");
      String header = String.format(STRING_SPECIFIERS, "NAME", "ADDRESS");
      System.out.println(header);
      System.out.println("");
      for (FoodTruck foodTruck : list) {
        String formattedFoodTruck =
            String.format(STRING_SPECIFIERS, foodTruck.getApplicant(), foodTruck.getLocation());
        offset++;
        System.out.println(formattedFoodTruck);
      }
      System.out.println(
          "----------------------------------------------------------------------------------------------------------");
    }

    return offset;
  }
}
