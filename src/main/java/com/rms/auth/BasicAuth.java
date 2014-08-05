package com.rms.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;
import org.eclipse.jetty.server.Authentication;


public class BasicAuth extends BasicAuthProvider implements RmsAuth<BasicCredentials> {

    public BasicAuth(Authenticator authenticator, String realm) {
        super(authenticator, realm);
    }

    @Override
    public Optional<Authentication.User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        return null;
    }
}
