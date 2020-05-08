package ancurio.traceutil.anno.backend;

import ancurio.traceutil.anno.Annotater;

public class BackendDummy implements IBackend {
	public void pushGroup(String scope) {
	}

	public void popGroup() {
	}

	public void insert(String message) {
	}

	public void objectLabel(int id, Annotater.ObjectType type, String label) {
	}

	public String extensionString() {
		return "";
	}
}
