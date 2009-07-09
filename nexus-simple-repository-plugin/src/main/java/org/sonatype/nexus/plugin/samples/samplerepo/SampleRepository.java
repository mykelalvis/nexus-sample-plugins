package org.sonatype.nexus.plugin.samples.samplerepo;

import org.sonatype.nexus.plugins.RepositoryType;
import org.sonatype.nexus.proxy.repository.HostedRepository;

@RepositoryType( pathPrefix = "sample" )
public interface SampleRepository
    extends HostedRepository
{
    RepositoryColor getRepositoryColor();

    void setRepositoryColor( RepositoryColor color );
}
