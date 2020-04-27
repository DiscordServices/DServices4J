package net.discordservices.dservices4j;

public class DServices4J{
    
    private final String TOKEN, id;
    private Command command = null;
    private Stats stats = null;
    private News news = null;
    
    public DServices4J(String token, String id){
        this.TOKEN = token;
        this.id = id;
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.Command Command} class, which is used to submit
     * the commands a bot has to the Discordservices API.
     * 
     * <p>This method is used to create instances of the Command class, or load it, when already created.
     * <br>Example:
     * <br><pre>{@code
     * Command command = new DServices4J(API_TOKEN, ID).getCommand();
     * }</pre>
     * 
     * @return Usable instance of the {@link net.discordservices.dservices4j.Command Command} class.
     */
    public Command getCommand(){
        if(command != null)
            return command;
        
        return (command = new Command(TOKEN, id));
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.Stats Stats} class, which is used to submit statistics
     * like server and shard count to the Discordservices API.
     * 
     * <p>This method is used to create an instance of the Stats class, or load it, when already created.
     * <br>Example:
     * <br><pre>{@code
     * Stats stats = new DServices4J(API_TOKEN, ID).getStats();
     * }</pre>
     * 
     * @return Usable instance of the {@link net.discordservices.dservices4j.Stats Stats} class.
     */
    public Stats getStats(){
        if(stats != null)
            return stats;
        
        return (stats = new Stats(TOKEN, id));
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.News News} class, which is used to post announcements
     * about your bot.
     *
     * <p>This method is used to create an instance of the News class, or load it, when already created.
     * <br>Example:
     * <br><pre>{@code
     * News news = new DServices4J(API_TOKEN, ID).getNews();
     * }</pre>
     *
     * @return Usable instance of the {@link net.discordservices.dservices4j.Stats Stats} class.
     */
    public News getNews(){
        if(news != null)
            return news;
        
        return (news = new News(TOKEN, id));
    }
    
    /**
     * Builder class for easier setup of the {@link net.discordservices.dservices4j.DServices4J DServices4J} class.
     */
    public static class Builder{
        private String token = null;
        private String id = null;
        
        public Builder(){}
    
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
