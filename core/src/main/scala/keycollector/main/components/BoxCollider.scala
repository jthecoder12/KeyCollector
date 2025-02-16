package keycollector.main.components

import com.badlogic.gdx.math.{Intersector, Rectangle}

final class BoxCollider(rectComponent: RectComponent) extends Collider {
    private[components] var rect: Rectangle = new Rectangle(rectComponent.position.x, rectComponent.position.y, rectComponent.size.width.toFloat, rectComponent.size.height.toFloat)

    def update(rectComponent: RectComponent): Unit = rect = new Rectangle(rectComponent.position.x, rectComponent.position.y, rectComponent.size.width.toFloat, rectComponent.size.height.toFloat)
    
    @Override
    override def touchingRect(boxCollider: BoxCollider): Boolean = boxCollider.rect.overlaps(rect)

    @Override
    override def touchingCircle(circleCollider: CircleCollider): Boolean = Intersector.overlaps(circleCollider.circle, rect)
}
