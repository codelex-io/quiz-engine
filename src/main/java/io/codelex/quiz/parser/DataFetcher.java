package io.codelex.quiz.parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {

    public List<String> fetchData(String passedUrl) throws Exception {
        try {
            URL url = new URL(passedUrl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
            in.close();

            return lines;
        }
        catch (MalformedURLException e) {
            throw new MalformedURLException("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            throw new IOException("I/O Error: " + e.getMessage());
        }
    }
}