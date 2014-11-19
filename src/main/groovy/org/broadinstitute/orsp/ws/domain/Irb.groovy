/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.ws.domain

import groovy.transform.EqualsAndHashCode
import org.codehaus.jackson.annotate.JsonIgnoreProperties

import javax.annotation.Nullable
import javax.validation.constraints.NotNull

/**
 *
 * Created: 11/19/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(includes = ["id"])
class Irb {

    @NotNull
    String id

    @NotNull
    String summary

    @NotNull
    String location

    @NotNull
    List<String> managers

    @Nullable
    String protocol

    @Nullable
    String status

    @Nullable
    List<String> sampleCollections

}
