package sugar.free.sightparser.applayer.configuration;

import lombok.Getter;
import lombok.Setter;
import sugar.free.sightparser.applayer.AppLayerMessage;
import sugar.free.sightparser.applayer.Service;
import sugar.free.sightparser.pipeline.ByteBuf;

public class WriteConfigurationBlockMessage extends AppLayerMessage {

    @Setter
    private ConfigurationBlock configurationBlock;
    @Getter
    private short configurationBlockId;
    @Setter
    private RestrictionLevel restrictionLevel = RestrictionLevel.PUMP;


    @Override
    public Service getService() {
        return Service.CONFIGURATION;
    }

    @Override
    public short getCommand() {
        return (short) 0xAA1E;
    }

    @Override
    protected byte[] getData() throws Exception {
        byte[] blockData = configurationBlock.getData();
        ByteBuf byteBuf = new ByteBuf(4 + blockData.length);
        byteBuf.putShort(configurationBlock.getID());
        byteBuf.putShort(restrictionLevel.getValue());
        byteBuf.putBytes(blockData);
        return byteBuf.getBytes();
    }

    @Override
    protected boolean outCRC() {
        return true;
    }

    @Override
    protected void parse(ByteBuf byteBuf) throws Exception {
        configurationBlockId = byteBuf.readShort();
    }
}
