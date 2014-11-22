/*
 * The Broad Institute
 * SOFTWARE COPYRIGHT NOTICE AGREEMENT
 * This software and its documentation are copyright 2014 by the
 * Broad Institute/Massachusetts Institute of Technology. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. Neither
 * the Broad Institute nor MIT can be responsible for its use, misuse, or functionality.
 */
package org.broadinstitute.orsp.api.views

import io.dropwizard.views.View

/**
 *
 * Created: 11/21/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
class IndexView extends View {

    public IndexView() {
        super("/views/index.ftl")
    }

}
