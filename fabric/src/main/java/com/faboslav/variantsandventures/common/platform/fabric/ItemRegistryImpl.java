package com.faboslav.variantsandventures.common.platform.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Supplier;

public final class ItemRegistryImpl
{
	public static <T extends Item> Supplier<T> register(String name, Supplier<T> item) {
		var registry = Registry.register(Registries.ITEM, VariantsAndVentures.makeID(name), item.get());
		return () -> registry;
	}

	private ItemRegistryImpl() {
	}
}
