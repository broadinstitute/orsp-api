/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.api.resources

import com.codahale.metrics.annotation.Timed
import com.mongodb.DB
import com.mongodb.MongoException
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import io.dropwizard.jersey.caching.CacheControl
import net.vz.mongodb.jackson.DBQuery
import net.vz.mongodb.jackson.JacksonDBCollection
import org.broadinstitute.orsp.api.domain.Irb

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 *
 * Created: 11/19/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
@Path('/irb')
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@TypeChecked
class IrbResource {

    private JacksonDBCollection<Irb, String> IRBS

    IrbResource(DB mongoDb) {
        IRBS = JacksonDBCollection.wrap(
                mongoDb.getCollection("irb"),
                Irb.class, String.class)
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Object add(@Valid Irb irb) {
        try {
            IRBS.findAndRemove(DBQuery.is("id", irb.id))
            return ResourceHelper.errorIfNull(IRBS.insert(irb).savedObject)
        } catch (MongoException e) {
            throw new WebApplicationException(e)
        }
    }

    @GET
    @Timed
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.MINUTES)
    public Collection<Irb> list() {
        IRBS.find().toArray()
    }

    @GET
    @Path('/{id}')
    @Timed
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.HOURS)
    public Irb getById(@PathParam("id") String id) {
        Irb irb = IRBS.findOne(DBQuery.is("id", id))
        ResourceHelper.notFoundIfNull(irb)
        irb
    }

    @DELETE
    @Path('/{id}')
    @Timed
    public Boolean removeById(@PathParam("id") String id) {
        def result = IRBS.findAndRemove(DBQuery.is("id", id))
        if (!result) {
            throw new WebApplicationException()
        }
        true
    }

    @DELETE
    @Timed
    public Boolean removeAll() {
        try {
            IRBS.find().toArray().each {
                IRBS.findAndRemove(DBQuery.is("id", it.id))
            }
        } catch (MongoException e) {
            throw new WebApplicationException(e)
        }
        true
    }

    @GET
    @Path('/find/{term}')
    @Timed
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.HOURS)
    public Collection<Irb> getCollection(@PathParam("term") String term) {
        Pattern p = Pattern.compile(ResourceHelper.decode(term), Pattern.CASE_INSENSITIVE)
        Collection<Irb> irbs = IRBS.find(
                DBQuery.or(
                    DBQuery.regex("id", p),
                    DBQuery.regex("summary", p),
                    DBQuery.regex("protocol", p),
                    DBQuery.regex("status", p),
                    DBQuery.regex("managers", p),
                    DBQuery.regex("sampleCollections", p)
                )
        ).toArray()
        ResourceHelper.notFoundIfEmpty(irbs)
        irbs
    }
    
}
