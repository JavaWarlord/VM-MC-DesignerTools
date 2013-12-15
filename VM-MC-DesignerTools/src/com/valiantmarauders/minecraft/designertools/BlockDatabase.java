package com.valiantmarauders.minecraft.designertools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockDatabase {
	Map<Block, Material> markedBlocks;

	public BlockDatabase() {
		this.markedBlocks = new HashMap<Block, Material>();
	}

	public void mark(Block block) {
		// TODO Auto-generated method stub
		markedBlocks.put(block, block.getType());
		block.setType(Material.NETHERRACK);
	}

	public boolean isMarked(Block block) {
		// TODO Auto-generated method stub
		return markedBlocks.containsKey(block);
	}

	public void reset(Block block) {
		// TODO Auto-generated method stub
		block.setType(markedBlocks.remove(block));
	}

	public void clearAll() {
		// TODO Auto-generated method stub
		for (Entry<Block, Material> entry : markedBlocks.entrySet()) {
			entry.getKey().setType(entry.getValue());
		}
		markedBlocks.clear();
	}
}
