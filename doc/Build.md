## Prerequisites 
Project uses Gradle as the build tool `gradle -v`. The project is set for java compatibility of version 1.8 inside the gradle script as shown below.
    
    sourceCompatibility = 1.8

The above gradle setting enables the build script to use the mentioned Java version to use when compiling Java source. So 1.8 is the recommended JVM to use.

[Back to README](../README.md)

---

## Build the Artifact

Go to the root of the project folder where the code is checked out and simply execute `gradle build`. This will get the code compiled and assembles the artifacts and executed the test (Unit test, Check Style and PMD) and generates the coverage report (Jacoco).

[Back to README](../README.md)

---

## Executing the Unit Test

To execute the test seperately,  simply execute `gradle check`. This will get the Unit test, Check Style and PMD executed and will generate the coverage report. The reports can be found inside the the reports directory as listed below

<pre>
Jacoco coverage report: ./build/reports/jacoco/test/html/index.html
Check style report: ./build/reports/checkstyle/test.html
PMD report: ./build/reports/pmd/test.html
Unit test report: ./build/reports/tests/test/index.html
</pre>

[Back to README](../README.md)

---

