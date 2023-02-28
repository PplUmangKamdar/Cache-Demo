## Uploading Artifacts to Repository

### Repository Credentials

The Pearson Realize Nexus repository requires credentials for any artifact upload. The repositories are configured in the `grable.build` file as shown below. 

    name = "Realize Nexus Snapshots"
    url = "${ltgRealizeNexusURL}/nexus/content/repositories/snapshots"    
    credentials {
       username "${ltgRealizeNexusUsername}"
       password "${ltgRealizeNexusPassword}"
    }

The properties mentioned above are configured in `~/.gradle/gradle.properties` file as show below.

    ltgRealizeNexusURL=http://nexus.realizedev-test.com
    ltgRealizeNexusUsername=deployment
    ltgRealizeNexusPassword=**********

The Gradle task for uploading is as show below.

```gradle
uploadArchives {
   repositories {
       mavenDeployer {
           repository(url: "http://nexus.realizedev-test.com/nexus/content/repositories/releases") {
               authentication(userName: ltgRealizeNexusUsername, password: ltgRealizeNexusPassword);
           }
           snapshotRepository(url: 'http://nexus.realizedev-test.com/nexus/content/repositories/snapshots') {
               authentication(userName: ltgRealizeNexusUsername, password: ltgRealizeNexusPassword);
           }
           repository(url: mavenLocal().url)
       }
   }
}
```

[Back to README](../README.md)

---

### Upload Task

To upload the artifacts to the remote Nexus repository simply execute the below Gradle task:

    gradle uploadArchives

Depending on the configured group name and the version as show below the artifact is generated

    group = 'com.pearson.ltg.rbs.CacheDemo-service'
    version ='1.0.0-SNAPSHOT'

Generates the artifact as `CacheDemo-service-1.0.0-SNAPSHOT.war` inside the `./build/libs` directory. Also the generated artifacts are uploaded/updated on the remote repository. With the right credentials you should be able to see/browse the uploaded artifacts in the nexus repository. For example for the above mentioned group and version you should see the files uploaded/updated at the below location

[CacheDemo-service/1.0.0-SNAPSHOT](https://nexus.realizedev-test.com/nexus/content/repositories/snapshots/com/pearson/ltg/rbs/CacheDemo-service/1.0.0-SNAPSHOT/)

<pre>
https://nexus.realizedev-test.com/nexus/content/repositories/snapshots/com/pearson/ltg/rbs/CacheDemo-service/1.0.0-SNAPSHOT
</pre>

[Back to README](../README.md)

---

