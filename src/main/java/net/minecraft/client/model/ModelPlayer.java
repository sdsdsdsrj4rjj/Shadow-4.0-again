package net.minecraft.client.model;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPlayer extends ModelBiped {
    // Standard layers
    public ModelRenderer bipedLeftArmwear;
    public ModelRenderer bipedRightArmwear;
    public ModelRenderer bipedLeftLegwear;
    public ModelRenderer bipedRightLegwear;
    public ModelRenderer bipedBodyWear;
    private ModelRenderer bipedCape;
    private ModelRenderer bipedDeadmau5Head;
    private boolean smallArms;

    // Custom Extra Model Parts from your JEM File
    public ModelRenderer customHeadLayer;
    public ModelRenderer bodyExtra1;
    public ModelRenderer bodyExtra2;
    public ModelRenderer bodyExtra3;
    public ModelRenderer customRightArmLayer;
    public ModelRenderer customLeftArmLayer;
    public ModelRenderer customRightLegExtra;
    public ModelRenderer customLeftLegExtra;

    public ModelPlayer(float parFloat1, boolean parFlag) {
        super(parFloat1, 0.0F, 64, 64);
        this.smallArms = parFlag;

        // --- DEADMAU5 & CAPE DEFAULT MAPS ---
        this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
        this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, parFloat1);
        this.bipedCape = new ModelRenderer(this, 0, 0);
        this.bipedCape.setTextureSize(64, 32);
        this.bipedCape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, parFloat1);

        // --- HEAD CONFIGURATION ---
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, parFloat1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

        // Custom JEM Head Box 2 (uvNorth/East mapping overlay)
        this.customHeadLayer = new ModelRenderer(this, 32, 0); 
        this.customHeadLayer.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9, parFloat1);
        this.bipedHead.addChild(this.customHeadLayer);

        // --- BODY CONFIGURATION ---
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, parFloat1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        // JEM extra body parts mapped sequentially from your UV references
        this.bodyExtra1 = new ModelRenderer(this, 16, 16);
        this.bodyExtra1.addBox(-6.0F, 2.0F, -5.0F, 12, 5, 8, parFloat1);
        this.bipedBody.addChild(this.bodyExtra1);

        this.bodyExtra2 = new ModelRenderer(this, 16, 21);
        this.bodyExtra2.addBox(-6.0F, -3.0F, -5.0F, 12, 4, 6, parFloat1);
        this.bipedBody.addChild(this.bodyExtra2);

        this.bodyExtra3 = new ModelRenderer(this, 20, 27);
        this.bodyExtra3.addBox(-5.0F, -3.0F, -2.5F, 10, 2, 5, parFloat1);
        this.bipedBody.addChild(this.bodyExtra3);

        this.bipedBodyWear = new ModelRenderer(this, 16, 32);
        this.bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, parFloat1 + 0.25F);
        this.bipedBodyWear.setRotationPoint(0.0F, 0.0F, 0.0F);

        // --- ARMS CONFIGURATION ---
        // Right Arm Setup (with 15 degree default Z rotation converted to Radians)
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-2.5F, -2.0F, -2.0F, 3, 12, 4, parFloat1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.rotateAngleZ = 0.261799F; 

        this.customRightArmLayer = new ModelRenderer(this, 40, 32);
        this.customRightArmLayer.addBox(-2.5F, -2.0F, -2.0F, 3, 12, 4, parFloat1 + 0.25F);
        this.bipedRightArm.addChild(this.customRightArmLayer);

        // Left Arm Setup (with -17.5 degree default Z rotation converted to Radians)
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.addBox(-0.5F, -2.0F, -2.0F, 3, 12, 4, parFloat1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.rotateAngleZ = -0.305433F;

        this.customLeftArmLayer = new ModelRenderer(this, 48, 48);
        this.customLeftArmLayer.addBox(-0.5F, -2.0F, -2.0F, 3, 12, 4, parFloat1 + 0.25F);
        this.bipedLeftArm.addChild(this.customLeftArmLayer);

        this.bipedRightArmwear = new ModelRenderer(this, 40, 32);
        this.bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, parFloat1 + 0.25F);
        this.bipedRightArmwear.setRotationPoint(-5.0F, 2.5F, 0.0F);

        this.bipedLeftArmwear = new ModelRenderer(this, 48, 48);
        this.bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, parFloat1 + 0.25F);
        this.bipedLeftArmwear.setRotationPoint(5.0F, 2.5F, 0.0F);

        // --- LEGS CONFIGURATION ---
        // Right Leg Setup
        this.bipedRightLeg = new ModelRenderer(this, 16, 48);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -3.5F, 7, 12, 6, parFloat1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.customRightLegExtra = new ModelRenderer(this, 0, 32);
        this.customRightLegExtra.addBox(-2.0F, 0.0F, -3.0F, 7, 12, 6, parFloat1);
        this.bipedRightLeg.addChild(this.customRightLegExtra);

        this.bipedRightLegwear = new ModelRenderer(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, parFloat1 + 0.25F);
        this.bipedRightLegwear.setRotationPoint(-1.9F, 12.0F, 0.0F);

        // Left Leg Setup
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-5.0F, 0.0F, -3.5F, 7, 12, 6, parFloat1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        this.customLeftLegExtra = new ModelRenderer(this, 0, 36);
        this.customLeftLegExtra.addBox(-5.0F, 0.0F, -3.0F, 7, 12, 6, parFloat1);
        this.bipedLeftLeg.addChild(this.customLeftLegExtra);

        this.bipedLeftLegwear = new ModelRenderer(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, parFloat1 + 0.25F);
        this.bipedLeftLegwear.setRotationPoint(1.9F, 12.0F, 0.0F);
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
