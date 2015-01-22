package com.favor.gods;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.favor.Favor;

public class GodStefan extends Gods {
	@SubscribeEvent
	public void onEntityDied(LivingDeathEvent event)
	{
		if(event.entity instanceof EntityPig)
		{
			if(event.source.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)event.source.getEntity();
				if(Favor.get(player) != null)
				{
					increaseFavor(10, player, GOD_STEFAN);
				}
			}
		}
	}
}