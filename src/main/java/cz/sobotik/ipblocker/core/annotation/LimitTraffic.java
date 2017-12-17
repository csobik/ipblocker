package cz.sobotik.ipblocker.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cz.sobotik.ipblocker.api.service.LimitTrafficService;

/**
 * Every facade method can be invoked with limited traffic rules
 * rules are defined in separate {@link LimitTrafficService} service which handle it
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LimitTraffic {
}