package dbclass.movie.controller;

import dbclass.movie.dto.movie.*;
import dbclass.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/register")
    public MovieDTO registerMovie(@ModelAttribute MovieRegisterDTO movieRegisterDTO) {
        return movieService.register(movieRegisterDTO);
    }

    @PostMapping("/rating/add")
    public List<RatingDTO> registerRating(@ModelAttribute RatingDTO ratingDTO) {
        return movieService.addRating(ratingDTO);
    }

    @PostMapping("/rating/modify")
    public List<RatingDTO> modifyRating(@ModelAttribute RatingDTO ratingDTO) {
        return movieService.modifyRating(ratingDTO);
    }

    @GetMapping("/rating/list")
    public List<RatingDTO> getRatingList() {
        return movieService.getRatingList();
    }

    @PostMapping("/role/add")
    public void addRole(@PathVariable("movieId") Long movieId, @ModelAttribute RoleAddDTO rolesToAddDTO) {
        movieService.addRole(movieId, rolesToAddDTO);
    }

    @PostMapping("/cast/register")
    public List<CastInMovieDTO> registerCast(@ModelAttribute CastInfoDTO infoDTO) {
        return movieService.addCast(infoDTO);
    }

    @PostMapping("/cast/modify")
    public List<CastInMovieDTO> modifyCast(@ModelAttribute CastInfoDTO infoDTO) {
        return movieService.modifyCast(infoDTO);
    }

    @GetMapping("/cast/getList")
    public List<CastInMovieDTO> getDirectorShortInfoList() {
        return movieService.getDirectorList();
    }

    @PostMapping("/genre/add")
    public List<GenreDTO> addGenre(@RequestBody String name) {
        return movieService.addGenre(name);
    }

    @GetMapping("/genre/list")
    public List<GenreDTO> getGenreList() {
        return movieService.loadGenreList();
    }
}
