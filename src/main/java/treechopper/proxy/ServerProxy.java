package treechopper.proxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import treechopper.common.handler.TreeHandler;
import treechopper.core.TreeChopper;

/**
 * Handles all event bus listeners.  Server side only.
 */
@Mod.EventBusSubscriber(modid = TreeChopper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ServerProxy {
    private static TreeHandler treeHandler = new TreeHandler();

    /**
     * Breaks the wood block, the tree, and damages the axe.
     *
     * @param breakEvent Event being listened for
     */
    @SubscribeEvent
    public static void destroyWoodBlock(BlockEvent.BreakEvent breakEvent) {
        treeHandler.destroyTreeCommonEvent(breakEvent);
    }
}
