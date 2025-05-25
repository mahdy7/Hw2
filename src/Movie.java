public class Movie {
    private String title;
    private Genre genre;
    private int year;
    private Director director;
    private boolean rented;

    /**
     * constructs the movie object
     *
     * @param title the name of the movie.
     * @param genre its genre.
     * @param year the year it was released in.
     * @param director the person who directed it.
     */
    Movie(String title,Genre genre,int year,Director director){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.rented = false;
    }

    public String movieTitle(){ return this.title; }

    public Director movieDirector(){ return this.director; }

    public int movieYear(){ return this.year; }

    public boolean isRented() {return this.rented;}

    /**
     * changes the state of the movie (rented or not rented)
     * @param rented the new state of the movie (rented or not rented)
     */
    public void rent(boolean rented){ this.rented = rented;}

    /**
     * equal function for movie objects
     *
     * @param movie the movie object
     * @return true if this.movie and the given movie are the same
     */
    private boolean equals(Movie movie) {
        if (movie == null) return false;
        return this.title.equals(movie.movieTitle()) &&
                this.director.equals(movie.movieDirector()) &&
                this.year == movie.movieYear();
    }

    /**
     * searches an array of movies for this.movie
     *
     * @param movies the array of movies.
     * @param movieCounter how many movies are in the movies array.
     * @return returns true if this.movies is in the movies array.
     */
    public boolean findMovie(Movie[] movies,int movieCounter){
        for (int i = 0; i < movieCounter; i++ ){
            if(this.equals(movies[i])){return true;}
        }
        return false;
    }

    /**
     * finds a movie object in the movies array using only the movie's name ,its release year and its directors name
     *
     * @param movieName the name of the movie
     * @param year the year the movie was released
     * @param director the name of the director
     * @param movies the array of movies
     * @param movieCounter how many movies are in the array
     * @return movie's index (-1 if not found)
     */
    public static int findMovie(String movieName,int year,String director,Movie[] movies,int movieCounter){
        for (int i = 0; i < movieCounter; i++ ){
            if (movieName.equals(movies[i].movieTitle()) &&
                    year == movies[i].movieYear() &&
                    director.equals(movies[i].movieDirector().getDirectorName())
            ){return i;}
        }
        return -1;
    }

    /**
     * prints this.movie's properties in the following manner
     *  "Title: <>, Genre: <>, Year: <>, director: <> "
     */
    public void printMovie(){
        System.out.println("Title: " + this.title + ", Genre: " + this.genre + ", Year: "
                + this.year + ", director: " + this.director.getDirectorName());
    }

}
