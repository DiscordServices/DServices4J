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
 * Class used to post News to the bot page on Discordservices.
 */
public class News{
    
    private final String TOKEN, id;
    private final RequestHandler REQUEST_HANDLER;
    
    News(String token, String id){
        this.TOKEN = token;
        this.id = id;
        this.REQUEST_HANDLER = new RequestHandler();
    }
    
    /**
     * Posts news to the bot page.
     * <br>The posts made with this method will never be seen as errors. Use {@link #postNews(String, String, boolean) postNews(String, String, true)}
     * if you want to send an Error message.
     *
     * <h2>Rate Limits</h2>
     * <p>By default will the Wrapper only perform one request per 15 seconds per endpoint.
     * <br>If however, the Request either isn't successful or gets rate limited will those errors be logged in your terminal.
     *
     * @param title
     *        The title of the News post.
     * @param message
     *        The message of the news.
     */
    public void postNews(String title, String message){
        postNews(title, message, false);
    }
    
    /**
     * Posts news to the bot page.
     *
     * <h2>Rate Limits</h2>
     * <p>By default will the Wrapper only perform one request per 15 seconds per endpoint.
     * <br>If however, the Request either isn't successful or gets rate limited will those errors be logged in your terminal.
     * 
     * @param title
     *        The title of the News post.
     * @param message
     *        The message of the news.
     * @param isError
     *        If the post should be treated as an error/issue.
     */
    public void postNews(String title, String message, boolean isError){
        JSONObject json = new JSONObject().put("title", title)
                .put("content", message)
                .put("error", isError);
        
        REQUEST_HANDLER.postNews(id, TOKEN, json);
    }
}
