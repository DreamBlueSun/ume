package com.marisa.ume.block;

import com.marisa.ume.Ume;
import com.marisa.ume.block.craft.ArmorMakeBlock;
import com.marisa.ume.block.craft.ArmorWidgetBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 方块注册
 */
public class BlockRegistry {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ume.MODID);

//    public static final RegistryObject<Block> EXAMPLE_BLOCK = Ume.BLOCKS.register("example_block", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).lightLevel((state) -> 7), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ARMOR_MAKE_BLOCK = BLOCKS.register("armor_make_block", ArmorMakeBlock::new);
    public static final RegistryObject<Block> ARMOR_WIDGET_BLOCK = BLOCKS.register("armor_widget_block", ArmorWidgetBlock::new);
    
}
