
# This repository pertains to the blow demos:

# Defualt aspect order in Resilience4j retry (curcuitbraker (target)).

 In this example, the retry aspect is the first entry point. So each retry attempt would consider for the circuit breaker computation.  

# Upon chaning the aspect  order curcuitbreaker (retry(target))).

 In this example, the circuit breaker aspect is the first entry point. So all retry attempts would consider as a single attempt for the circuit breaker computation. 
