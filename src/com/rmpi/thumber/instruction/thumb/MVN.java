package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.Width;

public class MVN extends ThumbInstruction {
    byte target = 0;
    byte register = 0;

    @Override
    public void assemble() {
        bits = 0x43C0
                | (register & 0x7) << 3
                | target & 0x7;
    }

    @Override
    public void disassemble() {
        register = (byte) ((bits & 0x0038) >>> 3);
        target = (byte) (bits & 0x0007);
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
