package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class MOVT extends ThumbInstruction {
    public byte target = 0; // nibble
    public short value = 0;

    @Override
    public void assemble() {
        bits = getBitFilter().toInteger()
                | (target & 0xF) << 8
                | ((value & 0xF000) >>> 12) << 16
                | ((value & 0x0800) >>> 11) << 26
                | ((value & 0x0700) >>> 8) << 12
                | (value & 0x00FF);
    }

    @Override
    public void disassemble() {
        target = (byte) ((bits & 0x00000F00) >>> 8);
        value = (short) (((bits & 0x000F0000) >>> 16) << 12
                | ((bits & 0x04000000) >>> 26) << 11
                | ((bits & 0x00007000) >>> 12) << 8
                | (bits & 0x000000FF));
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("11110X101100XXXX0XXXXXXXXXXXXXXX");
    }

    @Override
    public boolean isValid() {
        return super.isValid()
                && target < 0x10;
    }

    @Override
    public boolean isUnpredictable(boolean isInITBlock, boolean isLastInITBlock) {
        return 13 <= target && target <= 15;
    }

    @Override
    public Width getWidth() {
        return Width.WIDE;
    }
}
