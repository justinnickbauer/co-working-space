package ch.justinbauer.m223.service;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.justinbauer.m223.model.Booking;
import ch.justinbauer.m223.model.Duration;
import ch.justinbauer.m223.model.Member;
import ch.justinbauer.m223.model.Place;
import ch.justinbauer.m223.model.Role;
import ch.justinbauer.m223.model.Status;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import net.bytebuddy.asm.Advice.Local;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {

  @Inject
  EntityManager entityManager;

  long firstRecord = 1;
  long secondRecord = 2;

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

    // Members
    Member member1 = new Member();
    member1.setFirstname("Thomas");
    member1.setLastname("Mathis");
    member1.setEmail("thomas.mathis@gmail.com");
    member1.setPassword("QWERT1");
    member1.setBlocked(false);
    member1.setRole(entityManager.find(Role.class, firstRecord));
    entityManager.persist(member1);

    Member member2 = new Member();
    member2.setFirstname("Julie");
    member2.setLastname("Tifner");
    member2.setEmail("julie.tifner@gmail.com");
    member2.setPassword("QWERT1");
    member2.setBlocked(false);
    member2.setRole(entityManager.find(Role.class, secondRecord));
    entityManager.persist(member2);

    // Bookings
    Booking booking = new Booking();
    booking.setDate(LocalDate.now());
    booking.setPlace(entityManager.find(Place.class, firstRecord));
    booking.setDuration(entityManager.find(Duration.class, firstRecord));
    booking.setStatus(entityManager.find(Status.class, firstRecord));
    booking.setMember(entityManager.find(Member.class, firstRecord));
    booking.setWithPrinter(true);
    entityManager.persist(booking);
  }
}
