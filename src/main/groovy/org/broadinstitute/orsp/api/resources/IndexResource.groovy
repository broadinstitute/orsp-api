/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.api.resources

import com.codahale.metrics.annotation.Timed
import groovy.transform.CompileStatic
import io.dropwizard.views.View
import org.broadinstitute.orsp.api.views.IndexView

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 *
 * Created: 11/21/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
@CompileStatic
class IndexResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Timed
    public static View index() {
        return new IndexView()
    }

}
