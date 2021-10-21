package com.springtest.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springtest.restapi.domain.Pass;
import com.springtest.restapi.repository.PassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
public class PassController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PassController.class.getSimpleName());
    private final PassRepository repository;
    private final ObjectMapper objectMapper;

    public PassController(PassRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/save")
    public Pass save(@RequestBody Pass pass) {
        return repository.save(pass);
    }

    @PutMapping("update/{id}")
    @Transactional
    public HttpStatus update(@PathVariable int id, @RequestBody Pass pass) {
        var founded = repository.findById(id);
        if (founded.isPresent()) {
            pass.setId(founded.get().getId());
            repository.update(pass.getId(), pass.getPassNumber(), pass.getExpired());
            return HttpStatus.OK;
        }
        throw new IllegalArgumentException("Pass with id : " + id + " not exist");
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable int id) {
        var pass = repository.findById(id);
        if (pass.isPresent()) {
            repository.delete(pass.get());
            return HttpStatus.OK;
        }
        throw new IllegalArgumentException("Pass with id : " + id + " not exist");
    }

    @GetMapping("/find")
    public List<Pass> findAll() {
        return repository.findAll();
    }

    @GetMapping("/find/{number}")
    public Pass findByNumber(@PathVariable String number) {
        return repository.findByPassNumber(number).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @GetMapping("/unavailable")
    public List<Pass> unavailable() {
        return repository.unavailable();
    }

    @GetMapping("/find-replaceable")
    public List<Pass> replaceable() {
        return repository.findByExpiredBetween(
                Timestamp.from(ZonedDateTime.now().minusMonths(3).toInstant()),
                Timestamp.from(Instant.now())
        );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }
}
