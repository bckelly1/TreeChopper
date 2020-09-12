package treechopper.proxy;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import treechopper.common.handler.TreeHandler;
import treechopper.common.tree.Tree;
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
        BlockPos blockPos = breakEvent.getPos();
        Tree tree = treeHandler.analyzeTree((World) breakEvent.getWorld(), blockPos, breakEvent.getPlayer());
        treeHandler.destroyTree((World) breakEvent.getWorld(), tree);

        if (!breakEvent.getPlayer().isCreative() && breakEvent.getPlayer().getHeldItemMainhand().isDamageable()) {
            int axeDurability = breakEvent.getPlayer().getHeldItemMainhand().getDamage() + (int)(tree.getLogCount() * 1.5);
            breakEvent.getPlayer().getHeldItemMainhand().setDamage(axeDurability);
        }
    }
}
