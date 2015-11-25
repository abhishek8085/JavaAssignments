import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Abhishek Indukumar
 * 
 * @version 1.0 : HandshakeManager.java, 2015/11/06
 */
public class HandshakeManager {

    public static Player performHandshakeAndGetPlayer(DataInputStream dataInputStream, DataOutputStream dataOutputStream)
    {


        HandShakeFrame handShakeFrame = new HandShakeFrame(
                MachineHelper.getLocalHost(),"",new HandshakeInfo(MachineHelper.getLocalHost()
                , GlobalConfig.getRandomGamePiece()));

        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(handShakeFrame);
            byte[] bytes = bos.toByteArray();
            int length = bytes.length;
            dataOutputStream.writeInt(length);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();



            int inLength = dataInputStream.readInt();
            byte[] inBytes = new byte[inLength];
            dataInputStream.readFully(inBytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
            ObjectInputStream in = new ObjectInputStream(bis);

            HandShakeFrame handShakeFrame1 = ( HandShakeFrame )in.readObject();
            HandshakeInfo handshakeInfo = handShakeFrame1.getData();
            Player player = new Player(handshakeInfo.getUserName(),true,handshakeInfo.getGamePiece());

            return player;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static Player performHandshakeAndGetPlayer(HandShakeFrame handShakeFrame,GameClientConnection gameClientConnection,boolean initialHandshake )
    {




        try {

            //ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //ObjectOutputStream out = new ObjectOutputStream(bos);

            //out.writeObject(handShakeFrame);

/*            byte[] bytes = bos.toByteArray();
            int length = bytes.length;
            dataOutputStream.writeInt(length);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();*/

            if (initialHandshake)
            {
                HandShakeFrame handShakeFrame1 = new HandShakeFrame(
                        MachineHelper.getLocalHost(),"",new HandshakeInfo(MachineHelper.getLocalHost()
                        , GlobalConfig.getRandomGamePiece()));
                gameClientConnection.sendToClient(handShakeFrame1);
                return null;
            }


/*
            int inLength = dataInputStream.readInt();
            byte[] inBytes = new byte[inLength];
            dataInputStream.readFully(inBytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
            ObjectInputStream in = new ObjectInputStream(bis);

            HandShakeFrame handShakeFrame1 = ( HandShakeFrame )in.readObject();*/
            HandshakeInfo handshakeInfo = handShakeFrame.getData();
            Player player = new Player(handshakeInfo.getUserName(),true,handshakeInfo.getGamePiece());

            return player;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void performClientHandShake(HandShakeFrame handShakeFrame,GameServerConnection gameServerConnection) {

        if (handShakeFrame==null) {
            HandshakeInfo handshakeInfo1 = new HandshakeInfo(MachineHelper.getLocalHost(), GlobalConfig.getUserName(), GlobalConfig.getGamePiece(), GlobalConfig.getLocalLookUpurl());
            HandShakeFrame handShakeFrame1 = new HandShakeFrame(MachineHelper.getLocalHost(), "", handshakeInfo1);

            try {

                gameServerConnection.sendToServer(handShakeFrame1);
                return ;
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        HandshakeInfo handshakeInfo = handShakeFrame.getData();
        GlobalConfig.setGamePiece(handshakeInfo.getGamePiece());


        try {
            HandshakeInfo handshakeInfo1 = new HandshakeInfo(MachineHelper.getLocalHost(), GlobalConfig.getUserName(), GlobalConfig.getGamePiece(), GlobalConfig.getLocalLookUpurl());
            HandShakeFrame handShakeFrame1 = new HandShakeFrame(MachineHelper.getLocalHost(),GlobalConfig.getUserName(), handshakeInfo1);

            gameServerConnection.sendToServer(handShakeFrame1);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }
/*
    public static void performClientHandShake(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {





        try {

            int inLength = dataInputStream.readInt();
            byte[] inBytes = new byte[inLength];
            dataInputStream.readFully(inBytes);
            ByteArrayInputStream bis = new ByteArrayInputStream(inBytes);
            ObjectInputStream in = new ObjectInputStream(bis);

            HandShakeFrame handShakeFrame = (HandShakeFrame) in.readObject();
            HandshakeInfo handshakeInfo = handShakeFrame.getData();

            GlobalConfig.setGamePiece(handshakeInfo.getGamePiece());


            HandshakeInfo handshakeInfo1 = new HandshakeInfo(MachineHelper.getLocalHost(), GlobalConfig.getUserName(), handshakeInfo.getGamePiece());
            HandShakeFrame handShakeFrame1 = new HandShakeFrame(MachineHelper.getLocalHost(),"",handshakeInfo1);



            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(handShakeFrame1);
            byte[] bytes = bos.toByteArray();
            int length = bytes.length;
            dataOutputStream.writeInt(length);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();





        } catch (Exception e) {

        }


    }*/
}
