package com.frame.base.utl.util.media;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

/**
 * Created by YANGQIYUN on 2014/7/9.
 */
public class MediaScanner {

    private MediaScannerConnection mediaScanConn = null;
    private MusicSannerClient client = null;
    private String filePath = null;
    private String fileType = null;
    private String[] filePaths = null;

    /**
     * 然后调用MediaScanner.scanFile("/sdcard/2.mp3");
     */
    public MediaScanner(Context context) {
        //创建MusicSannerClient
        if (client == null) {
            client = new MusicSannerClient();
        }

        if (mediaScanConn == null) {
            mediaScanConn = new MediaScannerConnection(context, client);
        }
    }

    /**
     * 扫描文件标签信息
     *
     * @param filePath 文件路径 eg:/sdcard/MediaPlayer/dahai.mp3
     * @param fileType 文件类型 eg: audio/mp3  media/*  application/ogg
     */
    public void scanFile(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
        //连接之后调用MusicSannerClient的onMediaScannerConnected()方法
        mediaScanConn.connect();
    }

    /**
     * @param filePaths 文件路径
     * @param fileType  文件类型
     */
    public void scanFile(String[] filePaths, String fileType) {
        this.filePaths = filePaths;
        this.fileType = fileType;
        mediaScanConn.connect();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    class MusicSannerClient implements MediaScannerConnection.MediaScannerConnectionClient {
        public void onMediaScannerConnected() {
            if (filePath != null) {
                mediaScanConn.scanFile(filePath, fileType);
            }

            if (filePaths != null) {
                for (String file : filePaths) {
                    mediaScanConn.scanFile(file, fileType);
                }
            }

            filePath = null;
            fileType = null;
            filePaths = null;
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            mediaScanConn.disconnect();
        }

    }

}
