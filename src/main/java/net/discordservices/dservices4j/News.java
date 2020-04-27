package net.discordservices.dservices4j;

import net.discordservices.dservices4j.requests.RequestHandler;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class used to post News to the bot page on Discordservices.
 */
public class News{
    
    private final String TOKEN, id;
    private final JSONObject json;
    private final RequestHandler REQUEST_HANDLER;
    
    News(String token, String id){
        this.TOKEN = token;
        this.id = id;
        this.json = new JSONObject();
        this.REQUEST_HANDLER = new RequestHandler();
    }
    
    /**
     * Posts news to the bot page.
     * <br>The posts made with this method will never be seen as errors. Use {@link #postNews(String, String, boolean) postNews(String, String, true)}
     * if you want to send an Error message.
     *
     * <p>Note that this might throw a {@link java.io.IOException IOException} on a not successfull POST.
     *
     * @param title
     *        The title of the News post.
     * @param message
     *        The message of the news.
     */
    public void postNes(String title, String message){
        postNews(title, message, false);
    }
    
    /**
     * Posts news to the bot page.
     *
     * <p>Note that this might throw a {@link java.io.IOException IOException} on a not successfull POST.
     * 
     * @param title
     *        The title of the News post.
     * @param message
     *        The message of the news.
     * @param isError
     *        If the post should be treated as an error/issue.
     */
    public void postNews(String title, String message, boolean isError){
        json.put("title", title)
            .put("content", message)
            .put("error", isError);
        
        try{
            REQUEST_HANDLER.post("news", id, TOKEN, json);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
