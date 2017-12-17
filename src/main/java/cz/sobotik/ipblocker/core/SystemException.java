package cz.sobotik.ipblocker.core;

/**
 * Internal server exception
 * actually used when IPv6 address is given as parameter or
 * ServletRequest.getRemoteAddr() gives that
 *
 */
public class SystemException extends RuntimeException {

  private static final long serialVersionUID = -8155545356549000928L;
  private final String code;

  public SystemException(String code, String message) {
    super(message);
    this.code = code;
  }

  public SystemException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }
}
