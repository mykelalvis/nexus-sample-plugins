package org.sonatype.nexus.plugin.samples.samplerepo;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.sonatype.nexus.proxy.repository.AbstractRepositoryConfiguration;

public class SampleRepositoryConfiguration
    extends AbstractRepositoryConfiguration
{
    private static final String REPOSITORY_COLOR = "repositoryColor";

    public SampleRepositoryConfiguration( Xpp3Dom configuration )
    {
        super( configuration );
    }

    public RepositoryColor getRepositoryColor()
    {
        return RepositoryColor.valueOf( getNodeValue( getConfiguration( false ), REPOSITORY_COLOR, RepositoryColor.RED
            .toString() ) );
    }

    public void setRepositoryColor( RepositoryColor color )
    {
        setNodeValue( getConfiguration( true ), REPOSITORY_COLOR, color.toString() );
    }
}
