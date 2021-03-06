package org.sonatype.nexus.plugin.samples.kungfu.events;

import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.plexus.appevents.AbstractEvent;

/**
 * An event emitted when an infected item/file is found. Maybe someone will listen to this, who knows ;)
 * 
 * @author cstamas
 */
public class InfectedItemFoundEvent
    extends AbstractEvent<Repository>
{
    private final StorageFileItem file;

    public InfectedItemFoundEvent( Repository component, StorageFileItem file )
    {
        super( component );

        this.file = file;
    }

    public StorageFileItem getInfectedFile()
    {
        return file;
    }
}
