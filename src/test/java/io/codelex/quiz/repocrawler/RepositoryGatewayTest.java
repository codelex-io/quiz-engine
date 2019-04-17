package io.codelex.quiz.repocrawler;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;

class RepositoryGatewayTest {
    private RepositoryGateway gateway = new RepositoryGateway();
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    void should_return_list_of_md_files_path() throws Exception {
        //when
        String repoUrl = "https://github.com/codelex-io/spring-quiz.git";
        //then
        gateway.getAvailableContentMdLinks(repoUrl)
                .getUrlList()
                .forEach(it -> Assertions.assertTrue(it.contains(".md")));
    }

    @Test
    void should_not_find_any_md() throws Exception {
        //when
        String repoUrl = "https://github.com/codelex-io/ads-app";

        //then 
        Assertions.assertTrue(gateway.getAvailableContentMdLinks(repoUrl).getUrlList().isEmpty());
    }

    @Test
    void should_throw_exception_when_bad_link_passed() {
        //when
        String repoUrl = "https://facebook.com/";

        //then
        Assertions.assertThrows(IOException.class, () -> gateway.getAvailableContentMdLinks(repoUrl));

    }

}