package net.minecraft.client.model;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.entity.Entity;
import com.google.common.collect.Lists;

public class ModelPlayer extends ModelBiped {
    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedLeftLegwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedBodyWear;
    private ModelRenderer bipedCape;
    private ModelRenderer bipedDeadmau5Head;
    private boolean smallArms;

    // Custom structural layers from your Blockbench file
    public ModelRenderer bodyExtraLayer1;
    public ModelRenderer bodyExtraLayer2;
    public ModelRenderer bodyExtraLayer3;
    public ModelRenderer leftLegChunky;
    public ModelRenderer rightLegChunky;

    public ModelPlayer(float parFloat1, boolean parFlag) {
        // Automatically creates the default base cubes at texture size 64x64
        super(parFloat1, 0.0F, 64, 64);
        this.smallArms = parFlag;

        this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
        this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1);

        this.bipedCape = new ModelRenderer(this, 0, 0);
        this.bipedCape.setTextureSize(64, 32);
        this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1);

        if (parFlag) { 
            // ==========================================
            // SLIM MODEL (ALEX) - SAFE GEOMETRY OVERRIDES
            // ==========================================
            
            // 1. Wipe default Steve head box & insert Blockbench configuration safely
            this.bipedHead.cubeList = Lists.newArrayList();
            this.bipedHead.setTextureOffset(0, 0);
            this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
            
            this.bipedHeadwear.cubeList = Lists.newArrayList();
            this.bipedHeadwear.setTextureOffset(32, 0);
            this.bipedHeadwear.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9);

            // 2. Wipe default body & add custom stacked Blockbench shapes
            this.bipedBody.cubeList = Lists.newArrayList();
            this.bipedBody.setTextureOffset(16, 20);
            this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);

            // Add Blockbench sub-boxes as children to preserve tracking angles
            this.bodyExtraLayer1 = new ModelRenderer(this, 16, 29); 
            this.bodyExtraLayer1.addBox(-6.0F, 7.0F, -5.0F, 12, 5, 8);
            this.bipedBody.addChild(this.bodyExtraLayer1);

            this.bodyExtraLayer2 = new ModelRenderer(this, 16, 25);
            this.bodyExtraLayer2.addBox(-6.0F, 3.0F, -4.0F, 12, 4, 6);
            this.bipedBody.addChild(this.bodyExtraLayer2);

            this.bodyExtraLayer3 = new ModelRenderer(this, 20, 30);
            this.bodyExtraLayer3.addBox(-5.0F, 3.0F, -2.5F, 10, 2, 5);
            this.bipedBody.addChild(this.bodyExtraLayer3);

            // 3. Reconfigure Slim arms (3-pixel width) inside existing base elements
            this.bipedLeftArm.cubeList = Lists.newArrayList();
            this.bipedLeftArm.setTextureOffset(32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
            this.bipedLeftArm.rotateAngleZ = -0.3054F; // -17.5 degrees

            this.bipedRightArm.cubeList = Lists.newArrayList();
            this.bipedRightArm.setTextureOffset(40, 16);
            this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
            this.bipedRightArm.rotateAngleZ = 0.2618F; // 15 degrees

            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4);
            this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);

            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4);
            this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 0.0F);

        } else {
            // Standard Classic (Steve) fallback branch
            this.bipedLeftArm.cubeList = Lists.newArrayList();
            this.bipedLeftArm.setTextureOffset(32, 48);
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
            this.bipedLeftArmwear.setRotationPoint(5.0F, 2.0F, 0.0F);

            this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
            this.bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
            this.bipedRightArmwear.setRotationPoint(-5.0F, 2.0F, 0.0F);
        }

        // 4. Inject Blockbench leg adjustments into existing base leg pointers
        this.bipedLeftLeg.cubeList = Lists.newArrayList();
        this.bipedLeftLeg.setTextureOffset(16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.rotateAngleZ = 0.0873F; // 5 degrees

        this.leftLegChunky = new ModelRenderer(this, 0, 32); 
        this.leftLegChunky.addBox(-7.0F, 0.0F, -3.0F, 7, 12, 6);
        this.bipedLeftLeg.addChild(this.leftLegChunky);

        this.bipedRightLeg.cubeList = Lists.newArrayList();
        this.bipedRightLeg.setTextureOffset(0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.rotateAngleZ = -0.0873F; // -5 degrees

        this.rightLegChunky = new ModelRenderer(this, 0, 32);
        this.rightLegChunky.addBox(0.0F, 0.0F, -3.0F, 7, 12, 6);
        this.bipedRightLeg.addChild(this.rightLegChunky);

        // Layer Overlays
        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        // Enforce ambient texture profiles to kill pitch-black rendering bug
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
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

    public void renderRightArm() {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.bipedRightArm.render(0.0625F);
        this.bipedRightArmwear.render(0.0625F);
    }

    public void renderLeftArm() {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.bipedLeftArm.render(0.0625F);
        this.bipedLeftArmwear.render(0.0625F);
    }

    // Fixes the deadmau5 error thrown by LayerDeadmau5Head.java
    public void renderDeadmau5Head(float parFloat1) {
        copyModelAngles(this.bipedHead, this.bipedDeadmau5Head);
        this.bipedDeadmau5Head.rotationPointX = 0.0F;
        this.bipedDeadmau5Head.rotationPointY = 0.0F;
        this.bipedDeadmau5Head.render(parFloat1);
    }

    // Fixes the cape error thrown by SkinPreviewRenderer.java and LayerCape.java
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
