package com.myapp.convertcurrency.core.retrofit.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JsonPureConverterFactory extends Converter.Factory {
    public static JsonPureConverterFactory create() {
        return create(new Gson());
    }

    public static JsonPureConverterFactory create(Gson gson) {
        return new JsonPureConverterFactory(gson);
    }

    private final Gson gson;

    private JsonPureConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }


    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (!Utils.isAnnotationPresent(annotations, JsonPureConverter.class)) {
            return responseGSonBodyConverter(type, annotations, retrofit);
        } else {
            return responseNoConVentBodyConverter(type, annotations, retrofit);
        }
    }

    private Converter<ResponseBody, ?> responseNoConVentBodyConverter(Type type, Annotation[] annotations,
                                                                      Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonPureResponseBodyConverter<>();
    }


    private Converter<ResponseBody, ?> responseGSonBodyConverter(Type type, Annotation[] annotations,
                                                                 Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyConverter<>(gson, adapter);
    }

}
