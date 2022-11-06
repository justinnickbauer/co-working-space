package ch.justinbauer.m223.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.justinbauer.m223.model.Member;
import ch.justinbauer.m223.service.MemberService;

@Path("/members")
@Tag(name = "Members", description = "Handling of Members")
@RolesAllowed({ "Mitglied", "Administrator" })
public class MemberController {

    @Inject
    MemberService memberService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all members.", description = "Returns a list of all members.")
    public List<Member> index() {
        return memberService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new member. Also known as registration.", description = "Creates a new member and returns the newly added member.")
    @PermitAll
    public Member create(Member member) {
        return memberService.createMember(member);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "Deletes an member.", description = "Deletes an member by its id.")
    public void delete(@PathParam("id") Long id) {
        memberService.deleteMember(id);
    }

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an member.", description = "Updates an member by its id.")
    public Member update(@PathParam("id") Long id, Member member) {
        return memberService.updateMember(id, member);
    }

}
