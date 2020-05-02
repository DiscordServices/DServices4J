package net.discordservices.dservices4j.requests;

import net.discordservices.dservices4j.exceptions.RatelimitedException;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class RequestHandler{
    private final OkHttpClient CLIENT = new OkHttpClient();
    
    public void postNews(String id, String token, JSONObject json) throws IOException{
        post("news", id, token, json.toString());
    }
    
    public void postStats(String id, String token, JSONObject json) throws IOException{
        post("stats", id, token, json.toString());
    }
    
    public void postCommands(String id, String token, JSONArray json) throws IOException{
        post("commands", id, token, json.toString());
    }
    
    private void post(String endpoint, String id, String token, String json) throws IOException{
        String url = "https://api.discordservices.net/bot/" + id + endpoint;

        RequestBody body = RequestBody.create(json, null);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authentication", token)
                .post(body)
                .build();

        try(Response response = CLIENT.newCall(request).execute()){
            ResponseBody responseBody = response.body();

            if(responseBody == null)
                throw new NullPointerException("Received empty Response body.");

            String bodyString = responseBody.string();
            if(bodyString.isEmpty())
                throw new NullPointerException("Received empty Response body.");

            if(!response.isSuccessful()){
                if(response.code() == 429)
                    throw new RatelimitedException();

                throw new IOException("Received non-successful response. (" + response.code() + " " + response.message() + ")");
            }
        }
    }
}
