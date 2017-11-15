package SharedObject;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getZ();
	public boolean isDestroyed();
	public boolean isVisible();
	public void draw(GraphicsContext gc);
}
