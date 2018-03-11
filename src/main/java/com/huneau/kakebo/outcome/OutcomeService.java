package com.huneau.kakebo.outcome;

import java.util.List;

public interface OutcomeService {
    List<Outcome> getAll();

    Outcome save(Outcome outcome);

    Outcome findOne(Long id);

    void delete(Long id);

    Outcome update(Long id, Outcome outcome);
}
