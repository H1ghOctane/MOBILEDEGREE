package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties"
})
public interface LocalConfig extends Config {

    @Key("local.appiumUrl")
    @DefaultValue("http://127.0.0.1:4723")
    String appiumUrl();

    @Key("local.deviceName")
    String deviceName();

    @Key("local.platformVersion")
    String platformVersion();

    @Key("local.appPath")
    String appPath();
}
