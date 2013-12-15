package com.valiantmarauders.minecraft.designertools;

import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DesignerTools extends JavaPlugin {
	private BlockDatabase blockDatabase;
	private BlockSelectListener blockSelectListener;

	public void onEnable() {
		this.blockDatabase = new BlockDatabase();
		this.blockSelectListener = new BlockSelectListener(this, blockDatabase);
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(blockSelectListener, this);
	}

	public void onDisable() {
		blockDatabase.clearAll();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("dtool")
				|| cmd.getName().equalsIgnoreCase("dt")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("mid")) {
					if (blockSelectListener.hasTwoSelections()) {
						ArrayList<Location> middles = findMidPoints(
								blockSelectListener.getBlock(1).getLocation(),
								blockSelectListener.getBlock(2).getLocation());
						for (Location mid : middles) {
							blockDatabase.mark(mid.getBlock());
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	private ArrayList<Location> findMidPoints(Location location1,
			Location location2) {
		ArrayList<Double> xList = new ArrayList<Double>();
		double midX = (location1.getX() + location2.getX()) / 2;
		this.getLogger().info("midX: " + midX);
		if (((int) midX) != ((int) (midX + 0.5))) {
			xList.add(midX + 1);
		}
		xList.add(midX);

		ArrayList<Double> yList = new ArrayList<Double>();
		double midY = (location1.getY() + location2.getY()) / 2;
		this.getLogger().info("midY: " + midY);
		if (((int) midY) != ((int) (midY + 0.5))) {
			yList.add(midY + 1);
		}
		yList.add(midY);

		ArrayList<Double> zList = new ArrayList<Double>();
		double midZ = (location1.getZ() + location2.getZ()) / 2;
		this.getLogger().info("midZ: " + midZ);
		if (((int) midZ) != ((int) (midZ + 0.5))) {
			zList.add(midZ + 1);
		}
		zList.add(midZ);

		ArrayList<Location> midpoints = new ArrayList<Location>();
		for (Double x : xList) {
			for (Double y : yList) {
				for (Double z : zList) {
					midpoints.add(new Location(location1.getWorld(), x, y, z));
				}
			}
		}

		return midpoints;
	}
}
