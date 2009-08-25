package org.sonatype.nexus.plugin.samples.samplerepo;

import javax.inject.Named;

import org.sonatype.nexus.proxy.registry.AbstractIdContentClass;
import org.sonatype.nexus.proxy.registry.ContentClass;

@Named( SampleContentClass.ID )
public class SampleContentClass
    extends AbstractIdContentClass
    implements ContentClass
{
    public static final String ID = "sample";

    @Override
    public boolean isGroupable()
    {
        // NOT GROUPABLE
        return false;
    }

    public String getId()
    {
        return ID;
    }
}
