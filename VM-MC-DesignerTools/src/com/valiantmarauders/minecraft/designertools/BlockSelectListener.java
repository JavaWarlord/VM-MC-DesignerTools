package com.valiantmarauders.minecraft.designertools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

/**
 * BlockSelectListener
 * 
 * @author JavaWarlord
 * @version 0.0
 */
public class BlockSelectListener implements Listener {
	private Plugin plugin;
	private Block block1 = null;
	private Block block2 = null;
	private BlockDatabase blockDatabase;

	public BlockSelectListener(Plugin plugin, BlockDatabase blockDatabase) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		this.blockDatabase = blockDatabase;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			if (event.getItem().getType() == Material.ARROW) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					block1 = event.getClickedBlock();
					event.getPlayer().sendMessage(
							"Point 1: " + block1.getX() + "," + block1.getY()
									+ "," + block1.getZ());
					plugin.getLogger().info(
							event.getPlayer() + " selected block "
									+ block1.getX() + "," + block1.getY() + ","
									+ block1.getZ());
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					block2 = event.getClickedBlock();
					event.getPlayer().sendMessage(
							"Point 2: " + block2.getX() + "," + block2.getY()
									+ "," + block2.getZ());
					plugin.getLogger().info(
							event.getPlayer() + " selected block "
									+ block1.getX() + "," + block1.getY() + ","
									+ block1.getZ());
				}
			} else {
				if (blockDatabase.isMarked(event.getClickedBlock())) {
					blockDatabase.reset(event.getClickedBlock());
				}
			}
		}
	}

	public boolean hasTwoSelections() {
		// TODO Auto-generated method stub
		return (block1 != null && block2 != null);
	}

	public Block getBlock(int index) {
		// TODO Auto-generated method stub
		if (index == 1)
			return block1;
		if (index == 2)
			return block2;
		return null;
	}
}
