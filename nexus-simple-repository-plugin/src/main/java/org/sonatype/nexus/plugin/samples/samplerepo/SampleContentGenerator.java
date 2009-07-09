package org.sonatype.nexus.plugin.samples.samplerepo;

import javax.inject.Named;

import org.sonatype.nexus.proxy.IllegalOperationException;
import org.sonatype.nexus.proxy.ItemNotFoundException;
import org.sonatype.nexus.proxy.StorageException;
import org.sonatype.nexus.proxy.item.ContentGenerator;
import org.sonatype.nexus.proxy.item.ContentLocator;
import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.nexus.proxy.item.StringContentLocator;
import org.sonatype.nexus.proxy.repository.Repository;

@Named( SampleContentGenerator.GENERATOR_KEY )
public class SampleContentGenerator
    implements ContentGenerator
{
    public static final String GENERATOR_KEY = "sample";

    public ContentLocator generateContent( Repository repository, String path, StorageFileItem item )
        throws IllegalOperationException, ItemNotFoundException, StorageException
    {
        StringBuilder sb = new StringBuilder();

        sb.append( "Hi! This is a generated file, and is coming from repository named \"" );

        sb.append( repository.getName() );

        sb.append( "\", which has currently " );

        sb.append( repository.adaptToFacet( SampleRepository.class ).getRepositoryColor().toString() );

        sb.append( " color!" );
        
        // an ugly problem not solved in core: you have to "adjust" the size of the file
        item.setLength( sb.length() );

        return new StringContentLocator( sb.toString() );
    }

}
