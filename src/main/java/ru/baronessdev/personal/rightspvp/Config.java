package ru.baronessdev.personal.rightspvp;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static ru.baronessdev.personal.rightspvp.RightsPvP.inst;

public final class Config {
    private static final YamlConfiguration configuration = new YamlConfiguration();

    private Config() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {
        String path = inst.getDataFolder() + File.separator + "config.yml";
        File file = new File(path);

        if (!file.exists()) {
            inst.saveResource("config.yml", true);
        }

        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String path) {
        String message = configuration.getString(path);

        if (message != null) {
            return ChatColor.translateAlternateColorCodes('&', message);
        }

        return "";
    }

    public static List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    public static boolean getBoolean(String path) {
        return configuration.getBoolean(path);
    }
}
