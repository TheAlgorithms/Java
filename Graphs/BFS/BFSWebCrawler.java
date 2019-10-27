
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BFSWebCrawler {
    
    private Queue<String> queue;
    private List<String> discoveredWebsitesList;
    
    public BFSWebCrawler(){
        this.queue = new LinkedList<>();
        this.discoveredWebsitesList = new ArrayList<>();
    }
    
    public void discoverWeb(String root){
        this.queue.add(root);
        this.discoveredWebsitesList.add(root);
        
        while(!queue.isEmpty()){
            String v = queue.remove();
            String rawHtml = readURL(v);

            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHtml);

            while(matcher.find()){
                String actualUrl = matcher.group();
                if(!discoveredWebsitesList.contains(actualUrl)){
                    discoveredWebsitesList.add(actualUrl);
                    System.out.println("Website has been found with URL: "+ actualUrl );
                    queue.add(actualUrl);
                }
            }

        }
        
    }

    private String readURL(String v) {

        String rawHtml = "";

        try {

            URL url = new URL(v);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine = "";

            while ((inputLine = in.readLine()) != null) {

                rawHtml += inputLine;

            }
           in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return rawHtml;
    }
}



public class App {

    public static void main(String[] args) {
        BFSWebCrawler webCrawler = new BFSWebCrawler();

        webCrawler.discoverWeb("https://.........");
    }
}