package dev.toby7002.exampleplugin

import cn.nukkit.plugin.PluginBase

class ExamplePlugin: PluginBase() {
    override fun onEnable() {
        logger.info("It works")
    }
}