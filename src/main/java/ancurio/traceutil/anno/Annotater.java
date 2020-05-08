package ancurio.traceutil.anno;

import ancurio.traceutil.anno.backend.BackendLoader;

public class Annotater {
	// Technically gated by GL_MAX_DEBUG_GROUP_STACK_DEPTH
	private static final int MAX_DEPTH = 16;
	private int currentDepth = 0;
	private String fullPrefix;

	private String prefix(String str) {
		return fullPrefix + str;
	}

	/**
	 * Create an Annotater instance
	 * @param namespace the name of the message namespace
	 */
	public Annotater(String namespace) {
		fullPrefix = namespace + ":";
	}

	/**
	 * Optionally add a prefix to the annotations emitted by
	 * this instance; the resulting annotations will look like this:
	 * <namespace>:<prefix>/<object>
	 *
	 * @param prefix the prefix to append after the namespace
	 */
	public void appendPrefix(String prefix) {
		fullPrefix += prefix + "/";
	}

	/**
	 * Descend one scope level deeper.
	 * All GL calls / messages / scope after this call will be bundled
	 * in this scope until the matching @pop is called.
	 *
	 * @param scope the name of the new nested scope
	 */
	public void push(String scope) {
		currentDepth += 1;
		if (currentDepth > MAX_DEPTH) {
			throw new RuntimeException("Stack overflow");
		}

		BackendLoader.I.pushGroup(prefix(scope));
	}

	/**
	 * Close the currently active scope.
	 */
	public void pop() {
		currentDepth -= 1;
		if (currentDepth < 0) {
			throw new RuntimeException("Stack underflow");
		}

		BackendLoader.I.popGroup();
	}

	/**
	 * Insert a freeform string message into the GL stream
	 * at the current scope
	 *
	 * @param message a string to be inserted
	 */
	public void insert(String message) {
		BackendLoader.I.insert(prefix(message));
	}

	/**
	 * Close the current active scope and open a new one.
	 * Logically equivalent to @pop followed by @push.
	 *
	 * @param scope the name of the new scope
	 */
	public void swap(String scope) {
		pop();
		push(scope);
	}

	public enum ObjectType {
		TEXTURE
	}

	/**
	 * Attach a namespaced string label to an OpenGL object.
	 *
	 * @param id the OpenGL integer id of the object
	 * @param type the type of the object
	 * @param label the string label to attach
	 */
	public void labelObject(int id, Annotater.ObjectType type, String label) {
		BackendLoader.I.objectLabel(id, type, label);
	}

	/**
	 * Back compat
	 */
	public static Annotater chooseImpl(String namespace) {
		return new Annotater(namespace);
	}
}
