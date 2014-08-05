package com.rms.auth;

import com.google.common.base.Optional;
import com.rms.pilotapi.core.Person;
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
    public Optional<Person> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        String pw = basicCredentials.getPassword();
        if(pw.equals("secret")) {
            return Optional.of(new Person(basicCredentials.getUsername()));
        }
        return Optional.absent();
    }
}
