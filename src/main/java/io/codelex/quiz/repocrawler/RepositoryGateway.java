package io.codelex.quiz.repocrawler;

import io.codelex.quiz.api.UrlList;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.management.BadStringOperationException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RepositoryGateway {

    public UrlList getAvailableContentMdLinks(String urlString) throws GitAPIException, IOException {

        if (urlString.contains("github.com")) {


            FileUtils.deleteDirectory(new File("resources/quizrepository"));
            File file = new File("resources/quizrepository");
            Git.cloneRepository()
                    .setURI(urlString)
                    .setDirectory(file)
                    .call();

            Collection<Path> fileList = find(file.getPath());
            ArrayList<String> urlArray = new ArrayList<>();
            fileList.forEach(it -> urlArray.add(it.toString()));

            UrlList urlList = new UrlList(urlArray);
            FileUtils.deleteDirectory(new File("resources/quizrepository"));

            return urlList;
        } throw new IOException();
    }

    private static Collection<Path> find(String searchDirectory) throws IOException {

        try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
            return files.filter(f -> f.getFileName().toString().contains(".md"))
                    .collect(Collectors.toList());
        }
    }

}
