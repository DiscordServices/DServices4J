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

/**
 * Main class used for the Java Wrapper.
 * <br>To perform any actions with this Wrapper will you need to get an instance of this class using the
 * {@link net.discordservices.dservices4j.DServices4J.Builder nested Builder class}.
 * 
 * <p>After obtaining a Instance of this class can you use the different getter methods to get instances of the
 * {@link #getCommands() Commands}, {@link #getNews() News} or {@link #getStats() Stats} classes.
 */
public class DServices4J{
    
    private final String TOKEN, id;
    
    private Commands commands = null;
    private Stats stats = null;
    private News news = null;
    
    DServices4J(String token, String id){
        this.TOKEN = token;
        this.id = id;
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.Commands Command} class, which is used to submit
     * the commands a bot has to the Discordservices API.
     * 
     * <p>This method is used to create instances of the Command class, or load it, when already created.
     * 
     * <h2>Example</h2>
     * <pre><code>
     * DServices4J dservices = new DServices4J(API_TOKEN, ID);
     * 
     * Command command = dservices.getCommands();
     * </code></pre>
     * 
     * @return Usable instance of the {@link net.discordservices.dservices4j.Commands Command} class.
     */
    public Commands getCommands(){
        if(commands != null)
            return commands;
        
        return (commands = new Commands(TOKEN, id));
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.News News} class, which is used to post announcements
     * about your bot.
     *
     * <p>This method is used to create an instance of the News class, or load it, when already created.
     *
     * <h2>Example</h2>
     * <pre><code>
     * DServices4J dservices = new DServices4J(API_TOKEN, ID);
     *
     * News news = dservices.getNews();
     * </code></pre>
     *
     * @return Usable instance of the {@link net.discordservices.dservices4j.Stats Stats} class.
     */
    public News getNews(){
        if(news != null)
            return news;
        
        return (news = new News(TOKEN, id));
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.Stats Stats} class, which is used to submit statistics
     * like server and shard count to the Discordservices API.
     * 
     * <p>This method is used to create an instance of the Stats class, or load it, when already created.
     *
     * <h2>Example</h2>
     * <pre><code>
     * DServices4J dservices = new DServices4J(API_TOKEN, ID);
     *
     * Stats stats = dservices.getStats();
     * </code></pre>
     * 
     * @return Usable instance of the {@link net.discordservices.dservices4j.Stats Stats} class.
     */
    public Stats getStats(){
        if(stats != null)
            return stats;
        
        return (stats = new Stats(TOKEN, id));
    }
    
    /**
     * Builder class for easier setup of the {@link net.discordservices.dservices4j.DServices4J DServices4J} class.
     */
    public static class Builder{
        private String token = null;
        private String id = null;
    
        /**
         * Sets the API token (This is NOT your Bot token!) to use for the various endpoints.
         * 
         * @param  token
         *         The token to set.
         *         
         * @return This Builder after the token was set. Useful for chaining.
         */
        public Builder setToken(String token){
            this.token = token;
            return this;
        }
    
        /**
         * Sets the ID of your bot that will be used for the different endpoints.
         *
         * @param  id
         *         The id to set.
         *
         * @return This Builder after the id was set. Useful for chaining.
         */
        public Builder setId(String id){
            this.id = id;
            return this;
        }
    
        /**
         * Sets the ID of your bot that will be used for the different endpoints.
         *
         * @param  id
         *         The id to set.
         *
         * @return This Builder after the id was set. Useful for chaining.
         */
        public Builder setId(long id){
            return setId(String.valueOf(id));
        }
    
        /**
         * Builds an instance of the {@link net.discordservices.dservices4j.DServices4J DServices4J} class using the
         * provided {@link #setToken(String) token} and {@link #setId(String) bot id}.
         * 
         * @return The built, usable instance of {@link net.discordservices.dservices4j.DServices4J DServices4J}.
         * 
         * @throws java.lang.IllegalArgumentException
         *         When either token or id are null or empty.
         */
        public DServices4J build(){
            Checks.check((token != null) && (!token.isEmpty()), "Token may not be null nor empty.");
            Checks.check((id != null) && (!id.isEmpty()), "ID may not be null nor empty.");
            
            return new DServices4J(token, id);
        }
    }
}
