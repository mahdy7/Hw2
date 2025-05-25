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

    /**
     * shows that customer name and the his id and then print all of his movies
     */
    public void show(){
        System.out.println("Name"+ customerName +",ID"+ customerId) ;
        for(int i = 0; i < this.currentMovieIndex; i++){
            System.out.println(this.currentMovies[i]);
        }
    }

    /**
     * the func adds movie to customer rented already movies
     * @param movie the movie that customer want to rent
     */
    public void addMovie(Movie movie){
            this.currentMovies[this.currentMovieIndex] = movie;
            this.currentMovieIndex++;
    }

    /**
     * the function search in the system for specif customer and return the index of it
     * @param customerIdentity the customer identity that we want to find
     * @param customers all the customer we have
     * @param customerCounter how many customers we have
     * @return or the customer indeex in the the system or -1 where there is no such a customer.
     */
    public static int findCustomer(String customerIdentity ,Customer[] customers,int customerCounter){
        for (int i = 0; i < customerCounter; i++ ){
            if (customerIdentity.equals(customers[i].clientId()))
            {return i;}
        }
        return -1;
    }

    /**
     * the function search for a movie in the system and allow to return a movie that the customer already rented
     * it takes care of all the situation if the movie is not found
     *
     * @param movie the movie that customer want to return
     * @param customers all the customers we have.
     * @param customerCounter the number of customers we have
     * @return if the movie found zero that means the movie was returned and the movie is not found the function return a -1.
     */
    public int returnMovie(Movie movie,Customer[] customers,int customerCounter){
        for(int i = 0; i < this.currentMovieIndex; i++){
            if(this.currentMovies[i].equals(movie)){
                this.currentMovies[i] = this.currentMovies[this.currentMovieIndex - 1];
                //replacing the movie we removed with the last one;
                this.currentMovies[this.currentMovieIndex-1] = null;
                this.currentMovieIndex--;
                if (this.currentMovieIndex == 0) {
                    RentalSystem.removeCustomer(customers,this.customerId,customerCounter);
                    return 0;
                }
                return 1;
            }
        }
        return -1;// if the movie wasn't found.
    }
}
