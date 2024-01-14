package org.spigotmc.gui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class BuildToolsProcess {

    private final OutputStream output;
    private final ProcessBuilder builder;
    private Process process;
    private File workDirectory;

    public BuildToolsProcess(final List<String> args, OutputStream output, File workDirectory) {
        this.builder = new ProcessBuilder(args.toArray(new String[0])).redirectErrorStream(true);
        this.output = output;
        this.workDirectory = workDirectory;
    }

    public Process execute() throws IOException {
        if (workDirectory.exists()) {
            builder.directory(workDirectory);
        }

        process = builder.start();
        InputStream input = process.getInputStream();

        // Sadly ProcessBuilder.inheritIO does not work with System.setOut
        while (true) {
            int in = input.read();
            if (in == -1) break;
            this.output.write(in);
        }

        return process;
    }

    public Process getProcess() {
        return process;
    }

    public File getWorkDirectory() {
        return workDirectory;
    }
}
