package com.favorofthegods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.favorofthegods.favornetwork.Favor;

public class EventList {
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		// Check if a player died on the server only
		if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			// It was a player, so copy their NBTTag...
			NBTTagCompound playerData = new NBTTagCompound();
			PlayerProps.get(player).saveNBTData(playerData);
			
			// ...And save it to the CommonProxy
			CommonProxy.storeEntityData(player.getName(), playerData);
			PlayerProps.saveProxy(player);
		}
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		// Make sure it's a player joining
		if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			// Get their Favor data out of the CommonProxy
			NBTTagCompound playerData = CommonProxy.getEntityData(((EntityPlayer)event.entity).getName());
			
			// Make sure they have Favor
			if(playerData != null)
			{
				((PlayerProps)(PlayerProps.get((EntityPlayer)event.entity))).loadNBTData(playerData);
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityConstruct(EntityConstructing event)
	{
		// Make sure the entity is a player, and doesn't already have a Favor system. If yes and no, give them one
		if(event.entity instanceof EntityPlayer && PlayerProps.get((EntityPlayer)event.entity) == null)
		{
			PlayerProps.register((EntityPlayer)event.entity);
			System.out.println(": FAVOR SYSTEM IN PLACE :");
		}
	}
}
