package treechopper.common;

import net.minecraft.util.math.BlockPos;

public class PlayerInteract {

  public BlockPos blockPos; // Interact block position
  public float logCount;
  public int axeDurability;

  public PlayerInteract(BlockPos blockPos, float logCount, int axeDurability) {
    this.blockPos = blockPos;
    this.logCount = logCount;
    this.axeDurability = axeDurability;
  }
}
