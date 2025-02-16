package keycollector.main.components

import com.badlogic.gdx.math.{Circle, Intersector}

final class CircleCollider(circleComponent: CircleComponent) extends Collider {
    private[components] var circle: Circle = new Circle(circleComponent.position.x, circleComponent.position.y, circleComponent.radius)

    def update(circleComponent: CircleComponent): Unit = circle = new Circle(circleComponent.position.x, circleComponent.position.y, circleComponent.radius)
    
    @Override
    override def touchingRect(boxCollider: BoxCollider): Boolean = Intersector.overlaps(circle, boxCollider.rect)
    
    @Override
    override def touchingCircle(circleCollider: CircleCollider): Boolean = circle.overlaps(circleCollider.circle)
}
