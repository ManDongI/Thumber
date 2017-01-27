package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class MVNS extends ThumbInstruction {
    public byte target = 0;
    public byte register = 0;

    @Override
    public void assemble() {
        bits = getBitFilter().toInteger()
                | (register & 0x7) << 3
                | target & 0x7;
    }

    @Override
    public void disassemble() {
        register = (byte) ((bits & 0x0038) >>> 3);
        target = (byte) (bits & 0x0007);
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("0100001111XXXXXX");
    }

    @Override
    public boolean isValid() {
        return super.isValid()
                && target < 0x8
                && register < 0x8;
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
