package com.rms.auth;

import com.google.common.base.Optional;
import com.rms.pilotapi.core.Person;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.oauth.OAuthProvider;

public class OAuthImpl extends OAuthProvider<Person> implements RmsAuth<String> {

    public OAuthImpl(Authenticator<String, Person> authenticator, String realm) {
        super(authenticator, realm);
    }

    @Override
    public Optional<Person> authenticate(String s) throws AuthenticationException {
        return null;
    }

}
