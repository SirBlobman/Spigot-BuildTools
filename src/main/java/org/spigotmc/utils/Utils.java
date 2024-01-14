package org.spigotmc.utils;

import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import joptsimple.ValueConversionException;
import org.spigotmc.builder.Bootstrap;
import org.spigotmc.builder.PullRequest;
import org.spigotmc.builder.PullRequest.PullRequestConverter;
import org.spigotmc.builder.Repository;
import org.spigotmc.gui.BuildToolsGui;

public class Utils {

    public static String getFileContentsFromResource(String resource) {
        URL url = Resources.getResource(resource);
        String contents;
        try {
            contents = Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contents;
    }

    public static String getReadableStacktrace(Throwable throwable) {
        final StringBuilder builder = new StringBuilder();
        builder.append(throwable.getMessage());
        builder.append("\n");
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            builder.append(stackTraceElement.toString());
            builder.append("\n");
        }

        return builder.toString();
    }

    public static String readableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String getHexFromColor(Color color) {
        return "#" + Integer.toHexString(color.getRGB()).substring(2);
    }

    public static File getFile() {
        try {
            return new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon getIcon(String fileName) {
        try (InputStream input = BuildToolsGui.class.getClassLoader().getResourceAsStream(fileName)) {
            return new ImageIcon(ByteStreams.toByteArray(input));
        } catch (IOException ignored) {
            // ignored
        }

        return null;
    }

    public static <T> T randomElement(T normal, T easterEgg, int oneOver) {
        return ThreadLocalRandom.current().nextInt(oneOver) == 0 ? easterEgg : normal;
    }

    public static boolean isRanFromJar() {
        String classPath = Bootstrap.class.getName().replace('.', '/');
        String classJar = Bootstrap.class.getResource("/" + classPath + ".class").toString();
        if (classJar.startsWith("jar:")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isRanFromCommandLine() {
        // If a console is available, we're definitely running from command line.
        if (System.console() != null) {
            return true;
        }

        // Naive check for running from command line on Windows.
        // If ran from command line or inside most IDEs, the SESSIONNAME environment variable will be set to "Console".
        // It will be unset when double-clicking the .jar or when using other OSes
        if ("Console".equals(System.getenv("SESSIONNAME"))) {
            return true;
        }

        return false;
    }

    public static boolean isValidPR(String input, Repository repository) {
        PullRequest request = null;

        try {
            request = new PullRequestConverter().convert(input);
        } catch (ValueConversionException ignored) { }

        if (request == null) {
            return false;
        }

        return request.getRepository() == repository;
    }

    public static boolean isValidSpigotPR(String input) {
        return isValidPR(input, Repository.SPIGOT);
    }

    public static boolean isValidBukkitPR(String input) {
        return isValidPR(input, Repository.BUKKIT);
    }

    public static boolean isValidCraftbukkitPR(String input) {
        return isValidPR(input, Repository.CRAFTBUKKIT);
    }


    public static boolean isValidPath(String input) {
        File file = new File(input);
        if (!file.isDirectory()) {
            return false;
        }

        if (!file.canWrite()) {
            return false;
        }

        return true;
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
