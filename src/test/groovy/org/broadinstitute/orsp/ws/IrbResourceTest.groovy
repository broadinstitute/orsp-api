/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.ws

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import groovy.json.JsonBuilder
import io.dropwizard.testing.junit.DropwizardAppRule
import org.boon.json.JsonFactory
import org.boon.json.ObjectMapper
import org.broadinstitute.orsp.ws.domain.Irb
import org.junit.ClassRule
import org.junit.Test

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import static org.junit.Assert.assertTrue

/**
 *
 * Created: 11/19/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class IrbResourceTest extends BaseAppResourceTest {

    @SuppressWarnings("GroovyAssignabilityCheck")
    @ClassRule
    public static final DropwizardAppRule<TestConfiguration> RULE =
            new DropwizardAppRule<TestConfiguration>(OrspApplication.class, resourceFilePath("test-app-config.yml"))

    private static final String LOCAL_APPLICATION_URL = "http://localhost:%d/irb"

    def irb1 = new Irb(
            id: "IRB-1",
            summary: "Example IRB 1",
            location: "http://broadinstitute.org/",
            managers: ["John Smith", "Jane Doe"]
    )

    def irb2 = new Irb(
            id: "IRB-2",
            summary: "Example IRB 2",
            location: "http://broadinstitute.org/",
            managers: ["Sally Morgan"],
            protocol: "XYZ-123",
            status: "Complete",
            sampleCollections: ["SC-1", "SC-2"]
    )

    @Test
    public void testNotFound() {
        Client client = getClient()

        ClientResponse response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/afsjkfjklfsadfsd", RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(ClientResponse.class)
        assertTrue(response.status == Response.Status.NOT_FOUND.statusCode)

        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/find/afsjkfjklfsadfsd", RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(ClientResponse.class)
        assertTrue(response.status == Response.Status.NOT_FOUND.statusCode)
    }

    @Test
    public void testPostAndGetSampleCollections() {
        Client client = getClient()
        ObjectMapper mapper = JsonFactory.create()

        // Add
        def response = client.resource(
                String.format(LOCAL_APPLICATION_URL, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                post(String.class, new JsonBuilder(irb1).toString())
        Irb irb = mapper.readValue(response, Irb.class)
        assertTrue(irb.equals(irb1))

        response = client.resource(
                String.format(LOCAL_APPLICATION_URL, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                post(String.class, new JsonBuilder(irb2).toString())
        irb = mapper.readValue(response, Irb.class)
        assertTrue(irb.equals(irb2))


        // Find all
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        List<Irb> list = mapper.readValue(
                response,
                List.class,
                Irb.class)
        assertTrue(list.contains(irb1))
        assertTrue(list.contains(irb2))


        // Get by ID
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/" + irb1.id, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        Irb responseIrb = mapper.readValue(response, Irb.class)
        assertTrue(responseIrb.equals(irb1))

        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/" + irb2.id, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        responseIrb = mapper.readValue(response, Irb.class)
        assertTrue(responseIrb.equals(irb2))


        // Search
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/find/" + urlEncode(irb1.summary), RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        list = mapper.readValue(
                response,
                List.class,
                Irb.class)
        assertTrue(list.contains(irb1))

        // Search a collection
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/find/" + urlEncode(irb1.managers[1]), RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        list = mapper.readValue(
                response,
                List.class,
                Irb.class)
        assertTrue(list.contains(irb1))

        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/find/" + urlEncode(irb2.sampleCollections[0]), RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                get(String.class)
        list = mapper.readValue(
                response,
                List.class,
                Irb.class)
        assertTrue(list.contains(irb2))

        // Delete
        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/" + irb1.id, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                delete(ClientResponse.class)
        assertTrue(response.status == Response.Status.OK.statusCode)

        response = client.resource(
                String.format(LOCAL_APPLICATION_URL + "/" + irb2.id, RULE.getLocalPort())).
                accept(MediaType.APPLICATION_JSON).
                type(MediaType.APPLICATION_JSON).
                delete(ClientResponse.class)
        assertTrue(response.status == Response.Status.OK.statusCode)

    }

}
