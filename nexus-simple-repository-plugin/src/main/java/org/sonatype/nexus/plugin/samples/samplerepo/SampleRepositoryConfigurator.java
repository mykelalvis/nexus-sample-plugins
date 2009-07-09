package org.sonatype.nexus.plugin.samples.samplerepo;

import javax.inject.Named;

import org.sonatype.nexus.configuration.Configurator;
import org.sonatype.nexus.proxy.repository.AbstractRepositoryConfigurator;

@Named( "sample" )
public class SampleRepositoryConfigurator
    extends AbstractRepositoryConfigurator
    implements Configurator
{

}
