package ancurio.traceutil.anno.backend;

import ancurio.traceutil.Main;
import ancurio.traceutil.anno.backend.BackendDummy;
import ancurio.traceutil.anno.backend.BackendGLKHR;
import ancurio.traceutil.anno.backend.IBackend;

public class BackendLoader {
	public static IBackend I = new BackendDummy();

	public static void loadGlBackend() {
		if (BackendGLKHR.isAvailable()) {
			I = new BackendGLKHR();
		}

		if (!(I instanceof BackendDummy)) {
			Main.log(I.extensionString() + " found, starting annotations");
		} else {
			Main.log("No debug extensions found");
		}
	}
}
