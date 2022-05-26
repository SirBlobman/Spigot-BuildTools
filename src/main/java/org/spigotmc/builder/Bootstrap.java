package org.spigotmc.builder;

public final class Bootstrap {
    public static void main(String[] args) throws Exception {
        JavaVersion javaVersion = JavaVersion.getCurrentVersion();

        if ( javaVersion.isUnknown() )
        {
            System.err.println( "*** WARNING *** Unsupported Java detected (" + System.getProperty( "java.class.version" ) + "). BuildTools has only been tested up to Java 18. Use of development Java versions is not supported." );
            System.err.println( "*** WARNING *** You may use java -version to double check your Java version." );
        }
        
        long memoryBytes = Runtime.getRuntime().maxMemory();
        long memoryMb = (memoryBytes >> 20);
        if(memoryMb < 448) {
            printError("BuildTools requires at least 512M of memory to run (1024M recommended),"
                    + "but has only detected " + memoryMb + "M.");
            printError("This can often occur if you are running a 32-bit system, or one with low RAM.");
            printError("Please re-run BuildTools with manually specified memory, e.g: "
                    + "'java -Xmx1024M -jar BuildTools.jar " + String.join(" ", args) + "'");
            System.exit(1);
        }
        
        Builder.main(args);
    }
    
    private static void printError(String message) {
        System.err.println(message);
    }
}
