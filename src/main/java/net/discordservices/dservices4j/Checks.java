package net.discordservices.dservices4j;

public class Checks{
    
    public static void check(final boolean expression, String message){
        if(!expression)
            throw new IllegalArgumentException(message);
    }
}
