package icbm.core.compat.waila;

import calclavia.lib.access.AccessUser;
import calclavia.lib.access.IProfileContainer;
import calclavia.lib.utility.LanguageUtility;
import icbm.sentry.interfaces.ITurret;
import icbm.sentry.turret.Turret;
import icbm.sentry.turret.TurretRegistry;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author tgame14
 * @since 12/04/14
 */
public class WailaTurretDataProvider implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		Turret turret = TurretRegistry.constructSentry(accessor.getNBTData().getCompoundTag(ITurret.SENTRY_OBJECT_SAVE).getString(ITurret.SENTRY_TYPE_SAVE_ID), accessor.getTileEntity());
		return TurretRegistry.getItemStack(turret.getClass());
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		// TODO: Add data here
		IProfileContainer container = (IProfileContainer) accessor.getTileEntity();
		if (!container.canAccess(accessor.getPlayer().username))
		{
			currenttip.add(LanguageUtility.getLocal("info.turretdenied.waila").replaceAll("%u", accessor.getPlayer().username));
			return currenttip;
		}
		for (AccessUser user : container.getAccessProfile().getUsers())
		{
			currenttip.add(user.getGroup() + " : " + user.getName());
		}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}
}
