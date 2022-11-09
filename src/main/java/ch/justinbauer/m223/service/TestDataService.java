package ch.justinbauer.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.justinbauer.m223.model.Duration;
import ch.justinbauer.m223.model.Place;
import ch.justinbauer.m223.model.Role;
import ch.justinbauer.m223.model.Status;
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

    // Places
    Place place = new Place();
    place.setTitle("Einzelplatz");
    entityManager.persist(place);

    Place secondPlace = new Place();
    secondPlace.setTitle("Gruppenplatz");
    entityManager.persist(secondPlace);

    // Duration
    Duration duration = new Duration();
    duration.setTitle("Vormittag");
    entityManager.persist(duration);

    Duration secondDuration = new Duration();
    secondDuration.setTitle("Nachmittag");
    entityManager.persist(secondDuration);

    // Status
    Status status = new Status();
    status.setTitle("Offen");
    entityManager.persist(status);

    Status secondStatus = new Status();
    secondStatus.setTitle("Storniert");
    entityManager.persist(secondStatus);

    Status thirdstatus = new Status();
    thirdstatus.setTitle("Bestätigt");
    entityManager.persist(thirdstatus);

    Status fourtStatus = new Status();
    fourtStatus.setTitle("Abgelehnt");
    entityManager.persist(fourtStatus);
  }
}
