import java.lang.Override;import java.lang.String; /**
/**
 *
 * @author Abhishek Indukumar
 * 
 * @version 1.0 : HandShakeFrame.java, 2015/11/06
 */
public class HandShakeFrame extends AbstractFrame<HandshakeInfo> {

    private HandshakeInfo handshakeInfo;
    private boolean isHandshakeRequest=false;

    public HandShakeFrame(String sourceAddress, HandshakeInfo handshakeInfo) {
        super(sourceAddress);
        this.isHandshakeRequest = false;
        this.handshakeInfo = handshakeInfo;
    }

    public HandShakeFrame(String sourceAddress) {
        super(sourceAddress);
        this.isHandshakeRequest = true;
    }

    @Override
    public int getFrameId() {
        return FrameIds.HANDSHAKE_FRAME;
    }

    @Override
    public HandshakeInfo getData() {
        return handshakeInfo;
    }

    public boolean isHandshakeRequest() {
        return isHandshakeRequest;
    }
}
