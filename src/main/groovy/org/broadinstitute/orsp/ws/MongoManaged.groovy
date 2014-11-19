package org.broadinstitute.orsp.ws

import com.mongodb.Mongo
import io.dropwizard.lifecycle.Managed

/**
 *
 * Created: 11/17/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class MongoManaged implements Managed {

    private Mongo mongo

    public MongoManaged(Mongo mongo) {
          this.mongo = mongo
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        mongo.close()
    }

}
