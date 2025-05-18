public class Movie {
    private String title;
    private Genre genre;
    private int year;
    private Director director;

    Movie(String title,Genre genre,int year,Director director){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
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

}
