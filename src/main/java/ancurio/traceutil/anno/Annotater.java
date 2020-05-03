package ancurio.traceutil.anno;

public interface Annotater {
	void appendPrefix(String prefix);

	void push(String scope);
	void pop();

	void insert(String message);

	void swap(String scope);

	enum ThreadAwareness {
		IGNORANT,
		AWARE
	}

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

	static String supportedImplExt() {
		if (GLAnnotater.isAvailable()) {
			return GLAnnotater.extensionString();
		} else {
			return "";
		}
	}
}
