package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class MOVS_Imm extends ThumbInstruction {
    byte target = 0;
    byte value = 0;

    @Override
    public void assemble() {
        bits = getBitFilter().toInteger()
                | (target & 0x7) << 8
                | value & 0xFF;
    }

    @Override
    public void disassemble() {
        target = (byte) ((bits & 0x0700) >>> 8);
        value = (byte) (bits & 0x00FF);
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("00100XXXXXXXXXXX");
    }

    @Override
    public boolean isValid() {
        return super.isValid()
                && target < 0x8;
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
