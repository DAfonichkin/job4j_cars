package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {

    Post create(Post post);

    boolean update(Post post);

    boolean delete(int id);

    Collection<Post> findAll();

    Optional<Post> findById(int id);

    Collection<Post> getPostsOfLastDay();

    Collection<Post> getPostsWithPhoto();

    Collection<Post> getPostsWithBrand(Brand brand);
}