package com.asqool.magicalthings.variables;

import java.util.HashMap;

import net.minecraft.nbt.CompoundTag;

public class playervars {
    public playervars(){
        this.vars.put("mana",0f);
        this.vars.put("long_arm_or_not",0f);
        this.vars.put("gravity",0.08f);
        this.vars.put("namehideornot",0f);
        this.vars.put("knowmagic",0f);
        this.vars.put("tempcoin",0f);
    }

    HashMap<String, Float> vars= new HashMap<String, Float>();
    
    public void set(String var,float n){
        this.vars.put(var,n);
    }
    public float get(String var){
        return this.vars.get(var);
    }
    public void add(String var,float n){
        this.set(var,this.get(var)+n);
    }
    public void remove(String var,float n){
        this.set(var,this.get(var)-n);
    }
    public void copyFrom(playervars source){
        this.vars=source.vars;
    }
    public void saveNBTData(CompoundTag nbt){
        for (String i:this.vars.keySet()){
            nbt.putFloat(i, this.vars.get(i));
        }
    }
    public void loadNBTData(CompoundTag nbt){
        for (String i:this.vars.keySet()){
            this.set(i,nbt.getFloat(i));
        }
    }
}
