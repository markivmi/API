package com.rms.auth;

import com.google.common.base.Optional;
import com.rms.pilotapi.core.Person;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

public interface RmsAuth<T> extends Authenticator<T, Person> {
    @Override
    public Optional<Person> authenticate(T authenticator) throws AuthenticationException;
}
