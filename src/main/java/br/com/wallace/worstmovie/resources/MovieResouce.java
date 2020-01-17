package br.com.wallace.worstmovie.resources;


import br.com.wallace.worstmovie.data.models.ResponseModel;
import br.com.wallace.worstmovie.services.MovieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/worst/movie/service",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieResouce {

    private final MovieService movieService;

    public MovieResouce(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * <p>
     * Api with the GET verb, that returns the producers list with the interval between the shorter e longer time.
     * </p>
     *
     * @return Return the interval response object in a response entity.
     */
    @ApiOperation("Return method of producers with shorter and longer time interval between awards.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return an object with producer information with the shortest and longest prize interval."),
            @ApiResponse(code = 404, message = "Returns an error when there are no producers to calculate the range."),
    })
    @GetMapping("/intervals")
    public ResponseEntity<ResponseModel> getIntervals() {
        return ResponseEntity.ok(movieService.getProducersIntervals());
    }
}