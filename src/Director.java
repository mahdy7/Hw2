public class Director {

    private String directorName;

    Director(String directorName){
        this.directorName = directorName;
    }

    public String getDirectorName() {
        return this.directorName;
    }

    /**
     * finds a director object from a given array using only the director's name
     * returns the index of the director in the array
     * in case the director isn't found returns -1
     *
     * @param directorName the wanted director's name
     * @param directors the array of directors
     * @return directors index (-1 if not found)
     */
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

    /**
     * if the director of the movie that will be removed directed only that movie
     * he will be removed from the directors array
     *
     * @param movieCounter how many movies are currently in the system
     * @param movies the array of movies currently in the system
     * @param movieIndex the index of the movie that will be removed
     * @param directors the array of directors
     */
    public static void removeDirector(int movieCounter,Movie [] movies,int movieIndex, Director[] directors){
        int noOtherDirector = 0;
        for(int i = 0;i < movieCounter;i++) {
            if(movies[movieIndex].movieDirector().equals(movies[i].movieDirector())){
                noOtherDirector++;
            }
        }
        if(noOtherDirector == 1){
            int directorIndex= Director.findDirector(movies[movieIndex].movieDirector().getDirectorName(),directors);
            directors[directorIndex] = directors[directorIndex-1];
            directors[directorIndex-1] = null;
        }
    }

}
