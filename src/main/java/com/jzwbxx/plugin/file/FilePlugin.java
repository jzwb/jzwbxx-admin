package com.jzwbxx.plugin.file;

import com.jzwbxx.common.FileInfo;
import com.jzwbxx.plugin.StoragePlugin;
import com.jzwbxx.util.PathUtils;
import com.jzwbxx.util.SettingUtils;
import com.jzwbxx.util.Tools;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *  插件 - 存储 - 本地文件
 */
@Component("filePlugin")
public class FilePlugin extends StoragePlugin {

    @Override
    public String getName() {
        return "本地文件存储";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getSiteUrl() {
        return SettingUtils.get().getSiteUrl();
    }

    @Override
    public String getInstallUrl() {
        return "/admin/storage_plugin/file/install/";
    }

    @Override
    public String getUninstallUrl() {
        return "/admin/storage_plugin/file/uninstall/";
    }

    @Override
    public String getSettingUrl() {
        return "/admin/storage_plugin/file/setting/";
    }

    @Override
    public void upload(String path, File file, String contentType) {
        path = Tools.removeDomain(path);
        File destFile = new File(PathUtils.getRootPath(path));
        try {
            if (file != null && file.exists() && file.isFile()) {
                FileUtils.moveFile(file, destFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUrl(String path) {
        path = Tools.removeDomain(path);
        return PathUtils.getStaticUrl(path);
    }

    @Override
    public List<FileInfo> browser(String path) {
        return Collections.emptyList();
    }

    @Override
    public File getFile(String path) {
        return null;
    }

    @Override
    public String zoom(String srcFile, int destWidth, int destHeight) {
        return srcFile;
    }

    @Override
    public double calculateFileSize(String path) {
        return 0;
    }

    @Override
    public String resplace(String newPath, String oldPath) {
        return oldPath;
    }

    @Override
    public void deleteFile(String path) {
    }
}