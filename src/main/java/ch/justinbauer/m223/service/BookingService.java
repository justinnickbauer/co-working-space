package ch.justinbauer.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import ch.justinbauer.m223.model.Booking;

@ApplicationScoped
public class BookingService {
    
    @Inject
    EntityManager entityManager;

    @Inject
    Validator validator; 
    
    @Inject 
    MemberService memberService;

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

    public Response findAllByMember(Long id) {
        try {
            return Response
            .ok(entityManager
            .createNamedQuery("Booking.findByMember", Booking.class)
            .setParameter("id", id)
            .getResultList())
            .build();
        } catch (Exception e){
            System.err.println("Couldn't validate password." + e);
        }
        return Response.status(Response.Status.FORBIDDEN).build();
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
}
