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

    public void rentMovie(String customer,String customerId,String movie,int year,String director){}
    public void returnMovie(String customerId,String movie,int year,String director){}
    public void removeMovie(String movie,int year,String director){}
    public void printMovies(){
    }
}
