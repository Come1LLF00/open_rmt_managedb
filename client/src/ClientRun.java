import сlient.Servant;import communication.Mediator;/** * @author Come_1LL_F00 aka Lenar Khannanov * @author Leargy aka Anton Sushkevich */public class ClientRun {    public static void main(String[] args) throws InterruptedException {        Mediator mediator = new Mediator();        Servant servant = (Servant) mediator.getServant();        servant.setConnection();    }}