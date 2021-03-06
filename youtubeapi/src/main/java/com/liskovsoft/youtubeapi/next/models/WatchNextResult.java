package com.liskovsoft.youtubeapi.next.models;

import com.liskovsoft.youtubeapi.common.converters.jsonpath.JsonPath;

import java.util.List;

public class WatchNextResult {
    /**
     * Sections == Rows in web app version
     */
    @JsonPath("$.contents.singleColumnWatchNextResults.pivot.pivot.contents[*].shelfRenderer")
    private List<WatchNextSection> mWatchNextSections;
    @JsonPath("$.contents.singleColumnWatchNextResults.results.results.contents[0].itemSectionRenderer.contents[0].videoMetadataRenderer")
    private VideoMetadata mVideoMetadata;
    @JsonPath("$.contents.singleColumnWatchNextResults.results.results.contents[1].itemSectionRenderer.contents[0].videoOwnerRenderer")
    private VideoOwner mVideoOwner;
    @JsonPath("$.contents.singleColumnWatchNextResults.autoplay.autoplay.sets[0].nextVideoRenderer")
    private NextVideo mNextVideo;

    public List<WatchNextSection> getWatchNextSections() {
        return mWatchNextSections;
    }

    public VideoMetadata getVideoMetadata() {
        return mVideoMetadata;
    }

    public VideoOwner getVideoOwner() {
        return mVideoOwner;
    }

    public NextVideo getNextVideo() {
        return mNextVideo;
    }
}
