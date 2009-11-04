package org.sonatype.nexus.plugin.samples.kungfu;

import javax.inject.Singleton;

import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.plugin.Managed;

/**
 * This is the "contract interface" of the user implemented component that will perform actual virus scanning. Any
 * non-abstract class that <b>directly implements</b> this interface will be a component (will be managed by a container
 * used by Nexus).
 * 
 * @author cstamas
 */
@Managed
@Singleton
public interface VirusScanner
{
    boolean hasVirus( StorageFileItem file );
}
