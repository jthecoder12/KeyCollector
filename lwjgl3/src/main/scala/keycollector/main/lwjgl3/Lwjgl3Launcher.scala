package keycollector.main.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import keycollector.main.Main
import org.lwjgl.glfw.GLFW.{GLFW_PLATFORM, GLFW_PLATFORM_X11, glfwInitHint}
import org.lwjgl.system.Platform
import org.lwjgl.util.tinyfd.TinyFileDialogs.tinyfd_messageBox

// Desktop launcher
object Lwjgl3Launcher {
    def main(args: Array[String]): Unit = {
        if(StartupHelper.startNewJvmIfRequired()) return

        if(Platform.get() == Platform.LINUX) glfwInitHint(GLFW_PLATFORM, GLFW_PLATFORM_X11)
        val configuration: Lwjgl3ApplicationConfiguration = new Lwjgl3ApplicationConfiguration
        configuration.setTitle("Key Collector")
        configuration.useVsync(true)
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)
        configuration.setWindowedMode(1280, 720)

        try new Lwjgl3Application(new Main, configuration)
        catch
            case e: Exception =>
                e.printStackTrace()
                tinyfd_messageBox("Uncaught Exception", String.format("%s%nMessage: %s%nStack trace: %s", e.getClass.getCanonicalName, e.getMessage, e.getStackTrace.mkString("Array(", ", ", ")")).replace('"', Character.MIN_VALUE).replace('\'', Character.MIN_VALUE), "ok", "error", true)
                System.exit(1)
    }
}
