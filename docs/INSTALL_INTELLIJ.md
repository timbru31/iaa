0. Install a [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
1. Download and install IntelliJ
2. Install [Lombok](https://projectlombok.org/download.html) for IntelliJ
3. Install [Tomcat](https://tomcat.apache.org/download-80.cgi) (e.g. via Homebrew, apt-get or Windows Installer)
  1. Please choose Tomcat `v8` or `v8.5`
4. Import the project in IntelliJ via `New --> Project from Version Version Control --> Git`
5. Configure the project
  1. `File --> Project Structure`
  2. Under `Factes` make sure you have `Spring, Struts2, Web`
  3. Click `Artifacts` and add `Web Application: Exploder` if not already existing
  4. Add the Libraries to the artifact: `Add copy of --> Library Files --> Select All --> OK`
  5. Configure Tomcat server: `Run --> Edit Configuratons... --> + --> Tomcat Server --> Local`
  6. Give it a `Name`
  7. Go to `Deployment` and add `Artifact --> iaa-multiple-choice:war exploded --> OK`
  8. **IMPORTANT** Set the `Application context` to `/iaa-multiple-choice`!
  7. Configure database URL, see `src/main/resources/application.properties` for more information
6. Start the project via `Run As --> Run`

### Troubleshooting

* No CSS or images (e.g. the welcome image)?
* Make sure to set the **Application context** to `/iaa-multiple-choice`
