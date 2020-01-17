package br.com.wallace.worstmovie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DirtiesContext
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WorstMovieApplicationTests {
    @Test
    void mainTest() {
        Assertions.assertThrows(BeanCreationException.class,
                () -> WorstMovieApplication.main(new String[]{}));
    }
}