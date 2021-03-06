[bintray]: https://ci.codemc.io/job/DiscordServices/job/DServices4J/lastSuccessfulBuild/
[badge]: https://img.shields.io/nexus/maven-public/net.discordservices/dservices4j?label=Release&server=https%3A%2F%2Frepo.codemc.io&style=plastic

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
  maven{ url = 'https://repo.codemc.io/repository/maven-public' }
}

dependencies{
  implementation 'net.discordservices:dservices4j:{version}'
}
```

### Maven
Please replace `{version}` with the above displayed version:  
```xml
<repositories>
  <repository>
    <id>codemc</id>
    <url>https://repo.codemc.io/repository/maven-public</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>net.discordservices</groupId>
    <artifactId>dservices4j</artifactId>
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
String token = /* Your Discord Services API token */
String id = /* The ID of your Bot */
DServices4J instance = new DServices4J().Builder()
        .setToken(token)
        .setId(id)
        .build();
```

## Commands Class
DServices4J has a `Commands` class to set the commands of your bot and posting them to the corresponding API.

To get an instance, first create a [DServices4J instance](#dservices4j-class) and then use the `getCommands()` method.  
```java
// instance is our DServices4J class.
Commands commands = instance.getCommands();
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
