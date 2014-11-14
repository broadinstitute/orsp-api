package org.broadinstitute.orsp.ws

import com.google.common.base.Objects
import io.dropwizard.Configuration

class OrspApplicationConfiguration extends Configuration {

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add('server', server)
                      .add('logging', logging)
                      .toString()
    }

}
