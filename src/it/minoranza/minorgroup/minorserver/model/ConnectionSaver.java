package it.minoranza.minorgroup.minorserver.model;

import it.minoranza.minorgroup.commons.model.requests.DealerToServer;
import it.minoranza.minorgroup.commons.model.requests.ServerToDealer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ConnectionSaver extends Thread implements Comparable<String> {

    private final Socket s;
    private volatile boolean finish;
    private final String hostname, password;
    private final InputStream is;
    // private final Scanner scanner;
    private final int portUDP;


    public ConnectionSaver(final Socket s) throws IOException, JSONException {
        System.err.println("russ");

        this.s = s;

        finish = true;

        is = s.getInputStream();
        System.err.println("input");
        //buffer = new StringWriter();
        //System.out.println("blup");
        //IOUtils.copy(is, buffer);
        Scanner scanner = new Scanner(is);


        System.out.println("click");

        final JSONObject object = new JSONObject(scanner.nextLine()); //buffer.toString()
        hostname = object.getString(DealerToServer.nameDealer.name());
        password = object.getString(DealerToServer.passkey.name());
        portUDP = object.getInt(DealerToServer.portUDP.name());

        System.out.println("OK GTO");
        scanner.close();

        // System.out.println(" * CONNESSIONE ACCETTATA da "+s.getInetAddress()+":"+s.getPort()+" * ");
    }

    @Override
    public final void run() {

        //int i=0;

        while (finish) {
            try {
                System.out.println("virtual connectionsaver");

                final JSONObject object = new JSONObject();
                object.put(ServerToDealer.success.name(), true);

                final OutputStream out = s.getOutputStream();
                final PrintWriter pw = new PrintWriter(out, true);

                pw.println(object.toString());
                pw.close();
                out.close();
                //try {

                //Main.udp.refresh();


            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                finish = false;
            }
            //join();
            //interrupt();

            /*} catch (IOException exc) {
                System.err.println("Something went wrong during InputStream");
                exc.printStackTrace();
                finish=false;
            }*//* catch (InterruptedException exc){
                exc.printStackTrace();
            }*/
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionSaver that = (ConnectionSaver) o;

        return hostname != null ? hostname.equals(that.hostname) : that.hostname == null;
    }

    @Override
    public int hashCode() {
        return hostname != null ? hostname.hashCode() : 0;
    }

    public void refresh() {

    }

    public final String getHostname() {
        return hostname;
    }

    public final int getPort() {
        return portUDP;
    }

    public final String getPassword() {
        return password;
    }

    @Override
    public int compareTo(String o) {
        return hostname.compareTo(o);
    }

    /*public final void setFinish(final boolean finish) {
    this.finish = finish;
    }*/

}
