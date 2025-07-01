package com.gitlabjiralink.api;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class IssueService {
    private final List<IssueLink> links = new CopyOnWriteArrayList<>();

    public List<IssueLink> list() {
        return new ArrayList<>(links);
    }

    public void add(IssueLink link) {
        links.add(link);
    }
}
