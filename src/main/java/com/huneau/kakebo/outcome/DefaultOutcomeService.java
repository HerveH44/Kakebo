package com.huneau.kakebo.outcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DefaultOutcomeService implements OutcomeService {

    @Autowired
    OutcomeRepository repo;

    @Override
    public List<Outcome> getAll() {
        final List<Outcome> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Outcome save(Outcome outcome) {
        return repo.save(outcome);
    }

    @Override
    public Outcome findOne(Long id) {
        return repo.findOne(id);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    @Override
    public Outcome update(Long id, Outcome outcome) {
        Outcome copy = repo.findOne(id);

        copy.setName(outcome.getName());
        copy.setCost(outcome.getCost());
        copy.setNotes(outcome.getNotes());
        copy.setDate(outcome.getDate());

        return repo.save(copy);
    }
}
