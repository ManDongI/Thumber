package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class CMP_Reg_Low extends ThumbInstruction {
    byte register1 = 0;
    byte register2 = 0;

    @Override
    public void assemble() {
        bits = getBitFilter().toInteger()
                | (register1 & 0x7) << 3
                | register2 & 0x7;
    }

    @Override
    public void disassemble() {
        register1 = (byte) ((bits & 0x0038) >>> 3);
        register2 = (byte) (bits & 0x7);
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("0100001010XXXXXX");
    }

    @Override
    public boolean isValid() {
        return super.isValid()
                && register1 < 0x8
                && register2 < 0x8;
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
