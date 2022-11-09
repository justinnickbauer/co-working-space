package ch.justinbauer.m223.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.justinbauer.m223.model.Member;
import ch.justinbauer.m223.model.Role;
import ch.justinbauer.m223.model.dto.CredentialDto;
import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthService {
    @Inject
    MemberService memberService;

    @Inject
    RoleService roleService;

    @Inject
    EntityManager entityManager;

    public Response signin(CredentialDto credentialDto) {
        
        Optional<Member> member = memberService.findByEmail(credentialDto.getEmail());

        try {
            if (member.isPresent() && member.get().getPassword().equals(credentialDto.getPassword())) {

              String token = Jwt
                  .issuer("justinbauer")
                  .upn(member.get().getEmail())
                  .groups(member.get().getRole().getTitle())
                  .expiresIn(Duration.ofHours(24))
                  .sign();
              return Response
                  .ok(member.get())
                  .header("Authorization", "Bearer " + token)
                  .build();
            }
          } catch (Exception e) {
            System.err.println("Couldn't validate password.");
          }
      
          return Response.status(Response.Status.FORBIDDEN).build();

    }

    @Transactional
    public Response signup(Member member) {

        try {
            List<Member> members = memberService.findAll();

            if(members.isEmpty()) {
                Optional<Role> role = roleService.findByTitle("Administrator");
                member.setRole(role.get());
            } else {
                Optional<Role> role = roleService.findByTitle("Mitglied");
                member.setRole(role.get());
            }
            entityManager.merge(member);
            return Response
                  .ok(member)
                  .build();
          } catch (Exception e) {
            System.err.println("Bad Parameters");
          }
      
          return Response.status(Response.Status.BAD_REQUEST).build();
       
    }
        
}
