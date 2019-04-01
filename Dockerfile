#Docker server Java

FROM java:8

ADD docker /opt/src/

WORKDIR /opt/src/

ENTRYPOINT ["java","-jar","MockMock-1.4.0.one-jar.jar", "-p", "2525"]

