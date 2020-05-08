package ancurio.traceutil.anno;

public interface Annotater {
	/**
	 * Optionally add a prefix to the annotations emitted by
	 * this instance; the resulting annotations will look like this:
	 * <namespace>:<prefix>/<object>
	 *
	 * @param prefix the prefix to append after the namespace
	 */
	void appendPrefix(String prefix);

	/**
	 * Descend one scope level deeper.
	 * All GL calls / messages / scope after this call will be bundled
	 * in this scope until the matching @pop is called.
	 *
	 * @param scope the name of the new nested scope
	 */
	void push(String scope);

	/**
	 * Close the currently active scope.
	 */
	void pop();

	/**
	 * Insert a freeform string message into the GL stream
	 * at the current scope
	 *
	 * @param message a string to be inserted
	 */
	void insert(String message);

	/**
	 * Close the current active scope and open a new one.
	 * Logically equivalent to @pop followed by @push.
	 *
	 * @param scope the name of the new scope
	 */
	void swap(String scope);

	enum ObjectType {
		TEXTURE
	}

	/**
	 * Attach a namespaced string label to an OpenGL object.
	 *
	 * @param id the OpenGL integer id of the object
	 * @param type the type of the object
	 * @param label the string label to attach
	 */
	void labelObject(int id, ObjectType type, String label);

	/**
	 * Internal method
	 */
	void labelObjectNoPrefix(int id, ObjectType type, String label);

	enum ThreadAwareness {
		IGNORANT,
		AWARE
	}

	/**
	 * Create an Annotater instance appropriate for the current
	 * OpenGL context. A context _must_ be valid at call-time.
	 * Will construct a dummy implementation when no sufficient
	 * extension can be found.
	 *
	 * @param namespace the name of the message namespace
	 * @return a valid implementation instance
	 */
	static Annotater chooseImpl(String namespace) {
		return chooseImpl(namespace, ThreadAwareness.IGNORANT);
	}

	static Annotater chooseImpl(String namespace, ThreadAwareness mode) {
		if (GLAnnotater.isAvailable()) {
			if (mode == ThreadAwareness.AWARE) {
				return new ThreadAwareGLAnnotater(namespace);
			} else {
				return new GLAnnotater(namespace);
			}
		} else {
			return new DummyAnnotater();
		}
	}

	/**
	 * Query the name of the OpenGL extension that would be chosen
	 * by an appropriate Annotater implementation when calling
	 * @chooseImpl at this point.
	 *
	 * @return an OpenGL extenion string
	 */
	static String supportedImplExt() {
		if (GLAnnotater.isAvailable()) {
			return GLAnnotater.extensionString();
		} else {
			return "";
		}
	}
}
