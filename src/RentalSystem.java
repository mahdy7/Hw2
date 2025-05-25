public class RentalSystem {

    private Customer[] customers;
    private Movie[] movies;
    private Director[] directors;
    private int movieCounter = 0;
    private int customerCounter= 0;
    private int directorCounter = 0;

    RentalSystem() {
        this.customers = new Customer[30];
        this.movies = new Movie[30];
        this.directors = new Director[30];
    }

    /**
     * the function removes the customer from the system
     *
     * @param customers the customers that we have
     * @param customerId the identity of the customer
     * @param customerCounter the number of the customers
     */
    public static void removeCustomer(Customer[] customers,String customerId,int customerCounter) {
        int customerIndex = Customer.findCustomer(customerId,customers,customerCounter);
        customers[customerIndex] = customers[customerCounter-1];
        customers[customerCounter-1] = null;
    }

    /**
     * this function add movie to the movies that we have, it deals with all the problems like if the director is already is
     * in the system so we add the movie to him or if the movie is already in the system, or we don't have more space for more movies.
     *
     * @param movie the movie we want to add
     * @param genre the genre of the movie
     * @param year the year of the movie
     * @param directorName the name of the director who made that movie
     * @param biography the biography of the director
     */
    public void addMovie(String movie,Genre genre,int year,String directorName,String biography){

        //this is something wrong with genre
        int knownDirector = Director.findDirector(directorName,directors);
        if (knownDirector == -1) {
            directors[directorCounter] = new Director(directorName);
            knownDirector = directorCounter;
            directorCounter++;
        }

        Movie movieObj = new Movie(movie,genre,year,directors[knownDirector]);

        if (movieObj.findMovie(movies,movieCounter)){
            System.out.println("Movie is already in the system.");
            return;
        }
        else if (this.movieCounter == 30){
            System.out.println("System is full, Cannot add more movies.");
            return;
        }

        movies[movieCounter] = movieObj;
        this.movieCounter++;
    }

    /**
     * this function allows the customer to rent a movie, checks if the movie exist or the there is on new room for
     * customer or the customer have enough movies or the customer already has this movie, if non of them is valid
     * the function let the customer rent the movie.
     *
     * @param customer the customer that wants to rent the movie
     * @param customerId the identity of the customer
     * @param movie the movie the customer want to return
     * @param year the year when the movie was released
     * @param director the director's name
     */
    public void rentMovie(String customer,String customerId,String movie,int year,String director){

        int movieIndex = Movie.findMovie(movie,year,director,movies,movieCounter);
        if(movieIndex == -1){
            System.out.println("No such movie exists");
            return;
        }
        int knownClient = Customer.findCustomer(customerId,customers,customerCounter);

        if (knownClient == -1 && customerCounter < 30) {
            customers[customerCounter]= new Customer(customer,customerId);
            knownClient = customerCounter;
            customerCounter++;
        }
        else if (customerCounter == 30){
            System.out.println("No room for new customers.");
        }
        if(Movie.findMovie(movie,year,director,customers[knownClient].clientMovies(),customers[knownClient].clientMovieIndex()) != -1){
            System.out.println("Customer already has this movie");
            return;
        }

        if(customers[knownClient].clientMovieIndex() < 5){
            customers[knownClient].addMovie(this.movies[movieIndex]);
            this.movies[movieIndex].rent(true);
        }
        else{
            System.out.println("The customer has reached the limit");
        }
    }

    /**
     * this function, returns the rented movie from the customer, it deals with with problems, such as the customer is not found
     * or the customer is already not renting the movie.
     *
     * @param customerId the id of the customer
     * @param movie name of the movie
     * @param year the release year of the movie
     * @param director the director of the movie
     */
    public void returnMovie(String customerId,String movie,int year,String director){
        int movieIndex = Movie.findMovie(movie,year,director,movies,movieCounter);
        if(movieIndex == -1){
            System.out.println("Customer cannot return the movie.");
            return;
        }

        int currentCustomer = Customer.findCustomer(customerId,customers,customerCounter);
        if (currentCustomer == -1){
            System.out.println("Customer not found.");
            return;
        }

        int knownClient = customers[currentCustomer].returnMovie(this.movies[movieIndex],customers,customerCounter);
        if (knownClient == -1){
            System.out.println("Customer cannot return the movie.");
            return;
        } else if (knownClient == 0) {customerCounter--;}

        movies[movieIndex].rent(false);
    }

    /**
     * this function basically removes the movie from system,it deals with situations like if the movie doesn't exist
     * or if the movie is rented its acceptable to remove or if the director has only this movie we should delete the director
     *
     * @param movie the movie we want to remove
     * @param year the year of the movie was made
     * @param director the director of the movie
     */
    public void removeMovie(String movie,int year,String director){
        int movieIndex = Movie.findMovie(movie,year,director,movies,movieCounter);
        if(movieIndex == -1){
            System.out.println("No such movie exists.");
            return;
        }
        if (movies[movieIndex].isRented()){
            System.out.println("Cannot remove rented movie.");
            return;
        }
        Director.removeDirector(movieCounter, movies, movieIndex, directors);

        this.movies[movieIndex] = movies[movieCounter-1];
        this.movies[movieCounter-1] = null;
        this.movieCounter--;
    }

    /**
     * prints all the movies sorted in way where all the rented movies and all the unrented.
     */
    public void printMovies(){
       int rentedMoviesExist = -1, unrentedMoviesExist = -1;

        for (int i = 0;i < movieCounter;i++) {
            if (movies[i].isRented()) {rentedMoviesExist = 1;}
            if (!(movies[i].isRented())) {unrentedMoviesExist = 1;}
        }

        System.out.println("Rented Movies: ");
        if (rentedMoviesExist == 1) {
            for (int i = 0;i < movieCounter;i++) {
                if (movies[i].isRented()) {
                    movies[i].printMovie();
                }
            }
        } else {System.out.println("No Rented movies.");}

        System.out.println("Unrented Movies: ");
        if (unrentedMoviesExist == 1) {
            for (int i = 0;i < movieCounter;i++) {
                if (!movies[i].isRented()) {
                    movies[i].printMovie();
                }
            }
        } else {System.out.println("No Unrented movies.");}
    }

}
