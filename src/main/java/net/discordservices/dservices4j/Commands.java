package net.discordservices.dservices4j;

import net.discordservices.dservices4j.exceptions.RatelimitedException;
import net.discordservices.dservices4j.requests.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Class used for posting Command information (name, description and category) to the Discordservices API.
 */
public class Commands {
    
    private final String TOKEN, id;
    private JSONArray json;
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    
    Commands(String token, String id){
        this.TOKEN = token;
        this.id = id;
        this.json = new JSONArray();
    }
    
    /**
     * Adds a command to the list using a {@link net.discordservices.dservices4j.Commands.CommandInfo CommandInfo}
     * instance.
     * 
     * @param  command
     *         The Command that should be added.
     *         
     * @return This class after the command was added. Useful for chaining.
     */
    public Commands addCommand(CommandInfo command){
        JSONObject cmdJson = new JSONObject()
                .put("command", command.getName())
                .put("desc", command.getDescription())
                .put("category", command.getCategory());
        json.put(cmdJson);
        
        return this;
    }
    
    /**
     * Adds the provided commands to the list using the provided {@link net.discordservices.dservices4j.Commands.CommandInfo CommandInfo}
     * instances.
     * 
     * @param  commands
     *         The commands to add.
     *         
     * @return This class after the command was added. Useful for chaining.
     */
    public Commands addCommands(CommandInfo... commands){
        return addCommands(Arrays.asList(commands));
    }

    /**
     * Adds the provided commands to the list using the provided List of {@link net.discordservices.dservices4j.Commands.CommandInfo CommandInfo}
     * instances.
     *
     * @param  commands
     *         The commands to add.
     *
     * @return This class after the command was added. Useful for chaining.
     */
    public Commands addCommands(List<CommandInfo> commands){
        for(CommandInfo command : commands)
            addCommand(command);

        return this;
    }
    
    /**
     * Sets the provided commands for the list using the provided {@link net.discordservices.dservices4j.Commands.CommandInfo CommandInfo}
     * instances.
     * <br><b>This will remove all previously set commands!</b>
     * 
     * @param  commands
     *         The commands to set.
     *         
     * @return This class after the commands were set. Useful for chaining.
     */
    public Commands setCommands(CommandInfo... commands){
        json = new JSONArray();
        
        return addCommands(commands);
    }

    /**
     * Sets the provided commands for the list using the provided List of {@link net.discordservices.dservices4j.Commands.CommandInfo CommandInfo}
     * instances.
     * <br><b>This will remove all previously set commands!</b>
     *
     * @param  commands
     *         The commands to set.
     *
     * @return This class after the commands were set. Useful for chaining.
     */
    public Commands setCommands(List<CommandInfo> commands){
        json = new JSONArray();
        
        return addCommands(commands);
    }
    
    /**
     * Posts the previously set commands to the Discordservices API.
     * 
     * @throws java.lang.NullPointerException
     *         When no command was previously set.
     * @throws java.io.IOException
     *         When the request wasn't successfull.
     * @throws net.discordservices.dservices4j.exceptions.RatelimitedException
     *         When the Wrapper got rate limited by the API.
     */
    public void postCommands() throws IOException, RatelimitedException{
        if(json.isEmpty())
            throw new NullPointerException("Command list may not be empty.");
        
        REQUEST_HANDLER.postCommands(id, TOKEN, json);
    }
    
    /**
     * Class used for the {@link net.discordservices.dservices4j.Commands#addCommand(CommandInfo) addCommand(...)} methods.
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
        
        protected String getName(){
            return name;
        }

        protected String getDescription(){
            return description;
        }

        protected String getCategory(){
            return category;
        }
    }
}
