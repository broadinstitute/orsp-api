package org.broadinstitute.orsp.ws.health

import com.codahale.metrics.health.HealthCheck
import groovy.transform.CompileStatic
import org.broadinstitute.orsp.ws.cache.SampleCollectionsCache

// Do not remove this import. Gradle cannot compile the class without it.
import com.codahale.metrics.health.HealthCheck.Result

/**
 *
 * Created: 11/13/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
@CompileStatic
class SampleCacheHealthCheck extends HealthCheck {

    private static SampleCollectionsCache getCache() {
        SampleCollectionsCache.INSTANCE
    }

    public SampleCacheHealthCheck() {}

    @Override
    protected Result check() throws Exception {
        if (getCache().getSampleCollections().isEmpty()) {
            Result.unhealthy("Sample Cache is Empty.")
        } else {
            Result.healthy("Sample Cache is happy.")
        }
    }

}
