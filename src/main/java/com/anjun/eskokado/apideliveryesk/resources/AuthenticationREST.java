package com.anjun.eskokado.apideliveryesk.resources;

import com.anjun.eskokado.apideliveryesk.resources.dto.AuthRequest;
import com.anjun.eskokado.apideliveryesk.resources.dto.AuthResponse;
import com.anjun.eskokado.apideliveryesk.resources.dto.User;
import com.anjun.eskokado.apideliveryesk.utils.PBKDF2Encoder;
import com.anjun.eskokado.apideliveryesk.utils.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class AuthenticationREST {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "com.anjun.eskokado.apideliveryesk.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @PermitAll
    @POST
    @Path("/login") @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequest authRequest) {
        User user = User.findByUsername(authRequest.username);
        if (user != null && user.password.equals(passwordEncoder.encode(authRequest.password))) {
            try {
                return Response.ok(new AuthResponse(TokenUtils.generateToken(user.username, user.roles, duration, issuer))).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
