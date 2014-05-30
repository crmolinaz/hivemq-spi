/*
 * Copyright 2013 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dcsquare.hivemq.spi.util;

import com.google.common.io.Files;
import com.netflix.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Useful utilities when dealing with paths and folders
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class PathUtils {

    private static Logger log = LoggerFactory.getLogger(PathUtils.class);


    /**
     * @return the home folder of HiveMQ
     */
    public static File getHiveMQHomeFolder() {

        final String homeFolder = System.getProperty("hivemq.home");

        if (homeFolder == null) {
            final File tempDir = Files.createTempDir();
            log.warn("No hivemq.home property was set. Using a temporary directory ({}) HiveMQ will behave unexpectedly!", tempDir.getAbsolutePath());
            return tempDir;
        }
        return new File(homeFolder);
    }

    /**
     * @return the plugin folder of HiveMQ
     */
    public static File getPluginFolder() {
        // We are asking Archaius directly for the Plugin folder since we cannot
        // use dependency injection in static methods
        final String pluginFolder = ConfigurationManager.getConfigInstance().getString("plugin.folder");
        return findAbsoluteAndRelative(pluginFolder);
    }

    /**
     * @return the config folder of HiveMQ
     */
    public static File getHiveMQConfigFolder() {
        return findAbsoluteAndRelative("conf");
    }

    /**
     * @return the log folder of HiveMQ
     */
    public static File getHiveMQLogFolder() {
        return new File(getHiveMQHomeFolder(), "log");
    }

    /**
     * Tries to find a file in the given absolute path or
     * relative to the HiveMQ home folder
     *
     * @param fileLocation the absolute or relative path
     * @return a file
     */
    public static File findAbsoluteAndRelative(final String fileLocation) {
        final File file = new File(fileLocation);
        if (file.isAbsolute()) {
            return file;
        } else {
            return new File(getHiveMQHomeFolder(), fileLocation);
        }
    }
}
