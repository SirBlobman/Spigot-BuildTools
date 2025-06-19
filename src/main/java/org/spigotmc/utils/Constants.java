package org.spigotmc.utils;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/**
 * Constants Class
 */
public final class Constants {

    public static final String LOG_FILE = "BuildTools.log.txt";

    public static final ImageIcon ERROR = Utils.getIcon("error.png");
    public static final ImageIcon WARNING = Utils.getIcon("warning.png");
    public static final ImageIcon SUCCESS = Utils.getIcon("success.png");
    public static final ImageIcon LOGO = Utils.getIcon("logo.png");
    public static final ImageIcon LOGO_EASTER_EGG = Utils.getIcon("logo_easter_egg.png");
    public static final ImageIcon LOGO_ABOUT_EASTER_EGG = Utils.getIcon("logo_about_easter_egg.png");
    public static final ImageIcon LOGO_32x32 = Utils.getIcon("logo_32x32.png");

    public static final Color ACCENT_COLOR = new Color(255, 136, 0);
    public static final Color ACCENT_COLOR_DESATURATED = new Color(255, 136, 0, 128);

    public static final Color EXPERIMENTAL_WARNING_TEXT_PRIMARY = new Color( 255, 61, 61 );

    public static final Font GENERAL_FONT = new Font("Liberation Sans", Font.PLAIN, 12);

    public static final int GENERAL_TIMEOUT_MS = 30_000;

    public static final String WEBSITE_LINK = "https://spigotmc.org";
    public static final String PLUGINS_LINK = "https://spigotmc.org/resources";
    public static final String DONATE_LINK = "https://spigotmc.org/pages/donate";
    public static final String DISCORD_LINK = "https://spigotmc.org/go/discord";
    public static final String STASH_LINK = "https://hub.spigotmc.org/stash";
    public static final String WIKI_LINK = "https://www.spigotmc.org/wiki/buildtools/";
    public static final String BUG_TRACKER_LINK = "https://hub.spigotmc.org/jira/secure/Dashboard.jspa";

    public static final String DOWNLOAD_OPENJDK = "https://adoptium.net/temurin/releases/";
    public static final String DOWNLOAD_AZUL = "https://www.azul.com/downloads/#zulu";
    public static final String DOWNLOAD_ORACLE = "https://www.oracle.com/java/technologies/downloads/";

    public static final String MAVEN_VERSION = "3.9.6";
    public static final String MAVEN_HASH = "0eb0432004a91ebf399314ad33e5aaffec3d3b29279f2f143b2f43ade26f4db7bd1c0f08e436e9445ac6dc4a564a2945d13072a160ae54a930e90581284d6461";
    // Derived
    public static final String MAVEN_FOLDER = "apache-maven-" + MAVEN_VERSION;
    public static final String MAVEN_FILE = MAVEN_FOLDER + "-bin.zip";
    public static final String MAVEN_DOWNLOAD = "https://archive.apache.org/dist/maven/maven-3/" + MAVEN_VERSION + "/binaries/" + MAVEN_FILE;

    public static final String FLAG_EXPERIMENTAL = "--experimental";
    public static final String FLAG_OUTPUT_DIR = "--output-dir";
    public static final String FLAG_FINAL_NAME = "--final-name";
    public static final String FLAG_VERSION = "--rev";
    public static final String FLAG_PR = "--pr";
    public static final String FLAG_REMAPPED = "--remapped";
    public static final String FLAG_DISABLE_HTTPS_CHECK = "--disable-certificate-check";
    public static final String FLAG_DISABLE_JAVA_CHECK = "--disable-java-check";
    public static final String FLAG_DONT_UPDATE = "--dont-update";
    public static final String FLAG_GENERATE_SOURCE = "--generate-source";
    public static final String FLAG_GENERATE_DOCS = "--generate-docs";
    public static final String FLAG_COMPILE_IF_CHANGED = "--compile-if-changed";
    public static final String FLAG_COMPILE = "--compile";
    public static final String FLAG_NOGUI = "--nogui";

    public static final String LATEST_WARNING = "NOTE: 'latest' refers to the latest stable version and may not necessarily correlate to the absolute newest version available!";

    public static final String EXPERIMENTAL_WARNING = "WARNING: Experimental builds should not be run in production! You will NOT receive support if you use an experimental jar to run your server!! YOU HAVE BEEN WARNED!!!";

    public static final String VERSION_SCHEME_NORMAL = "spigot-%version%.jar";
    public static final String VERSION_SCHEME_EXPERIMENTAL = "spigot-experimental.jar";

    public static final String SPECIAL_CHARACTERS_WARNING = "Please do not run BuildTools in a path with special characters!";
    public static final String NOT_RUN_IN_BASH_WARNING = "You have to run BuildTools through bash (msysgit). Please read our wiki.";
}
