package com.example.guardian_assignment.persistence;

import androidx.room.TypeConverter;

import com.example.guardian_assignment.models.Fields;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

// class to convert Fields object
public class Converters {

    @TypeConverter
    public static Fields fromString(String value){
        Type listType = new TypeToken<Fields>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromFieldObject(Fields fields){
        Gson gson = new Gson();
        String json = gson.toJson(fields);
        return json;
    }
}
