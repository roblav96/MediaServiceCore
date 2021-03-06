package com.liskovsoft.mediaserviceinterfaces;

import com.liskovsoft.mediaserviceinterfaces.data.MediaItem;
import com.liskovsoft.mediaserviceinterfaces.data.MediaItemFormatInfo;
import com.liskovsoft.mediaserviceinterfaces.data.MediaItemMetadata;

import java.io.InputStream;
import java.util.List;

public interface MediaItemManager {
    MediaItemFormatInfo getFormatInfo(MediaItem item);
    MediaItemFormatInfo getFormatInfo(String videoId);
    MediaItemMetadata getMetadata(MediaItem item);
    MediaItemMetadata getMetadata(String videoId);
}
