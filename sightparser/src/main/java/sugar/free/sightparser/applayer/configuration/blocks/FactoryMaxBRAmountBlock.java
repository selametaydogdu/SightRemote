package sugar.free.sightparser.applayer.configuration.blocks;

import lombok.Getter;
import lombok.Setter;
import sugar.free.sightparser.applayer.configuration.ConfigurationBlock;
import sugar.free.sightparser.pipeline.ByteBuf;

public class FactoryMaxBRAmountBlock extends ConfigurationBlock {

    private static final long serialVersionUID = 1L;

    public static final short ID = 0x19A1;

    @Getter
    @Setter
    private float maximumAmount;

    @Override
    public short getID() {
        return ID;
    }

    @Override
    public void parse(ByteBuf byteBuf) {
        maximumAmount = byteBuf.readShortLE() / 100F;
    }

    @Override
    public byte[] getData() {
        ByteBuf byteBuf = new ByteBuf(2);
        byteBuf.putShortLE((short) (maximumAmount * 100F));
        return byteBuf.getBytes();
    }
}
