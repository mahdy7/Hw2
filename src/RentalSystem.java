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

    public void rentMovie(String customer,String customerId,String movie,int year,String director){

            int movieIndex = Movie.findMovie(movie,year,director,movies,movieCounter);//the movie is not registered
            if(movieIndex == -1){
                System.out.println(" Customer cannot return the movie.");
                return;
            }
            int knownClient = Customer.findCustomer(customer,customerId,customers,customerCounter);

            if (knownClient == -1 && customerCounter < 30) { // if there is no such a customer, make new one.
                customers[customerCounter]= new Customer(customer,customerId);
                knownClient = customerCounter;
                customerCounter++;
            }
            else if (customerCounter == 30){ // if there is more than 30 customers
                System.out.println("No room for new customers.");
            }
            if(Movie.findMovie(movie,year,director,customers[knownClient].clientMovies(),customers[knownClient].clientMovieIndex()) != -1){//find the movie if already exists
                System.out.println("Customer already has this movie");
                return;
            }

            if(customers[knownClient].clientMovieIndex() < 5){ // if the movie is not in customer movie selection, add it.
                customers[knownClient].addMovie(this.movies[movieIndex]);
                this.movies[movieIndex].rent(true);
            }
            else{ // if tje customer has already 5 movies
                System.out.println("The customer has reached the limit");
            }

    }
    public void returnMovie(String customerId,String movie,int year,String director){
        int movieIndex = Movie.findMovie(movie,year,director,movies,movieCounter);
        if(movieIndex == -1){
            System.out.println("No such movie exists.");

            return;
        }
    }

    public void removeMovie(String movie,int year,String director){
        int movieIndex = Movie.findMovie(movie,year,director,movies,movieCounter);
        //check if movie exist
        if(movieIndex == -1){
            System.out.println("No such movie exists.");
            return;
        }
        // check if movie is rented
        if (movies[movieIndex].isRented()){
            System.out.println("Cannot remove a rented movie.");
            return;
        }
        //removing the movie
        movies[movieIndex] = movies[movieCounter];
        movies[movieCounter] = null;
        movieCounter--;
    }

    public void printMovies(){
        int rentedMoviesExist = -1, unrentedMoviesExist = -1;
        for (int i = 0;i < movieCounter;i++) {
            if (movies[i].isRented()) {rentedMoviesExist = 1;}
            if (!movies[i].isRented()) {unrentedMoviesExist = 1;}
        }

        if (rentedMoviesExist == 1) {
            System.out.println("Rented Movies: ");
            for (int i = 0;i < movieCounter;i++) {
                if (movies[i].isRented()) {
                    movies[i].printMovie();
                }
            }
        } else {System.out.println("No Rented movies.");}

        if (unrentedMoviesExist == 1) {
            System.out.println("Unrented Movies: ");
            for (int i = 0;i < movieCounter;i++) {
                if (!movies[i].isRented()) {
                    movies[i].printMovie();
                }
            }
        } else {System.out.println("No Unrented movies.");}
    }

}
