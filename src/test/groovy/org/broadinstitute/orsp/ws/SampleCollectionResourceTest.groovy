package org.broadinstitute.orsp.ws

import com.google.common.io.Resources
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.api.client.filter.LoggingFilter
import groovy.json.JsonBuilder
import io.dropwizard.testing.junit.DropwizardAppRule
import org.boon.json.JsonFactory
import org.boon.json.ObjectMapper
import org.broadinstitute.orsp.ws.domain.SampleCollection
import org.junit.ClassRule
import org.junit.Test

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import static org.junit.Assert.assertTrue

class SampleCollectionResourceTest {

    private static final String LOCAL_APPLICATION_URL = "http://localhost:%d/collection"

    // SC-1103	Colombian Population Samples	Reich	Population	0
    // SC-1100	Jackson Heart Study - Diabetes	Reich - Diabetes	Population	0
    def sample1 = new SampleCollection(
            id: "SC-1103",
            name: "Colombian Population Samples",
            category: "Reich",
            groupName: "Population",
            archived: "0"
    )
    def sample2 = new SampleCollection(
            id: "SC-1100",
            name: "Jackson Heart Study - Diabetes",
            category: "Reich - Diabetes",
            groupName: "Population",
            archived: "0"
    )

    @SuppressWarnings("GroovyAssignabilityCheck")
    @ClassRule
    public static final DropwizardAppRule<TestConfiguration> RULE =
            new DropwizardAppRule<TestConfiguration>(OrspApplication.class, resourceFilePath("test-app-config.yml"))

    @Test
    public void testNotFound() {
        Client client = new Client()
        client.addFilter(new LoggingFilter())

        ClientResponse response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/garbagetext", RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(ClientResponse.class)
        assertTrue(response.status == Response.Status.NOT_FOUND.statusCode)

        def ids = ["bad id 1", "bad id 1", "bad id 3"]
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/findAll", RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                post(ClientResponse.class, new JsonBuilder(ids).toString())
        assertTrue(response.status == Response.Status.NOT_FOUND.statusCode)
    }

    @Test
    public void testPostAndGetSampleCollections() {
        Client client = new Client()
        client.addFilter(new LoggingFilter())
        ObjectMapper mapper =  JsonFactory.create()

        // post an example sample1 collection:
        def response = client.resource(
                String.format(LOCAL_APPLICATION_URL, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                post(String.class, new JsonBuilder(sample1).toString())
        SampleCollection responseSample = mapper.readValue(response, SampleCollection.class)
        assertTrue(responseSample.equals(sample1))

        // post an example sample2 collection:
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                post(String.class, new JsonBuilder(sample2).toString())
        responseSample = mapper.readValue(response, SampleCollection.class)
        assertTrue(responseSample.equals(sample2))

        // retrieve all sample collections and ensure that the one we posted exists
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        List<SampleCollection> list = mapper.readValue(
                response,
                List.class,
                SampleCollection.class)
        assertTrue(list.contains(sample1))
        assertTrue(list.contains(sample2))

        // retrieve the sample1 collections matching the ID we posted
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/" + sample1.id, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        responseSample = mapper.readValue(response, SampleCollection.class)
        assertTrue(responseSample.equals(sample1))

        // find the sample1 collections matching the search term we used
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/find/" + sample1.category, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        list = mapper.readValue(
                response,
                List.class,
                SampleCollection.class)
        assertTrue(list.contains(sample1))

        def ids = [sample1, sample2]*.id
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/findAll", RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                post(ClientResponse.class, new JsonBuilder(ids).toString())
        list = mapper.readValue(
                response.getEntity(String.class),
                List.class,
                SampleCollection.class)
        assertTrue(response.status == Response.Status.OK.statusCode)
        assertTrue(list.contains(sample1))
        assertTrue(list.contains(sample2))

    }

    private static String resourceFilePath(String resourceClassPathLocation) {
        try {
            return new File(Resources.getResource(resourceClassPathLocation).toURI()).getAbsolutePath()
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

}
