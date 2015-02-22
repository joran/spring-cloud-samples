# spring-cloud-samples

This is just another collection of simple Spring Cloud application samples

## Hystrix
Hystrix contains a small sample of Netflix Hystrix Dashboard and a sample application using Netflix Hystrix Circuit Breaker


1. Start the dashboard:

```
cd hystrix/dashboard
gradlew bootRun
```

This will start the dashboard application and make it available on `http://localhost:7979`

2. Start the sample application:

```
cd hystrix/circuit-breaker
gradlew bootRun
```

This will start the sample application and make it available on `http://localhost:8080`
The sample application exposes some services through REST:
* `http://localhost:8080/` - this service always returns "OK" and never fails
* `http://localhost:8080/fail` - this service always fails and will call the fallback service, witch returns "from the fallback"
* `http://localhost:8080/unstable` - this service will normally return "OK", but occasionally it will fail and call the fallback service, witch return "from fallback"
* `http://localhost:8080/slow` - this service will always return "OK", but the response time will be between 0 and 1000 ms.
* `http://localhost:8080/hystrix.stream` - this service exposes the hystrix event stream and can be consumed by the dashsboard


3. Goto http://localhost:7979 with a browser and enter `http://localhost:8080/hystrix.stream` to monitor the sample application.

4. Make some calls to the sample aplication services and watch the dashboard
```
curl http://localhost:8080/
curl http://localhost:8080/fail
http://localhost:8080/slow
for x in {1..100}; do sleep 3 curl localhost:8080/unstable ;done
```
