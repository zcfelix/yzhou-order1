package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_return_201_and_location_when_create_a_valid_user() {
        final Response POST = post("users", TestHelper.userMap(1, "felix"));
        assertThat(POST.getStatus(), is(HttpStatus.CREATED_201.getStatusCode()));
        assertThat(Pattern.matches(".*?/users/[0-9-]*", POST.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_create_an_invalid_user() {
        final Response POST = post("users", TestHelper.userMap(1, ""));
        assertThat(POST.getStatus(), is(HttpStatus.BAD_REQUEST_400.getStatusCode()));
    }

    @Test
    public void should_return_200_when_get_an_user() {
        User user = userRepository.createUser(TestHelper.userMap(1, "felix"));
        final Response GET = get("users/" + user.getId());
        assertThat(GET.getStatus(), is(HttpStatus.OK_200.getStatusCode()));
        final Map<String, Object> userInfo = GET.readEntity(Map.class);
        assertThat(userInfo.get("uri"), is("/users/" + user.getId()));
    }

    @Test
    public void should_return_404_when_user_not_find() {
        User user = userRepository.createUser(TestHelper.userMap(1, "felix"));
        final Response GET = get("users/" + user.getId() + 1);
        assertThat(GET.getStatus(), is(HttpStatus.NOT_FOUND_404.getStatusCode()));
    }
}
