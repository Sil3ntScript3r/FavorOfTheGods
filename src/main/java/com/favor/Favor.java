package com.favor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class Favor implements IExtendedEntityProperties {
	// Set the most and least favor you're allowed to acquire for any one god
	private static final int MAX_FAVOR = 100;
	private static final int MIN_FAVOR = 0;
	
	// Set the name of the property
	public static final String FAVOR_TAG = "Favor";
	
	private final EntityPlayer player;
	
	// [PROBABLY TEMP] Store the favors of the gods
	private int favorStefan, favorDesertPig;
	
	// When Favor is created, set player to a player passed in
	public Favor(EntityPlayer player)
	{
		this.player = player;
	}
	
	// Call to assign Favor to a player
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(Favor.FAVOR_TAG, new Favor(player));
	}
	
	// Retrieve the Favor from a player
	public static final Favor get(EntityPlayer player)
	{
		return (Favor)player.getExtendedProperties(FAVOR_TAG);
	}
	
	// Save Favor data to a Tag
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("FavorStefan", MAX_FAVOR / 2);
		properties.setInteger("FavorDesertPig", MAX_FAVOR / 2);
		
		compound.setTag(FAVOR_TAG, properties);
	}

	// Load Favor data from a tag
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(FAVOR_TAG);
		
		this.favorStefan = properties.getInteger("FavorStefan");
		this.favorDesertPig = properties.getInteger("FavorDesertPig");
		
		System.out.println("--Favor--");
		System.out.println("Stefan: " + favorStefan);
		System.out.println("Desert Pig: " + favorDesertPig);
	}

	@Override
	public void init(Entity entity, World world)
	{
		
	}
}
