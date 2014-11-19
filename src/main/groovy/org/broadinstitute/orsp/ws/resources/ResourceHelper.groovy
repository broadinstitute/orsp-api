package org.broadinstitute.orsp.ws.resources

import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response

/**
 *
 * Created: 11/18/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class ResourceHelper {

    public static void notFoundIfNull(Object obj) {
        errorIfNull(obj, Response.Status.NOT_FOUND)
    }

    public static void errorIfNull(Object obj, Response.Status status) {
        if (obj == null) {
            throw new WebApplicationException(status)
        }
    }

}
