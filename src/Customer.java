import java.sql.SQLOutput;

public class Customer {
    private String customerName;
    private String customerId;
    private Movie [] currentMovies;
    private int currentMovieIndex;

    public Customer(String customerName,String customerId,Movie [] currentMovies) {
        this.currentMovies = new Movie[5];
        this.customerId = customerId;
        this.customerName = customerName;
        this.currentMovieIndex = 0;
    }

    public void show(){
        System.out.println("Name"+ customerName +",ID"+ customerId) ;
        for(int i = 0; i < this.currentMovieIndex; i++){
            System.out.println(this.currentMovies[i]);
        }
    }

    public boolean addMovie(Movie movie){
        if(this.currentMovieIndex < 5){
            this.currentMovies[this.currentMovieIndex] = movie;
            this.currentMovieIndex++;
            return true;
        }
        return false;
    }

    public boolean returnMovie(Movie movie){
        for(int i = 0; i < this.currentMovieIndex; i++){
            if(this.currentMovies[i].equals(movie)){
                this.currentMovies[i] = this.currentMovies[this.currentMovieIndex - 1];
                //replacing the movie we removed with the last one;
                this.currentMovies[this.currentMovieIndex-1] = null;
                this.currentMovieIndex--;
                return true;
            }
        }
        return false;// if the movie wasn't found.
    }
}
