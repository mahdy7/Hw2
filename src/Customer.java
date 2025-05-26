public class Customer {
    public static final int MAX_RENTED_MOVIES = 5;
    private String customerName;
    private String customerId;
    private Movie[] currentMovies;
    private int currentMovieIndex;

    public Customer(String customerName,String customerId) {
        this.currentMovies = new Movie[MAX_RENTED_MOVIES];
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
     * prints the customer's name and his id , then prints all the movies he rented
     */
    public void show(){
        System.out.println("Name"+ customerName +",ID"+ customerId) ;
        for(int i = 0; i < this.currentMovieIndex; i++){
            System.out.println(this.currentMovies[i]);
        }
    }

    /**
     * adds the movie to the customer's rented movies array
     * @param movie the movie that the customer wants to rent
     */
    public void addMovie(Movie movie){
            this.currentMovies[this.currentMovieIndex] = movie;
            this.currentMovieIndex++;
    }

    /**
     * search the customers array for a specif customer and returns its index
     * @param customerIdentity the customer's id
     * @param customers the array of customers
     * @param customerCounter how many customers we have
     * @return the customer's index in the array or -1 when there is no such customer.
     */
    public static int findCustomer(String customerIdentity ,Customer[] customers,int customerCounter){
        for (int i = 0; i < customerCounter; i++ ){
            if (customerIdentity.equals(customers[i].clientId()))
            {return i;}
        }
        return -1;
    }

    /**
     * searches for a movie in the system and returns the movie if the customer was renting it
     *
     * @param movie the movie that the customer wants to return
     * @param customers the array of the customers
     * @param customerCounter the number of customers we have
     * @return -1 if the movie wasn't found, 0 if we need to remove the customer from the system
     * ,1 if the movie is found and there is no need to delete the customer
     */
    public int returnMovie(Movie movie,Customer[] customers,int customerCounter){
        for(int i = 0; i < this.currentMovieIndex; i++){
            if(this.currentMovies[i].equals(movie)){
                this.currentMovies[i] = this.currentMovies[this.currentMovieIndex - 1];
                this.currentMovies[this.currentMovieIndex-1] = null;
                this.currentMovieIndex--;
                if (this.currentMovieIndex == 0) {
                    RentalSystem.removeCustomer(customers,this.customerId,customerCounter);
                    return 0;
                }
                return 1;
            }
        }
        return -1;
    }
}
