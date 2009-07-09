package org.sonatype.nexus.plugin.samples.samplerepo;

import javax.inject.Named;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.sonatype.nexus.configuration.ExternalConfiguration;
import org.sonatype.nexus.configuration.Validator;
import org.sonatype.nexus.proxy.repository.AbstractRepositoryValidator;

@Named( "sample" )
public class SampleRepositoryValidator
    extends AbstractRepositoryValidator
    implements Validator
{
    @Override
    protected ExternalConfiguration createExternalConfiguration( Xpp3Dom dom )
    {
        return new SampleRepositoryConfiguration( dom );
    }
}
