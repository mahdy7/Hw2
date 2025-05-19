public class Movie {
    private String title;
    private Genre genre;
    private int year;
    private Director director;
    private boolean rented;

    Movie(String title,Genre genre,int year,Director director){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.rented = false;
    }

    public String movieTitle(){
        return this.title;
    }

    public Director movieDirector(){
        return this.director;
    }

    public int movieYear(){
        return this.year;
    }

    public void rent(boolean rented){this.rented = rented;}

    public boolean isRented() {return this.rented;}

    private boolean equals(Movie movie) {
        return this.title.equals(movie.movieTitle()) &&
                this.director.equals(movie.movieDirector()) &&
                this.year == movie.movieYear();
    }
    //change name
    public boolean findMovie(Movie[] movies,int movieCounter){
        for (int i = 0; i < movieCounter; i++ ){
            if(this.equals(movies[i])){return true;}
        }
        return false;
    }

    public static int findMovie(String movieName,int year,String director,Movie[] movies,int movieCounter){
        for (int i = 0; i < movieCounter; i++ ){
            if (movieName.equals(movies[i].movieTitle()) &&
                    year == movies[i].movieYear() &&
                    director.equals(movies[i].movieDirector().getDirectorName())
            ){return i;}
        }
        return -1;
    }

    public void printMovie(){
        System.out.println("Title: " + this.title + ", Genre: " + this.genre + ", Year: "
                + this.year + ", director: " + this.director.getDirectorName() + ".");
    }

}
