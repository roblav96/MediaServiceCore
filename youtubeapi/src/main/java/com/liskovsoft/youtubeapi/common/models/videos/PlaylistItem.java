package com.liskovsoft.youtubeapi.common.models.videos;

import com.liskovsoft.youtubeapi.common.converters.jsonpath.JsonPath;

import java.util.List;

public class PlaylistItem {
    @JsonPath({"$.playlistId", "$.navigationEndpoint.watchEndpoint.playlistId"})
    private String mPlaylistId;
    @JsonPath({"$.title.simpleText", "$.title.runs[0].text"})
    private String mTitle;
    @JsonPath("$.navigationEndpoint.watchEndpoint.videoId")
    private String mVideoId;
    @JsonPath("$.secondaryNavigationEndpoint.watchEndpoint.videoId")
    private String mSecondVideoId;
    @JsonPath("$.videoCountText.runs[0].text")
    private String mVideoCountText;
    @JsonPath("$.videoCountShortText.runs[0].text")
    private String mVideoCountShortText;
    @JsonPath("$.longBylineText.simpleText")
    private String mDescription;
    @JsonPath("$.thumbnailText.runs[0].text")
    private String mThumbnailText1;
    @JsonPath("$.thumbnailText.runs[1].text")
    private String mThumbnailText2;
    @JsonPath("$.thumbnail.thumbnails[*]")
    private List<Thumbnail> mThumbnails;

    public String getPlaylistId() {
        return mPlaylistId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public String getSecondVideoId() {
        return mSecondVideoId;
    }

    public String getVideoCountText() {
        return mVideoCountText;
    }

    public String getVideoCountShortText() {
        return mVideoCountShortText;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getThumbnailText() {
        return mThumbnailText1 + " " + mThumbnailText2;
    }

    public List<Thumbnail> getThumbnails() {
        return mThumbnails;
    }
}
