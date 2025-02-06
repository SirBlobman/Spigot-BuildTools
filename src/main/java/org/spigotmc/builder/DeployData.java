package org.spigotmc.builder;

import java.io.File;

public final class DeployData {
    private final File fileName;
    private final String packaging;
    private final String groupId;
    private final String artifactId;
    private final String version;
    private final String classifier;
    private final DeployType type;

    public DeployData(File fileName, String packaging, String groupId, String artifactId, String version, String classifier) {
        this.fileName = fileName;
        this.packaging = packaging;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.type = (this.classifier == null ? DeployType.MAIN : DeployType.SUB);
    }

    public String getFileName() {
        return fileName.toString();
    }

    public String getPackaging() {
        return packaging;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public String getClassifier() {
        return classifier;
    }

    public DeployType getType() {
        return type;
    }

    public enum DeployType {
        MAIN, SUB
    }
}
