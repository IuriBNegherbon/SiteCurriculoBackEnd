package com.iurirest.APIGitHub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitUserController {

    @Autowired
    private GitHubApiClient gitHubApiClient;

    @GetMapping("/git/user/{username}")
    public GitHubUser getUser(@PathVariable String username) {
        return gitHubApiClient.getUser(username);
    }
}

