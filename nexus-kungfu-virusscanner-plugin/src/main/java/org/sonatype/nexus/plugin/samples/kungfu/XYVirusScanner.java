package org.sonatype.nexus.plugin.samples.kungfu;

import javax.inject.Named;

import org.sonatype.nexus.proxy.item.StorageFileItem;

/**
 * This is a user implementation of the virus scanner. This is a component, since it implements a VirusScanner
 * interface, that is marked as "component contract" using the @Managed annotation.
 * 
 * @author cstamas
 */
@Named( "XY" )
public class XYVirusScanner
    implements VirusScanner
{
    public boolean hasVirus( StorageFileItem file )
    {
        // DO THE JOB HERE
        System.out.println( "Kung fu VirusScanner --- scanning for viruses on item: " + file.getPath() );

        // simulating virus hit by having the filename contain the "infected" string
        return file.getName().contains( "infected" );
    }

}
