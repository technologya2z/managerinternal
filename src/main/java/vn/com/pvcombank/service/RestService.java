package vn.com.pvcombank.service;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.com.pvcombank.domain.response.ResponseApi;

@Service
public class RestService {

    private final Logger log = LoggerFactory.getLogger(RestService.class);

    @Autowired
    RestTemplate restTemplate;

    HttpHeaders headers;
    HttpEntity<String> entity;

    public RestService() {
        this.headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.entity = new HttpEntity<String>(headers);
    }

    public ResponseApi getRestRequest(String apiUrl) {
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        ResponseApi responseApi = new ResponseApi(response.getStatusCodeValue(), response.getBody());
        return responseApi;
    }

    public ResponseApi postRestRequest(String apiUrl, String request) {
        entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        ResponseApi responseApi = new ResponseApi(response.getStatusCodeValue(), response.getBody());
        return responseApi;
    }
}
