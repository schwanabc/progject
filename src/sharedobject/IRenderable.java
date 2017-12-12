package sharedobject;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getZ();//z=-9999 for background z=board[i][j] if board[i][j]=0 z=11 for ordinary attacker z=999 for bullet
	public boolean isDestroyed();
	public boolean isVisible();
	public void draw(GraphicsContext gc);
}
