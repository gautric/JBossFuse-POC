JBossFuse-PoC
=============

Go to "project-evt" directory
```
 $> cd project-evt
 
 $> mvn clean install -Dmaven.test.skip=true
``` 

Configure Derby Database outside JBossFuse

 Download db-derby-10.10.1.1

and start it like this :
bin/startNetworkServer

```
 $> bin/startNetworkServer
 
``` 

Create SCHEMA Event (via ij)

```
 derby> CREATE SCHEMA EVENT;
 derby> SET SCHEMA EVENT;
 derby> CREATE TABLE Event (id VARCHAR(255) NOT NULL, author VARCHAR(255), creationTime TIMESTAMP, geo VARCHAR(255), message VARCHAR(255), state VARCHAR(20), system VARCHAR(255), type VARCHAR(255), updateTime TIMESTAMP, PRIMARY KEY (id));
 
``` 

Modify etc/users.properties to activate admin=admin,admin user

Start JBossFuse
```
 $> bin/fuse

 @> features:addurl mvn:com.redhat.poc/feature-evt/0.0.4-SNAPSHOT/xml/features

 @> features:install main-evt

 @> features:install reception-evt

 @> features:install emission-evt

 @> features:install full-evt // for all features

```

Connect to Karaf console

```
 @> camel:route-start timer.evenement.route

```

Connect to WebEvent Controler

```
 http://localhost:8181/event.web/event.html
```

Take a look into JBossFuse log file and have fun ;-)

