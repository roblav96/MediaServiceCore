package com.liskovsoft.youtubeapi.service;

import com.liskovsoft.mediaserviceinterfaces.FormatInfo;
import com.liskovsoft.mediaserviceinterfaces.MediaFormat;
import com.liskovsoft.youtubeapi.videoinfo.models.VideoInfoResult;
import com.liskovsoft.youtubeapi.videoinfo.models.formats.AdaptiveVideoFormat;

import java.util.ArrayList;

public class YouTubeFormatInfo implements FormatInfo {
    private ArrayList<MediaFormat> mAdaptiveFormats;

    public static FormatInfo from(VideoInfoResult videoInfo) {
        YouTubeFormatInfo formatInfo = new YouTubeFormatInfo();

        if (videoInfo.getAdaptiveFormats() != null) {
            formatInfo.mAdaptiveFormats = new ArrayList<>();

            for (AdaptiveVideoFormat format : videoInfo.getAdaptiveFormats()) {
                formatInfo.mAdaptiveFormats.add(YouTubeMediaFormat.from(format));
            }
        }

        return formatInfo;
    }

    @Override
    public ArrayList<MediaFormat> getAdaptiveFormats() {
        return mAdaptiveFormats;
    }
}
