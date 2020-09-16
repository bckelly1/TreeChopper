package treechopper.proxy;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import treechopper.command.*;
import treechopper.common.handler.TreeHandler;
import treechopper.common.tree.Tree;
import treechopper.core.TreeChopper;

/**
 * Handles all event bus listeners.  Client side only.
 */
@Mod.EventBusSubscriber(modid = TreeChopper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class CommonProxy {
  private static TreeHandler treeHandler = new TreeHandler();

  /**
   * Commands are registered when the server starts. This used to be on the FMLServerStartEvent
   * but the RegistercommandsEvent was created for all mods to use to register commands.
   *
   * @param event Event being listened for.
   */
  @SubscribeEvent
  public static void onServerStarting(final RegisterCommandsEvent event) {
    TreeChopperCommand.register(event.getDispatcher());
  }

  /**
   * Begin breaking the tree. We slow down the breaking depending on the number of blocks in the tree.
   *
   * @param interactEvent Event being listened for.
   */
  @SubscribeEvent
  public static void interactWithTree(PlayerInteractEvent.LeftClickBlock interactEvent) {
    // Add the tree to the analyzed tree map
    treeHandler.analyzeTree(interactEvent.getWorld(), interactEvent.getPos(), interactEvent.getPlayer());
  }

  /**
   * Slow down the block breaking depending on the number of blocks in the tree.
   *
   * @param breakSpeed Event being listened for.
   */
  @SubscribeEvent
  public static void breakingBlock(PlayerEvent.BreakSpeed breakSpeed) {
    // If it's not a valid wood block, don't do anything
    if (!TreeHandler.checkWoodenBlock(breakSpeed.getPlayer().getEntityWorld(), breakSpeed.getPos())) {
      return;
    }

    // A check for pressing shift/sneaking
    if (!treeHandler.shouldDestroyTree(breakSpeed)) {
      return;
    }

    Tree tree = treeHandler.analyzeTree(breakSpeed.getPlayer().getEntityWorld(), breakSpeed.getPos(), breakSpeed.getPlayer());
    breakSpeed.setNewSpeed(breakSpeed.getOriginalSpeed() / (tree.getLogCount() / 2.0f));
  }

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