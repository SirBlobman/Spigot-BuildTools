# Spigot BuildTools Fork by SirBlobman

Custom fork of md_5's SpigotMC BuildTools that adds support for deploying jars to maven repositories.


## Build Requirements

- Java 11
- Apache Maven

## New Command Line Options

| Option               | Description                                 |
|----------------------|---------------------------------------------|
| --deploy-id <String> | ID of the maven repository to deploy jars.  |
| --deploy-url <URL>   | URL of the maven repository to deploy jars. |

Default code still uses 'mvn install'. 
Options `deploy-id` and `deploy-url` are both required to deploy to maven repositories.
