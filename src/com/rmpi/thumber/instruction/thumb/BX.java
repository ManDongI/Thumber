package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.Width;

public class BX extends ThumbInstruction {
    public byte register = 14;

    @Override
    public void assemble() {
        bits = 0x4700
                | (register & 0xF) << 3;
    }

    @Override
    public void disassemble() {
        register = (byte) ((bits & 0x0078) >> 3);
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
