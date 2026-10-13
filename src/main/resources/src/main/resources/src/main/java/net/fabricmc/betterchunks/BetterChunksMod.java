package net.fabricmc.betterchunks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class BetterChunksMod implements ModInitializer {
    public static KeyBinding toggleKey;
    public static boolean isEnabled = false;

    @Override
    public void onInitialize() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.betterchunks.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "Better Chunks Optimization"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            while (toggleKey.wasPressed()) {
                isEnabled = !isEnabled;
                client.player.sendMessage(new LiteralText("Better Chunks: " + (isEnabled ? "§a§lFast" : "§7§lDefault")), true);
            }
        });
    }
}
