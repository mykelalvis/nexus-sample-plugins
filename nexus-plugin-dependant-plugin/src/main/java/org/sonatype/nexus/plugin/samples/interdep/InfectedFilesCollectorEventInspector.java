package org.sonatype.nexus.plugin.samples.interdep;

import javax.inject.Inject;

import org.sonatype.nexus.plugin.samples.kungfu.events.InfectedItemFoundEvent;
import org.sonatype.nexus.proxy.events.EventInspector;
import org.sonatype.plexus.appevents.Event;

/**
 * This is a core extension point implementation. It's purpose is to listen for the InfectedItemFoundEvent's and just
 * pass them to a user component.
 * 
 * @author cstamas
 */
public class InfectedFilesCollectorEventInspector
    implements EventInspector
{
    @Inject
    private InfectedFilesCollector infectedFilesCollector;

    public boolean accepts( Event<?> evt )
    {
        return ( evt instanceof InfectedItemFoundEvent );
    }

    public void inspect( Event<?> evt )
    {
        InfectedItemFoundEvent ievt = (InfectedItemFoundEvent) evt;

        infectedFilesCollector.addInfectedItemReport( ievt.getEventSender(), ievt.getInfectedFile() );
    }
}
