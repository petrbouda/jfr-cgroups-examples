# Heavy Spring Application

The intention is to simulate a heavy application startup which needs to 
initialize a lot of modules before the application is ready to accept
requests. It shows how the application can be throttled during the startup
procedure.

- Servlets
- MongoDB Connections
- Actuator (scraping from Prometheus)
- Swagger

### Test Application

Build a Docker Image
```
docker build -f src/main/docker/Dockerfile -t spring-example .
```

Run the Application
```
docker run -it --cpus="1" --memory="500m" --memory-swap="500m" --network host -v /var/run/docker.sock:/var/run/docker.sock -v ${PWD}:/tmp  spring-example
```

```
Docker 1.13 and higher:
docker run -it --cpus=".5" ubuntu /bin/bash

Docker 1.12 and lower:
docker run -it --cpu-period=100000 --cpu-quota=50000 ubuntu /bin/bash
```

### Start load

```
java -jar ../spring-client/target/spring-client-1.0-SNAPSHOT.jar
```

