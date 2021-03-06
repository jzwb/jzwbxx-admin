package com.jzwbxx.service.impl;

import com.jzwbxx.dao.PluginConfigDao;
import com.jzwbxx.model.PluginConfig;
import com.jzwbxx.service.PluginConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 插件配置
 */
@Service
public class PluginConfigServiceImpl extends BaseServiceImpl<PluginConfig, Long> implements PluginConfigService {

	@Autowired
	private PluginConfigDao pluginConfigDao;

	@Autowired
	public void setBaseDao(PluginConfigDao pluginConfigDao) {
		super.setBaseDao(pluginConfigDao);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean pluginIdExists(String pluginId) {
		return pluginConfigDao.pluginIdExists(pluginId);
	}

	@Override
	@Transactional(readOnly = true)
	public PluginConfig findByPluginId(String pluginId) {
		return pluginConfigDao.findByPluginId(pluginId);
	}

}