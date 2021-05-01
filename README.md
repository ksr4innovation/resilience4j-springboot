
# Resilience4j Aspect Order demo's
# Defualt aspect order in Resilience4j retry (curcuitbraker (target)).

 In this example, the retry aspect is the first entry point. So each retry attempt would consider for the circuit breaker computation.  
 
 esilience4j.circuitbreaker:
	
    circuit-breaker-aspect-order: 2	
    instances:	
        getEmploye:		
            registerHealthIndicator: true
            slidingWindowSize: 10
            permittedNumberOfCallsInHalfOpenState: 5
            slidingWindowType: COUNT_BASED
            minimumNumberOfCalls: 4
            waitDurationInOpenState: 20s
            failureRateThreshold: 50
            eventConsumerBufferSize: 10
            record-exceptions:
            - org.springframework.web.client.ResourceAccessException
            - java.net.ConnectException
            - java.lang.RuntimeException
        
resilience4j:
  retry:
    retry-aspect-order: 4
    instances:
        getEmployetretry:
          max-retry-attempts: 3
          wait-duration:  1000

# Upon chaning the aspect  order curcuitbreaker (retry(target))).

 In this example, the circuit breaker aspect is the first entry point. So all retry attempts would consider as a single attempt for the circuit breaker computation. 
 
 esilience4j.circuitbreaker:
 
    circuit-breaker-aspect-order: 4
    instances:
        getEmploye:
            registerHealthIndicator: true
            slidingWindowSize: 10
            permittedNumberOfCallsInHalfOpenState: 5
            slidingWindowType: COUNT_BASED
            minimumNumberOfCalls: 4
            waitDurationInOpenState: 20s
            failureRateThreshold: 50
            eventConsumerBufferSize: 10
            record-exceptions:
            - org.springframework.web.client.ResourceAccessException
            - java.net.ConnectException
            - java.lang.RuntimeException
        
resilience4j:
  retry:
    retry-aspect-order: 2
    instances:
        getEmployetretry:
          max-retry-attempts: 3
          wait-duration:  1000
