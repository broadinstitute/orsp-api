package org.broadinstitute.orsp.ws.resources

import com.codahale.metrics.annotation.Timed
import groovy.transform.TypeChecked
import io.dropwizard.jersey.caching.CacheControl
import groovy.util.logging.Slf4j
import org.broadinstitute.orsp.ws.cache.SampleCollectionsCache
import org.broadinstitute.orsp.ws.domain.SampleCollection

import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.util.concurrent.TimeUnit

@Path('/collection')
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@TypeChecked
class SampleCollectionResource {

    private static SampleCollectionsCache getCache() {
        SampleCollectionsCache.INSTANCE
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public static SampleCollection addCollection(@Valid SampleCollection collection) {
        return getCache().addSampleCollection(collection)
    }

    @GET
    @Path('/')
    @Timed(name = 'get-requests')
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public static Collection<SampleCollection> list() {
        return getCache().sampleCollections
    }

    @GET
    @Path('/{id}')
    @Timed(name = 'get-requests')
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public static SampleCollection getById(@PathParam("id") String id) {
        SampleCollection collection = getCache().findByID(id)
        if (collection == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return collection
    }

    @GET
    @Path('/find/{term}')
    @Timed(name = 'get-requests')
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public static Collection<SampleCollection> getCollection(@PathParam("term") String term) {
        return getCache().findCollectionsBySearchTerm(term)
    }

    @POST
    @Path('/findAll')
    @Timed(name = 'get-requests')
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public static Collection<SampleCollection> getCollectionsByIds(@Valid Collection<String> ids) {
        Collection<SampleCollection> collections = getCache().findCollectionsByIDs(ids)
        if (collections.isEmpty()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND)
        }
        return collections
    }

}
