package com.example.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FWFireball extends FireballEntity {
	
	private int ticksInAir = 0;
	

	public FWFireball(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(worldIn, shooter, accelX, accelY, accelZ); //this is the constructor i want
		
	}



	@Override
	 public void tick()
	{
	    if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.removed) && this.world.isBlockLoaded(new BlockPos(this))) {
	         super.tick();
	         if (this.isFireballFiery()) {
	            this.setFire(1);
	         }

	         this.ticksInAir = this.ticksInAir + 1;
	         RayTraceResult raytraceresult = ProjectileHelper.func_221266_a(this, true, this.ticksInAir >= 25, this.shootingEntity, RayTraceContext.BlockMode.COLLIDER);
	         if (raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
	            this.onImpact(raytraceresult);
	         }

	         Vec3d vec3d = this.getMotion();
	         this.posX += vec3d.x;
	         this.posY += vec3d.y;
	         this.posZ += vec3d.z;
	         ProjectileHelper.rotateTowardsMovement(this, 0.2F);
	         float f = this.getMotionFactor();
	         if (this.isInWater()) {
	            for(int i = 0; i < 4; ++i) {
	               float f1 = 0.25F;
	               this.world.addParticle(ParticleTypes.BUBBLE, this.posX - vec3d.x * 0.25D, this.posY - vec3d.y * 0.25D, this.posZ - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
	            }

	            f = 0.8F;
	         }

	         this.setMotion(vec3d.add(this.accelerationX, this.accelerationY, this.accelerationZ).scale((double)f));
	         this.world.addParticle(this.getParticle(), this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
	         this.setPosition(this.posX, this.posY, this.posZ);
	         if (this.ticksInAir >= 12)  //limiting range
	         {
	        	 boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
	             this.world.createExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.explosionPower, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
	        	 this.remove();
	         }

	      	} else 
	      	{
	      		this.remove();
	      	}
	}

	   	


	
	
}
