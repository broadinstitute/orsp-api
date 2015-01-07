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
import groovy.transform.CompileStatic
import io.dropwizard.Configuration
import org.codehaus.jackson.annotate.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Max
import javax.validation.constraints.Min

@CompileStatic
class OrspApplicationConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String mongohost = "localhost";

    @Min(1L)
    @Max(65535L)
    @JsonProperty
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "orsp-api";

    public Mongo getMongo() {
        new Mongo(mongohost, mongoport)
    }

}
