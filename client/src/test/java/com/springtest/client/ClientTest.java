package com.springtest.client;

import com.springtest.client.domain.Pass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.springframework.util.Assert.isTrue;

@SpringBootTest
public class ClientTest {

    @Autowired
    private Client client;

    @Test
    public void whenAddPassShouldReturnThisPass() {
        var pass = client.add(new Pass(0, "b1",
                Timestamp.from(ZonedDateTime.now().minusYears(3).toInstant())));
        assertThat(pass.getPassNumber(), is("b1"));
    }

    @Test
    public void whenUpdatePassShouldReturnTrue() {
        var result = client.update(1, new Pass(0, "z1",
                Timestamp.from(ZonedDateTime.now().minusYears(3).toInstant())));
        isTrue(result, "The value must be true");
    }

    @Test
    public void whenDeletePassShouldReturnTrue() {
        var result = client.delete(2);
        isTrue(result, "The value must be true");
    }

    @Test
    public void whenFindAllShouldReturnPasses() {
        var result = client.findAll();
        assertThat(result.size(), greaterThan(3));
    }
}
