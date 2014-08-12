package com.rms.auth;

import com.google.common.base.Optional;
import com.rms.pilotapi.core.Person;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;


public class BasicAuth implements RmsAuth<BasicCredentials> {

    @Override
    public Optional<Person> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        String pw = basicCredentials.getPassword();
        if (pw.equals("secret")) {
            return Optional.of(new Person(basicCredentials.getUsername()));
        }
        return Optional.absent();
    }
}
