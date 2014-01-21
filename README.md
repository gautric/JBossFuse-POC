JBossFuse-PoC
=============

Go to "project-evt" directory
```
 $> cd project-evt
 
 $> mvn clean install -Dmaven.test.skip=true
``` 

Modify etc/users.properties to activate admin=admin,admin user

Start JBossFuse
```
 $> bin/fuse

 @> features:addurl mvn:com.redhat.poc/feature-evt/0.0.1-SNAPSHOT/xml/features

 @> features:install main-evt

 @> features:install reception-evt

 @> features:install emission-evt
```

Take a look into JBossFuse log file and have fun ;-)

