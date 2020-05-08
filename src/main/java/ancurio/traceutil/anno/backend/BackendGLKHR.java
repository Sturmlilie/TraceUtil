package ancurio.traceutil.anno.backend;

import ancurio.traceutil.anno.Annotater;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class BackendGLKHR implements IBackend {
	final static int SOURCE = KHRDebug.GL_DEBUG_SOURCE_APPLICATION;
	final static int ID = 0;
	final static int TYPE = KHRDebug.GL_DEBUG_TYPE_MARKER;
	final static int SEVERITY = KHRDebug.GL_DEBUG_SEVERITY_NOTIFICATION;

	private int objectTypeToGl(Annotater.ObjectType type) {
		switch (type) {
			case TEXTURE:
			return GL11.GL_TEXTURE;
		}

		throw new RuntimeException("Should never happen");
	}

	public static boolean isAvailable() {
		return GL.getCapabilities().GL_KHR_debug;
	}

	public void pushGroup(String scope) {
		KHRDebug.glPushDebugGroup(SOURCE, ID, scope);
	}

	public void popGroup() {
		KHRDebug.glPopDebugGroup();
	}

	public void insert(String message) {
		KHRDebug.glDebugMessageInsert(SOURCE, TYPE, ID, SEVERITY, message);
	}

	public void objectLabel(int id, Annotater.ObjectType type, String label) {
		KHRDebug.glObjectLabel​(objectTypeToGl(type), id, label);
	}

	public String extensionString() {
		return "KHR_debug";
	}
}
