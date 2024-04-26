package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {
    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();
    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();
    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Pavel Pavlov", "Pavel's description"));
        save(new Candidate(0, "Denis Denisov", "Denis's description"));
        save(new Candidate(0, "Andrey Andreev", "Andrey's description"));
        save(new Candidate(0, "Aleksey Alekseev Pavlov", "Aleksey's description"));
        save(new Candidate(0, "Stepan Stepanov Pavlov", "Stepan's description"));
        save(new Candidate(0, "Egor Egorov", "Egor's description", LocalDate.now().plusDays(1)));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public void deleteById(int id) {
        candidates.remove(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldValue) -> new Candidate(
                        id, candidate.getName(), candidate.getDescription(), candidate.getCreationDate())
        ) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }
    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
