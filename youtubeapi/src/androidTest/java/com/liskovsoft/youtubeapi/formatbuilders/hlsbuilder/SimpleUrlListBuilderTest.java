package com.liskovsoft.youtubeapi.formatbuilders.hlsbuilder;

import com.liskovsoft.mediaserviceinterfaces.data.MediaItemFormatInfo;
import com.liskovsoft.mediaserviceinterfaces.MediaService;
import com.liskovsoft.sharedutils.okhttp.OkHttpManager;
import com.liskovsoft.youtubeapi.common.helpers.TestHelpers;
import com.liskovsoft.youtubeapi.service.YouTubeMediaService;
import okhttp3.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SimpleUrlListBuilderTest {
    private MediaService mService;

    @Before
    public void setUp() {
        mService = YouTubeMediaService.instance();
    }

    @Test
    public void testThatUrlListNotEmpty() {
        testUrlList(TestHelpers.VIDEO_ID_SIMPLE);
    }

    @Test
    public void testThatCipheredUrlListNotEmpty() {
        testUrlList(TestHelpers.VIDEO_ID_CIPHERED);
    }

    private void testUrlList(String videoId) {
        MediaItemFormatInfo mediaItemDetails = mService.getMediaItemManager().getFormatInfo(videoId);

        List<String> urlList = mediaItemDetails.getUrlList();

        assertNotNull("Url list not empty", urlList);

        for (String url : urlList) {
            assertTrue("Video url is working", TestHelpers.urlExists(url));
        }
    }
}