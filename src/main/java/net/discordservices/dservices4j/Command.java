package net.discordservices.dservices4j;

import org.json.JSONObject;

/**
 * Class used for posting Command information (name, description and category) to the Discordservices API.
 */
public class Command{
    
    private final String TOKEN, id;
    private JSONObject json;
    
    Command(String token, String id){
        this.TOKEN = token;
        this.id = id;
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
    
    /**
     * Class used for the {@link net.discordservices.dservices4j.Command#addCommand(CommandInfo) addCommand(...)} methods.
     * <br>You set the name, description and category through the constructors.
     * 
     * <p>Note that the description and category can be set to {@code null}, which makes them default to the following values:
     * <br><ul>
     *     <li>{@code description} defaults to an empty String ({@code ""})</li>
     *     <li>{@code category} defaults to {@code null} (Which is treated as All on the API)</li>
     * </ul>
     */
    public static class CommandInfo{
        
        private final String name;
        private final String description;
        private final String category;
    
        /**
         * Constructor to set the CommandInfo instance, which will be used in the addCommand options.
         * <br>Note that you can provide {@code null} for description and category and it will default to specific values.
         * 
         * @param name
         *        Name of the command. Can't be null.
         * @param description
         *        Description of the command. Can be set to {@code null} for no description.
         * @param category
         *        Category of the command. Can be set to {@code null} for category All.
         */
        public CommandInfo(String name, String description, String category){
            Checks.check((name != null) && (!name.isEmpty()), "Command name may not be empty nor null.");
            
            this.name = name;
            this.description = description == null ? "" : description;
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
