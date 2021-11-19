package com.ewyboy.fps.cleint;

import com.ewyboy.fps.FpsMonitor;
import com.ewyboy.fps.config.Settings;
import com.ewyboy.fps.util.Translation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class Keybindings {

    private static KeyBinding fps;
    private static KeyBinding ping;
    private static KeyBinding memory;

    public static void setup() {
        initKeyBinding();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, Keybindings :: onKeyInput);
    }

    public static void initKeyBinding() {
        fps = new KeyBinding(Translation.Key.FPS, KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_DONT_CARE, FpsMonitor.NAME);
        ClientRegistry.registerKeyBinding(fps);
        ping = new KeyBinding(Translation.Key.PING, KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_DONT_CARE, FpsMonitor.NAME);
        ClientRegistry.registerKeyBinding(ping);
        memory = new KeyBinding(Translation.Key.MEMORY, KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_DONT_CARE, FpsMonitor.NAME);
        ClientRegistry.registerKeyBinding(memory);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if(fps.consumeClick()) {
            Settings.ClientSettings.toggle(Settings.CLIENT_SETTINGS.toggleFps);
        }
        if(ping.consumeClick()) {
            Settings.ClientSettings.toggle(Settings.CLIENT_SETTINGS.togglePing);
        }
        if(memory.consumeClick()) {
            Settings.ClientSettings.toggle(Settings.CLIENT_SETTINGS.toggleMemory);
        }
    }

}
