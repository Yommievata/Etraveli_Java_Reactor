package model;

public class MovieRental {
    private final String movieId;
    private final int daysRented;

    public MovieRental(String movieId, int daysRented) {
        this.movieId = movieId;
        this.daysRented = daysRented;
    }

    public String getMovieId() {
        return movieId;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
