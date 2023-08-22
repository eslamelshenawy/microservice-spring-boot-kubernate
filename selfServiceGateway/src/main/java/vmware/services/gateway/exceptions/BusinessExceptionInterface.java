package vmware.services.gateway.exceptions;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionInterface {
	String getErrorMessage();
	String getErrorCode();
	HttpStatus getHttpStatus();
}
