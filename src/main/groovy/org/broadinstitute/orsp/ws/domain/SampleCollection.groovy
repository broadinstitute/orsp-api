package org.broadinstitute.orsp.ws.domain

import groovy.transform.EqualsAndHashCode

import javax.validation.constraints.NotNull

/**
 *
 * Created: 11/13/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
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
