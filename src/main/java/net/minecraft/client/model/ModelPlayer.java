package net.minecraft.client.model;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.entity.Entity;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files (c) 2022-2024 lax1dude, ayunami2000. All Rights Reserved.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * ---
 * 
 * Custom body geometry ported from a Blockbench "Modded Entity" export
 * (CustomModel.java) into the ModelBiped skeleton so vanilla walk/swing/
 * sneak/riding animation still drives the limbs. Original Blockbench part
 * names -> biped part mapping:
 *   bb_main (head-ish cubes)             -> bipedHead
 *   bb_main (torso/hip/belt cubes)       -> bipedBody
 *   left_arm_r1 / right_arm_r1           -> bipedLeftArm / bipedRightArm
 *   left_leg_r1 + right_leg_r2 (same X
 *     side, matching baked Z-tilt)       -> bipedLeftLeg
 *   right_leg_r1 + left_leg_r2 (same X
 *     side, matching baked Z-tilt)       -> bipedRightLeg
 * Leg pivots were moved from the exported group's incidental rotation-tool
 * pivot (near the ankle) up to hip height (Y=12, vanilla convention) so
 * walk-swing rotates naturally from the hip; the rigid transform was
 * preserved by adjusting each cube's local offset to compensate, so the
 * rest pose is visually identical to the Blockbench export.
 * Texture is a single fixed 16x16 custom texture (not the per-player 64x64
 * skin) -- see RenderPlayer.getEntityTexture().
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

	// baked-in rest-pose tilts from the Blockbench export, re-applied every
	// frame after ModelBiped's animation logic runs (ModelBiped resets arm
	// rotateAngleZ to 0.0F each frame, so it can't be set once in the
	// constructor the way the leg tilt can)
	private static final float LEFT_ARM_REST_Z = -0.2618F;
	private static final float RIGHT_ARM_REST_Z = 0.2618F;
	private static final float LEFT_LEG_REST_Z = 0.0436F;
	private static final float RIGHT_LEG_REST_Z = -0.0436F;

	public ModelPlayer(float parFloat1, boolean parFlag) {
		super(parFloat1, 0.0F, 16, 16);
		this.smallArms = parFlag;

		this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
		this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, parFloat1);
		this.bipedCape = new ModelRenderer(this, 0, 0);
		this.bipedCape.setTextureSize(64, 32);
		this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, parFloat1);

		// --- head: base cube + outer layer cube, pivot (0,0,0) ---
		this.bipedHead = new ModelRenderer(this, "head");
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHead.cubeList.add(new ModelBox(this.bipedHead, 0, 0, -4.25F, -7.5F, -4.0F, 8, 8, 8, 0.0F, false));
		this.bipedHead.cubeList.add(new ModelBox(this.bipedHead, 0, 0, -4.75F, -8.0F, -4.5F, 9, 9, 9, 0.0F, false));

		// hat/head-overlay layer is baked into bipedHead above, so leave
		// bipedHeadwear empty (still needed structurally by ModelBiped)
		this.bipedHeadwear = new ModelRenderer(this, "headwear");
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

		// --- body: torso + hips + hip nubs + belt + outer torso layer ---
		this.bipedBody = new ModelRenderer(this, "body");
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, -4.25F, 0.5F, -2.0F, 8, 11, 4, 0.0F, false));
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, -5.25F, 9.5F, -4.25F, 10, 6, 7, 0.0F, false));
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, -6.0F, 9.5F, -1.25F, 3, 3, 3, 0.0F, false));
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, 2.5F, 9.5F, -1.25F, 3, 3, 3, 0.0F, false));
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, -5.25F, 8.49F, -4.25F, 10, 1, 4, 0.0F, false));
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, -4.75F, 7.5F, -2.5F, 9, 2, 5, 0.0F, false));
		this.bipedBody.cubeList.add(new ModelBox(this.bipedBody, 0, 0, -4.25F, -0.03F, -2.51F, 8, 12, 5, 0.0F, false));

		// --- arms: pivots from the Blockbench export (converted into
		// biped-absolute space), rest tilt re-applied in setRotationAngles ---
		this.bipedLeftArm = new ModelRenderer(this, "leftArm");
		this.bipedLeftArm.setRotationPoint(6.25F, 6.5F, -0.25F);
		this.bipedLeftArm.cubeList.add(new ModelBox(this.bipedLeftArm, 0, 0, -2.0F, -6.5F, -1.75F, 3, 13, 4, 0.0F, false));
		this.bipedLeftArm.cubeList.add(new ModelBox(this.bipedLeftArm, 0, 0, -1.5F, -6.0F, -1.25F, 2, 12, 3, 0.0F, false));

		this.bipedRightArm = new ModelRenderer(this, "rightArm");
		this.bipedRightArm.setRotationPoint(-6.75F, 6.5F, 0.0F);
		this.bipedRightArm.cubeList.add(new ModelBox(this.bipedRightArm, 0, 0, -1.5F, -6.5F, -2.0F, 3, 13, 4, 0.0F, false));
		this.bipedRightArm.cubeList.add(new ModelBox(this.bipedRightArm, 0, 0, -1.0F, -6.0F, -1.5F, 2, 12, 3, 0.0F, false));

		// arm "wear" (sleeve) layers are baked into the arm cubes above --
		// keep the fields present (referenced elsewhere) but empty
		this.bipedLeftArmwear = new ModelRenderer(this, "leftArmwear");
		this.bipedLeftArmwear.setRotationPoint(6.25F, 6.5F, -0.25F);
		this.bipedRightArmwear = new ModelRenderer(this, "rightArmwear");
		this.bipedRightArmwear.setRotationPoint(-6.75F, 6.5F, 0.0F);

		// --- legs: pivot raised to hip height (Y=12) from the export's
		// incidental ankle-area pivot; cube offsets adjusted to compensate
		// so the rest pose matches the original export exactly ---
		this.bipedLeftLeg = new ModelRenderer(this, "leftLeg");
		this.bipedLeftLeg.setRotationPoint(8.25F, 12.0F, -8.0F);
		this.bipedLeftLeg.rotateAngleZ = LEFT_LEG_REST_Z;
		this.bipedLeftLeg.cubeList.add(new ModelBox(this.bipedLeftLeg, 0, 0, -8.5F, 0.0F, 4.5F, 7, 13, 7, 0.0F, false));
		this.bipedLeftLeg.cubeList.add(new ModelBox(this.bipedLeftLeg, 0, 0, -9.0F, 0.5F, 5.25F, 6, 12, 6, 0.0F, false));

		this.bipedRightLeg = new ModelRenderer(this, "rightLeg");
		this.bipedRightLeg.setRotationPoint(-8.25F, 12.0F, -8.0F);
		this.bipedRightLeg.rotateAngleZ = RIGHT_LEG_REST_Z;
		this.bipedRightLeg.cubeList.add(new ModelBox(this.bipedRightLeg, 0, 0, 1.5F, 0.0F, 4.5F, 7, 13, 7, 0.0F, false));
		this.bipedRightLeg.cubeList.add(new ModelBox(this.bipedRightLeg, 0, 0, 2.5F, 0.5F, 5.25F, 6, 12, 6, 0.0F, false));

		// leg "wear" (pants) layers baked into leg cubes above -- keep
		// fields present but empty
		this.bipedLeftLegwear = new ModelRenderer(this, "leftLegwear");
		this.bipedLeftLegwear.setRotationPoint(8.25F, 12.0F, -8.0F);
		this.bipedRightLegwear = new ModelRenderer(this, "rightLegwear");
		this.bipedRightLegwear.setRotationPoint(-8.25F, 12.0F, -8.0F);

		// body "wear" (jacket) layer baked into body cubes above -- keep
		// field present but empty
		this.bipedBodyWear = new ModelRenderer(this, "bodyWear");
		this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
	}

	/**+
	 * Sets the models various rotation angles then renders the
	 * model.
	 */
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

	/**+
	 * Sets the model's various rotation angles. For bipeds, par1
	 * and par2 are used for animating the movement of arms and
	 * legs, where par1 represents the time(so that arms and legs
	 * swing back and forth) and par2 represents how "far" arms and
	 * legs can swing at most.
	 */
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		// re-apply the custom model's baked rest-pose tilts on top of
		// whatever ModelBiped's vanilla animation just computed
		this.bipedLeftArm.rotateAngleZ += LEFT_ARM_REST_Z;
		this.bipedRightArm.rotateAngleZ += RIGHT_ARM_REST_Z;
		this.bipedLeftLeg.rotateAngleZ = LEFT_LEG_REST_Z;
		this.bipedRightLeg.rotateAngleZ = RIGHT_LEG_REST_Z;

		copyModelAngles(this.bipedLeftLeg, this.bipedLeftLegwear);
		copyModelAngles(this.bipedRightLeg, this.bipedRightLegwear);
		copyModelAngles(this.bipedLeftArm, this.bipedLeftArmwear);
		copyModelAngles(this.bipedRightArm, this.bipedRightArmwear);
		copyModelAngles(this.bipedBody, this.bipedBodyWear);
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
