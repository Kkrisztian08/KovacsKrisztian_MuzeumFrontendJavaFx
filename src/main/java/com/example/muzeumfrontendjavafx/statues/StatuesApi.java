package com.example.muzeumfrontendjavafx.statues;

import com.example.muzeumfrontendjavafx.ApiError;
import com.example.muzeumfrontendjavafx.RequestHandler;
import com.example.muzeumfrontendjavafx.Response;
import com.example.muzeumfrontendjavafx.Statues;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class StatuesApi {
    private static final String BASE_URL="http://127.0.0.1:8000";
    private static final String Statue_API_URL = BASE_URL + "/api/statue";

    public static List<Statues> getStatues() throws IOException {
        Response response = RequestHandler.get(Statue_API_URL);
        String json = response.getContent();
        Gson jsonConverted = new Gson();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        Type type = new TypeToken<List<Statues>>(){}.getType();
        return jsonConverted.fromJson(json,type);
    }

    public static Statues statueHozzaadasa(Statues ujStatue) throws IOException {
        Gson jsonConverted = new Gson();
        String filmJson=jsonConverted.toJson(ujStatue);
        Response response=RequestHandler.post(Statue_API_URL, filmJson);
        String json = response.getContent();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        return jsonConverted.fromJson(json, Statues.class);
    }

    public static Statues statueModositas(Statues modositando) throws IOException {
        Gson jsonConverted = new Gson();
        String filmJson=jsonConverted.toJson(modositando);
        Response response=RequestHandler.put(Statue_API_URL +"/"+ modositando.getId(), filmJson);
        String json = response.getContent();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        return jsonConverted.fromJson(json, Statues.class);
    }

    public static boolean statueTorlese(int id) throws IOException {
        Response response=RequestHandler.delete(Statue_API_URL+"/"+id);

        Gson jsonConverted = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        return response.getResponseCode()==204;
    }
}
