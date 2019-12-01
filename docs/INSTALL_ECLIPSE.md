1. Install a [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) (Minimum LTS 11)
1. Download [eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/release/2019-09/r/eclipse-ide-enterprise-java-developers)
1. Install [Lombok](https://projectlombok.org/download.html) for eclipse
1. Install [Tomcat](https://tomcat.apache.org/download-90.cgi) (e.g. via Homebrew, apt-get or Windows Installer)
1. Please choose Tomcat `v9.0`
1. Import the project in eclipse via `Import --> Import... --> Maven --> Check out Maven Projects from SCM`
1. If the SCM URL dropdown is empty, click the link to the `m2e Marketplace` and install the `m2e-egit` handler
1. Configure the project
1. `Properties --> Project Facets --> Convert to faceted form...`
1. Check `Dynamic Web Module` and version `4.0`.
1. Close all dialogs
1. Open `Properties --> Deployment Assembly --> Add --> Java Build Path Entries --> Next --> Maven dependencies --> Finish`
1. Configure database URL, see `src/main/resources/application.properties` for more information
1. Start the project via `Run As --> Run on Server`
1. Optionally add a Tomcat v9 Server and specify the installation location

### Troubleshooting

- In general it helps to refresh the dependencies:  
  `Maven --> Update Project` (optionally check `Force Update of Snapshots/Releases`)
- No Servers? No Project Facets? No Dynamic Web Module? Make sure to use eclipse IDE for **Java EE Developers**
  - You might want to install the packages `Eclipse Web Developer Tools`, `Eclipse Java Web Developer Tools`, `m2e-wtp - Maven integration for WTP`, `WST Server Adapters`
