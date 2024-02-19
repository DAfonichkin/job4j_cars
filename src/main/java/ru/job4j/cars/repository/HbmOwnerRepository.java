package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmOwnerRepository implements OwnerRepository {

    private final CrudRepository crudRepository;

    @Override
    public Owner save(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return owner;
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional(
                "FROM Owner WHERE id = :fId", Owner.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Owner> findAll() {
        return crudRepository.query("FROM Owner", Owner.class);
    }

    @Override
    public boolean update(Owner owner) {
        crudRepository.run(session -> session.merge(owner));
        return true;
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "DELETE FROM Owner WHERE id = :fId",
                Map.of("fId", id)
        );
        return true;
    }
}