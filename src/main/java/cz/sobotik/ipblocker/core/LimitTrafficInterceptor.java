package cz.sobotik.ipblocker.core;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cz.sobotik.ipblocker.api.service.LimitTrafficService;
import cz.sobotik.ipblocker.core.rest.converter.StringToInetAddressConverter;

/**
 * Every facade method can be invoked with limited traffic rules
 * rules are defined in separate {@link LimitTrafficService} service which handle it
 * when traffic limit is exceeded server returns "429 Too Many Requests" error
 *
 */
@Aspect
public class LimitTrafficInterceptor {

  @Autowired
  StringToInetAddressConverter stringToInetAddressConverter;

  @Autowired
  LimitTrafficService limitTraficService;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private HttpServletResponse response;

  private static final Logger log = LoggerFactory.getLogger(LimitTrafficInterceptor.class);

  @Around("@annotation(cz.sobotik.ipblocker.core.annotation.LimitTraffic)")
  public Object initiateLimitTraffic(ProceedingJoinPoint joinPoint) throws Throwable {

    if (!limitTraficService.isValid(stringToInetAddressConverter.convert(request.getRemoteAddr()))) {
      log.debug("Limit for IP: {} has exeeded", request.getRemoteAddr());
      response.sendError(429, "Too Many Requests from address: " + request.getRemoteAddr());
      return null;
    }

    return joinPoint.proceed();

  }

}
