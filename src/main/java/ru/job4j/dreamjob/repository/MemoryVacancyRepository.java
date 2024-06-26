package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Vacancy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryVacancyRepository implements VacancyRepository {
    private static final MemoryVacancyRepository INSTANCE = new MemoryVacancyRepository();

    private int nextId = 1;

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "", LocalDate.now()));
        save(new Vacancy(0, "Junior Java Developer", "", LocalDate.now()));
        save(new Vacancy(0, "Junior+ Java Developer", "", LocalDate.now()));
        save(new Vacancy(0, "Middle Java Developer", "", LocalDate.now()));
        save(new Vacancy(0, "Middle+ Java Developer", "", LocalDate.now()));
        save(new Vacancy(0, "Senior Java Developer", "", LocalDate.now()));
    }

    public static MemoryVacancyRepository getINSTANCE() {
        return INSTANCE;
    }
    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public void deleteById(int id) {
        vacancies.remove(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(),
                (key, oldValue) -> new Vacancy(
                        oldValue.getId(), vacancy.getTitle(), vacancy.getDescription(), vacancy.getCreationDate())) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}
