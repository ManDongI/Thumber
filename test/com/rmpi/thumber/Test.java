package com.rmpi.thumber;

import com.rmpi.thumber.format.Endianness;
import com.rmpi.thumber.instruction.thumb.BL;
import com.rmpi.thumber.instruction.thumb.BX;
import com.rmpi.thumber.instruction.thumb.MOVT;

public class Test {
    public static void main(String args[]) {
        // MOVT
        MOVT movtTest = new MOVT();
        movtTest.target = 0;
        movtTest.value = 0x3DCC;
        movtTest.assemble();
        System.out.println(Integer.toHexString(movtTest.getBits(Endianness.LITTLE)));
        movtTest.setBits(movtTest.getBits(Endianness.LITTLE), Endianness.LITTLE);
        movtTest.disassemble();
        System.out.println(Integer.toHexString(movtTest.value));
        // BL
        BL blTest = BL.getBranchInstruction(0x01988200, 0x019881FE);
        System.out.println(Integer.toHexString(blTest.getBits(Endianness.LITTLE)));
        blTest.setBits(blTest.getBits(Endianness.LITTLE), Endianness.LITTLE);
        blTest.disassemble();
        System.out.println(Integer.toHexString(blTest.getTarget(0x01988200)));
        // BX
        BX bxlr = new BX();
        bxlr.register = 14; // Can omit
        bxlr.assemble();
        System.out.println(Integer.toHexString(bxlr.getBits(Endianness.LITTLE)));
        bxlr.setBits(0x7047, Endianness.LITTLE);
        bxlr.disassemble();
        System.out.println(bxlr.register);
        //
    }
}
