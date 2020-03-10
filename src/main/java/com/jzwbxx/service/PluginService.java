package com.jzwbxx.service;

import com.jzwbxx.plugin.StoragePlugin;

import java.util.List;

/**
 * Service - 插件
 */
public interface PluginService {

    /**
     * 获取存储插件
     *
     * @return
     */
    List<StoragePlugin> getStoragePlugins();

    /**
     * 获取存储插件
     * @param isEnabled 是否启用
     * @return
     */
    List<StoragePlugin> getStoragePlugins(boolean isEnabled);
}
