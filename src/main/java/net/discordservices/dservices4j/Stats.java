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

package net.discordservices.dservices4j;

import net.discordservices.dservices4j.requests.RequestHandler;
import org.json.JSONObject;

/**
 * Class used to post Bot statistics (Server and shard count) to the Discordservices API.
 */
public class Stats{
    
    private final String TOKEN, id;
    private final JSONObject json;
    private final RequestHandler REQUEST_HANDLER;
    
    Stats(String token, String id){
        this.TOKEN = token;
        this.id = id;
        this.json = new JSONObject();
        this.REQUEST_HANDLER = new RequestHandler();
    }
    
    /**
     * Performs a POST request towards the Stats endpoint.
     * <br>This will post the provided servers with shards being set to {@code 1}.
     * 
     * <p>Use {@link #postStats(long, long) postStats(long, long)} if you also want to provide the shards.
     *
     * <h2>Rate Limits</h2>
     * <p>By default will the Wrapper only perform one request per 5 Minutes per endpoint.
     * <br>If however, the Request either isn't successful or gets rate limited will those errors be logged in your terminal.
     * 
     * @param server
     *        Amount of servers your bot is in.
     */
    public void postStats(long server){
        postStats(server, 1);
    }
    
    /**
     * Performs a POST request towards the Stats endpoint.
     * <br>This will post the provided servers with the provided shards.
     *
     * <h2>Rate Limits</h2>
     * <p>By default will the Wrapper only perform one request per 5 Minutes per endpoint.
     * <br>If however, the Request either isn't successful or gets rate limited will those errors be logged in your terminal.
     * 
     * @param server
     *        Amount of servers your bot is in.
     * @param shards
     *        Amount of shards your bot is in.
     */
    public void postStats(long server, long shards){
        json.put("servers", server)
            .put("shards", shards);
        
        REQUEST_HANDLER.postStats(id, TOKEN, json);
    }
}
