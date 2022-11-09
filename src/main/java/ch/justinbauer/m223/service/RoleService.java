package ch.justinbauer.m223.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.justinbauer.m223.model.Role;

@ApplicationScoped
public class RoleService {

    @Inject
    EntityManager entityManager;

    public Optional<Role> findByTitle(String title) {
        return entityManager
                .createNamedQuery("Role.findByTitle", Role.class)
                .setParameter("title", title)
                .getResultStream()
                .findFirst();
    }
    
}
