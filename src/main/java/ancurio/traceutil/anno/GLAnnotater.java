package ancurio.traceutil.anno;

import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.opengl.GL;

public class GLAnnotater extends AbstractAnnotater {
	final static int SOURCE = KHRDebug.GL_DEBUG_SOURCE_APPLICATION;
	final static int ID = 0;
	final static int TYPE = KHRDebug.GL_DEBUG_TYPE_MARKER;
	final static int SEVERITY = KHRDebug.GL_DEBUG_SEVERITY_NOTIFICATION;

	public static boolean isAvailable() {
		return GL.getCapabilities().GL_KHR_debug;
	}

	public GLAnnotater(String namespace) {
		super(namespace);
	}

	@Override
	public void push(String scope) {
		super.push(scope);
		KHRDebug.glPushDebugGroup(SOURCE, ID, prefix(scope));
	}

	@Override
	public void pop() {
		super.pop();
		KHRDebug.glPopDebugGroup();
	}

	public void insert(String message) {
		KHRDebug.glDebugMessageInsert(SOURCE, TYPE, ID, SEVERITY, prefix(message));
	}

	public static String extensionString() {
		return "KHR_debug";
	}
}
