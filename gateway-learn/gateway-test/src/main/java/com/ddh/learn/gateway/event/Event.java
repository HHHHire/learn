package com.ddh.learn.gateway.event;

public interface Event {
    void onBeforeUpload();
    void onBeforeDownload();
}
