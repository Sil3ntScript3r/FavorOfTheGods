package com.favor.gods;

import net.minecraft.entity.player.EntityPlayer;

import com.favor.Favor;

public class Gods {
	private static String name;
	
	public static final int GOD_STEFAN = 0;
	public static final int GOD_DESERTPIG = 1;
	
	protected void increaseFavor(int num, EntityPlayer player, int god)
	{
		if(Favor.get(player) != null)
		{
			Favor.get(player).increaseFavor(num, god);
		}
	}
}