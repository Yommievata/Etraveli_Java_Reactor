package model;

public class Movie {
    private final String title;
    private final String code;
    private final MovieType movieType;

    public Movie(String title, String code, MovieType movieType) {

        this.title = title;
        this.code = code;
        this.movieType = movieType;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public MovieType getMovieType() {
        return movieType;
    }
}
