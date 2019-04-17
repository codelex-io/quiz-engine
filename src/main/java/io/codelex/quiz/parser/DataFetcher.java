package io.codelex.quiz.parser;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
class DataFetcher {

    List<String> fetchData(String passedPath) throws IOException {

        BufferedReader in;
        
        if(passedPath.contains("http://") || passedPath.contains("https://")) {
            URL url = new URL(passedPath);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        else {
            in = Files.newBufferedReader(Paths.get(passedPath));
        }
        
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }
        in.close();
        return lines;
    }
}