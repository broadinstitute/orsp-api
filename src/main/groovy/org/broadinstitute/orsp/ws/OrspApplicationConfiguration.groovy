package org.broadinstitute.orsp.ws

import com.mongodb.Mongo
import io.dropwizard.Configuration
import org.codehaus.jackson.annotate.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Max
import javax.validation.constraints.Min

class OrspApplicationConfiguration extends Configuration {

    @JsonProperty @NotEmpty
    public String mongohost = "localhost";

    @Min(1L)
    @Max(65535L)
    @JsonProperty
    public int mongoport = 27017;

    @JsonProperty @NotEmpty
    public String mongodb = "orsp-api";


    public Mongo getMongo() {
        new Mongo(mongohost, mongoport)
    }

}
