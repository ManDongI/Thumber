package com.rmpi.thumber.instruction;

import com.rmpi.thumber.format.Endianness;

public abstract class Instruction {
    protected int bits = 0;

    public abstract void assemble();
    public abstract void disassemble();

    public abstract int getBits(Endianness endian);
    public abstract void setBits(int bits, Endianness endian);
}
