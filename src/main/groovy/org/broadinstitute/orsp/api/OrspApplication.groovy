/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.api

import com.mongodb.DB
import com.mongodb.Mongo
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.dropwizard.views.ViewBundle
import org.broadinstitute.orsp.api.health.MongoHealthCheck
import org.broadinstitute.orsp.api.resources.IndexResource
import org.broadinstitute.orsp.api.resources.IrbResource
import org.broadinstitute.orsp.api.resources.SampleCollectionResource

@Slf4j
@CompileStatic
class OrspApplication extends Application<OrspApplicationConfiguration> {

    private final String name = 'orsp-api'

    public static void main(String[] args) throws Exception {
        new OrspApplication().run(args)
    }

    @Override
    public void run(OrspApplicationConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        log.debug('Running ... ' + name)
        Mongo mongo = configuration.getMongo()
        MongoManaged mongoManaged = new MongoManaged(mongo)
        environment.lifecycle().manage(mongoManaged)
        DB mongoDb = mongo.getDB(configuration.mongodb);

        environment.jersey().register(new IndexResource())
        environment.jersey().register(new IrbResource(mongoDb))
        environment.jersey().register(new SampleCollectionResource(mongoDb))
        environment.healthChecks().register("MongoHealthCheck", new MongoHealthCheck(mongo))
    }

    @Override
    public void initialize(Bootstrap<OrspApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle())
    }

}
