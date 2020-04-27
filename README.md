## DServices4J
DServices4J is the official Java library for the Discordservices.net API.  
It supports all GET and POST APIs of the site.

### Download
Coming soon.

### DServices4J Class
The DServices4J class is the main class for everything.  
It is used to set the API token and to access the various API endpoints.

To get an instance of it, just do this:  
```java
String token = /* API token */
DServices4J instance = new DServices4J(token);
```

### Commands
DServices4J has a `Command` class to set the commands of your bot and posting them to the corresponding API.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and then use the `getCommand()` method.  
```java
// instance is our DServices4J class.
Command command = instance.getCommand();
```

### Stats
The `Stats` class is the main class to post your bot's statistic with.  
As of now does the class offer `postStats(long)` for Guilds and a single shard and `postStats(long, long)` for Guilds with multiple shards.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and the use the `getStats()` method.  
```java
// instance is our DServices4J class.
Stats stats = instance.getStats();
```

### News
The `News` class is used to post announcements about your bot.  
These announcements can be both normal or error/issue ones.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and the use the `getNews()` method.  
```java
// instance is our DServices4J class.
News news = instance.getNews();
```