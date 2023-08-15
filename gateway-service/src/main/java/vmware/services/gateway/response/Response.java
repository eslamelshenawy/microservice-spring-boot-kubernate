package vmware.services.gateway.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
@Builder
public class Response<T> implements Serializable {

    private T data;
    private int ResponseCode;
    private String ResponseMessage;
}
