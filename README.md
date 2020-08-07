# TraceUtil
TraceUtil is a fabric mod/library aimed at developers.

Setting up apitrace with MultiMC: [Tutorial](https://gist.github.com/Sturmlilie/69d6c4d2dce9d648cd706093c95ba195)
## As a stand-alone mod
TraceUtil contains common annotation for OpenGL-intensive methods in Minecraft versions `1.15.2-1.16.1` which are automatically inserted via mixin-hooks, including:  
`DiffuseLighting`, `DrawableHelper`, `GameRenderer`, `ItemRenderer`, `ModelLoader`, `Screen`, `SpriteAtlasTexture`, `TextureManager`, `WorldRenderer`
Also contains a special annotater for `RenderLayer` instances, and an optional annotater which hooks into Minecrafts own Profiler (off by default).
Each texture registered with `TextureManager` will have the assosicated `Identifier` attached on the GL level so it shows up under *Surfaces*.

**Without TraceUtil:**  
![without_traceutil](https://imgur.com/0ctOB9K.png)

**With TraceUtil:**  
![with_traceutil](https://imgur.com/4pxNePM.png)
![with_traceutil2](https://imgur.com/KunuSHz.png)
## As a library
Insert your own structuring annotations. Sample:
```java
/* imports ... */
import ancurio.traceutil.anno.Annotater;
/* constructors ... */
Annotater tater = new Annotater("tis3d");
tater.appendPrefix("serialport");
/* render methods ... */
tater.push("SerialPortModule.render");
ignoreLighting();
tater.pop();
```
Result:
![result](https://imgur.com/v3LMhCC.png)

## Todo
* Sodium / fabric rendering API support (as soon as those are stable)
* Configuration file
* Auto-generated mixin hooks

## License
LGPLv3
