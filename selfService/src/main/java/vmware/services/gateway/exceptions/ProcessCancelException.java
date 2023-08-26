package vmware.services.gateway.exceptions;

public class ProcessCancelException extends Exception {

    public ProcessCancelException(ErrorCodes msg){
        super(msg.getValue());
    }
}
