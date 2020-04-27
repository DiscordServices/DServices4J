package net.discordservices.dservices4j;

import net.discordservices.dservices4j.requests.RequestHandler;
import org.json.JSONObject;

import java.io.IOException;

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
     * <p>Note that this might throw a {@link java.io.IOException IOException} on a not successfull POST.
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
     * <p>Note that this might throw a {@link java.io.IOException IOException} on a not successfull POST.
     * 
     * @param server
     *        Amount of servers your bot is in.
     * @param shards
     *        Amount of shards your bot is in.
     */
    public void postStats(long server, long shards){
        json.put("servers", server)
            .put("shards", shards);
        
        try{
            REQUEST_HANDLER.post("stats", id, TOKEN, json);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
