package keycollector.main.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import keycollector.main.Main
import org.lwjgl.glfw.GLFW.{GLFW_PLATFORM, GLFW_PLATFORM_X11, glfwInitHint}
import org.lwjgl.system.Platform

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
        configuration.setResizable(false)

        new Lwjgl3Application(new Main, configuration)
    }
}
