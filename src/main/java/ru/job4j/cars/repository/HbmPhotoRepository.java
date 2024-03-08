package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPhotoRepository implements PhotoRepository {
    private CrudRepository crudRepository;

    @Override
    public Photo save(Photo photo) {
        crudRepository.run(session -> session.persist(photo));
        return photo;
    }

    @Override
    public Optional<Photo> findById(int id) {
        return crudRepository.optional("FROM Photo WHERE id = :fId", Photo.class,
                Map.of("fId", id)
        );
    }

    @Override
    public void deleteById(int id) {
        crudRepository.run("delete from Photo where id = :fId",
                Map.of("fId", id)
        );
    }
}