package com.reghutcpslave;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.net.ModbusTCPListener;
import net.wimpi.modbus.procimg.SimpleDigitalIn;
import net.wimpi.modbus.procimg.SimpleDigitalOut;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;


/**
 * Class implementing a simple Modbus/TCP slave.
 * A simple process image is available to test
 * functionality and behaviour of the implementation.
 *
 * @author Dieter Wimberger
 * @version 1.2rc1 (09/11/2004)
 */
public class reghutcpslave {


    public static void main(String[] args) {

        ModbusTCPListener listener = null;
        SimpleProcessImage spi = null;
        int port = Modbus.DEFAULT_PORT;

        try {
            if(args != null && args.length ==1) {
                port = port + Integer.parseInt(args[0]);
            }
            System.out.println("jModbus Modbus Slave (Server)");

            //1. prepare a process image
            spi = new SimpleProcessImage();

            spi.addDigitalOut(new SimpleDigitalOut(true));
            spi.addDigitalOut(new SimpleDigitalOut(true));

//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            // allow checking LSB/MSB order
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));

            spi.addDigitalIn(new SimpleDigitalIn(false));//H
            spi.addDigitalIn(new SimpleDigitalIn(false));//G
            spi.addDigitalIn(new SimpleDigitalIn(false));//F
            spi.addDigitalIn(new SimpleDigitalIn(true));//E
            spi.addDigitalIn(new SimpleDigitalIn(true));//D
            spi.addDigitalIn(new SimpleDigitalIn(false));//C
            spi.addDigitalIn(new SimpleDigitalIn(false));//B
            spi.addDigitalIn(new SimpleDigitalIn(false));//A

//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            // allow checking LSB/MSB order
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));
//            spi.addDigitalIn(new SimpleDigitalIn(true));


            spi.addRegister(new SimpleRegister(251));
            spi.addInputRegister(new SimpleInputRegister(45));

            //2. create the coupler holding the image
            ModbusCoupler.getReference().setProcessImage(spi);
            ModbusCoupler.getReference().setMaster(false);
            ModbusCoupler.getReference().setUnitID(15);

            //3. create a listener with 3 threads in pool
            if (Modbus.debug) System.out.println("Listening...");
            listener = new ModbusTCPListener(3);
            listener.setPort(port);
            listener.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//main


}//class TCPSlaveTest
