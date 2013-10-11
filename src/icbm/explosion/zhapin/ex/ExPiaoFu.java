package icbm.explosion.zhapin.ex;

import icbm.core.ICBMConfiguration;
import icbm.core.base.ModelICBM;
import icbm.explosion.explosive.explosion.BzPiaoFu;
import icbm.explosion.model.missiles.MMPiaoFu;
import icbm.explosion.zhapin.daodan.Missile;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.prefab.RecipeHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExPiaoFu extends Missile
{
	public ExPiaoFu(String mingZi, int tier)
	{
		super(mingZi, tier);
	}

	@Override
	public void init()
	{
		RecipeHelper.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "EEE", "ETE", "EEE", 'T', tui.getItemStack(), 'E', Item.eyeOfEnder }), this.getUnlocalizedName(), ICBMConfiguration.CONFIGURATION, true);
	}

	@Override
	public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
	{
		new BzPiaoFu(world, entity, x, y, z, 30).explode();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelICBM getMissileModel()
	{
		return new MMPiaoFu();
	}
}
