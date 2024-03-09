package org.spigotmc.utils;

import java.io.File;
import java.util.Arrays;
import joptsimple.OptionParser;
import joptsimple.OptionSpec;
import joptsimple.util.EnumConverter;
import org.spigotmc.builder.Builder;
import org.spigotmc.builder.Compile;
import org.spigotmc.builder.PullRequest;

public class Flags
{

    public static final OptionParser PARSER = new OptionParser();

    public static final OptionSpec<Void> HELP_FLAG = PARSER.accepts( "help", "Show the help" );
    public static final OptionSpec<Void> DISABLE_CERT_FLAG = PARSER.accepts( "disable-certificate-check", "Disable HTTPS certificate check" );
    public static final OptionSpec<Void> DISABLE_JAVA_CHECK_FLAG = PARSER.accepts( "disable-java-check", "Disable Java version check" );
    public static final OptionSpec<Void> DONT_UPDATE_FLAG = PARSER.accepts( "dont-update", "Don't pull updates from Git" );
    public static final OptionSpec<Void> SKIP_COMPILE_FLAG = PARSER.accepts( "skip-compile", "Skip compilation" );
    public static final OptionSpec<Void> GENERATE_SOURCE_FLAG = PARSER.accepts( "generate-source", "Generate source jar" );
    public static final OptionSpec<Void> GENERATE_DOCS_FLAG = PARSER.accepts( "generate-docs", "Generate Javadoc jar" );
    public static final OptionSpec<Void> DEV_FLAG = PARSER.accepts( "dev", "Development mode" );
    public static final OptionSpec<Void> EXPERIMENTAL_FLAG = PARSER.accepts( "experimental", "Build experimental version" );
    public static final OptionSpec<Void> REMAPPED_FLAG = PARSER.accepts( "remapped", "Produce and install extra remapped jars" );
    public static final OptionSpec<File> OUTPUT_DIR_FLAG = PARSER.acceptsAll( Arrays.asList( "o", "output-dir" ), "Final jar output directory" ).withRequiredArg().ofType( File.class ).defaultsTo( Builder.CWD );
    public static final OptionSpec<String> OUTPUT_NAME_FLAG = PARSER.accepts( "final-name", "Name of the final jar" ).withRequiredArg();
    public static final OptionSpec<String> JENKINS_VERSION_FLAG = PARSER.accepts( "rev", "Version to build" ).withRequiredArg().defaultsTo( "latest" );
    public static final OptionSpec<Compile> TO_COMPILE_FLAG = PARSER.accepts( "compile", "Software to compile" ).withRequiredArg().ofType( Compile.class ).withValuesConvertedBy( new EnumConverter<Compile>( Compile.class )
    {
    } ).withValuesSeparatedBy( ',' );
    public static final OptionSpec<Void> COMPILE_IF_CHANGED_FLAG = PARSER.accepts( "compile-if-changed", "Run BuildTools only when changes are detected in the repository" );
    public static final OptionSpec<PullRequest> BUILD_PULL_REQUEST_FLAG = PARSER.acceptsAll( Arrays.asList( "pull-request", "pr" ), "Build specific pull requests" ).withOptionalArg().withValuesConvertedBy( new PullRequest.PullRequestConverter() );
    public static final OptionSpec<Void> GUI_FLAG = PARSER.accepts( "gui", "Explicitly show the GUI" );
    public static final OptionSpec<Void> NO_GUI_FLAG = PARSER.accepts( "nogui", "Explicitly don't show the GUI" );
}
