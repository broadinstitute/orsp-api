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

import com.google.common.io.Resources
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.filter.LoggingFilter
import org.apache.commons.lang3.CharEncoding

/**
 *
 * Created: 11/19/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class BaseAppResourceTest {

    protected static String resourceFilePath(String resourceClassPathLocation) {
        try {
            return new File(Resources.getResource(resourceClassPathLocation).toURI()).getAbsolutePath()
        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

    protected static Client getClient() {
        Client client = new Client()
        client.addFilter(new LoggingFilter())
        client
    }

    protected static String urlEncode(String str) {
        URLEncoder.encode(str, CharEncoding.UTF_8)
    }

}
