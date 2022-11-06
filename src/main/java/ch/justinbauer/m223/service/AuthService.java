package ch.justinbauer.m223.service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import ch.justinbauer.m223.model.Member;
import ch.justinbauer.m223.model.dto.CredentialDto;
import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthService {
    @Inject
    MemberService memberService;

    public Response signin(CredentialDto credentialDto) {
        
        Optional<Member> member = memberService.findByEmail(credentialDto.getEmail());

        try {
            if (member.isPresent() && member.get().getPassword().equals(credentialDto.getPassword())) {
              String token = Jwt
                  .issuer("justinbauer")
                  .upn(member.get().getEmail())
                  .groups(member.get().getRole().toString())
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
        
}
