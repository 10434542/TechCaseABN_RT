package ray.playground.techcaseabnrt.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
class AuthenticationService {

    public String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}


