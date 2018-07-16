package com.myapp.convertcurrency.core.retrofit.converter;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    protected Gson gson;
    private final TypeAdapter<T> adapter;

    public GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } catch (IOException e) {
            throw e;
        } finally {
            value.close();
        }
    }
}