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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Class used for posting Command information (name, description and category) to the Discordservices API.
 */
public class Commands{
    
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
     * <h2>Rate Limits</h2>
     * <p>By default will the Wrapper only perform one request per 15 seconds per endpoint.
     * <br>If however, the Request either isn't successful or gets rate limited will those errors be logged in your terminal.
     * 
     * @throws java.lang.NullPointerException
     *         When no command was previously set.
     */
    public void postCommands(){
        if(json.isEmpty())
            throw new NullPointerException("Command list may not be empty.");
        
        REQUEST_HANDLER.postCommands(id, TOKEN, json);
    }
    
    /**
     * Class used for the {@link net.discordservices.dservices4j.Commands#addCommand(CommandInfo) addCommand(...)} methods.
     * <br>You set the name, description and category through the constructor.
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
         * 
         * <p>You can provide {@code null} for the description and category to use their respective default value.
         * <br>A null description is equal to using an empty String and a null category will be treated as category being
         * {@code all}.
         * 
         * @param name
         *        Name of the command. Can't be null.
         * @param description
         *        Description of the command. Can be set to {@code null} for no description.
         * @param category
         *        Category of the command. Can be set to {@code null} for category All.
         * 
         * @see net.discordservices.dservices4j.Commands#addCommand(CommandInfo)
         * @see net.discordservices.dservices4j.Commands#addCommands(CommandInfo...)
         * @see net.discordservices.dservices4j.Commands#addCommands(List)
         * @see net.discordservices.dservices4j.Commands#setCommands(CommandInfo...)
         * @see net.discordservices.dservices4j.Commands#setCommands(List)
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
