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
        for (Director director : directors) {
            if (director.getDirectorName().equals(directorName)) return counter;
            counter++;
        }
        return -1;
    }

    public boolean equals(Director director){
        return this.directorName.equals(director.getDirectorName());
    }

}
