package org.sonatype.nexus.plugin.samples.kungfu;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sonatype.nexus.proxy.item.StorageFileItem;

public class VirusScannerTest
{

    private VirusScanner scanner;

    @Before
    public void createScanner()
        throws Exception
    {
        scanner = new XYVirusScanner(); // TODO must lookup for it!
    }

    @Test
    public void scan()
    {
        StorageFileItem file = mock( StorageFileItem.class );
        when( file.getName() ).thenReturn( "clean-file.xtx" );
        Assert.assertFalse( scanner.hasVirus( file ) );
        when( file.getName() ).thenReturn( "infected-file.xtx" );
        Assert.assertTrue( scanner.hasVirus( file ) );
    }

}
