package ch.justinbauer.m223.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import ch.justinbauer.m223.model.Booking;
import ch.justinbauer.m223.model.Member;

@ApplicationScoped
public class BookingService {
    
    @Inject
    EntityManager entityManager;

    @Inject
    Validator validator; 

    @Transactional
    public void deleteBooking(Long id) {
        Booking booking = entityManager.find(Booking.class, id);
        entityManager.remove(booking);
    }

    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        booking.setId(id);
        return entityManager.merge(booking);
    }

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Booking", Booking.class);
        return query.getResultList();
    }

    @Transactional
    public Response createBooking(Booking booking) {
        try {
            Booking newBooking = entityManager.merge(booking);
            return Response
                  .ok(newBooking)
                  .build();
          } catch (Exception e) {
            System.err.println("Bad Parameters:" + e);
          }
          return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public List<Booking> findByUser(Member member) {
        return entityManager
                .createNamedQuery("Booking.findByUser", Booking.class)
                .setParameter("member", member)
                .getResultList();
    }

}
