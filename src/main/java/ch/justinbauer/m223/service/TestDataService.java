package ch.justinbauer.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.justinbauer.m223.model.Role;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  @Transactional
  void generateTestData(@Observes StartupEvent event) {
    // Roles
    Role firstRole = new Role();
    firstRole.setTitle("Mitglied");
    entityManager.persist(firstRole);

    Role secondRole = new Role();
    secondRole.setTitle("Administrator");
    entityManager.persist(secondRole);
  }
}
