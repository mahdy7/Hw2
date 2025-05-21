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

}
