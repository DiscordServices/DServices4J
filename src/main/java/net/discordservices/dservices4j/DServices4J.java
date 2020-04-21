package net.discordservices.dservices4j;

public class DServices4J{
    
    private final String TOKEN;
    private Command command = null;
    
    public DServices4J(String token){
        this.TOKEN = token;
    }
    
    public String getToken(){
        return TOKEN;
    }
    
    /**
     * Gets an instance of the {@link net.discordservices.dservices4j.Command Command} class, which is used to submit
     * the commands a bot has to the Discordservices API.
     * 
     * <p>This method is used to create instances of the Command class.
     * <br>Example:
     * <br><code><pre>
     * Command command = new DServices4J(API_TOKEN).getCommand();
     * </pre></code>
     * 
     * @return Usable instance of the {@link net.discordservices.dservices4j.Command Command} class.
     */
    public Command getCommand(){
        if(command != null)
            return command;
        
        return (command = new Command(TOKEN));
    }
}
