# Developer Tool Guides for Gradle-based Spring Applcation
For fast pace file changes and testing during Integration and development use Gradle Continuous build and Spring Boot DevTools.

## Gradle Continuous Build 
Other than the main Gradle build terminal where you have the gradle command running like this `gradle bootRun` open another second terminal in parallel, on the second terminal go to the root of the User Profile service directory and run the Gradle continuous build task as show below.

Reference documentation

[Gradle Java Plugin "gradle classes" task](https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_tasks)

[Gradle Continuous Build using -t switch](https://docs.gradle.org/current/userguide/continuous_build.html)

     $ gradle classes -t

     Continuous build is an incubating feature.
     BUILD SUCCESSFUL
     Total time: 3.939 secs
     Waiting for changes to input files of tasks... (ctrl-d to exit)

[Back to README](../README.md)

----------

## Spring Boot DevTools

To add Spring Boot dev tools in to the project simply add the module dependency using the below code. In case of user profile service add it to the `<project-dir>/gradle/dependencies.gradle` file as show below. 

Reference documentation:

[Spring Boot Hotswapping](http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#using-boot-hot-swapping)

     compile "org.springframework.boot:spring-boot-devtools"

[Back to README](../README.md)

----------

## For Static Resource File

To reload static file during development add the below line to the build.gradle file. In the case of user profile service add it to the `/<project-dir>/build.gradle` file

Reference documentation

[Running a project in place](http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#build-tool-plugins-gradle-running-applications)

     bootRun {
         addResources = true
     }

[Back to README](../README.md)

----------

## Remote Debugging

Use the Gradle Application plugin to create the JVM environment to manage locally. Add the below lines to the build.gradle file. Restart the build `gradle bootRun` and note that the `suspend=n` and hence the build/server startup won't wait but your eclipse will be able to connect once you start the debug in Eclipse with the port given below, i.e 5005. 

Reference Documentation

[Gradle Application Plugin](https://docs.gradle.org/current/userguide/application_plugin.html)

[Eclipse Remote Debug Setup](https://dzone.com/articles/how-debug-remote-java-applicat)
 
     apply plugin: 'application'

     applicationDefaultJvmArgs = [ 
         "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
     ]

If you are executing the artifact directly using a Java command you can pass the debug option as a command line parameter like this `java -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y -jar ./<project-dir>/build/libs/<project-name>.war`

[Back to README](../README.md)

----------