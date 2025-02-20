package keycollector.main.entities

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import keycollector.main.components.{BoxCollider, RectComponent}

import java.awt.Dimension
import scala.math.sqrt

// The player
final class Player(engine: Engine) extends Renderable {
    // Constants
    private val size: Int = 30
    private val speed: Float = 2.5f
    
    var moving: Boolean = true

    // Adds a new rect component with the position in the middle of the screen
    add(new RectComponent(new Vector2(Gdx.graphics.getWidth/2f, Gdx.graphics.getHeight/2f), new Dimension(size, size), Color.WHITE))
    
    // Adds a box collider
    add(new BoxCollider(getComponent(classOf[RectComponent])))

    // Adds the player to the engine
    engine.addEntity(this)

    @Override
    override def render(shapeRenderer: ShapeRenderer): Unit = {
        getComponent(classOf[RectComponent]).render(shapeRenderer)

        if(moving) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                // Pythagorean theorem, if moving diagonally, instead of going at full speed, we go at the square root of the speed squared divided by 2. This happens twice when moving diagonally so the total distance would be the speed moving
                // diagonally squared which gives us the speed squared divided by 2. We add this twice, or double it giving us the speed squared. This means that the hypotenuse, or the total distance traveled is equal to square root of the
                // speed squared, which is the original speed.
                if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) getComponent(classOf[RectComponent]).move(new Vector2(0, sqrt((speed * speed) / 2).toFloat))
                // Go at full speed when moving normally
                else getComponent(classOf[RectComponent]).move(new Vector2(0, speed))
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)) getComponent(classOf[RectComponent]).move(new Vector2(-sqrt((speed * speed) / 2).toFloat, 0))
                else getComponent(classOf[RectComponent]).move(new Vector2(-speed, 0))
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) getComponent(classOf[RectComponent]).move(new Vector2(0, -sqrt((speed * speed) / 2).toFloat))
                else getComponent(classOf[RectComponent]).move(new Vector2(0, -speed))
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)) getComponent(classOf[RectComponent]).move(new Vector2(sqrt((speed * speed) / 2).toFloat, 0))
                else getComponent(classOf[RectComponent]).move(new Vector2(speed, 0))
            }
        }
    }
}
