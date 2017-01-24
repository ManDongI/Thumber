package com.rmpi.thumber;

import com.rmpi.thumber.format.Endianness;
import com.rmpi.thumber.instruction.BL;
import com.rmpi.thumber.instruction.MOVT;

public class Test {
    public static void main(String args[]) {
        // MOVT
        MOVT movtTest = new MOVT();
        movtTest.rd = 0;
        movtTest.imm16 = 0x3DCC;
        movtTest.assemble();
        System.out.println(Integer.toHexString(movtTest.getBits(Endianness.LITTLE)));
        movtTest.setBits(movtTest.getBits(Endianness.LITTLE), Endianness.LITTLE);
        movtTest.disassemble();
        System.out.println(Integer.toHexString(movtTest.imm16));
        BL blTest = BL.getBranchInstruction(0x01988200, 0x019881FE);
        System.out.println(Integer.toHexString(blTest.getBits(Endianness.LITTLE)));
        blTest.setBits(blTest.getBits(Endianness.LITTLE), Endianness.LITTLE);
        System.out.println(Integer.toHexString(blTest.getTarget(0x01988200)));
    }
}
