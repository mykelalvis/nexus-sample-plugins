package org.sonatype.nexus.plugin.samples.interdep;

import java.util.List;

import javax.inject.Singleton;

import org.sonatype.nexus.proxy.item.StorageFileItem;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.plugin.Managed;

@Managed
@Singleton
public interface InfectedFilesCollector
{
    void addInfectedItemReport( Repository source, StorageFileItem item );

    List<String> getInfectedItemsReport();
}
