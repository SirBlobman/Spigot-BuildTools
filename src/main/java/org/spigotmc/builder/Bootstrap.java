package org.spigotmc.builder;

import com.google.common.base.Joiner;
import java.awt.GraphicsEnvironment;
import joptsimple.OptionSet;
import org.spigotmc.gui.BuildToolsGui;
import org.spigotmc.utils.Flags;
import org.spigotmc.utils.SwingUtils;
import org.spigotmc.utils.Utils;

public class Bootstrap
{

    private static boolean guiEnabled = false;

    public static void main(String[] args) throws Exception
    {
        OptionSet options = Flags.PARSER.parse( args );

        if ( ( args.length == 0 && !Utils.isRanFromCommandLine() ) || options.has( Flags.GUI_FLAG ) )
        {
            if ( !options.has( Flags.NO_GUI_FLAG ) )
            {
                if ( !GraphicsEnvironment.isHeadless() )
                {
                    guiEnabled = true;

                    SwingUtils.applyInitialTheme();

                    BuildToolsGui gui = new BuildToolsGui();
                    gui.setLocationRelativeTo( null );
                    gui.setVisible( true );
                } else
                {
                    System.err.println( "Headless environment detected, BuildTools GUI unavailable." );
                }
            }
        }

        if ( !guiEnabled )
        {
            JavaVersion javaVersion = JavaVersion.getCurrentVersion();

            if ( javaVersion.isUnknown() )
            {
                System.err.println( "*** WARNING *** Unsupported Java detected (" + System.getProperty( "java.class.version" ) + "). BuildTools has only been tested up to Java 21. Use of development Java versions is not supported." );
                System.err.println( "*** WARNING *** You may use java -version to double check your Java version." );
            }

            long memoryMb = Runtime.getRuntime().maxMemory() >> 20;
            if ( memoryMb < 448 ) // Older JVMs (including Java 8) report less than Xmx here. Allow some slack for people actually using -Xmx512M
            {
                System.err.println( "BuildTools requires at least 512M of memory to run (1024M recommended), but has only detected " + memoryMb + "M." );
                System.err.println( "This can often occur if you are running a 32-bit system, or one with low RAM." );
                System.err.println( "Please re-run BuildTools with manually specified memory, e.g: java -Xmx1024M -jar BuildTools.jar " + Joiner.on( ' ' ).join( args ) );
                System.exit( 1 );
            }

            Builder.logOutput( System.out, System.err );
            Builder.startBuilder( args, options );
        }
    }
}
