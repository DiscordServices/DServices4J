package net.discordservices.dservices4j;

import org.json.JSONObject;

public class Command{
    
    private final String TOKEN;
    private JSONObject json;
    
    Command(String token){
        this.TOKEN = token;
        this.json = new JSONObject();
    }
    
    /**
     * Adds a command to the list using a {@link net.discordservices.dservices4j.Command.CommandInfo CommandInfo}
     * instance.
     * 
     * @param  commandInfo
     *         The Command that should be added.
     *         
     * @return This class after the command was added. Useful for chaining.
     */
    public Command addCommand(CommandInfo commandInfo){
        JSONObject command = new JSONObject()
                .put("command", commandInfo.getName())
                .put("description", commandInfo.getDescription())
                .put("category", commandInfo.getCategory());
        
        return this;
    }
    
    /**
     * Adds the provided commands to the list using the provided {@link net.discordservices.dservices4j.Command.CommandInfo CommandInfo}
     * instances.
     * 
     * @param  commandInfos
     *         The commands to add.
     *         
     * @return This class after the command was added. Useful for chaining.
     */
    public Command addCommands(CommandInfo... commandInfos){
        for(CommandInfo info : commandInfos)
            addCommand(info);
        
        return this;
    }
    
    /**
     * Sets the provided commands for the list using the provided {@link net.discordservices.dservices4j.Command.CommandInfo CommandInfo}
     * instances.
     * <br><b>This will remove all previously set commands!</b>
     * 
     * @param  commandInfos
     *         The commands to set.
     *         
     * @return This class after the commands were set. Useful for chaining.
     */
    public Command setCommands(CommandInfo... commandInfos){
        json = new JSONObject();
        
        for(CommandInfo info : commandInfos)
            addCommand(info);
        
        return this;
    }
    
    /**
     * Posts the previously set commands to the Discordservices API.
     * 
     * @throws java.lang.NullPointerException
     *         When no command was previously set.
     */
    public void postCommands(){
        if(json.isEmpty())
            throw new NullPointerException("Command list may not be empty.");
    }
    
    public static class CommandInfo{
        
        private String name = "";
        private String description = "";
        private String category = "";
        
        public CommandInfo(String name, String description, String category){
            this.name = name;
            this.description = description;
            this.category = category;
        }
        
        public String getName(){
            return name;
        }
    
        public String getDescription(){
            return description;
        }
    
        public String getCategory(){
            return category;
        }
    }
}
