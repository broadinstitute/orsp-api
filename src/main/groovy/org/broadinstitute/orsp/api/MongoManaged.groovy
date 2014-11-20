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
