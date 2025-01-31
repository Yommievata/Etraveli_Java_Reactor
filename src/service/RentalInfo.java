package service;

import model.Customer;
import model.Movie;
import model.MovieRental;
import model.MovieType;

import java.util.HashMap;
import java.util.Map;

public class RentalInfo {

  private final Map<String, Movie> movies;

  /**
   * Initializes a new RentalInfo instance with a predefined catalog of movies.
   */
  public RentalInfo() {
    movies = new HashMap<>();
    movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR.getCode(), MovieType.REGULAR));
    movies.put("F002", new Movie("Matrix", MovieType.REGULAR.getCode(), MovieType.REGULAR));
    movies.put("F003", new Movie("Cars", MovieType.CHILDRENS.getCode(), MovieType.CHILDRENS));
    movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW_RELEASE.getCode(), MovieType.NEW_RELEASE));
  }

  /**
   * Generates a rental statement for a customer.
   *
   * @param customer The customer for whom to generate the statement
   * @return A formatted string containing the rental statement with details of each rental,
   *         total amount owed, and frequent renter points earned
   * @throws IllegalArgumentException if the movie ID in any rental is not found in the catalog
   */
  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentRentalPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

    for (MovieRental movieRental : customer.getRentals()) {
      Movie movie = movies.get(movieRental.getMovieId());
      double thisAmount = calculateRentalAmount(movie.getMovieType(), movieRental.getDaysRented());
      frequentRentalPoints += calculateRentalPoints(movie.getMovieType(), movieRental.getDaysRented());

      result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }

    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRentalPoints).append(" frequent points\n");

    return result.toString();
  }

  /**
   * Calculates the rental amount for a movie based on its type and rental duration.
   * Different movie types have different pricing rules:
   * - Regular: Base price of 2.0, additional 1.5 per day after 2 days
   * - New Release: 3.0 per day
   * - Children's: Base price of 1.5, additional 1.5 per day after 3 days
   *
   * @param movieType The type of the movie
   * @param daysRented The number of days the movie is rented
   * @return The calculated rental amount
   */
  private double calculateRentalAmount(MovieType movieType, int daysRented) {
    return switch (movieType) {
      case REGULAR -> {
        double amount = 2;
        if (daysRented > 2) {
          amount += (daysRented - 2) * 1.5;
        }
        yield amount;
      }
      case NEW_RELEASE -> daysRented * 3.0;
      case CHILDRENS -> {
        double amount = 1.5;
        if (daysRented > 3) {
          amount += (daysRented - 3) * 1.5;
        }
        yield amount;
      }
    };
  }

  /**
   * Calculates the frequent renter points earned for a rental.
   * Base points is 1 for any rental, with a bonus point for new releases rented for more than 2 days.
   *
   * @param movieType The type of the movie
   * @param daysRented The number of days the movie is rented
   * @return The number of frequent renter points earned
   */
  private int calculateRentalPoints(MovieType movieType, int daysRented) {
    int points = 1; //Use 1 as a baseline
    if (movieType == MovieType.NEW_RELEASE && daysRented > 2) {
      points++;
    }
    return points;
  }
}
