package ru.byters.azure;

import java.util.List;

public interface AzureThrowListener {
    <T> void OnDownloadCompleted(String tablename, List<T> result, Boolean error);

    void OnUploadCompleted(String tablename, Boolean error);
}
