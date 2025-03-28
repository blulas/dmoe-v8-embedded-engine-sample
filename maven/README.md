# Maven Repository
BAMOE relies on Apache Maven for compilation, testing, and deployment of applications.  It is necessary to have a fully configured instance of Maven on your local development machine, or in the alternative, via a Maven server.  Each application will contain a Maven `pom.xml` file that describes all project dependencies, etc.

## Configuring a local Maven Repository 
You can download and configure the BAMOE Maven repository as a compressed file. The BAMOE Maven repository contains the libraries that Java developers need to build BAMOE applications.  For convience, the offline repository files are provided with the product binaries.  To configure the BAMOE Maven repository locally follow these steps:

1.  Download the BAMOE v8.0.x Maven Repository (bamoe-8.0.7-maven-repository.zip).
2.  Extract the downloaded archive in your local `~/.m2/` directory and open the Maven settings.xml file in a text editor or IDE.
4.  Add the following lines to the <profiles> element of the Maven settings.xml file, where <MAVEN_REPOSITORY> is the path of the Maven repository that you downloaded. The format of <MAVEN_REPOSITORY> must be file://$PATH. For example, file:///home/userX/bamoe-8.0.7-maven-repository/:.

```xml
```

5.  Add the following lines to the <activeProfiles> element of the Maven settings.xml file and save the file:

```xml
<settings xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/SETTINGS/1.0.0" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <localRepository>${user.home}/.m2/repository</localRepository>
    <profiles>
        <profile>
            <id>ibm-bamoe-v8-enterprise-maven-repository</id>
            <repositories>
                <repository>
                    <id>ibm-bamoe-v8-enterprise-maven-repository</id>
                    <url>https://maven.repository.redhat.com/ga/</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>

            <pluginRepositories>
                <pluginRepository>
                    <id>ibm-bamoe-v8-enterprise-maven-repository</id>
                    <url>https://maven.repository.redhat.com/ga/</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </pluginRepository>
            </pluginRepositories>
        </profile>

        <!-- BAMOE 8.0.6 via Offline -->
        <profile>
            <id>ibm-bamoe-v806-offline-maven-repository</id>
            <repositories>
                <repository>
                    <id>ibm-bamoe-v806-offline-maven-repository</id>
                    <url>file:///Users/${env.USER}/.m2/bamoe-8.0.6.GA-maven-repository</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>ibm-bamoe-v806-offline-maven-repository</id>
                    <url>file:///Users/${env.USER}/.m2/bamoe-8.0.6.GA-maven-repository</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                    </pluginRepository>
            </pluginRepositories>
        </profile>

        <!-- BAMOE 8.0.7 via Offline -->
        <profile>
            <id>ibm-bamoe-v807-offline-maven-repository</id>
            <repositories>
                <repository>
                    <id>ibm-bamoe-v807-offline-maven-repository</id>
                    <url>file:///Users/${env.USER}/.m2/bamoe-8.0.7.GA-maven-repository</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>ibm-bamoe-v807-offline-maven-repository</id>
                    <url>file:///Users/${env.USER}/.m2/bamoe-8.0.7.GA-maven-repository</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                    </pluginRepository>
            </pluginRepositories>
        </profile>
    </profiles>

    <activeProfiles>
        <activeProfile>ibm-bamoe-v8-enterprise-maven-repository</activeProfile>
        <activeProfile>ibm-bamoe-v806-enterprise-offline-maven-repository</activeProfile>
<!--    <activeProfile>ibm-bamoe-v807-enterprise-offline-maven-repository</activeProfile> -->
    </activeProfiles>
</settings>
```

If your Maven repository contains outdated artifacts, you might encounter Maven error messages when you build or deploy the project, such as:

-  Missing artifact <PROJECT_NAME>
- [ERROR] Failed to execute goal on project <ARTIFACT_NAME>; Could not resolve dependencies for <PROJECT_NAME>

To resolve these issues, delete the cached version of your local repository that is located in the ~/.m2/repository directory to force a download of the latest Maven artifacts.

Your Maven repository is now properly configured and ready to build BAMOE based projects using a standard Maven workflow.



