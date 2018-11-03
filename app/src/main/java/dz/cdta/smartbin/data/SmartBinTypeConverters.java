package dz.cdta.smartbin.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import dz.cdta.smartbin.model.SmartBin;

public class SmartBinTypeConverters {
    
    private static Gson gson = new Gson();
    
    @TypeConverter
    public static List<SmartBin> stringToSmartBinList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<SmartBin>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String smartBinListToString(List<SmartBin> smartBins) {
        return gson.toJson(smartBins);
    }
}