package org.sonatype.nexus.plugin.samples.interdep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.nexus.proxy.repository.ProxyRepository;
import org.sonatype.nexus.proxy.repository.Repository;

public class NaiveInfectedFilesCollector
    implements InfectedFilesCollector
{
    private ArrayList<String> reportEntries = new ArrayList<String>();

    public void addInfectedItemReport( Repository source, StorageFileItem item )
    {
        StringBuffer sb = new StringBuffer( "Infected item found in repository \"" );

        sb.append( source.getName() );

        sb.append( "\" (repoID=\"" );
        sb.append( source.getId() );
        sb.append( "\")" );

        if ( source instanceof ProxyRepository )
        {
            ProxyRepository proxySource = (ProxyRepository) source;
            sb.append( " (it is a proxy for \"" );
            sb.append( proxySource.getRemoteUrl() );
            sb.append( "\")" );
        }

        sb.append( " on path \"" );
        sb.append( item.getPath() );
        sb.append( "\"." );

        System.out.println( " *** Added infected report item: " + item.getPath() );

        reportEntries.add( sb.toString() );
    }

    public List<String> getInfectedItemsReport()
    {
        System.out.println( " *** Retrieving infected report of size: " + reportEntries.size() );

        return Collections.unmodifiableList( reportEntries );
    }

}
