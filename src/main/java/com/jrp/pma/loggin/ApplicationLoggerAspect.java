package com.jrp.pma.loggin;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class ApplicationLoggerAspect {
//  private final Logger log = LoggerFactory.getLogger(this.getClass());
//  
//  @Pointcut("within(com.jrp.pma.controllers..*)")
//  public void definePackagePointcuts() {
//	  //empty method to name the location specified in the pointcut
//	  
//  }
//  
//  @Before("definePackagePointcuts()")
//  public void logBefore(JoinPoint jp) {
//	  log.debug("\n \n \n");
//	  log.debug(("******** Before Method Execution ********"),
//	  jp.getSignature().getDeclaringTypeName(),
//	  jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//	  log.debug("_________________________________________________ \n \n \n");
//  }
//}
