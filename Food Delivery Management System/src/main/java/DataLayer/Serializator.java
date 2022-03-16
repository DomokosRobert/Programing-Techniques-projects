package DataLayer;

import BusinessLayer.DeliveryService;

import java.io.*;

/**
 * The type Serializator.
 */
public class Serializator {

    /**
     * Serialize delivery service.
     *
     * @param d the d
     */
    public static void serializeDeliveryService(DeliveryService d)
    {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("Delivery.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(d);
            out.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deserialize delivery service delivery service.
     *
     * @return the delivery service
     */
    public static DeliveryService deserializeDeliveryService(){
        DeliveryService d = null;
        try {
            FileInputStream file = new FileInputStream("Delivery.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            d = (DeliveryService)in.readObject();
            in.close();
            file.close();
            return d;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            d = new DeliveryService();
            serializeDeliveryService(d);

        }
        return d;
    }
}
