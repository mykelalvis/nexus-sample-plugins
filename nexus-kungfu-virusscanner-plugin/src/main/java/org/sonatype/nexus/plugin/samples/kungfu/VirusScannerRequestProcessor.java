package org.sonatype.nexus.plugin.samples.kungfu;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.nexus.plugin.samples.kungfu.events.InfectedItemFoundEvent;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.access.Action;
import org.sonatype.nexus.proxy.item.AbstractStorageItem;
import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.nexus.proxy.repository.ProxyRepository;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.nexus.proxy.repository.RequestProcessor;
import org.sonatype.plexus.appevents.ApplicationEventMulticaster;

/**
 * This is a user implementation of a Nexus core interface marked with @ExtensionPoint (in short: implementation of
 * extension point). Hence, this non abstract class will be managed as component by the core. All the IoC benefits are
 * applied to this class.
 * 
 * @author cstamas
 */
@Named( "virusScanner" )
public class VirusScannerRequestProcessor
    implements RequestProcessor
{
    @Inject
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Inject
    private @Named( "XY" )
    VirusScanner virusScanner;

    public boolean process( Repository repository, ResourceStoreRequest request, Action action )
    {
        // don't decide until have content but say "yeah, okie-dokie"
        return true;
    }

    public boolean shouldProxy( ProxyRepository repository, ResourceStoreRequest request )
    {
        // don't decide until have content but say "yeah, okie-dokie"
        return true;
    }

    public boolean shouldCache( ProxyRepository repository, AbstractStorageItem item )
    {
        // we have a content here, just before it is fetched from remote and is about to be stored/cached in local
        // storage. The client still did not get a single byte, so we can check it here and prevent it if infected
        if ( item instanceof StorageFileItem )
        {
            StorageFileItem file = (StorageFileItem) item;

            // do a virus scan
            boolean hasVirus = virusScanner.hasVirus( file );

            if ( hasVirus )
            {
                applicationEventMulticaster.notifyEventListeners( new InfectedItemFoundEvent( item
                    .getRepositoryItemUid().getRepository(), file ) );
            }

            return !hasVirus;
        }
        else
        {
            return true;
        }
    }

}
