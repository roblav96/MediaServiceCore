package com.liskovsoft.youtubeapi.browse.models.sections;

import com.liskovsoft.youtubeapi.common.converters.jsonpath.JsonPath;

import java.util.List;

public class BrowseTab {
    @JsonPath("$.title")
    private String title;

    @JsonPath("$.endpoint.browseEndpoint.browseId")
    private String browseId;

    /**
     * Sections == Rows in web app version
     */
    @JsonPath("$.content.tvSurfaceContentRenderer.content.sectionListRenderer.contents[*].shelfRenderer")
    private List<BrowseSection> sections;

    @JsonPath("$.content.tvSurfaceContentRenderer.content.sectionListRenderer.continuations[0].nextContinuationData.continuation")
    private String mNextPageKey;

    @JsonPath("$.content.tvSurfaceContentRenderer.continuation.reloadContinuationData.continuation")
    private String mReloadPageKey;

    public String getTitle() {
        return title;
    }

    public String getBrowseId() {
        return browseId;
    }

    public List<BrowseSection> getSections() {
        return sections;
    }


    public String getNextPageKey() {
        return mNextPageKey;
    }

    public String getReloadPageKey() {
        return mReloadPageKey;
    }
}
