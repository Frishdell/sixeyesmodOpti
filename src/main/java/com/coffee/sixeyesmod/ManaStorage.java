package com.coffee.sixeyesmod;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.*;
import javax.annotation.Nullable;

public class ManaStorage {
    @CapabilityInject(IMana.class)
    public static Capability<IMana> MANA_CAPABILITY = null;

    public interface IMana {
        int getMana(); int getMaxMana();
        void addMana(int a); void consume(int a);
        void setMana(int m); void setMax(int m);
    }

    public static class DefaultMana implements IMana {
        private int mana = 100, max = 100;
        public int getMana() { return mana; }
        public int getMaxMana() { return max; }
        public void setMana(int m) { mana = m; }
        public void setMax(int m) { max = m; }
        public void addMana(int a) { mana = Math.min(mana + a, max); }
        public void consume(int a) {
            mana = Math.max(0, mana - a);
            // Механика прогрессии: если мана < 20% от лимита, лимит +5
            if (mana < max * 0.2) {
                max += 5;
            }
        }
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IMana.class, new Capability.IStorage<IMana>() {
            @Nullable @Override public INBT writeNBT(Capability<IMana> c, IMana i, Direction s) {
                CompoundNBT n = new CompoundNBT();
                n.putInt("m", i.getMana()); n.putInt("mx", i.getMaxMana());
                return n;
            }
            @Override public void readNBT(Capability<IMana> c, IMana i, Direction s, INBT n) {
                CompoundNBT cn = (CompoundNBT) n;
                i.setMana(cn.getInt("m")); i.setMax(cn.getInt("mx"));
            }
        }, DefaultMana::new);
    }
}
