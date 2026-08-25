package net.minecraft.client.model;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.entity.Entity;

/**+
 * Modified EaglercraftX ModelPlayer to inject custom Bedrock JSON geometry maps.
 */
public class ModelPlayer extends ModelBiped {
	public ModelRenderer bipedLeftArmwear;
	public ModelRenderer bipedRightArmwear;
	public ModelRenderer bipedLeftLegwear;
	public ModelRenderer bipedRightLegwear;
	public ModelRenderer bipedBodyWear;
	private ModelRenderer bipedCape;
	private ModelRenderer bipedDeadmau5Head;
	private boolean smallArms;

	public ModelPlayer(float parFloat1, boolean parFlag) {
		super(parFloat1, 0.0F, 64, 64);
		this.smallArms = parFlag;

		// --- DEADMAU5 EARS ---
		this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
		this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, parFloat1);

		// --- CUSTOM BEDROCK CAPE ---
		this.bipedCape = new ModelRenderer(this, 36, 28);
		this.bipedCape.setTextureSize(64, 64);
		this.bipedCape.addBox(-4.0F, 2.0F, 1.0F, 8, 14, 1, parFloat1);

		// --- HEAD & HELMET ---
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		// Base Head [-4, 24, -4, 8, 8, 8] -> Translated to native Minecraft Y origin (-8.0F)
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, parFloat1);

		// Outer Helmet Layer [-4.5, 23.5, -4.5, 9, 9, 9] -> UV (32, 0)
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHeadwear.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9, parFloat1);

		// --- BODY & MULTI-BOX COAT LAYERS ---
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		// Box 1: Core Torso [-4, 12, -2, 8, 12, 4]
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, parFloat1);
		// Box 2: Lower Coat Flair [-6, 10, -5, 12, 5, 8]
		this.bipedBody.addBox(-6.0F, 2.0F, -5.0F, 12, 5, 8, parFloat1);
		// Box 3: Mid Coat Section [-6, 15, -5, 12, 4, 6]
		this.bipedBody.addBox(-6.0F, -3.0F, -5.0F, 12, 4, 6, parFloat1);
		// Box 4: Upper Vest Collar [-5, 15, -2.5, 10, 2, 5]
		this.bipedBody.addBox(-5.0F, -3.0F, -2.5F, 10, 2, 5, parFloat1);

		// Retaining clean reference mapping for outer body render hooks
		this.bipedBodyWear = new ModelRenderer(this, 20, 26);
		this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);

		// --- LEFT ARM & SLEEVE ---
		this.bipedLeftArm = new ModelRenderer(this, 32, 48);
		this.bipedLeftArm.setRotationPoint(4.0F, 2.0F, 0.0F); // Origin translated from translation [4, -22, 0]
		// Slim format size mapping [3, 12, 4]
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, parFloat1);

		this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
		this.bipedLeftArmwear.setRotationPoint(4.0F, 2.0F, 0.0F);
		this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, parFloat1 + 0.25F);

		// --- RIGHT ARM & SLEEVE ---
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.setRotationPoint(-4.0F, 2.0F, 0.0F); // Origin translated from translation [-4, -22, 0]
		// Slim format size mapping [3, 12, 4]
		this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, parFloat1);

		this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
		this.bipedRightArmwear.setRotationPoint(-4.0F, 2.0F, 0.0F);
		this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, parFloat1 + 0.25F);

		// --- LEFT LEG & PANTS ---
		this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		// Box 1: Core Left Leg [7, 12, 6] relative offset from JSON origin
		this.bipedLeftLeg.addBox(-3.8F, 0.0F, -3.0F, 7, 12, 6, parFloat1);

		this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
		this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.bipedLeftLegwear.addBox(-3.8F, 0.0F, -3.0F, 7, 12, 6, parFloat1 + 0.25F);

		// --- RIGHT LEG & PANTS ---
		this.bipedRightLeg = new ModelRenderer(this, 16, 48);
		this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		// Box 1: Core Right Leg [7, 12, 6] relative offset from JSON origin
		this.bipedRightLeg.addBox(-3.2F, 0.0F, -3.0F, 7, 12, 6, parFloat1);

		this.bipedRightLegwear = new ModelRenderer(this, 0, 48);
		this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.bipedRightLegwear.addBox(-3.2F, 0.0F, -3.0F, 7, 12, 6, parFloat1 + 0.25F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		GlStateManager.pushMatrix();
		if (this.isChild) {
			float f6 = 2.0F;
			GlStateManager.scale(1.0F / f6, 1.0F / f6, 1.0F / f6);
			GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
			this.bipedLeftLegwear.render(f5);
			this.bipedRightLegwear.render(f5);
			this.bipedLeftArmwear.render(f5);
			this.bipedRightArmwear.render(f5);
			this.bipedBodyWear.render(f5);
		} else {
			if (entity != null && entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.bipedLeftLegwear.render(f5);
			this.bipedRightLegwear.render(f5);
			this.bipedLeftArmwear.render(f5);
			this.bipedRightArmwear.render(f5);
			this.bipedBodyWear.render(f5);
		}
		GlStateManager.popMatrix();
	}

	public void renderDeadmau5Head(float parFloat1) {
		copyModelAngles(this.bipedHead, this.bipedDeadmau5Head);
		this.bipedDeadmau5Head.rotationPointX = 0.0F;
		this.bipedDeadmau5Head.rotationPointY = 0.0F;
		this.bipedDeadmau5Head.render(parFloat1);
	}

	public void renderCape(float parFloat1) {
		GlStateManager.matrixMode(GL_TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.scale(2.0f, 1.0f, 1.0f);
		GlStateManager.matrixMode(GL_MODELVIEW);
		this.bipedCape.render(parFloat1);
		GlStateManager.matrixMode(GL_TEXTURE);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(GL_MODELVIEW);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		// Match the outer skins to their respective parent pieces during movements
		copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
		copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
		copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
		copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
		copyModelAngles(this.bipedBody, this.bipedBodyWear);

		// Apply exact custom JSON default rotation flair angles instead of vanilla base offsets
		// Right arm rotation: 15.0 degrees = 0.2618 Radians
		this.bipedRightArm.rotateAngleZ += 0.2618F;
		this.bipedRightArmwear.rotateAngleZ += 0.2618F;

		// Left arm rotation: -17.5 degrees = -0.3054 Radians
		this.bipedLeftArm.rotateAngleZ -= 0.3054F;
		this.bipedLeftArmwear.rotateAngleZ -= 0.3054F;

		if (entity != null && entity.isSneaking()) {
			this.bipedCape.rotationPointY = 2.0F;
		} else {
			this.bipedCape.rotationPointY = 0.0F;
		}
	}

	public void renderRightArm() {
		this.bipedRightArm.render(0.0625F);
		this.bipedRightArmwear.render(0.0625F);
	}

	public void renderLeftArm() {
		this.bipedLeftArm.render(0.0625F);
		this.bipedLeftArmwear.render(0.0625F);
	}

	public void setInvisible(boolean flag) {
		super.setInvisible(flag);
		this.bipedLeftArmwear.showModel = flag;
		this.bipedRightArmwear.showModel = flag;
		this.bipedLeftLegwear.showModel = flag;
		this.bipedRightLegwear.showModel = flag;
		this.bipedBodyWear.showModel = flag;
		this.bipedCape.showModel = flag;
		this.bipedDeadmau5Head.showModel = flag;
	}

	public void postRenderArm(float f) {
		if (this.smallArms) {
			++this.bipedRightArm.rotationPointX;
			this.bipedRightArm.postRender(f);
			--this.bipedRightArm.rotationPointX;
		} else {
			this.bipedRightArm.postRender(f);
		}
	}
}
