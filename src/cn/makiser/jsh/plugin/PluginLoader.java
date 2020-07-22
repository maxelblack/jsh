package cn.makiser.jsh.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {
    File jar;
    Plugin plugin;

    public PluginLoader(File jar) {
        this.jar = jar;
    }

    public Plugin load() throws MalformedURLException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        URLClassLoader loader = new URLClassLoader(
                new URL[]{new URL(jar.getPath())},
                Thread.currentThread().getContextClassLoader());
        Class<?> manifestClass = loader.loadClass("Manifest");
        Manifest manifest = (Manifest) manifestClass.newInstance();
        Class<?> runnableClass = loader.loadClass(manifest.runnableClass);
        Runnable runnable = (Runnable) runnableClass.newInstance();
        plugin = new Plugin(manifest.pluginName, manifest.pluginInfo,
                manifest.versionName, manifest.versionCode);
        runnable.run(plugin);
        return plugin;
    }

    //get&set

    public Plugin getPlugin() {
        return plugin;
    }
}
