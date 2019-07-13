package digispark.tech.ganapatiaarti;


public class Album {
    private String name;
    private int thumbnail;


    public Album(String name, int numOfSongs, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }


}