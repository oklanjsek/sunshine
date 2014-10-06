package com.example.android.sunshine.app;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by a602417 on 10/5/14.
 */
public class WeatherData {

    final String OWM_LIST = "list";
    final String OWM_WEATHER = "weather";
    final String OWM_TEMPERATURE = "temp";
    final String OWM_DATETIME = "dt";
    final String OWM_DESCRIPTION = "main";

    // For now, using the format "Day, description, hi/low"
    String mDay;
    String mDescription;
    TemperatureObject mTemperatureObject;
    String mWeatherDataStr;

    public WeatherData(JSONObject dayForecast) {

        // The date/time is returned as a long.  We need to convert that
        // into something human-readable, since most people won't read "1400356800" as
        // "this saturday".
        long dateTime = 0;
        try {
            dateTime = dayForecast.getLong(OWM_DATETIME);
            mDay = getReadableDateString(dateTime);

            // description is in a child array called "weather", which is 1 element long.
            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            mDescription = weatherObject.getString(OWM_DESCRIPTION);

            mTemperatureObject = new TemperatureObject(dayForecast.getJSONObject(OWM_TEMPERATURE));
            String highLowStr = mTemperatureObject.getHighLowStr();
            mWeatherDataStr = mDay + " - " + mDescription + " - " + highLowStr;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getWeatherDataStrStr() {
        return mWeatherDataStr;
    }

    private static String getReadableDateString(long time){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date).toString();
    }

}
