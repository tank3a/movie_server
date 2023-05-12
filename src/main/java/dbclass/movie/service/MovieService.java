package dbclass.movie.service;

import dbclass.movie.domain.movie.*;
import dbclass.movie.dto.ImageDTO;
import dbclass.movie.dto.movie.*;
import dbclass.movie.exceptionHandler.DataExistsException;
import dbclass.movie.exceptionHandler.DataNotExistsException;
import dbclass.movie.exceptionHandler.ServerException;
import dbclass.movie.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService {

    private final FileService fileService;
    private final MovieRepository movieRepository;
    private final PosterRepository posterRepository;
    private final CastRepository castRepository;
    private final RatingRepository ratingRepository;
    private final RoleRepository roleRepository;
    private final GenreRepository genreRepository;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Transactional
    public MovieDTO register(MovieRegisterDTO registerDTO) {
        log.debug("movie register start");
        Cast director = castRepository.findById(registerDTO.getDirectorId()).orElseThrow(() -> new DataNotExistsException("존재하지 않는 감독 ID입니다.", "cast"));
        Rating rating = ratingRepository.findById(registerDTO.getRatingId()).orElseThrow(() -> new DataNotExistsException("존재하지 않는 등급입니다.", "rating"));
        Poster poster = createPoster(registerDTO.getPoster());

        Movie movie = Movie.builder()
                .title(registerDTO.getTitle())
                .releaseDate(Date.valueOf(registerDTO.getReleaseDate()))
                .runningTime(registerDTO.getRunningTime())
                .info(registerDTO.getInfo())
                .countryCode(registerDTO.getCountryCode())
                .language(registerDTO.getLanguage())
                .poster(poster)
                .director(director)
                .rating(rating)
                .build();

        movie = movieRepository.save(movie);

        CastInMovieDTO directorDTO = CastInMovieDTO.builder()
                .castId(director.getCastId())
                .name(director.getName())
                .build();

        RatingDTO ratingDTO = RatingDTO.builder()
                .ratingId(rating.getRatingId())
                .name(rating.getName())
                .minAge(rating.getMinAge())
                .build();

        PosterDTO posterDTO = PosterDTO.builder()
                .posterId(poster.getPosterId())
                .uuid(poster.getUuid())
                .fileName(poster.getFileName())
                .fileUrl(poster.getFileUrl())
                .build();

        List<RoleAddDTO> rolesToAddDTO = registerDTO.getCastRoles();
        ListIterator list = rolesToAddDTO.listIterator();
        while (list.hasNext()) {
            addRole(movie.getMovieId(), (RoleAddDTO) list.next());
        }

        return MovieDTO.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .releaseDate(movie.getReleaseDate())
                .runningTime(movie.getRunningTime())
                .info(movie.getInfo())
                .countryCode(movie.getCountryCode())
                .language(movie.getLanguage())
                .poster(posterDTO)
                .director(directorDTO)
                .rating(ratingDTO)
                .build();
    }

    @Transactional
    private Poster createPoster(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        Path root = Paths.get(uploadPath, "poster");

        try {
            ImageDTO imageDTO =  fileService.createImageDTO(originalName, root);
            Poster poster = Poster.builder()
                    .uuid(imageDTO.getUuid())
                    .fileName(imageDTO.getFileName())
                    .fileUrl(imageDTO.getFileUrl())
                    .build();

            file.transferTo(Paths.get(imageDTO.getFileUrl()));

            return posterRepository.save(poster);
        } catch (IOException e) {
            throw new ServerException("파일 저장 실패");
        }
    }

    @Transactional
    public List<RatingDTO> addRating(RatingDTO ratingDTO) {

        if(ratingRepository.existsByName(ratingDTO.getName())) {
            throw new DataExistsException("존재하는 rating 이름입니다.", "rating");
        }

        Rating rating = Rating.builder()
                .name(ratingDTO.getName())
                .minAge(ratingDTO.getMinAge())
                .build();

        ratingRepository.save(rating);

        return getRatingList();
    }

    @Transactional
    public List<RatingDTO> modifyRating(RatingDTO ratingDTO) {
        if(!ratingRepository.existsById(ratingDTO.getRatingId())) {
            throw new DataNotExistsException("존재하지 않는 rating입니다.", "rating");
        }

        Rating rating = Rating.builder()
                .ratingId(ratingDTO.getRatingId())
                .minAge(ratingDTO.getMinAge())
                .name(ratingDTO.getName())
                .build();
        ratingRepository.save(rating);

        return getRatingList();
    }

    @Transactional(readOnly = true)
    public List<RatingDTO> getRatingList() {
        return ratingRepository.findAll().stream().map(rating -> ratingMapper(rating)).collect(Collectors.toList());
    }

    private RatingDTO ratingMapper(Rating rating) {
        return RatingDTO.builder()
                .ratingId(rating.getRatingId())
                .name(rating.getName())
                .minAge(rating.getMinAge())
                .build();

    }

    //같은 인물 저장되어 있는지 확인할 방법은?
    @Transactional
    public List<CastInMovieDTO> addCast(CastInfoDTO infoDTO) {
        ImageDTO imageDTO = createCastImageDTO(infoDTO.getProfileImage());

        Cast cast = Cast.builder()
                .name(infoDTO.getName())
                .birthDate(Date.valueOf(infoDTO.getBirthDate()))
                .nationality(infoDTO.getNationality())
                .info(infoDTO.getInfo())
                .uuid(imageDTO.getUuid())
                .fileName(imageDTO.getFileName())
                .fileUrl(imageDTO.getFileUrl())
                .build();

        cast = castRepository.save(cast);
        return getDirectorList();
    }

    private ImageDTO createCastImageDTO(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        Path root = Paths.get(uploadPath, "cast");

        try {
            ImageDTO imageDTO =  fileService.createImageDTO(originalName, root);
            file.transferTo(Paths.get(imageDTO.getFileUrl()));

            return imageDTO;
        } catch (IOException e) {
            throw new ServerException("파일 저장 실패");
        }
    }

    @Transactional
    public List<CastInMovieDTO> modifyCast(CastInfoDTO infoDTO) {
        Cast originalCast = castRepository.findById(infoDTO.getCastId()).orElseThrow(() -> new DataNotExistsException("존재하지 않는 감독/배우입니다.", "cast"));

        ImageDTO imageDTO = infoDTO.getProfileImage().isEmpty() ? null : createCastImageDTO(infoDTO.getProfileImage());

        Cast cast = Cast.builder()
                .castId(originalCast.getCastId())
                .name(infoDTO.getName())
                .birthDate(Date.valueOf(infoDTO.getBirthDate()))
                .nationality(infoDTO.getNationality())
                .info(infoDTO.getInfo())
                .uuid(imageDTO != null ? imageDTO.getUuid() : originalCast.getUuid())
                .fileName(imageDTO != null ? imageDTO.getFileName() : originalCast.getFileName())
                .fileUrl(imageDTO != null ? imageDTO.getFileUrl() : originalCast.getFileUrl())
                .build();

        castRepository.save(cast);
        return getDirectorList();
    }

    @Transactional(readOnly = true)
    public List<CastInMovieDTO> getDirectorList() {
        List<Cast> directorList = castRepository.findAll();
        return directorList.stream().map(cast -> castMapper(cast)).collect(Collectors.toList());
    }

    private CastInMovieDTO castMapper(Cast cast) {
        return CastInMovieDTO.builder().castId(cast.getCastId()).name(cast.getName()).birthDate(cast.getBirthDate()).build();
    }

    @Transactional
    public void addRole(Long movieId, RoleAddDTO roleAddDTO) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new DataNotExistsException("존재하지 않는 영화 ID입니다.", "movie"));
        Cast cast = castRepository.findById(roleAddDTO.getCastId()).orElseThrow(() -> new DataNotExistsException("존재하지 않는 배우 ID입니다.", "cast"));

        Role role = Role.builder()
                .movie(movie)
                .cast(cast)
                .role(roleAddDTO.getRole())
                .starring(roleAddDTO.isStarring())
                .build();

        roleRepository.save(role);
    }

    @Transactional
    public List<GenreDTO> addGenre(String name) {
        if(genreRepository.existsByName(name)) {
            throw new DataExistsException("이미 저장된 장르 입니다.", "genre");
        }

        Genre genre = Genre.builder()
                .name(name)
                .build();

        genreRepository.save(genre);

        return loadGenreList();
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> loadGenreList() {

        return genreRepository.findAll().stream().map(genre -> genreMapper(genre)).collect(Collectors.toList());
    }

    private GenreDTO genreMapper(Genre genre) {
        return GenreDTO.builder()
                .genreId(genre.getGenreId())
                .name(genre.getName())
                .build();
    }
//
//    @Transactional
//    public void addGenreToMovie()
}
