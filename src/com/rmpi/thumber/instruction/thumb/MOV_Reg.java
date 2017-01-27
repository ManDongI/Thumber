package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class MOV_Reg extends ThumbInstruction {
    public byte target = 0;
    public byte register = 0;

    @Override
    public void assemble() {
        bits = getBitFilter().toInteger()
                | ((target & 0x8) >>> 3) << 7
                | ((register & 0xF)) << 3
                | (target & 0x7);
    }

    @Override
    public void disassemble() {
        target = (byte) (((bits & 0x0080) >>> 7) << 3
                | (bits & 0x0007));
        register = (byte) ((bits & 0x0078) >>> 3);
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("01000110XXXXXXXX");
    }

    @Override
    public boolean isValid() {
        return super.isValid()
                && target < 0x10
                && register < 0x10;
    }

    @Override
    public boolean isUnpredictable(boolean isInITBlock, boolean isLastInITBlock) {
        return target == 15
                && isInITBlock
                && !isLastInITBlock;
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
