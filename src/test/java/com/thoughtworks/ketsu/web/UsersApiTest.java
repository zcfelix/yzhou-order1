package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_return_201_when_create_a_valid_user() {
        final Response POST = post("users", TestHelper.userMap(1, "felix"));
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        //assertThat(Pattern.matches(".*?/users/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }
}
