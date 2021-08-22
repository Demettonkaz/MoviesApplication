package com.example.moviesapp.ui.detail;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DetailPresenter {

    private final Context context;

    public DetailPresenter(Context context) {
        this.context = context;
    }

    public static <T> T stringToObject(String s, Class<T> classOfT) {
        try {
            GsonBuilder builder = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .serializeSpecialFloatingPointValues();
            Gson gson = builder.create();
            return gson.fromJson(s, classOfT);
        } catch (Exception error) {
            return null;
        }
    }

}
