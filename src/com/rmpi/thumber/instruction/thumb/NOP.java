package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.BitFilter;
import com.rmpi.thumber.format.Width;

public class NOP extends ThumbInstruction {
    @Override
    public void assemble() {

    }

    @Override
    public void disassemble() {
        bits = 0xbf00;
    }

    @Override
    public BitFilter getBitFilter() {
        return new BitFilter("1011111100000000");
    }

    @Override
    public Width getWidth() {
        return Width.NARROW;
    }
}
