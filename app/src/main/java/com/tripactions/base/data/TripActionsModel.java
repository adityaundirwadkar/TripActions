package com.tripactions.base.data;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.zip.CRC32;

/**
 * Base class for models.
 */
public class TripActionsModel implements Cloneable {

    public String toString() {
        return new Gson().toJson(this);
    }

    public String quickHash(String... data) {
        String s = TextUtils.join("-", data);
        CRC32 c = new CRC32();
        c.update(s.getBytes());
        return Long.toHexString(c.getValue());
    }

    @Override
    public TripActionsModel clone() {
        try {
            return (TripActionsModel) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return null;
    }
}
