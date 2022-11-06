package ch.justinbauer.m223.controller;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.justinbauer.m223.model.dto.CredentialDto;
import ch.justinbauer.m223.service.AuthService;

@Path("/auth")
@Tag(name = "Authentication", description = "Handling of Authentications")
@PermitAll
public class AuthController {
    
    @Inject
    AuthService authService;
  
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Operation(summary = "Authenticate a member.", description = "Returns a token upon successful authentication.")
    public Response create(@Valid CredentialDto credentialdto) {
      return this.authService.signin(credentialdto);
    }

}
