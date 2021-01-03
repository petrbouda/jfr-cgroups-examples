FROM adoptopenjdk/openjdk15
WORKDIR /work/
COPY target/application.jar /work/application.jar
COPY custom.jfc /work/custom.jfc
RUN chmod 775 -R /work
ENTRYPOINT java -Xmx300m -Xms300m -XX:+UseSerialGC -XX:StartFlightRecording=duration=180s,settings=/work/custom.jfc,filename=/tmp/spring.jfr -jar application.jar