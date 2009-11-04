package org.sonatype.nexus.plugin.samples.kungfu;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.configuration.ConfigurationException;
import org.sonatype.nexus.plugins.RepositoryCustomizer;
import org.sonatype.nexus.proxy.repository.ProxyRepository;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.nexus.proxy.repository.RequestProcessor;

/**
 * This is a user implementation of a Nexus core interface marked with @ExtensionPoint (in short: implementation of
 * extension point). Hence, this non abstract class will be managed as component by the core. All the IoC benefits are
 * applied to this class.
 * 
 * @author cstamas
 */
public class VirusScannerRepositoryCustomizer
    implements RepositoryCustomizer
{
    @Inject
    private @Named( "virusScanner" )
    RequestProcessor virusScannerRequestProcessor;

    public boolean isHandledRepository( Repository repository )
    {
        // handle proxy reposes only
        return repository.getRepositoryKind().isFacetAvailable( ProxyRepository.class );
    }

    public void configureRepository( Repository repository )
        throws ConfigurationException
    {
        repository.getRequestProcessors().put( "virusScanner", virusScannerRequestProcessor );
    }

}
