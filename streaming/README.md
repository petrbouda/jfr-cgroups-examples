# Simple Streaming

```
// Build with Java 14 - JFR Streaming Feature

mvn clean package
docker build -f src/main/docker/Dockerfile -t jfr-cgroups-streaming .
docker run --cpus=0.2 -it jfr-cgroups-streaming

------------------------
 QUOTA: 20000
 PERIOD: 100000
------------------------

[periods: 293, throttled: 291, throttled-time: 75172000]
[periods: 307, throttled: 305, throttled-time: 80487000]
[periods: 307, throttled: 305, throttled-time: 80487000]
[periods: 307, throttled: 305, throttled-time: 80487000]
[periods: 313, throttled: 311, throttled-time: 82272000]
[periods: 323, throttled: 321, throttled-time: 84629000]
[periods: 333, throttled: 331, throttled-time: 86259000]
[periods: 343, throttled: 340, throttled-time: 87080000]
[periods: 348, throttled: 340, throttled-time: 87080000]
[periods: 350, throttled: 340, throttled-time: 87080000]
[periods: 356, throttled: 340, throttled-time: 87080000]
[periods: 358, throttled: 340, throttled-time: 87080000]
[periods: 362, throttled: 340, throttled-time: 87080000]
[periods: 366, throttled: 340, throttled-time: 87080000]
[periods: 372, throttled: 341, throttled-time: 87153000]
[periods: 378, throttled: 341, throttled-time: 87153000]
[periods: 380, throttled: 341, throttled-time: 87153000]
^C[periods: 388, throttled: 345, throttled-time: 87459000]
```