public class RentalSystem {
    public static final int MAX_CAPACITY = 30;
    public static final int MAX_RENTED_MOVIES = 5;
    private Customer[] customers;
    private Movie[] movies;
    private Director[] directors;
    private int movieCounter = 0;
    private int customerCounter= 0;
    private int directorCounter = 0;

    /**
     * constructs the rentalSystem
     */
    RentalSystem() {
        this.customers = new Customer[MAX_CAPACITY];
        this.movies = new Movie[MAX_CAPACITY];
        this.directors = new Director[MAX_CAPACITY];
    }

    /**
     * removes the customer from the system
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
     * adds a movie to the movies array,if the director is already in the system the movie points to him in his properties,
     * if not a new director object is made, in case the movies array is full we don't add a movie
     *
     * @param movie the movie we want to add
     * @param genre the genre of the movie
     * @param year the year of the movie
     * @param directorName the name of the director who made that movie
     * @param biography the biography of the director
     */
    public void addMovie(String movie,Genre genre,int year,String directorName,String biography){

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
        else if (this.movieCounter == MAX_CAPACITY) {
            System.out.println("System is full, Cannot add more movies.");
            return;
        }

        movies[movieCounter] = movieObj;
        this.movieCounter++;
    }

    /**
     * allows the customer to rent a movie if it exists, or not if there is no new room for the customer
     * or the customer rented enough movies or the customer already has this movie
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

        if (knownClient == -1 && customerCounter < MAX_CAPACITY) {
            customers[customerCounter]= new Customer(customer,customerId);
            knownClient = customerCounter;
            customerCounter++;
        }
        else if (customerCounter == MAX_CAPACITY){
            System.out.println("No room for new customers.");
        }
        if(Movie.findMovie(movie,year,director,customers[knownClient].clientMovies(),customers[knownClient].clientMovieIndex()) != -1){
            System.out.println("Customer already has this movie");
            return;
        }

        if(customers[knownClient].clientMovieIndex() < MAX_CAPACITY) {
            customers[knownClient].addMovie(this.movies[movieIndex]);
            this.movies[movieIndex].rent(true);
        }
        else{
            System.out.println("The customer has reached the limit");
        }
    }

    /**
     * returns the rented movie from the customer if the customer is found
     * ,and he is renting the movie.
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
     * removes the movie from system,it deals with situations like if the movie doesn't exist
     * or if the movie is rented it's acceptable to remove or if the director has only this movie we should delete the director
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
     * prints all the movies sored in two lists: Rented and Unrented
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
