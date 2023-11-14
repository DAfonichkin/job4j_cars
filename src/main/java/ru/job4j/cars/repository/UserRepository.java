package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    public Optional<User> create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return Optional.empty();
        }
        session.close();
        return Optional.of(user);
    }

    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET password = :fPassword, login = :fLogin WHERE id = :fId")
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        Query query = session.createQuery("from User ORDER BY id ");
        List<User> rsl = query.list();
        session.close();
        return rsl;
    }

    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User as u where u.id = :fId", User.class);
        query.setParameter("fId", userId);
        Optional<User> rsl = Optional.of(query.uniqueResult());
        session.close();
        return rsl;
    }

    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        Query query = session.createQuery("from User as u WHERE u.login like :fLogin");
        query.setParameter("fLogin", key);
        List<User> rsl = query.list();
        session.close();
        return rsl;
    }

    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User as u where u.login = :fLogin", User.class);
        query.setParameter("fLogin", login);
        Optional<User> rsl = Optional.of(query.uniqueResult());
        session.close();
        return rsl;
    }
}