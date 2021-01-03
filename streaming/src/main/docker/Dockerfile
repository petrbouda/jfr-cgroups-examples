FROM adoptopenjdk/openjdk14
WORKDIR /work/
COPY target/dependency /work/dependency
COPY target/classes /work/classes
RUN chmod 775 -R /work
ENTRYPOINT java --module-path dependency:classes --module jfr.cgroups.streaming/pbouda.jfr.cgroups.streaming.Streaming