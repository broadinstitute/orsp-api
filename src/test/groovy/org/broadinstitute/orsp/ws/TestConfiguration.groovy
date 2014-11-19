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

import com.mongodb.Mongo

class TestConfiguration extends OrspApplicationConfiguration {

    // TODO: Look into embedded mongo for testing like hsqldb:
    //      https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo
    @Override
    public Mongo getMongo() {
        new Mongo(mongohost, mongoport)
    }

}
