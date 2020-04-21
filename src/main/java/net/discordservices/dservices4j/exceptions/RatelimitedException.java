package net.discordservices.dservices4j.exceptions;

public class RatelimitedException extends RuntimeException{
    
    public RatelimitedException(){}
    
    @Override
    public String getMessage(){
        return "Got Ratelimited by Discordservices.net";
    }
}
