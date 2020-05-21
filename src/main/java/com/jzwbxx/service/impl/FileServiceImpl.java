package com.jzwbxx.service.impl;

import com.jzwbxx.common.FileInfo;
import com.jzwbxx.common.Setting;
import com.jzwbxx.plugin.StoragePlugin;
import com.jzwbxx.service.FileService;
import com.jzwbxx.service.PluginService;
import com.jzwbxx.util.FreemarkerUtils;
import com.jzwbxx.util.SettingUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Service - 文件上传
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private PluginService pluginService;
    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    @Override
    public boolean isValid(FileInfo.FileType fileType, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return false;
        }
        Setting setting = SettingUtils.get();
        if (setting.getUploadImageMaxSize() != null && setting.getUploadImageMaxSize() != 0
                && multipartFile.getSize() > setting.getUploadImageMaxSize() * 1024L * 1024L) {
            return false;
        }
        String[] uploadExtensions;
        if (fileType == FileInfo.FileType.image) {
            uploadExtensions = setting.getUploadImageExtensions();
        } else {
            uploadExtensions = setting.getUploadImageExtensions();
        }
        if (ArrayUtils.isNotEmpty(uploadExtensions)) {
            return FilenameUtils.isExtension(multipartFile.getOriginalFilename().toLowerCase(), uploadExtensions);
        }
        return false;
    }

    @Override
    public String upload(FileInfo.FileType fileType, MultipartFile multipartFile) {
        try {
            if (fileType == null || multipartFile == null) {
                return null;
            }
            String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());//扩展名
            File tempFile = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID() + ".tmp");
            if (!tempFile.getParentFile().exists()) {
                tempFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(tempFile);
            return baseUpload(fileType, tempFile, null, fileExtension, null, false);
        } catch (Exception e) {
            LOGGER.error("上传文件异常", e);
        }
        return null;
    }

    /**
     * 获取上传路径
     *
     * @param fileType 文件类型
     * @return
     */
    private String getUploadPath(FileInfo.FileType fileType) {
        Setting setting = SettingUtils.get();
        if (fileType == FileInfo.FileType.image) {
            return setting.getUploadImagePath();
        } else {
            return setting.getUploadImagePath();
        }
    }

    /**
     * 基础上传方法
     *
     * @param fileType      文件类型
     * @param file          文件
     * @param filename      文件名
     * @param fileExtension 文件扩展名
     * @param uploadPath    上传路径
     * @param async         是否异步
     * @return
     */
    private String baseUpload(FileInfo.FileType fileType, File file, String filename, String fileExtension, String uploadPath, boolean async) {
        try {
            if (fileType == null || file == null || StringUtils.isBlank(fileExtension)) {
                return null;
            }
            if (StringUtils.isBlank(uploadPath)) {
                Map<String, Object> map = new HashMap<>();
                uploadPath = FreemarkerUtils.process(getUploadPath(fileType), map);
            }
            if (StringUtils.isBlank(filename)) {
                filename = UUID.randomUUID().toString();
            }
            String path = uploadPath + filename + "." + fileExtension;
            for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)) {
                if (async) {
                    addTask(storagePlugin, path, file, fileExtension);
                } else {
                    storagePlugin.upload(path, file, fileExtension);
                }
                return storagePlugin.getUrl(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步上传任务
     *
     * @param storagePlugin 存储插件
     * @param path          路径
     * @param file          文件
     * @param fileExtension 文件扩展名
     */
    private void addTask(final StoragePlugin storagePlugin, final String path, final File file, final String fileExtension) {
        taskExecutor.execute(() -> {
            try {
                storagePlugin.upload(path, file, fileExtension);
            } finally {
                if (file != null && file.exists() && file.isFile()) {
                    FileUtils.deleteQuietly(file);
                }
            }
        });
    }
}