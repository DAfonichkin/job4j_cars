package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;

import java.util.Collection;
import java.util.Optional;

public interface BrandRepository {

    Brand save(Brand brand);

    Collection<Brand> findAll();

    Optional<Brand> findById(int id);

    boolean update(Brand brand);

    boolean delete(int id);
}