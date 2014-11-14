package org.broadinstitute.orsp.ws.cache

import groovy.transform.Synchronized
import org.broadinstitute.orsp.ws.domain.SampleCollection

/**
 *
 * Created: 11/13/14
 *
 * @author <a href="mailto:grushton@broadinstitute.org">grushton</a>
 */
enum SampleCollectionsCache {
    INSTANCE,

    private cache = new ArrayList<SampleCollection>()

    @Synchronized
    def SampleCollection addSampleCollection(SampleCollection sampleCollection) {
        if (cache.contains(sampleCollection)) {
            Collections.replaceAll(
                    cache,
                    cache.find { it.id = sampleCollection.id },
                    sampleCollection)
        } else {
            cache.add(sampleCollection)
        }
        sampleCollection
    }

    @Synchronized
    def Collection<SampleCollection> getSampleCollections() {
        // Return a copy of the cache
        cache.collect { it }
    }

    @Synchronized
    def Collection<SampleCollection> findCollectionsBySearchTerm(String term) {
        term?
                cache.findAll {
                    it.id.toLowerCase().contains(term.toLowerCase()) ||
                            it.name.toLowerCase().contains(term.toLowerCase()) ||
                            it.category.toLowerCase().contains(term.toLowerCase()) ||
                            it.groupName.toLowerCase().contains(term.toLowerCase())
                } :
                Collections.EMPTY_LIST
    }

    @Synchronized
    def Collection<SampleCollection> findCollectionsByIDs(Collection<String> ids) {
        ids ?
                cache.findAll {
                    ids.contains(it.id)
                } :
                Collections.EMPTY_LIST
    }

    @Synchronized
    def SampleCollection findByID(String id) {
        id ?
                cache.find {
                    id.equalsIgnoreCase(it.id)
                } :
                null
    }

}