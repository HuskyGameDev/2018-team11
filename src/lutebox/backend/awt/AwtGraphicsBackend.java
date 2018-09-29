package lutebox.backend.awt;

import lutebox.backend.GraphicsBackend;
import lutebox.backend.NativeTexture;

public class AwtGraphicsBackend implements GraphicsBackend {

    @Override
    public NativeTexture createNativeTexture(int width, int height) {
        return new BufferedImageTexture(width, height); 
    }
    
}
