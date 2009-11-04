package org.sonatype.nexus.plugin.samples.interdep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.nexus.rest.feeds.sources.FeedSource;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * A core extension point implementation. The FeedSource core extension point adds a new RSS feeds source. Here, this
 * RSS feed will just list all the reported infected items.
 * 
 * @author cstamas
 */
@Named( InfectedItemsFeedSource.CHANNEL_KEY )
public class InfectedItemsFeedSource
    implements FeedSource
{
    public static final String CHANNEL_KEY = "infectedItems";

    @Inject
    private InfectedFilesCollector infectedFilesCollector;

    public String getFeedKey()
    {
        return CHANNEL_KEY;
    }

    public String getFeedName()
    {
        return "Infected artifacts in all Nexus repositories (as detected by KungFu Virus Scanner).";
    }

    public SyndFeed getFeed( Integer from, Integer count, Map<String, String> params )
        throws IOException
    {
        List<String> reportItems = infectedFilesCollector.getInfectedItemsReport();

        SyndFeedImpl feed = new SyndFeedImpl();

        feed.setTitle( getFeedKey() );

        feed.setDescription( getFeedName() );

        feed.setAuthor( "Nexus" );

        feed.setPublishedDate( new Date() );

        List<SyndEntry> entries = new ArrayList<SyndEntry>( reportItems.size() );

        for ( String item : reportItems )
        {
            SyndEntry entry = new SyndEntryImpl();

            entry.setTitle( "Infected File Detected" );

            SyndContent content = new SyndContentImpl();

            content.setType( "text/plain" );

            content.setValue( item );

            entry.setPublishedDate( feed.getPublishedDate() );

            entry.setAuthor( feed.getAuthor() );

            entry.setLink( "http://nexus.sonatype.org/" );

            entry.setDescription( content );

            entries.add( entry );
        }

        feed.setEntries( entries );

        return feed;
    }

}
