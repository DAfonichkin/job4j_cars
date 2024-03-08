package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    @Override
    public boolean update(Post post) {
        crudRepository.run(session -> session.merge(post));
        return true;
    }

    @Override
    public boolean delete(int id) {
        crudRepository.run("DELETE FROM Post WHERE id = :fId",
                Map.of("fId", id));
        return true;
    }

    @Override
    public List<Post> findAll() {
        return crudRepository.query(
                "from Post",
                Post.class
        );
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional(
                "from Post WHERE id = :id",
                Post.class,
                Map.of("id", id)
        );
    }

    @Override
    public List<Post> getPostsOfLastDay() {
        return crudRepository.query(
                "from Post WHERE created >= current_date()", Post.class);
    }

    @Override
    public List<Post> getPostsWithPhoto() {
        return crudRepository.query(
                "from p Post where p.autoPhoto IS NOT NULL", Post.class);
    }

    @Override
    public List<Post> getPostsWithBrand(Brand brand) {
        return crudRepository.query(
                "from p Post where brand = :fBrand", Post.class,
                Map.of("fBrand", brand)
        );
    }

}
