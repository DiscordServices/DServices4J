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

/**
 * <h1>Installation</h1>
 * <p>Please replace {@code API_VERSION} with the latest version you can find on the
 * <a href="https://github.com/DiscordServices/DServices4J/blob/development/README.md" target="_blank">GitHub README.md</a>.
 * 
 * <h2>Gradle (recommended)</h2>
 *
 * <pre><code>
 * repositories{
 *     maven{ url = 'https://repo.codemc.io/repository/maven-public' }
 * }
 *
 * dependencies{
 *     compile 'net.discordservices:dservices4j:API_VERSION'
 * }
 * </code></pre>
 *
 * <h2>Maven</h2>
 *
 * <pre><code>{@literal
 * <repositories>
 *     <repository>
 *         <id>jcenter</id>
 *         <name>jcenter-bintray</name>
 *         <url>https://repo.codemc.io/repository/maven-public</url>
 *     </repository>
 * </repositories>
 *
 * <dependencies>
 *     <dependency>
 *         <groupId>net.discordservices</groupId>
 *         <artifactId>dservices4j</artifactId>
 *         <version>API_VERSION</version>
 *     </dependency>
 * </dependencies>
 * }</code></pre>
 *
 * <h2>Manual</h2>
 * We do not recommend using jar files directly and instead use one of the above dependency management systems.
 *
 * <p>If you still want to do it manually, or can't use one of the other option, head over to the
 * <a target="_blank" href="https://github.com/DiscordServices/DServices4J/releases/latest">GitHub releases page</a> and
 * download the jar files from there.
 *
 * <p>Note that you will not receive any support when using this method.
 * 
 * <h1>Usage</h1>
 * <p>To use the Java Wrapper, you should first get an instance of the
 * {@link net.discordservices.dservices4j.DServices4J DServices4J class} as all other classes and methods will be used
 * through it.
 * <br>Afterwards can you just use the corresponding Getter Methods to retrieve an instance of the classes you need.
 * 
 * <h2>Example</h2>
 * <pre><code>
 * // Get a DServices4J instance
 * DServices4J dservices = new DServices4J.Builder()
 *     .setToken("api.token.here")
 *     .setId("botId")
 *     .build();
 * 
 * // Get Commands instance
 * Commands commands = dservices.getCommands();
 * 
 * // Add a command to the list.
 * // The CommandInfo class can be either CommandInfo(name, description, category) or
 * // CommandInfo(name, null, category) for no description.
 * commands.addCommand(new CommandInfo("vote", "Let's you vote for the bot", "info"));
 * 
 * // Post commands
 * commands.postCommands();
 * 
 * 
 * // Get News instance
 * News news = dservices.getNews();
 * 
 * // Post news
 * news.postNews("New Command", "A vote command has been added!");
 * 
 * // Post an error/issue
 * news.postNews("Discord issues", "Discord has currently issues!", true);
 * 
 * 
 * // Get Stats instance
 * Stats stats = dservices.getStats();
 * 
 * // Post Server count
 * stats.postStats(1000);
 * 
 * // Post Server count with shard count
 * stats.postStats(3000, 3);
 * </code></pre>
 */
package net.discordservices.dservices4j;