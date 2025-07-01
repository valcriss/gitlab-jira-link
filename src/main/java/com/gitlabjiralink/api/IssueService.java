package com.gitlabjiralink.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class IssueService {

    @Inject
    IssueRepository repository;

    public List<IssueLink> list() {
        return repository.listAll();
    }

    @Transactional
    public void add(IssueLink link) {
        repository.persist(link);
    }
}
