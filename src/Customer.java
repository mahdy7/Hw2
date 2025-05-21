public class Customer {
    private String customerName;
    private String customerId;
    private Movie[] currentMovies;
    private int currentMovieIndex;

    public Customer(String customerName,String customerId) {
        this.currentMovies = new Movie[5];
        this.customerId = customerId;
        this.customerName = customerName;
        this.currentMovieIndex = 0;
    }
    public int clientMovieIndex() {
        return this.currentMovieIndex;
    }
    public String clientName(){
        return this.customerName;
    }

    public String clientId(){
        return this.customerId;
    }

    public Movie[] clientMovies(){
        return this.currentMovies;
    }

    public void show(){
        System.out.println("Name"+ customerName +",ID"+ customerId) ;
        for(int i = 0; i < this.currentMovieIndex; i++){
            System.out.println(this.currentMovies[i]);
        }
    }

    public void addMovie(Movie movie){
            this.currentMovies[this.currentMovieIndex] = movie;
            this.currentMovieIndex++;
    }

    public static int findCustomer(String customerIdentity ,Customer[] customers,int customerCounter){
        for (int i = 0; i < customerCounter; i++ ){
            if (customerIdentity.equals(customers[i].clientId()))
            {return i;}
        }
        return -1;
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
