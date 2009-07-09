package org.sonatype.nexus.plugin.samples.samplerepo;

import javax.inject.Named;

import org.sonatype.nexus.proxy.registry.ContentClass;
import org.sonatype.nexus.proxy.registry.DefaultContentClass;

@Named( "sample" )
public class SampleContentClass
    extends DefaultContentClass
    implements ContentClass
{
    public SampleContentClass()
    {
        super( "sample" );
    }

    @Override
    public boolean isGroupable()
    {
        // NOT GROUPABLE
        return false;
    }
}
