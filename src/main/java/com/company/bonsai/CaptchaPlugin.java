package com.company.bonsai;

import com.company.bonsai.plugin.Plugin;
import com.company.bonsai.plugin.PluginConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptchaPlugin implements Plugin {

    private static final Logger LOG = LoggerFactory.getLogger(CaptchaPlugin.class);

    public CaptchaPlugin() {
        LOG.debug("Captcha plugin started");
    }

    public String getCaptcha(String source) {
        return "Captcha plugin " + source;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getName() {
        return "captcha";
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return null;
    }

    @Override
    public void setConfiguration(PluginConfiguration configuration) {

    }
}
