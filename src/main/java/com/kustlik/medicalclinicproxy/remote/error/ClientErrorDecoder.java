package com.kustlik.medicalclinicproxy.remote.error;

import com.kustlik.medicalclinicproxy.remote.exception.BadRequestException;
import com.kustlik.medicalclinicproxy.remote.exception.InternalServerErrorException;
import com.kustlik.medicalclinicproxy.remote.exception.ObjectNotFoundException;
import com.kustlik.medicalclinicproxy.remote.exception.ServiceUnavailableException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException feignException = FeignException.errorStatus(methodKey, response);
        String message = feignException.getMessage();
        return switch (response.status()) {
            case 400 -> new BadRequestException(message != null ? message :
                    "Bad Request.");
            case 404 -> new ObjectNotFoundException(message != null ? message :
                    "Object not found.");
            case 500 -> new InternalServerErrorException(message != null ? message :
                    "The server has encountered a situation it does not know how to handle.");
            case 503 -> new ServiceUnavailableException(message != null ? message :
                    "The request method is not supported by the server and cannot be handled.");
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}
