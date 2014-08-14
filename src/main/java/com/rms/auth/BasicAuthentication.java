package com.rms.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;

public class BasicAuthentication implements Authentication<BasicCredentials> {

    @Override
    public Optional<Boolean> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        String pw = basicCredentials.getPassword();
        if (pw.equals("secret")) {
            return Optional.of(true);
        }
        return Optional.of(false);
    }
}
