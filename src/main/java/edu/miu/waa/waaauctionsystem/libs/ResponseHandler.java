package edu.miu.waa.waaauctionsystem.libs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseHandler {

    public ResponseEntity<Object> response(Object data, String message, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("message", message);
        map.put("status", status);

        return new ResponseEntity<>(map, status);
    }
}
