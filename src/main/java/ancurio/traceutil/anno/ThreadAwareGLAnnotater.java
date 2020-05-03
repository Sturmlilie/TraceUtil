package ancurio.traceutil.anno;

import com.mojang.blaze3d.systems.RenderSystem;
import org.lwjgl.opengl.GL;

public class ThreadAwareGLAnnotater extends GLAnnotater {

	public ThreadAwareGLAnnotater(String namespace) {
		super(namespace);
	}
	
	@Override
	public void push(String scope) {
		if (RenderSystem.isOnRenderThread()) {
			super.push(scope);
		}
	}
	
	@Override
	public void pop() {
		if (RenderSystem.isOnRenderThread()) {
			super.pop();
		}
	}
	
	@Override
	public void insert(String message) {
		if (RenderSystem.isOnRenderThread()) {
			super.insert(message);
		}
	}
}
