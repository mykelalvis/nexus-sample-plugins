package org.sonatype.nexus.plugin.samples.samplerepo;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.sonatype.nexus.configuration.Configurator;
import org.sonatype.nexus.configuration.model.CRepository;
import org.sonatype.nexus.configuration.model.CRepositoryExternalConfigurationHolderFactory;
import org.sonatype.nexus.proxy.registry.ContentClass;
import org.sonatype.nexus.proxy.repository.AbstractRepository;
import org.sonatype.nexus.proxy.repository.DefaultRepositoryKind;
import org.sonatype.nexus.proxy.repository.HostedRepository;
import org.sonatype.nexus.proxy.repository.RepositoryKind;

public class DefaultSampleRepository
    extends AbstractRepository
    implements SampleRepository
{
    @Inject
    @Named( "sample" )
    private Configurator configurator;

    @Inject
    @Named( "sample" )
    private ContentClass contentClass;

    private RepositoryKind repositoryKind;

    @Override
    protected Configurator getConfigurator()
    {
        return configurator;
    }

    public ContentClass getRepositoryContentClass()
    {
        return contentClass;
    }

    public RepositoryKind getRepositoryKind()
    {
        if ( repositoryKind == null )
        {
            repositoryKind =
                new DefaultRepositoryKind( SampleRepository.class, Arrays
                    .asList( new Class<?>[] { HostedRepository.class } ) );
        }

        return repositoryKind;
    }

    @Override
    protected SampleRepositoryConfiguration getExternalConfiguration( boolean forWrite )
    {
        return (SampleRepositoryConfiguration) super.getExternalConfiguration( forWrite );
    }

    @Override
    protected CRepositoryExternalConfigurationHolderFactory<?> getExternalConfigurationHolderFactory()
    {
        return new CRepositoryExternalConfigurationHolderFactory<SampleRepositoryConfiguration>()
        {
            public SampleRepositoryConfiguration createExternalConfigurationHolder( CRepository config )
            {
                return new SampleRepositoryConfiguration( (Xpp3Dom) config.getExternalConfiguration() );
            }
        };
    }

    // ==
    // custom method
    // ==

    public RepositoryColor getRepositoryColor()
    {
        return getExternalConfiguration( false ).getRepositoryColor();
    }

    public void setRepositoryColor( RepositoryColor color )
    {
        getExternalConfiguration( true ).setRepositoryColor( color );
    }

}
