package br.com.wallace.worstmovie.resources;

import br.com.wallace.worstmovie.data.models.ResponseModel;
import br.com.wallace.worstmovie.data.repositories.MovieRepository;
import br.com.wallace.worstmovie.data.repositories.ProducerRepository;
import br.com.wallace.worstmovie.data.repositories.StudioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieResourceErrorTests {

    private String path;

    @LocalServerPort
    private int port;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private ProducerRepository producerRepository;

    @MockBean
    private StudioRepository studioRepository;


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
    public void getIntervalsError() {

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        ProducerRepository producerRepository = Mockito.mock(ProducerRepository.class);
        StudioRepository studioRepository = Mockito.mock(StudioRepository.class);

        Mockito.when(movieRepository.getIntervals()).thenReturn(Collections.emptySet());

        ResponseEntity<ResponseModel> response = testRestTemplate.exchange(
                path + "/intervals",
                HttpMethod.GET,
                null,
                ResponseModel.class);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}