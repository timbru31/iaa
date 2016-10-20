0. Install a JDK from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
1. Download eclipse IDE (neon) for Java EE Developers from http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon1a
2. Install Lombok for eclipse from https://projectlombok.org/download.html
3. Install Tomcat (e.g. via Homebrew or apt-get) or otherwise from Apache Tomcat website: https://tomcat.apache.org/download-80.cgi
   1. Please choose Tomcat `v8` or `v8.5`
4. Import the project in eclipse via `Import --> Import... --> Maven --> Check out Maven Projects from SCM`
   1. If the SCM URL dropdown is empty, click the link to the `m2e Marketplace` and install the `m2e-egit` handler
5. Configure the project
   1. `Properties --> Project Facets --> Convert to faceted form...`
   2. Choose `Dynamic Web Module 3.1`
   3. Choose `Further configuration available`
   4. Content directory: `/src/main/webapp`
   5. `Properties --> Deployment Assembly --> Add --> Java Build Path Entries --> Next --> Maven dependencies --> Finish`
6. Run As --> Run on Server
   1. Optionally add a Tomcat v8.0 or v8.5 Server and specify the installation location



### Troubleshooting

In general it helps to refresh the dependencies:  
`Maven --> Update Project (optionally check Force Update of Snapshots/Releases`
