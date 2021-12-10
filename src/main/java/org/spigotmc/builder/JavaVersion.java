package org.spigotmc.builder;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class JavaVersion {
    private static final Map<Integer, JavaVersion> byVersion;
    public static final JavaVersion JAVA_5;
    public static final JavaVersion JAVA_6;
    public static final JavaVersion JAVA_7;
    public static final JavaVersion JAVA_8;
    public static final JavaVersion JAVA_9;
    public static final JavaVersion JAVA_10;
    public static final JavaVersion JAVA_11;
    public static final JavaVersion JAVA_12;
    public static final JavaVersion JAVA_13;
    public static final JavaVersion JAVA_14;
    public static final JavaVersion JAVA_15;
    public static final JavaVersion JAVA_16;
    public static final JavaVersion JAVA_17;
    
    static {
        byVersion = new HashMap<>();
        JAVA_5 = new JavaVersion("Java 5", 49);
        JAVA_6 = new JavaVersion("Java 6", 50);
        JAVA_7 = new JavaVersion("Java 7", 51);
        JAVA_8 = new JavaVersion("Java 8", 52);
        JAVA_9 = new JavaVersion("Java 9", 53);
        JAVA_10 = new JavaVersion("Java 10", 54);
        JAVA_11 = new JavaVersion("Java 11", 55);
        JAVA_12 = new JavaVersion("Java 12", 56);
        JAVA_13 = new JavaVersion("Java 13", 57);
        JAVA_14 = new JavaVersion("Java 14", 58);
        JAVA_15 = new JavaVersion("Java 15", 59);
        JAVA_16 = new JavaVersion("Java 16", 60);
        JAVA_17 = new JavaVersion("Java 17", 61);
    }
    
    private final String name;
    private final int version;
    private final boolean unknown;
    
    private JavaVersion(String name, int version) {
        this(name, version, false);
    }
    
    private JavaVersion(String name, int version, boolean unknown) {
        this.name = name;
        this.version = version;
        this.unknown = unknown;
        
        byVersion.put(version, this);
    }
    
    public static JavaVersion getByVersion(int version) {
        JavaVersion java = byVersion.get(version);
        if(java == null) {
            java = new JavaVersion("Java " + (version - 44) + "*", version, true);
        }
        
        return java;
    }
    
    public static JavaVersion getCurrentVersion() {
        return getByVersion((int) Float.parseFloat(System.getProperty("java.class.version")));
    }
    
    public static String printVersions(int[] versions) {
        StringBuilder sb = new StringBuilder();
        
        sb.append('[');
        for(int v : versions) {
            JavaVersion found = getByVersion(v);
            sb.append(found);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(']');
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
