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

    public static void removeDirector(int movieCounter,Movie [] movies,int movieIndex, Director[] directors){
        int noOtherDirector = 0;
        //removing the director
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
