call mvn install:install-file -Dfile=jlc-1.4.7.jar -DpomFile=jlc-1.4.7.pom.xml
call mvn install:install-file -Dfile=se.skillytaire.course.tools.junit.jlc.processor-1.4.7.jar -DpomFile=se.skillytaire.course.tools.junit.jlc.processor-1.4.7.pom.xml

call mvn install:install-file -Dfile=se.skillytaire.didactic.generator-1.0.2.jar -DpomFile=se.skillytaire.didactic.generator-1.0.2.pom.xml
call mvn install:install-file -Dfile=se.skillytaire.didactic.annotation.fluent-1.0.2.jar -DpomFile=se.skillytaire.didactic.annotation.fluent-1.0.2.pom.xml

call mvn install:install-file -Dfile=se.skillytaire.java.datatype-1.0.0.jar -DpomFile=se.skillytaire.java.datatype-1.0.0-pom.xml
call mvn install:install-file -Dfile=se.skillytaire.java.datatype-1.0.0-sources.jar -DgroupId=se.skillytaire.java -DartifactId=datatype -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=sources
call mvn install:install-file -Dfile=se.skillytaire.java.datatype-1.0.0-javadoc.jar -DgroupId=se.skillytaire.java -DartifactId=datatype -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=javadoc

call mvn install:install-file -Dfile=se.skillytaire.service.wheater.api-1.0.1.jar -DpomFile=se.skillytaire.service.wheater.api-1.0.1-pom.xml
call mvn install:install-file -Dfile=se.skillytaire.service.wheater.api-1.0.1-sources.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=api -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=sources
call mvn install:install-file -Dfile=se.skillytaire.service.wheater.api-1.0.1-javadoc.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=api -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=javadoc
call mvn install:install-file -Dfile=se.skillytaire.service.wheater.klart-1.0.1.jar -DpomFile=se.skillytaire.service.wheater.klart-1.0.1-pom.xml
call mvn install:install-file -Dfile=se.skillytaire.service.wheater.klart-1.0.1-sources.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=klart -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=sources
call mvn install:install-file -Dfile=se.skillytaire.service.wheater.klart-1.0.1-javadoc.jar -DgroupId=se.skillytaire.service.wheater -DartifactId=klart -Dversion=1.0.0 -Dpackaging=jar -Dclassifier=javadoc

call mvn install:install-file -Dfile=Builder-0.9.8.jar -DpomFile=Builder-0.9.8.pom.xml
call mvn install:install-file -Dfile=Builder-0.9.8-sources.jar -DgroupId=com.rvlstudio -DartifactId=Builder -Dversion=0.9.8 -Dpackaging=jar -Dclassifier=sources
call mvn install:install-file -Dfile=Builder-0.9.8-javadoc.jar -DgroupId=com.rvlstudio -DartifactId=Builder -Dversion=0.9.8 -Dpackaging=jar -Dclassifier=javadoc