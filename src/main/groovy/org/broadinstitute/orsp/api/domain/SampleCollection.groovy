/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.api.domain

import groovy.transform.CompileStatic
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
@CompileStatic
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
