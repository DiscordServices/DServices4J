package net.discordservices.dservices4j.requests;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.discordservices.dservices4j.exceptions.RatelimitedException;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RequestHandler{
    private final OkHttpClient CLIENT = new OkHttpClient();
    
    private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();
    
    public void postNews(String id, String token, JSONObject json) throws IOException, RatelimitedException{
        post("news", id, token, json.toString());
    }
    
    public void postStats(String id, String token, JSONObject json) throws IOException, RatelimitedException{
        post("stats", id, token, json.toString());
    }
    
    public void postCommands(String id, String token, JSONArray json) throws IOException, RatelimitedException{
        post("commands", id, token, json.toString());
    }
    
    private void post(String endpoint, String id, String token, String json) throws IOException, RatelimitedException{
        if(cache.getIfPresent(endpoint) != null)
            return;
        
        String url = "https://api.discordservices.net/bot/" + id + "/" + endpoint;
        
        RequestBody body = RequestBody.create(json, null);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        try(Response response = CLIENT.newCall(request).execute()){
            if(!response.isSuccessful()){
                if(response.code() == 429)
                    throw new RatelimitedException();

                throw new IOException("Received non-successful response. (" + response.code() + " " + response.message() + ")");
            }
        }
    }
}
