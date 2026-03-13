package tfcreborncore.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import tfcreborncore.Tags;

public class RCLogger {

    public static Logger log = LogManager.getLogger(Tags.MODNAME);

    private RCLogger() {}

    public static void init(@NotNull Logger modLogger) {
        log = modLogger;
    }
}
