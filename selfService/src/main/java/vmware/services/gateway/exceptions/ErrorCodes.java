package vmware.services.gateway.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ErrorCodes {
    org$0001("No Organization found with Id !"),
    U$0001("this email used  !");

    @JsonValue
    private final String value;

    @JsonCreator
    ErrorCodes(String value) {
        this.value = value;
    }
}
