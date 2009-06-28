package org.sonatype.nexus.plugin.samples.kungfu;

import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.plugin.Managed;

@Managed
public interface VirusScanner
{
    boolean hasVirus( StorageFileItem file );
}
