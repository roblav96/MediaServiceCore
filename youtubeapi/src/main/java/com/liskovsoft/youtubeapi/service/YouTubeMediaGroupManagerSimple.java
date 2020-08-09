package com.liskovsoft.youtubeapi.service;

import com.liskovsoft.mediaserviceinterfaces.MediaGroup;
import com.liskovsoft.mediaserviceinterfaces.MediaGroupManager;
import com.liskovsoft.sharedutils.mylogger.Log;
import com.liskovsoft.youtubeapi.browse.BrowseService;
import com.liskovsoft.youtubeapi.browse.models.sections.BrowseSection;
import com.liskovsoft.youtubeapi.search.SearchService;
import com.liskovsoft.youtubeapi.search.models.SearchResult;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class YouTubeMediaGroupManagerSimple implements MediaGroupManager {
    private static final String TAG = YouTubeMediaGroupManagerSimple.class.getSimpleName();
    private static YouTubeMediaGroupManagerSimple sInstance;
    private final BrowseService mBrowseService;
    private final SearchService mSearchService;

    private YouTubeMediaGroupManagerSimple() {
        mSearchService = SearchService.instance();
        mBrowseService = BrowseService.instance();
    }

    public static YouTubeMediaGroupManagerSimple instance() {
        if (sInstance == null) {
            sInstance = new YouTubeMediaGroupManagerSimple();
        }

        return sInstance;
    }

    public static void unhold() {
        sInstance = null;
    }

    @Override
    public MediaGroup getSearchGroup(String searchText) {
        SearchResult searchResult = mSearchService.getSearch(searchText);
        return YouTubeMediaGroup.from(searchResult, MediaGroup.TYPE_SEARCH);
    }

    @Override
    public Observable<MediaGroup> getSearchGroupObserve(String searchText) {
        return Observable.fromCallable(() -> YouTubeMediaGroup.from(mSearchService.getSearch(searchText), MediaGroup.TYPE_SEARCH));
    }

    @Override
    public MediaGroup getRecommendedGroup() {
        List<MediaGroup> tabs = getFirstHomeGroups();

        return tabs.get(0); // first one is Recommended tab
    }

    @Override
    public Observable<MediaGroup> getRecommendedGroupObserve() {
        return Observable.fromCallable(this::getRecommendedGroup);
    }

    @Override
    public MediaGroup getHomeGroup() {
        List<MediaGroup> result = new ArrayList<>();

        List<MediaGroup> tabs = getFirstHomeGroups();

        while (!tabs.isEmpty()) {
            result.addAll(tabs);
            tabs = getNextHomeGroups();
        }

        return YouTubeMediaGroup.from(result, MediaGroup.TYPE_HOME);
    }

    @Override
    public Observable<MediaGroup> getHomeGroupObserve() {
        return Observable.create(emitter -> {
            List<MediaGroup> tabs = getFirstHomeGroups();

            while (!tabs.isEmpty()) {
                emitter.onNext(YouTubeMediaGroup.from(tabs, MediaGroup.TYPE_HOME));
                tabs = getNextHomeGroups();
            }

            emitter.onComplete();
        });
    }

    private List<MediaGroup> getFirstHomeGroups() {
        Log.d(TAG, "Emitting first home tabs...");
        List<BrowseSection> browseTabs = mBrowseService.getHomeSections();
        return YouTubeMediaGroup.from(browseTabs);
    }

    private List<MediaGroup> getNextHomeGroups() {
        Log.d(TAG, "Emitting next home tabs...");
        List<BrowseSection> browseTabs = mBrowseService.getNextHomeSections();
        return YouTubeMediaGroup.from(browseTabs);
    }

    @Override
    public MediaGroup continueGroup(MediaGroup mediaTab) {
        Log.d(TAG, "Continue tab " + mediaTab.getTitle() + "...");
        return YouTubeMediaGroup.from(
                mBrowseService.continueSection(YouTubeMediaServiceHelper.extractNextKey(mediaTab)),
                mediaTab
        );
    }

    @Override
    public Observable<MediaGroup> continueGroupObserve(MediaGroup mediaTab) {
        return Observable.create(emitter -> {
            MediaGroup newMediaTab = continueGroup(mediaTab);

            if (newMediaTab != null) {
                emitter.onNext(newMediaTab);
            }

            emitter.onComplete();
        });
    }

    // SHOULD BE EMPTY FOR UNSIGNED

    @Override
    public MediaGroup getSubscriptionsGroup() {
        return YouTubeMediaGroup.EMPTY_GROUP;
    }

    @Override
    public MediaGroup getHistoryGroup() {
        return YouTubeMediaGroup.EMPTY_GROUP;
    }

    @Override
    public Observable<MediaGroup> getSubscriptionsGroupObserve() {
        return Observable.fromCallable(this::getSubscriptionsGroup);
    }

    @Override
    public Observable<MediaGroup> getHistoryGroupObserve() {
        return Observable.fromCallable(this::getHistoryGroup);
    }
}
