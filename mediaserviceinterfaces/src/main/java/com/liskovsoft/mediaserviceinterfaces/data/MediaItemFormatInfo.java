package com.liskovsoft.mediaserviceinterfaces.data;

import java.io.InputStream;
import java.util.List;

public interface MediaItemFormatInfo {
    List<MediaFormat> getAdaptiveFormats();
    List<MediaFormat> getRegularFormats();
    List<MediaSubtitle> getSubtitles();
    String getHlsManifestUrl();
    String getDashManifestUrl();
    // video metadata
    String getLengthSeconds();
    void setLengthSeconds(String lengthSeconds);
    String getTitle();
    void setTitle(String title);
    String getAuthor();
    void setAuthor(String author);
    String getViewCount();
    void setViewCount(String viewCount);
    String getTimestamp();
    void setTimestamp(String timestamp);
    String getDescription();
    void setDescription(String description);
    String getVideoId();
    void setVideoId(String videoId);
    String getChannelId();
    void setChannelId(String channelId);
    boolean containsDashInfo();
    boolean containsUrlListInfo();
    InputStream getMpdStream();
    List<String> getUrlList();
    String getEventId();
    void setEventId(String eventId);
}
