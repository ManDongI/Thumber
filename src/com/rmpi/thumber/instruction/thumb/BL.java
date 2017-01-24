package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.Width;

public class BL extends ThumbInstruction {
    public int offset = 0;

    @Override
    public void assemble() {
        int _offset = offset - 4; // PC padding
        int sign = _offset < 0 ? 1 : 0;
        bits = 0xF000D000
                | sign << 26
                | (notBit((_offset & 0x00800000) >>> 23) ^ sign) << 13
                | (notBit((_offset & 0x00400000) >>> 22) ^ sign) << 11
                | ((_offset & 0x003FF000) >>> 12) << 16
                | ((_offset & 0x00000FFE) >>> 1);
    }

    private int notBit(int bit) {
        return bit == 1 ? 0 : 1;
    }

    @Override
    public void disassemble() {
        int sign = (bits & 0x04000000) >>> 26;
        offset = ((-sign & 0xFF) << 24
                | notBit(((bits & 0x00002000) >>> 13) ^ sign) << 23
                | notBit(((bits & 0x00000800) >>> 11) ^ sign) << 22
                | ((bits & 0x03FF0000) >>> 16) << 12
                | (bits & 0x000007FF) << 1) + 4;
    }

    @Override
    public Width getWidth() {
        return Width.WIDE;
    }

    public int getTarget(int base) {
        return base + offset;
    }

    public static BL getBranchInstruction(int base, int target) {
        BL inst = new BL();
        inst.offset = target - base;
        inst.assemble();
        return inst;
    }
}
