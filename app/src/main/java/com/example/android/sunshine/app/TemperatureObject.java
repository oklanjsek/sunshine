package com.example.android.sunshine.app;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by a602417 on 10/5/14.
 */
public class TemperatureObject {

    final String OWM_MAX = "max";
    final String OWM_MIN = "min";

    double mHigh = 0;
    double mLow = 0;
    String mHighLowStr = "";

    public TemperatureObject(JSONObject temperatureObject) {

        try {
            mHigh = temperatureObject.getDouble(OWM_MAX);
            mLow = temperatureObject.getDouble(OWM_MIN);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        long roundedHigh = Math.round(mHigh);
        long roundedLow = Math.round(mLow);

        mHighLowStr = roundedHigh + "/" + roundedLow;
    }

    public String getHighLowStr() { return mHighLowStr; }
}
