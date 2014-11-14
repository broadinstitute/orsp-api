package org.broadinstitute.orsp.ws

import com.hubspot.dropwizard.guice.GuiceBundle
import groovy.util.logging.Slf4j
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.broadinstitute.orsp.ws.resources.SampleCollectionResource

@Slf4j
class OrspApplication extends Application<OrspApplicationConfiguration> {

    private final String name = 'OrspApplication'

    public static void main(String[] args) throws Exception {
        new OrspApplication().run(args)
    }

    @Override
    public void run(OrspApplicationConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        log.debug('Running ... ' + name)
        environment.jersey().register(new SampleCollectionResource())
    }

    @Override
    public void initialize(Bootstrap<OrspApplicationConfiguration> bootstrap) {

//        bootstrap.with {
//            addBundle guiceBundle
//        }

    }

//    private final GuiceBundle<OrspApplicationConfiguration> guiceBundle =
//            GuiceBundle.<OrspApplicationConfiguration>newBuilder()
//                    .setConfigClass(OrspApplicationConfiguration.class)
//                    .build()

}
