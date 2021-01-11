/*
 * Copyright 2020 - 2021 Andre601
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.discordservices.dservices4j.requests;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import net.discordservices.dservices4j.exceptions.RatelimitedException;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RequestHandler{
    private final Logger LOG = LoggerFactory.getLogger("DServices4J RequestHandler");
    private final OkHttpClient CLIENT = new OkHttpClient();
    
    private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();
    
    public void postNews(String id, String token, JSONObject json){
        try{
            post("news", id, token, json.toString());
        }catch(IOException ex){
            LOG.warn(getErrorMsg(id, "news"), ex);
        }catch(RatelimitedException ex){
            LOG.warn("Received RatelimitedException!", ex);
        }
    }
    
    public void postStats(String id, String token, JSONObject json){
        try{
            post("stats", id, token, json.toString());
        }catch(IOException ex){
            LOG.warn(getErrorMsg(id, "stats"), ex);
        }catch(RatelimitedException ex){
            LOG.warn("Received RatelimitedException!", ex);
        }
    }
    
    public void postCommands(String id, String token, JSONArray json){
        try{
            post("commands", id, token, json.toString());
        }catch(IOException ex){
            LOG.warn(getErrorMsg(id, "commands"), ex);
        }catch(RatelimitedException ex){
            LOG.warn("Received RatelimitedException!", ex);
        }
    }
    
    private void post(String endpoint, String id, String token, String json) throws IOException, RatelimitedException{
        if(cache.getIfPresent(endpoint) != null){
            LOG.info("Denied POST request towards /bot/{}/{}! Please wait at least 5 Minutes between requests!", id, endpoint);
            return;
        }
        
        cache.put(endpoint, json);
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
                    throw new RatelimitedException(endpoint, id);

                throw new IOException("Received non-successful response. (" + response.code() + " " + response.message() + ")");
            }
        }
    }
    
    private String getErrorMsg(String id, String endpoint){
        return "There was an IOException while performing a POST request towards /bot/" + id + "/" + endpoint;
    }
}
