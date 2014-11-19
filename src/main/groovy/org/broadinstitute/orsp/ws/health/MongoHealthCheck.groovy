package org.broadinstitute.orsp.ws.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import com.mongodb.Mongo

/**
 *
 * Created: 11/18/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class MongoHealthCheck extends HealthCheck {

    private Mongo mongo;

    public MongoHealthCheck(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy();
    }

}
