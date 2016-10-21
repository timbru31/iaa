0. Install a JDK from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
1. Download eclipse IDE (neon) for Java EE Developers from http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon1a
  1. Otherwise make sure you install the Eclipse Web Developer Tools **and** Eclipse Java Web Developer Tools
2. Install Lombok for eclipse from https://projectlombok.org/download.html
3. Install Tomcat (e.g. via Homebrew or apt-get) or otherwise from Apache Tomcat website: https://tomcat.apache.org/download-80.cgi
  1. Please choose Tomcat `v8` or `v8.5`
  2. If you are running Windows, it's recommended to use the .zip instead of the setup
4. Import the project in eclipse via `Import --> Import... --> Maven --> Check out Maven Projects from SCM`
  1. If the SCM URL dropdown is empty, click the link to the `m2e Marketplace` and install the `m2e-egit` handler
5. Configure the project
  1. `Properties --> Project Facets --> Convert to faceted form...`
  2. Check `Dynamic Web Module` and version `3.1`
  3. Click `Further configuration available`
  4. Content directory has to be modified to `/src/main/webapp`
  5. Close all dialogs
  5. Open `Properties --> Deployment Assembly --> Add --> Java Build Path Entries --> Next --> Maven dependencies --> Finish`
6. Start the project via `Run As --> Run on Server`
  1. Optionally add a Tomcat v8.0 or v8.5 Server and specify the installation location

### Troubleshooting

In general it helps to refresh the dependencies:  
`Maven --> Update Project` (optionally check `Force Update of Snapshots/Releases`)
