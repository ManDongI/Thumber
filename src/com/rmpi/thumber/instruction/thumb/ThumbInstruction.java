package com.rmpi.thumber.instruction.thumb;

import com.rmpi.thumber.format.Endianness;
import com.rmpi.thumber.format.Width;
import com.rmpi.thumber.instruction.Instruction;

public abstract class ThumbInstruction extends Instruction {
    public abstract Width getWidth();

    @Override
    public int getBits(Endianness endian) {
        switch (endian) {
            case BIG:
                return bits;
            case LITTLE:
                switch (getWidth()) {
                    case NARROW:
                        return (bits & 0xFF00) >>> 8
                                | (bits & 0x00FF) << 8;
                    case WIDE:
                        return (bits & 0xFF000000) >>> 8
                                | (bits & 0x00FF0000) << 8
                                | (bits & 0x0000FF00) >>> 8
                                | (bits & 0x000000FF) << 8;
                    default:
                        // ?
                        return 0;
                }
            default:
                // ?
                return 0;
        }
    }

    @Override
    public void setBits(int bits, Endianness endian) {
        switch (endian) {
            case BIG:
                this.bits = bits;
                break;
            case LITTLE:
                switch (getWidth()) {
                    case NARROW:
                        this.bits = (bits & 0xFF00) >> 8
                                | (bits & 0x00FF) << 8;
                        break;
                    case WIDE:
                        this.bits = (bits & 0xFF000000) >> 8
                                | (bits & 0x00FF0000) << 8
                                | (bits & 0x0000FF00) >> 8
                                | (bits & 0x000000FF) << 8;
                        break;
                    default:
                        // ?
                }
            default:
                // ?
        }
    }
}
