package io.github.kloping.qqbot.plugins;

import io.github.kloping.qqbot.entities.ex.MessageAsyncBuilder;

/**
 * 插件
 *
 * @author github.kloping
 */
public interface PluginsInfo {
	MessageAsyncBuilder dealmsg(String content);
}
