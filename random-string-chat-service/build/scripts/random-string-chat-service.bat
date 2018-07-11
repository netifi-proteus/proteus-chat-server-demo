@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  random-string-chat-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and RANDOM_STRING_CHAT_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-XX:+UnlockExperimentalVMOptions" "-XX:+UseCGroupMemoryLimitForHeap" "-XX:MaxRAMFraction=1" "-XX:+UseG1GC" "-XX:MaxGCPauseMillis=200" "-XX:+AlwaysPreTouch" "-XX:+UseStringDeduplication" "-XX:+ExplicitGCInvokesConcurrent" "-XX:+ParallelRefProcEnabled" "-XX:HeapDumpPath=/" "-XX:+PrintGCDateStamps" "-verbose:gc" "-XX:+PrintGCDetails" "-Xloggc:gc.log" "-XX:+UseGCLogFileRotation" "-XX:NumberOfGCLogFiles=10" "-XX:GCLogFileSize=100M" "-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\random-string-chat-service-1.0-SNAPSHOT.jar;%APP_HOME%\lib\chat-idl-1.0-SNAPSHOT.jar;%APP_HOME%\lib\random-string-idl-1.0-SNAPSHOT.jar;%APP_HOME%\lib\core-0.7.18.jar;%APP_HOME%\lib\protobuf-java-3.6.0.jar;%APP_HOME%\lib\log4j-core-2.8.2.jar;%APP_HOME%\lib\log4j-slf4j-impl-2.8.2.jar;%APP_HOME%\lib\log4j-api-2.8.2.jar;%APP_HOME%\lib\frames-0.7.18.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\rsocket-transport-netty-0.11.3.jar;%APP_HOME%\lib\rsocket-core-0.11.3.jar;%APP_HOME%\lib\micrometer-core-1.0.3.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\reactor-netty-0.7.7.RELEASE.jar;%APP_HOME%\lib\netty-handler-4.1.24.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.24.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.24.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.24.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.24.Final.jar;%APP_HOME%\lib\netty-codec-4.1.24.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.24.Final.jar;%APP_HOME%\lib\netty-transport-4.1.24.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.24.Final.jar;%APP_HOME%\lib\reactor-extra-3.1.6.RELEASE.jar;%APP_HOME%\lib\reactor-core-3.1.7.RELEASE.jar;%APP_HOME%\lib\HdrHistogram-2.1.10.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\netty-resolver-4.1.24.Final.jar;%APP_HOME%\lib\netty-common-4.1.24.Final.jar;%APP_HOME%\lib\reactive-streams-1.0.2.jar

@rem Execute random-string-chat-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %RANDOM_STRING_CHAT_SERVICE_OPTS%  -classpath "%CLASSPATH%" io.netifi.proteus.demo.ChatStringMain %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable RANDOM_STRING_CHAT_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%RANDOM_STRING_CHAT_SERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
