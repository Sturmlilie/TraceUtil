package ancurio.traceutil.anno.backend;

import ancurio.traceutil.anno.Annotater;

public interface IBackend {
	void pushGroup(String scope);
	void popGroup();

	void insert(String message);
	void objectLabel(int id, Annotater.ObjectType type, String label);

	String extensionString();
}
