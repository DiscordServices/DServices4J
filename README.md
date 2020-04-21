## DServices4J
DServices4J is the official library for the Discordservices.net API.  
It supports all GET and POST APIs.

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

To get an instance, first create a [DServices4J instance](#dservices4j-class) and the use the `getCommand()` method.  
```java
// instance is out DServices4J instance.
Command command = instance.getCommand();
```