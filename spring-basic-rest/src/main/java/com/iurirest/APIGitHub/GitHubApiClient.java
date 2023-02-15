package com.iurirest.APIGitHub;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class GitHubApiClient {

    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public GitHubUser getUser(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = GITHUB_API_URL + username;
        GitHubUser user = restTemplate.getForObject(url, GitHubUser.class);
        return user;
    }
}

