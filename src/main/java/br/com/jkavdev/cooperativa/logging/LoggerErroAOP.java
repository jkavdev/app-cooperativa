package br.com.jkavdev.cooperativa.logging;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerErroAOP {

	private Logger logger = LoggerFactory.getLogger(LoggerErroAOP.class);

	@AfterThrowing(pointcut = "within(br.com.jkavdev.cooperativa..*)", throwing = "e")
	public void logAfterException(JoinPoint jp, Exception e) {
		logger.error(ExceptionUtils.getStackTrace(e));
	}

}
