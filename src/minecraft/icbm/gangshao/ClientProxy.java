package icbm.gangshao;

import icbm.gangshao.fx.FXLaser;
import icbm.gangshao.gui.GuiPlatformAccess;
import icbm.gangshao.gui.GuiPlatformSlots;
import icbm.gangshao.gui.GuiPlatformTerminal;
import icbm.gangshao.platform.TileEntityTurretPlatform;
import icbm.gangshao.render.BlockRenderingHandler;
import icbm.gangshao.render.RenderFakeMountable;
import icbm.gangshao.render.RenderGunTurret;
import icbm.gangshao.render.RenderRailgun;
import icbm.gangshao.turret.EntityFakeMountable;
import icbm.gangshao.turret.TileEntityBaseTurret;
import icbm.gangshao.turret.TileEntityRailgun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import universalelectricity.core.vector.Vector3;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();
		MinecraftForge.EVENT_BUS.register(SoundHandler.INSTANCE);
	}

	@Override
	public void init()
	{
		super.init();

		/**
		 * TileEntities
		 */
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBaseTurret.class, new RenderGunTurret());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRailgun.class, new RenderRailgun());

		RenderingRegistry.registerEntityRenderingHandler(EntityFakeMountable.class, new RenderFakeMountable());
		RenderingRegistry.registerBlockHandler(new BlockRenderingHandler());
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity != null)
		{
			switch (ID)
			{
				case GUI_PLATFORM_ID:
					return new GuiPlatformSlots(player.inventory, ((TileEntityTurretPlatform) tileEntity));
				case GUI_PLATFORM_TERMINAL_ID:
					return new GuiPlatformTerminal(player, ((TileEntityTurretPlatform) tileEntity));
				case GUI_PLATFORM_ACCESS_ID:
					return new GuiPlatformAccess(player, ((TileEntityTurretPlatform) tileEntity));
			}
		}

		return null;
	}

	public void shootLaser(World world, Vector3 position, Vector3 target, float red, float green, float blue, int age)
	{
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXLaser(world, position, target, red, green, blue, age));
	}
}