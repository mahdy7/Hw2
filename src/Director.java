public class Director {

    private String directorName;

    Director(String directorName){
        this.directorName = directorName;
    }

    public String getDirectorName() {
        return this.directorName;
    }

    // implementation change??
    public static int findDirector(String directorName,Director[] directors){
        int counter = 0;
        if (directors == null) {return -1;}
        for (Director director : directors) {
            if (director == null) {return -1;}
            if (director.getDirectorName().equals(directorName)) return counter;
            counter++;
        }
        return -1;
    }

    public boolean equals(Director director){
        return this.directorName.equals(director.getDirectorName());
    }

    public static void removeDirector(int directorCounter,Movie [] movies,int movieIndex, Director[] directors,int movieCounter){
        int noOtherDirector = 0, directorPlace = 0;
        //removing the director
        for(int i = 0;i < directorCounter;i++)
        {
            if(movies[movieIndex].movieDirector().equals(movies[i].movieDirector())){
                directorPlace = i;
                noOtherDirector++;
            }
        }
        movies[movieIndex] = movies[movieCounter];
        movies[movieCounter] = null;
        movieCounter--;
        if(noOtherDirector == 1){
            directors[directorPlace] = directors[directorCounter-1];
            directors[directorCounter-1] = null;
        }
    }

}
