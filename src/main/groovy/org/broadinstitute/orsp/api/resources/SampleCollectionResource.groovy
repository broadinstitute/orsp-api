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
import org.broadinstitute.orsp.api.domain.SampleCollection

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

@Path('/collection')
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@TypeChecked
class SampleCollectionResource {

    private JacksonDBCollection<SampleCollection, String> COLLECTIONS

    SampleCollectionResource(DB mongoDb) {
        COLLECTIONS = JacksonDBCollection.wrap(
                mongoDb.getCollection("sampleCollection"),
                SampleCollection.class, String.class)
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Object add(@Valid SampleCollection collection) {
        try {
            COLLECTIONS.findAndRemove(DBQuery.is("id", collection.id))
            return ResourceHelper.errorIfNull(COLLECTIONS.insert(collection).savedObject)
        } catch (MongoException e) {
            throw new WebApplicationException(e)
        }
    }

    @GET
    @Timed
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.MINUTES)
    public Collection<SampleCollection> list() {
        COLLECTIONS.find().toArray()
    }

    @GET
    @Path('/{id}')
    @Timed
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.HOURS)
    public SampleCollection getById(@PathParam("id") String id) {
        SampleCollection collection = COLLECTIONS.findOne(DBQuery.is("id", id))
        ResourceHelper.notFoundIfNull(collection)
        collection
    }

    @DELETE
    @Path('/{id}')
    @Timed
    public Boolean removeById(@PathParam("id") String id) {
        def result = COLLECTIONS.findAndRemove(DBQuery.is("id", id))
        if (!result) {
            throw new WebApplicationException()
        }
        true
    }

    @DELETE
    @Timed
    public Boolean removeAll() {
        try {
            COLLECTIONS.find().toArray().each {
                COLLECTIONS.findAndRemove(DBQuery.is("id", it.id))
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
    public Collection<SampleCollection> getCollection(@PathParam("term") String term) {
        Pattern p = Pattern.compile(ResourceHelper.decode(term), Pattern.CASE_INSENSITIVE)
        Collection<SampleCollection> collections = COLLECTIONS.find(
                DBQuery.or(
                    DBQuery.regex("id", p),
                    DBQuery.regex("name", p),
                    DBQuery.regex("category", p),
                    DBQuery.regex("groupName", p)
            )
        ).toArray()
        ResourceHelper.notFoundIfEmpty(collections)
        collections
    }

    @POST
    @Path('/findAll')
    @Timed
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.HOURS)
    public Collection<SampleCollection> getCollectionsByIds(@Valid Collection<String> ids) {
        Collection<SampleCollection> collections = COLLECTIONS.find(DBQuery.in("id", ids)).toArray()
        ResourceHelper.notFoundIfNull(collections.isEmpty() ? null : collections)
        collections
    }

}
