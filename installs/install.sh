mvn install:install-file -Dfile=jlc-1.4.6.jar -DpomFile=jlc-1.4.6.pom.xml

mvn install:install-file -Dfile=se.skillytaire.didactic.generator-1.0.1.jar -DpomFile=se.skillytaire.didactic.generator-1.0.1.pom.xml
mvn install:install-file -Dfile=se.skillytaire.didactic.annotation.fluent-1.0.1.jar -DpomFile=se.skillytaire.didactic.annotation.fluent-1.0.1.pom.xml



mvn install:install-file -Dfile=se.skillytaire.java.datatype-1.0.0.jar -DpomFile=datatype-pom.xml
mvn install:install-file -Dfile=se.skillytaire.java.datatype-1.0.0-sources.jar -DgroupId=se.skillytaire.java -DartifactId=datatype -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=sources
mvn install:install-file -Dfile=se.skillytaire.java.datatype-1.0.0-javadoc.jar -DgroupId=se.skillytaire.java -DartifactId=datatype -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=javadoc


mvn install:install-file -Dfile=se.skillytaire.service.wheater.api-1.0.1.jar -DpomFile=wheater.api-pom.xml
mvn install:install-file -Dfile=se.skillytaire.service.wheater.api-1.0.1-sources.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=api -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=sources
mvn install:install-file -Dfile=se.skillytaire.service.wheater.api-1.0.1-javadoc.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=api -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=javadoc
mvn install:install-file -Dfile=se.skillytaire.service.wheater.klart-1.0.1.jar -DpomFile=wheater.klart-pom.xml
mvn install:install-file -Dfile=se.skillytaire.service.wheater.klart-1.0.1-sources.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=klart -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=sources
mvn install:install-file -Dfile=se.skillytaire.service.wheater.klart-1.0.1-javadoc.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=klart -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=javadoc
