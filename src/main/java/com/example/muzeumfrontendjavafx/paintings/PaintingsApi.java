package com.example.muzeumfrontendjavafx.paintings;

import com.example.muzeumfrontendjavafx.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PaintingsApi {
    private static final String BASE_URL="http://127.0.0.1:8000";
    private static final String Paintings_API_URL = BASE_URL + "/api/paintings";

    public static List<Paintings> getPaintings() throws IOException {
        Response response =RequestHandler.get(Paintings_API_URL);
        String json = response.getContent();
        Gson jsonConverted = new Gson();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        Type type = new TypeToken<List<Paintings>>(){}.getType();
        return jsonConverted.fromJson(json,type);
    }

    public static Paintings paintingHozzaadasa(Paintings ujStatue) throws IOException {
        Gson jsonConverted = new Gson();
        String filmJson=jsonConverted.toJson(ujStatue);
        Response response=RequestHandler.post(Paintings_API_URL, filmJson);
        String json = response.getContent();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        return jsonConverted.fromJson(json, Paintings.class);
    }

    public static Paintings paintingModositas(Paintings modositando) throws IOException {
        Gson jsonConverted = new Gson();
        String filmJson=jsonConverted.toJson(modositando);
        Response response=RequestHandler.put(Paintings_API_URL +"/"+ modositando.getId(), filmJson);
        String json = response.getContent();
        if (response.getResponseCode() >=400){
            System.out.println(json);
            String message = jsonConverted.fromJson(json, ApiError.class).getMessage();
            throw  new IOException(message);
        }
        return jsonConverted.fromJson(json, Paintings.class);
    }

    public static boolean paintingTorlese(int id) throws IOException {
        Response response=RequestHandler.delete(Paintings_API_URL+"/"+id);

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
