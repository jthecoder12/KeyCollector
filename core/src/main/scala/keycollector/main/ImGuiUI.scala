package keycollector.main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics
import com.badlogic.gdx.utils.Disposable
import imgui.{ImFontConfig, ImGui, ImGuiIO}
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import org.apache.commons.lang3.SystemUtils

object ImGuiUI extends Disposable {
    private var imGuiGlfw: ImGuiImplGlfw = _
    private var imGuiGl3: ImGuiImplGl3 = _

    def init(): Unit = {
        imGuiGlfw = new ImGuiImplGlfw
        imGuiGl3 = new ImGuiImplGl3

        ImGui.createContext

        val io: ImGuiIO = ImGui.getIO
        io.setIniFilename(null)

        if(!SystemUtils.IS_OS_MAC) io.getFonts.setFreeTypeRenderer(true)
        val fontConfig: ImFontConfig = new ImFontConfig
        io.getFonts.addFontFromMemoryTTF(Gdx.files.internal("OpenSans.ttf").readBytes(), 25, fontConfig)
        io.getFonts.build
        fontConfig.destroy()
        
        imGuiGlfw.init(Gdx.graphics.asInstanceOf[Lwjgl3Graphics].getWindow.getWindowHandle, true)
        imGuiGl3.init("#version 150")
    }
    
    def beginLoop(): Unit = {
        imGuiGl3.newFrame()
        imGuiGlfw.newFrame()
        ImGui.newFrame()
    }
    
    def endLoop(): Unit = {
        ImGui.render()
        imGuiGl3.renderDrawData(ImGui.getDrawData)
    }

    @Override
    override def dispose(): Unit = {
        imGuiGl3.shutdown()
        imGuiGl3 = null
        imGuiGlfw.shutdown()
        imGuiGlfw = null
        ImGui.destroyContext()
    }
}
