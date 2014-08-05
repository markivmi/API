package com.rms.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.oauth.OAuthProvider;
import org.eclipse.jetty.server.Authentication;

public class OAuthImpl  extends OAuthProvider<Authentication.User> implements RmsAuth<String> {

    public OAuthImpl(Authenticator<String, Authentication.User> authenticator, String realm) {
        super(authenticator, realm);
    }

    @Override
    public Optional<Authentication.User> authenticate(String s) throws AuthenticationException {
        return null;
    }
}
