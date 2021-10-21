package com.springtest.client;

import com.springtest.client.domain.Pass;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Client {
    private final RestTemplate client;
    private final static String URL = "http://localhost:8080/";

    public Client(RestTemplate client) {
        this.client = client;
    }

    public Pass add(Pass item) {
        return client.postForEntity(
                URL + "save", item, Pass.class).getBody();
    }

    public boolean update(int id, Pass pass) {
        return client.exchange(URL + "update/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(pass),
                Void.class)
                .getStatusCode() == HttpStatus.OK;
    }

    public boolean delete(int id) {
        return client.exchange(URL + "delete/" + id,
                        HttpMethod.DELETE,
                        HttpEntity.EMPTY,
                        Void.class)
                .getStatusCode() == HttpStatus.OK;
    }

    public List<Pass> findAll() {
        return client.exchange(URL + "find",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Pass>>() {}
        ).getBody();
    }

    public Pass findById(int id) {
        return client.exchange(URL + "find/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Pass>() {}
        ).getBody();
    }

    public List<Pass> findUnavailable() {
        return client.exchange(URL + "unavailable",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Pass>>() {}
        ).getBody();
    }

    public List<Pass> findReplaceable() {
        return client.exchange(URL + "find-replaceable",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Pass>>() {}
        ).getBody();
    }

}
