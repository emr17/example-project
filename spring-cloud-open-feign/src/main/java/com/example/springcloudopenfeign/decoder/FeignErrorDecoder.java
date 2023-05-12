package com.example.springcloudopenfeign.decoder;

import com.example.springcloudopenfeign.exceptions.AccessDeniedException;
import com.example.springcloudopenfeign.exceptions.ApiRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder  implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 400:
                return new ApiRequestException("Bad request");
            case 403:
                return new AccessDeniedException("Access denied");
            default:
                return new Exception("Library generic exception");
        }
    }
}
