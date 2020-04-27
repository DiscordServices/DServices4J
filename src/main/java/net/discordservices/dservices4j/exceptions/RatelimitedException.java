package net.discordservices.dservices4j.exceptions;

/**
 * Indicates that the bot/application got rate limited by the Discordservices API.
 */
public class RatelimitedException extends RuntimeException{
    
    public RatelimitedException(){}
    
    @Override
    public String getMessage(){
        return "Got Ratelimited by Discordservices.net";
    }
}
