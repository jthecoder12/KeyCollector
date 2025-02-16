package keycollector.main.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.{Lwjgl3Application, Lwjgl3ApplicationConfiguration}
import keycollector.main.Main

// Desktop launcher
object Lwjgl3Launcher {
    def main(args: Array[String]): Unit = {
        if(StartupHelper.startNewJvmIfRequired()) return

        val configuration: Lwjgl3ApplicationConfiguration = new Lwjgl3ApplicationConfiguration
        configuration.setTitle("Key Collector")
        configuration.useVsync(true)
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)
        configuration.setWindowedMode(1280, 720)
        
        new Lwjgl3Application(new Main, configuration)
    }
}
