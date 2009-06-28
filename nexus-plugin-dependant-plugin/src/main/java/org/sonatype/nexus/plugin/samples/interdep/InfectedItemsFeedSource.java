package org.sonatype.nexus.plugin.samples.interdep;

import java.io.IOException;
import java.util.Map;

import org.sonatype.nexus.rest.feeds.sources.FeedSource;

import com.sun.syndication.feed.synd.SyndFeed;

public class InfectedItemsFeedSource
    implements FeedSource
{
    public static final String CHANNEL_KEY = "infectedItems";

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
        // TODO Auto-generated method stub
        return null;
    }

}
