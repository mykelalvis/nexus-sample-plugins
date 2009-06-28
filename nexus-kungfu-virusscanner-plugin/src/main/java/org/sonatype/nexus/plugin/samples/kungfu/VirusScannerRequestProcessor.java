package org.sonatype.nexus.plugin.samples.kungfu;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.access.Action;
import org.sonatype.nexus.proxy.item.AbstractStorageItem;
import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.nexus.proxy.repository.ProxyRepository;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.nexus.proxy.repository.RequestProcessor;

@Named( "virusScanner" )
public class VirusScannerRequestProcessor
    implements RequestProcessor
{
    @Inject
    private @Named( "XY" )
    VirusScanner virusScanner;

    //@Inject
    //private @Named("A") CommonDependency commonDependency;

    public boolean process( Repository repository, ResourceStoreRequest request, Action action )
    {
        // Check dependency
        // System.out.println( "VirusScannerRequestProcessor --- CommonDependency data: " + commonDependency.getData() );

        // don't decide until have content
        return true;
    }

    public boolean shouldProxy( ProxyRepository repository, ResourceStoreRequest request )
    {
        // don't decide until have content
        return true;
    }

    public boolean shouldCache( ProxyRepository repository, AbstractStorageItem item )
    {
        if ( item instanceof StorageFileItem )
        {
            StorageFileItem file = (StorageFileItem) item;

            // do a virus scan
            return !virusScanner.hasVirus( file );
        }
        else
        {
            return true;
        }
    }

}
