package org.spigotmc.gui.data;

import lombok.Getter;
import lombok.Setter;
import org.spigotmc.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all build settings that can be used to alter the output of the build.
 */
@Getter
@Setter
public final class BuildSettings {

    private String version = "";
    private String finalName = "";
    private String outputDirectory = "";
    private String spigotPrId = "";
    private String craftbukkitPrId = "";
    private String bukkitPrId = "";
    private String workDirectory = "";

    private boolean remapped = false;
    private boolean generateJavadocs = false;
    private boolean generateSource = false;
    private boolean skipHttpsCheck = false;
    private boolean skipJavaVersionCheck = false;
    private boolean compileIfChanged = false;
    private boolean dontPullUpdates = false;
    private boolean compileSpigot = false;
    private boolean compileCraftbukkit = false;
    private boolean compileNone = false;
    private boolean overrideJavaExecutable = false;

    public BuildSettings() { }

    /**
     * Get all build arguments.
     *
     * @return a list of all applicable build settings
     */
    public List<String> getArguments() {
        List<String> args = new ArrayList<>();

        args.add(Constants.FLAG_NOGUI); // We definitely don't want the GUI for the child builder process

        if (version.equals("experimental")) {
            args.add(Constants.FLAG_EXPERIMENTAL);

            if (!(outputDirectory == null || outputDirectory.isEmpty())) {
                args.add(Constants.FLAG_OUTPUT_DIR);
                args.add(outputDirectory);
            }


            if (!finalName.isEmpty()) {
                args.add(Constants.FLAG_FINAL_NAME);
                args.add(finalName);
            }

            return args;
        }

        // `--rev latest` isn't needed for default behavior and will actually interfere with some other flags.
        // This will only show up if the user selects `latest` from the dropdown menu.
        // This also keeps future updates open for expandability. (Specifying specific build numbers)
        if (!version.equals("latest")) {
            args.add(Constants.FLAG_VERSION);
            args.add(version);
        }

        if (!spigotPrId.isEmpty()) {
            args.add(Constants.FLAG_PR);
            args.add(spigotPrId);
        }

        if (!bukkitPrId.isEmpty()) {
            args.add(Constants.FLAG_PR);
            args.add(bukkitPrId);
        }

        if (!craftbukkitPrId.isEmpty()) {
            args.add(Constants.FLAG_PR);
            args.add(craftbukkitPrId);
        }

        if (remapped) {
            args.add(Constants.FLAG_REMAPPED);
        }
        if (generateJavadocs) {
            args.add(Constants.FLAG_GENERATE_DOCS);
        }
        if (generateSource) {
            args.add(Constants.FLAG_GENERATE_SOURCE);
        }
        if (skipHttpsCheck) {
            args.add(Constants.FLAG_DISABLE_HTTPS_CHECK);
        }
        if (skipJavaVersionCheck) {
            args.add(Constants.FLAG_DISABLE_JAVA_CHECK);
        }
        if (compileIfChanged) {
            args.add(Constants.FLAG_COMPILE_IF_CHANGED);
        }
        if (dontPullUpdates) {
            args.add(Constants.FLAG_DONT_UPDATE);
        }

        if (compileNone) {
            args.add(Constants.FLAG_COMPILE);
            args.add("NONE");
        } else if (compileSpigot && compileCraftbukkit) {
            args.add(Constants.FLAG_COMPILE);
            args.add("SPIGOT,CRAFTBUKKIT");
        } else if (compileSpigot) {
            args.add(Constants.FLAG_COMPILE);
            args.add("SPIGOT");
        } else if (compileCraftbukkit) {
            args.add(Constants.FLAG_COMPILE);
            args.add("CRAFTBUKKIT");
        }

        if (!(outputDirectory == null || outputDirectory.isEmpty())) {
            args.add(Constants.FLAG_OUTPUT_DIR);
            args.add(outputDirectory);
        }

        if (!finalName.isEmpty()) {
            args.add(Constants.FLAG_FINAL_NAME);
            args.add(finalName);
        }

        return args;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String directory) {
        this.outputDirectory = directory;
    }

    public void setCompile(boolean spigot, boolean craftbukkit) {
        this.compileNone = !(spigot || craftbukkit);
        this.compileSpigot = spigot;
        this.compileCraftbukkit = craftbukkit;
    }

    public void setCompile(boolean none) {
        this.compileNone = none;
    }

    public void setFinalName(String finalName) {
        if (finalName.isEmpty()) {
            this.finalName = finalName;
            return;
        }

        if (finalName.startsWith(".")) {
            this.finalName = "";
            return;
        }

        if (!finalName.endsWith(".jar")) {
            finalName += ".jar";
        }
        this.finalName = finalName;
    }
}
