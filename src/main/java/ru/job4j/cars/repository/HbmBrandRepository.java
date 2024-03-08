package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmBrandRepository implements BrandRepository {

    private final CrudRepository crudRepository;

    @Override
    public Brand save(Brand brand) {
        crudRepository.run(session -> session.persist(brand));
        return brand;
    }

    @Override
    public Optional<Brand> findById(int id) {
        return crudRepository.optional(
                "FROM Brand WHERE id = :fId", Brand.class,
                Map.of("fId", id)
        );
    }

    @Override
    public Collection<Brand> findAll() {
        return crudRepository.query("FROM Brand", Brand.class);
    }

    @Override
    public boolean update(Brand brand) {
        crudRepository.run(session -> session.merge(brand));
        return true;
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run(
                "DELETE FROM Brand WHERE id = :fId",
                Map.of("fId", id)
        );
        return true;
    }
}