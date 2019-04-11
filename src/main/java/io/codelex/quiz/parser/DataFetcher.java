package io.codelex.quiz.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class DataFetcher {

    List<String> fetchData(String passedUrl) throws Exception {

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
}