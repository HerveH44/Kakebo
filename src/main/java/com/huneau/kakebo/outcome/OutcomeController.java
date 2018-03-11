package com.huneau.kakebo.outcome;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/outcomes")
public class OutcomeController {

    @Autowired
    public OutcomeService service;

    @Autowired
    public ModelMapper mapper;

    @GetMapping
    public List<OutcomeDTO> getAll() {
        return service.getAll().stream().map(o -> mapper.map(o, OutcomeDTO.class)).collect(Collectors.toList());
    }

    @PostMapping
    public OutcomeDTO create(@RequestBody OutcomeDTO outcomeDTO) {
        final Outcome outcome = mapper.map(outcomeDTO, Outcome.class);
        final Outcome savedOutome = service.save(outcome);
        return mapper.map(savedOutome, OutcomeDTO.class);
    }

    @GetMapping(value = "{id}")
    public OutcomeDTO get(@PathVariable Long id) {
        return mapper.map(service.findOne(id), OutcomeDTO.class);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping(value = "{id}")
    public OutcomeDTO update(@PathVariable Long id, @RequestBody OutcomeDTO request) {

        final Outcome outcome = mapper.map(request, Outcome.class);
        final Outcome outcomeUpdated = service.update(id, outcome);
        return mapper.map(outcomeUpdated, OutcomeDTO.class);
    }
}
