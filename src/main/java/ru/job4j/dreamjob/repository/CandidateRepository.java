package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface CandidateRepository {

Candidate save(Candidate candidate);
void deleteById(int id);
boolean update(Candidate candidate);
Optional<Candidate> findById(int id);
Collection<Candidate> findAll();
}