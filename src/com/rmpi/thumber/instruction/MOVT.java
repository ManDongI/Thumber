package com.rmpi.thumber.instruction;

import com.rmpi.thumber.format.Width;

public class MOVT extends Instruction {
    public byte rd; // nibble
    public short imm16;

    @Override
    public void assemble() {
        bits = 0xF2C00000
                | (rd & 0xF) << 8
                | ((imm16 & 0xF000) >>> 12) << 16
                | ((imm16 & 0x0800) >>> 11) << 26
                | ((imm16 & 0x0700) >>> 8) << 12
                | (imm16 & 0x00FF);
    }

    @Override
    public void disassemble() {
        rd = (byte) ((bits & 0x00000F00) >>> 8);
        imm16 = (short) (((bits & 0x000F0000) >>> 16) << 12
                | ((bits & 0x04000000) >>> 26) << 11
                | ((bits & 0x00007000) >>> 12) << 8
                | (bits & 0x000000FF));
    }

    @Override
    public Width getWidth() {
        return Width.WIDE;
    }
}
