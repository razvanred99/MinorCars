package it.minoranza.minorgroup.minorclient.control.threads;

import it.minoranza.minorgroup.minorclient.control.ListeningServer;
import it.minoranza.minorgroup.minorclient.control.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class StageOne extends Thread {

    // private InetAddress ip;
    private int port;
    private final ListeningServer ls;
    private Thread thread;

    private volatile boolean run;

    public StageOne(final ListeningServer ls) {
        this.ls = ls;
        run = true;
    }

    @Override
    public void run() {
        while (run) {
            try {
                System.out.println("PORTA USATA "+port);
                DatagramSocket ds = new DatagramSocket(port);
                byte[] res = new byte[2048];
                DatagramPacket response = new DatagramPacket(res, res.length);

                ds.receive(response);

                //ds.send(packet);
                /*Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ls.setUpDown(true);
                    }
                });*/


                //JSONObject object=new JSONObject(new String(response.getData(),0,response.getLength()));
                //String title=object.getString(DealerToServer.nameDealer.name());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        //ls.changeList(array);
                        Stage stage = new Stage();
                        try {
                            ls.close();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/minoranza/minorgroup/minorclient/view/main.fxml"));
                            Parent root = loader.load();
                            stage.setScene(new Scene(root, 1240, 850));
                            Main main = loader.getController();
                            StageTwo two = new StageTwo(ds, response, main);
                            main.attachSecond(two);
                            stage.show();

                            run = false;
                            two.start();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                /*thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                ds.receive(response);
                                final String json = new String(response.getData(), 0, response.getLength());

                                try {
                                    //JSONArray object=new JSONObject(json).getJSONArray(ServerToClient.listDealers.name());
                                    JSONArray array = new JSONObject(json).getJSONArray(ServerToClient.listDealers.name());/*new JSONArray();
                                    JSONObject object = new JSONObject(json);

                                    array.put(object.getString(DealerToServer.nameDealer.name()));

                                    /*Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            ls.changeList(array);
                                        }
                                    });

                        /*
                                } catch (JSONException exc) {
                                    exc.printStackTrace();
                                }

                                //System.err.println("GOT IT");


                            }
                        } catch (IOException io) {
                            io.printStackTrace();
                        }


                    }
                });

                thread.start();*/
            } catch (IOException ioException) {
                ioException.printStackTrace();
                run = false;
            }
        }
    }

    public final void explode() {
        if (thread != null && thread.isAlive())
            thread.interrupt();
        run = false;
    }

    public void startOperations(final int port) {
        this.port = port;
       // ds=new DatagramSocket(port);
        start();
    }
}
