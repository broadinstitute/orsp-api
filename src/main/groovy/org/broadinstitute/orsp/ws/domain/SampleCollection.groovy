package org.broadinstitute.orsp.ws.domain

import groovy.transform.EqualsAndHashCode
import org.codehaus.jackson.annotate.JsonIgnoreProperties

import javax.validation.constraints.NotNull

/**
 *
 * Created: 11/13/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(includes = ["id"])
class SampleCollection {

    @NotNull
    String id

    @NotNull
    String name

    @NotNull
    String category

    @NotNull
    String groupName

    @NotNull
    String archived

}
