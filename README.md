[bintray]: https://bintray.com/andre601/maven/DServices4J
[badge]: https://api.bintray.com/packages/andre601/maven/DServices4J/images/download.svg

[Discord]: https://discord.gg/cpmXFsz

[Javadoc]: https://discordservices.github.io/DServices4J
[Releases]: https://github.com/DiscordServices/DServices4J/releases

# DServices4J
DServices4J is the official Java library for the Discordservices.net API.  
It supports all GET and POST APIs of the site.

## Download
> Latest Release:  
> [![badge]][bintray]

You can use the latest version (displayed) above by using either Maven or Gradle for your project.  
Alternatively can you also download the individual jar-files from the GitHub releases.

### Gradle
Please replace `{version}` with the above displayed version:  
```groovy
repositories{
  jcenter()
}

dependencies{
  implementation 'net.discordservices:DServices4J:{version}'
}
```

### Maven
Please replace `{version}` with the above displayed version:  
```xml
<repositories>
  <repository>
    <id>Bintray</id>
    <url>https://jcenter.bintray.com</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>net.discordservices</groupId>
    <artifactId>DServices4J</artifactId>
    <version>{version}</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

## DServices4J Class
The DServices4J class is the main class for everything.  
It is used to set the API token and to access the various API endpoints.

To get an instance of it, just do this:  
```java
String token = /* API token */
String id = /* Bot id */
DServices4J instance = new DServices4J(token, id);
```

## Commands Class
DServices4J has a `Commands` class to set the commands of your bot and posting them to the corresponding API.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and then use the `getCommands()` method.  
```java
// instance is our DServices4J class.
Command command = instance.getCommands();
```

## Stats Class
The `Stats` class is the main class to post your bot's statistic with.  
As of now does the class offer `postStats(long)` for Guilds and a single shard and `postStats(long, long)` for Guilds with multiple shards.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and the use the `getStats()` method.  
```java
// instance is our DServices4J class.
Stats stats = instance.getStats();
```

### News Class
The `News` class is used to post announcements about your bot.  
These announcements can be both normal or error/issue ones.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and the use the `getNews()` method.  
```java
// instance is our DServices4J class.
News news = instance.getNews();
```

## Additional Links
- [Discordservices Discord][Discord]
- [Javadoc]
- [Releases]