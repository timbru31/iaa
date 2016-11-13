0. Install a [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
1. Download [eclipse IDE (neon) for Java EE Developers](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon1a)
2. Install [Lombok](https://projectlombok.org/download.html) for eclipse
3. Install [Tomcat](https://tomcat.apache.org/download-80.cgi) (e.g. via Homebrew, apt-get or Windows Installer)
  1. Please choose Tomcat `v8` or `v8.5`
4. Import the project in eclipse via `Import --> Import... --> Maven --> Check out Maven Projects from SCM`
  1. If the SCM URL dropdown is empty, click the link to the `m2e Marketplace` and install the `m2e-egit` handler
5. Configure the project
  1. `Properties --> Project Facets --> Convert to faceted form...`
  2. Check `Dynamic Web Module` and version `3.1`.
  3. Click `Further configuration available`. **THIS STEP IS IMPORTANT!**
  4. Content directory has to be modified to `/src/main/webapp`
  5. Close all dialogs
  6. Open `Properties --> Deployment Assembly --> Add --> Java Build Path Entries --> Next --> Maven dependencies --> Finish`
  7. Configure database URL, see `src/main/resources/application.properties` for more information
6. Start the project via `Run As --> Run on Server`
  1. Optionally add a Tomcat v8.0 or v8.5 Server and specify the installation location

### Troubleshooting

* In general it helps to refresh the dependencies:  
`Maven --> Update Project` (optionally check `Force Update of Snapshots/Releases`)
* No Servers? No Project Facets? No Dynamic Web Module? Make sure to use eclipse IDE for **Java EE Developers**
  * You might want to install the packages `Eclipse Web Developer Tools`, `Eclipse Java Web Developer Tools`, `m2e-wtp - Maven integration for WTP`, `WST Server Adapters`
