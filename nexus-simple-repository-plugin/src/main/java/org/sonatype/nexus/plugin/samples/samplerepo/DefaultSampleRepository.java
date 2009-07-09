package org.sonatype.nexus.plugin.samples.samplerepo;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.nexus.configuration.Configurator;
import org.sonatype.nexus.configuration.Validator;
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
    private Validator validator;

    @Inject
    @Named( "sample" )
    private ContentClass contentClass;

    private RepositoryKind repositoryKind;

    @Override
    protected Configurator getConfigurator()
    {
        return configurator;
    }

    @Override
    public Validator getValidator()
    {
        return validator;
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
    protected SampleRepositoryConfiguration getExternalConfiguration()
    {
        return (SampleRepositoryConfiguration) super.getExternalConfiguration();
    }

    // ==
    // custom method
    // ==

    public RepositoryColor getRepositoryColor()
    {
        return getExternalConfiguration().getRepositoryColor();
    }

    public void setRepositoryColor( RepositoryColor color )
    {
        getExternalConfiguration().setRepositoryColor( color );
    }
}
