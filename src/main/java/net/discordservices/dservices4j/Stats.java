package net.discordservices.dservices4j;

import net.discordservices.dservices4j.exceptions.RatelimitedException;
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
     * <p><b>Rate limits</b>
     * <br>This method only performs a request every 5 seconds and othewhise does nothing.
     * 
     * @param server
     *        Amount of servers your bot is in.
     *
     * @throws java.io.IOException
     *         When the request wasn't successfull.
     * @throws net.discordservices.dservices4j.exceptions.RatelimitedException
     *         When the Wrapper got rate limited by the API.
     */
    public void postStats(long server) throws IOException, RatelimitedException{
        postStats(server, 1);
    }
    
    /**
     * Performs a POST request towards the Stats endpoint.
     * <br>This will post the provided servers with the provided shards.
     *
     * <p><b>Rate limits</b>
     * <br>This method only performs a request every 5 seconds and othewhise does nothing.
     * 
     * @param server
     *        Amount of servers your bot is in.
     * @param shards
     *        Amount of shards your bot is in.
     * 
     * @throws java.io.IOException
     *         When the request wasn't successfull.
     * @throws net.discordservices.dservices4j.exceptions.RatelimitedException
     *         When the Wrapper got rate limited by the API.
     */
    public void postStats(long server, long shards) throws IOException, RatelimitedException{
        json.put("servers", server)
            .put("shards", shards);
        
        REQUEST_HANDLER.postStats(id, TOKEN, json);
    }
}
