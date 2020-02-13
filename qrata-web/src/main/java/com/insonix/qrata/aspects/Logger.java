package com.insonix.qrata.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component("loggerAspect")
public class Logger {
	 @Around("execution(public * com.insonix.qrata.service.*.*(..))")
	    public Object object(ProceedingJoinPoint pjp) throws Throwable {
		 org.apache.log4j.Logger log =  org.apache.log4j.Logger.getLogger("com.insonix.qrata.service");

			log.info("STARTED method-name : "+ pjp.getSignature().getName()+"| class-name : "+pjp.getTarget().getClass());
		
	        Object retVal = pjp.proceed();
	        log.info("ENDED method-name : "+ pjp.getSignature().getName()+"| class-name : "+pjp.getTarget().getClass());
        return retVal;
	    }
	 @Around("execution(public * com.insonix.qrata.dao.*.*(..))")
	    public Object in(ProceedingJoinPoint pjp) throws Throwable {
		 org.apache.log4j.Logger log =  org.apache.log4j.Logger.getLogger("com.insonix.qrata.service");
			log.info("STARTED method-name : "+ pjp.getSignature().getName()+"| class-name : "+pjp.getTarget().getClass());
		
	        Object retVal = pjp.proceed();
	        log.info("ENDED method-name : "+ pjp.getSignature().getName()+"| class-name : "+pjp.getTarget().getClass());
     return retVal;
	    }
	

}
