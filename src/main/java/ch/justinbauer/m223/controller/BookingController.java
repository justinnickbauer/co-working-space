package ch.justinbauer.m223.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.justinbauer.m223.model.Booking;
import ch.justinbauer.m223.service.BookingService;

@Path("/bookings")
@Tag(name = "Bookings", description = "Handling of Bookings")
@PermitAll
public class BookingController {
    

    @Inject
    BookingService bookingService;

    @Inject
    Validator validator; 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all bookings.", description = "Returns a list of all bookings.")
    @PermitAll
    public List<Booking> index() {
        return bookingService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all bookings.", description = "Returns a list of all bookings.")
    @PermitAll
    @Path("/{id}")
    public List<Booking> findByUser(@PathParam("id") Long id) {
        return bookingService.findByUser(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new booking.", description = "Creates a new booking and returns the newly added booking.")
    @PermitAll
    public Response create(Booking booking) {
        return bookingService.createBooking(booking);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes an booking.", description = "Deletes an booking by its id.")
    public void delete(@PathParam("id") Long id) {
        bookingService.deleteBooking(id);
    }

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an booking.", description = "Updates an booking by its id.")
    public Booking update(@PathParam("id") Long id, Booking booking) {
        return bookingService.updateBooking(id, booking);
    }
}
