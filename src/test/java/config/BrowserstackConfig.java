package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:~/browserstack.properties",
        "classpath:config/browserstack.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("bs.username")
    String username();

    @Key("bs.accessKey")
    String accessKey();

    @Key("bs.remoteUrl")
    @DefaultValue("https://hub.browserstack.com/wd/hub")
    String remoteUrl();

    @Key("bs.appUrl")
    String appUrl();

    @Key("bs.deviceName")
    String deviceName();

    @Key("bs.osVersion")
    String osVersion();
}
