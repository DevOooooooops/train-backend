package app.cashquest.api.endpoint.rest.security.authentication;

import app.cashquest.api.endpoint.rest.security.model.Principal;
import app.cashquest.api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserService userService;
    private final BearerAuthenticator authenticator;
    private static final String BEARER_PREFIX = "Bearer ";


    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // nothing
    }

    @Override
    protected UserDetails retrieveUser(
            String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        String bearer = getBearerFromHeader(authentication);
        if (bearer == null) {
            throw new UsernameNotFoundException("bad credentials");
        }
        String extractedUsername = authenticator.apply(bearer);
        return new Principal(userService.findByUsername(extractedUsername), bearer);
    }

    private String getBearerFromHeader(
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        Object tokenObject = usernamePasswordAuthenticationToken.getCredentials();
        if (!(tokenObject instanceof String) || !((String) tokenObject).startsWith(BEARER_PREFIX)) {
            return null;
        }
        return ((String) tokenObject).substring(BEARER_PREFIX.length()).trim();
    }
}
