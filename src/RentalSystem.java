public class RentalSystem {

    private Customer[] customers;
    private Movie[] movies;
    private Director[] directors;
    private int movieCounter = 0;
    private int customerCounter= 0;
    private int directorCounter = 0;

    RentalSystem() {
        Customer[] customers = new Customer[30];
        Movie[] movies = new Movie[30];
        Director[] directors = new Director[30];
    }

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
                System.out.println("No such movie exists.");
                return;
            }
            int knownClient = Customer.findCustomer(customer,customerId,customers,customerCounter);

            if(customers[knownClient].clientMovieIndex() < 4){ // if the movie is not in customer movie selection, add it.
                customers[knownClient].addMovie(this.movies[movieIndex]);
                for(int j= 0;j < customers[knownClient].clientMovieIndex(); j++){
                    if(Movie.findMovie(movie,year,director,customers[knownClient].clientMovies(),movieCounter) == -1){//find the movie if already exists
                        System.out.println("Customer is already has this movie.");
                        return;
                    }
                }
                this.movies[movieIndex].rent(true);
                this.movieCounter++;
            }
            else{ // if tje customer has already 5 movies
                System.out.println("The customer has reached the limit.");
            }

            if (knownClient != -1 && customerCounter <= 30) { // if there is no such a customer, make new one.
                Customer client = new Customer(customer,customerId);
                client.addMovie( this.movies[movieIndex]);
                this.movies[movieIndex].rent(true);
                this.movieCounter++;
           }
            else{ // if there is more than 30 customers
                System.out.println("No room for new customers.");
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

    }

    public void printMovies(){
        int rentedMoviesExist = -1, unrentedMoviesExist = -1;
        for (int i = 0;i < movieCounter;i++) {
            if (movies[i].isRented()) {
                rentedMoviesExist = 1;
            }
            if (!movies[i].isRented()) {
                unrentedMoviesExist = 1;
            }

        }
        System.out.println("Rented movies:");
        System.out.println("Unrented movies:");
    }

}
