package dijkstra;

import java.util.HashMap;

public class Result {
    HashMap<String,Float> distances;
    HashMap<String,String> previouses;

    public void setResult(HashMap<String, Float> distances, HashMap<String, String> previouses) {
        this.distances = distances;
        this.previouses = previouses;
    }

    @Override
    public String toString() {
        return "Result [distances=" + distances + ", previouses=" + previouses + "]";
    }
}
