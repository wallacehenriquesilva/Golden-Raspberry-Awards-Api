package br.com.wallace.worstmovie.resources;

import br.com.wallace.worstmovie.data.models.MovieProducerModel;
import br.com.wallace.worstmovie.data.models.ResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieResourceSuccessTests {

    private String path;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    private void init() {
        this.path = new StringBuilder("http://127.0.0.1:")
                .append(port)
                .append("/api/v1/worst/movie/service")
                .toString();
    }


    @Test
    public void getIntervalsSuccess() {
        ResponseEntity<ResponseModel> response = testRestTemplate.exchange(
                path + "/intervals",
                HttpMethod.GET,
                null,
                ResponseModel.class);

        Assertions.assertEquals(Boolean.TRUE, "Joel Silver".equals(response.getBody().getMin().stream().findFirst().map(MovieProducerModel::getProducer).orElse(null)));
    }
}
