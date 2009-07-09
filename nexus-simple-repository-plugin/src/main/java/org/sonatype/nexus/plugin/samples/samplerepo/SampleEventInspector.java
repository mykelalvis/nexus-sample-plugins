package org.sonatype.nexus.plugin.samples.samplerepo;

import java.util.Date;

import javax.inject.Inject;

import org.sonatype.nexus.proxy.NoSuchRepositoryException;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.events.AbstractEventInspector;
import org.sonatype.nexus.proxy.events.EventInspector;
import org.sonatype.nexus.proxy.events.RepositoryRegistryEventAdd;
import org.sonatype.nexus.proxy.events.RepositoryRegistryEventRemove;
import org.sonatype.nexus.proxy.events.RepositoryRegistryRepositoryEvent;
import org.sonatype.nexus.proxy.item.ContentGenerator;
import org.sonatype.nexus.proxy.item.DefaultStorageCollectionItem;
import org.sonatype.nexus.proxy.item.DefaultStorageFileItem;
import org.sonatype.nexus.proxy.item.DefaultStorageFileTemplateItem;
import org.sonatype.nexus.proxy.item.DefaultStorageLinkItem;
import org.sonatype.nexus.proxy.item.StorageItem;
import org.sonatype.nexus.proxy.item.StringContentLocator;
import org.sonatype.nexus.proxy.maven.MavenProxyRepository;
import org.sonatype.nexus.proxy.registry.RepositoryRegistry;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.plexus.appevents.Event;

public class SampleEventInspector
    extends AbstractEventInspector
    implements EventInspector
{
    private static final String PATH_PREFIX = "/examples/";

    @Inject
    private RepositoryRegistry repositoryRegistry;

    public boolean accepts( Event<?> evt )
    {
        // we accept only events about adding/removing SampleRepository kind repositories
        if ( evt instanceof RepositoryRegistryEventAdd || evt instanceof RepositoryRegistryEventRemove )
        {
            RepositoryRegistryRepositoryEvent registryEvent = (RepositoryRegistryRepositoryEvent) evt;

            Repository repository = registryEvent.getRepository();

            return repository.getRepositoryKind().isFacetAvailable( SampleRepository.class );
        }
        else
        {
            return false;
        }
    }

    public void inspect( Event<?> evt )
    {
        RepositoryRegistryRepositoryEvent registryEvent = (RepositoryRegistryRepositoryEvent) evt;

        SampleRepository sampleRepository = registryEvent.getRepository().adaptToFacet( SampleRepository.class );

        // check and manage SampleRepositories only!
        if ( evt instanceof RepositoryRegistryEventAdd )
        {
            // add simple text file
            DefaultStorageFileItem file =
                new DefaultStorageFileItem( sampleRepository,
                                            new ResourceStoreRequest( PATH_PREFIX + "simpleFile.txt" ), true, false,
                                            new StringContentLocator(
                                                                      "This file was created programatically by class \""
                                                                          + this.getClass().getName() + "\" at "
                                                                          + new Date().toString() ) );

            storeItem( sampleRepository, file );

            // add simple subdir
            DefaultStorageCollectionItem subdir =
                new DefaultStorageCollectionItem( sampleRepository, new ResourceStoreRequest( PATH_PREFIX + "subdir" ),
                                                  true, false );

            storeItem( sampleRepository, subdir );

            // add a link to some pom _known_ to exist in central
            // but central may not exists in this instance, so be careful
            try
            {
                MavenProxyRepository centralProxyRepo =
                    repositoryRegistry.getRepositoryWithFacet( "central", MavenProxyRepository.class );

                DefaultStorageLinkItem linkToPom =
                    new DefaultStorageLinkItem( sampleRepository, new ResourceStoreRequest( PATH_PREFIX
                        + "slf4j-api-1.5.8.pom" ), true, false, centralProxyRepo
                        .createUid( "/org/slf4j/slf4j-api/1.5.8/slf4j-api-1.5.8.pom" ) );

                storeItem( sampleRepository, linkToPom );
            }
            catch ( NoSuchRepositoryException e )
            {
                // registry does not have it, just skip this example silently
            }

            // add a content generated item to sample repo
            DefaultStorageFileItem generatedFile =
                new DefaultStorageFileItem( sampleRepository, new ResourceStoreRequest( PATH_PREFIX
                    + "generatedFile.txt" ), true, false, new StringContentLocator( "blah" ) );

            generatedFile.getAttributes().put( ContentGenerator.CONTENT_GENERATOR_ID,
                                               SampleContentGenerator.GENERATOR_KEY );

            storeItem( sampleRepository, generatedFile );

            // add a Velocity generated item to sample repo
            DefaultStorageFileTemplateItem templateFile =
                new DefaultStorageFileTemplateItem(
                                                    sampleRepository,
                                                    new ResourceStoreRequest( PATH_PREFIX
                                                        + "generatedFormTemplateFile.txt" ),
                                                    true,
                                                    false,
                                                    new StringContentLocator(
                                                                              "The file content is actually a Velocity template, see? For example, this file was requested as ${request.url} and from address ${request.address}" ) );

            storeItem( sampleRepository, templateFile );
        }
        else if ( evt instanceof RepositoryRegistryEventRemove )
        {
        }
    }

    // ==

    protected boolean storeItem( Repository repository, StorageItem item )
    {
        try
        {
            repository.storeItem( false, item );

            return true;
        }
        catch ( Exception e )
        {
            getLogger().warn( "Was unable to store item: " + item.getPath(), e );

            return false;
        }
    }
}