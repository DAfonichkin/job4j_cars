package ru.job4j.cars.repository;

import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Optional;

public interface OwnerRepository {

    Owner save(Owner owner);

    Collection<Owner> findAll();

    Optional<Owner> findById(int id);

    boolean update(Owner owner);

    boolean delete(int id);
}