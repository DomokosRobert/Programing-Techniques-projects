package BusinessLayer;

import DataLayer.Serializator;
import com.opencsv.CSVReader;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Delivery service.
 */
public class DeliveryService extends Observable implements Serializable,DeliveryServiceProcessing {

    private HashMap<Order,List<MenuItem>> orderList= new HashMap<>();
    private List<MenuItem> itemList;
    private List<User> userList;

    /**
     * Instantiates a new Delivery service.
     */
    public DeliveryService(){
        orderList=new HashMap<>();
        itemList= new ArrayList<MenuItem>();
        userList= new ArrayList<>();
    }

    /**
     * Gets order list.
     *
     * @return the order list
     */
    public HashMap<Order, List<MenuItem>> getOrderList() {
        return orderList;
    }

    /**
     * Sets order list.
     *
     * @param orderList the order list
     */
    public void setOrderList(HashMap<Order, List<MenuItem>> orderList) {
        this.orderList = orderList;
    }

    /**
     * Gets item list.
     *
     * @return the item list
     */
    public List<MenuItem> getItemList() {
        return itemList;
    }

    /**
     * Sets item list.
     *
     * @param itemList the item list
     */
    public void setItemList(List<MenuItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets user list.
     *
     * @param userList the user list
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Import from csv.
     *
     * @param file the file
     */
    public void importFromCSV(String file)
    {
        try {

            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {

                if (nextRecord[1].equals("Rating"))
                    continue;

                BaseProduct it = new BaseProduct(nextRecord[0], Double.parseDouble(nextRecord[1]), Integer.parseInt(nextRecord[2]), Integer.parseInt(nextRecord[3]), Integer.parseInt(nextRecord[4]), Integer.parseInt(nextRecord[5]),Integer.parseInt(nextRecord[6]));
                itemList.add(it);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        List<MenuItem> listWithDuplicates = itemList;
        itemList= listWithDuplicates.stream().distinct().collect(Collectors.toList());
        Serializator.serializeDeliveryService(this);
    }

    @Override
    public void importProducts() {
        importFromCSV("products.csv");
        Serializator.serializeDeliveryService(this);

    }

    @Override
    public void addItem(JTextField t0, JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6) {
        assert(t0!=null);
        BaseProduct it = new BaseProduct(t0.getText(),Double.parseDouble(t2.getText()),Integer.parseInt(t3.getText()),Integer.parseInt(t4.getText()),Integer.parseInt(t5.getText()),Integer.parseInt(t6.getText()),Integer.parseInt(t1.getText()));
        itemList.add(it);
        Serializator.serializeDeliveryService(this);
    }

    @Override
    public void removeItem(JTextField t0) {
        assert(t0!=null);
        int i=0,del=-1;
        for(MenuItem mem: itemList){
            if(mem.getTitle().equals(t0.getText()))
            {
                del=i;
            }
            i++;
        }
        itemList.remove(del);
        Serializator.serializeDeliveryService(this);
    }

    @Override
    public void updateItem(JTextField t0, JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6) {
        assert(t0!=null);
        for(MenuItem mem: itemList){
            if(mem.getTitle().equals(t0.getText()))
            {
                if(!t1.getText().isEmpty())
                    mem.setRating(Double.parseDouble(t1.getText()));
                if(!t2.getText().isEmpty())
                    mem.setCalories(Integer.parseInt(t2.getText()));
                if(!t3.getText().isEmpty())
                    mem.setProteins(Integer.parseInt(t3.getText()));
                if(!t4.getText().isEmpty())
                    mem.setFats(Integer.parseInt(t4.getText()));
                if(!t5.getText().isEmpty())
                    mem.setSodium(Integer.parseInt(t5.getText()));
                if(!t6.getText().isEmpty())
                    mem.setPrice(Integer.parseInt(t6.getText()));
            }

        }
        Serializator.serializeDeliveryService(this);
    }

    @Override
    public void createCompositeItem(JTextField t0, JTextField t1) {
        assert(t0!=null);
        List<MenuItem> lis = new ArrayList<>();
        double rating=0;
        int cal=0,pro=0,fat=0,sod=0,count=0;
        for(String s :t1.getText().split(";")){
            count=count+1;
            MenuItem mem = searchOne(s);
            rating += mem.getRating();
            cal +=mem.getCalories();
            pro +=mem.getProteins();
            fat +=mem.getFats();
            sod +=mem.getSodium();
            lis.add(mem);

        }

        rating = rating/count;
        CompositeProduct comp = new CompositeProduct(t0.getText(),rating,cal,pro,fat,sod,0, lis);
        comp.setPrice(comp.computePrice());

        itemList.add(comp);
        Serializator.serializeDeliveryService(this);


    }
    private MenuItem searchOne(String one){
        Optional<MenuItem> op=itemList.stream().filter(p->p.getTitle().equals(one)).findFirst();
        if(!op.isPresent())
        {
            return new BaseProduct("",0.00,0,0,0,0,0);
        }
        return op.get();
    }

    @Override
    public void createOrder(JTextField t0, JTextField t1,JTextField t2) {
        assert(t0!=null);
        int totalPrice = 0;
        List<MenuItem> lis = new ArrayList<>();
        for(String s :t0.getText().split(";")){
            MenuItem mem = searchOne(s);
            totalPrice += mem.getPrice();
            lis.add(mem);
        }

        t1.setText(String.valueOf(totalPrice));
        int idd = -1;
        for(User u: userList)
        {
            if(u.getUsername().equals(t2.getText()))
            {
                idd = u.getId();
            }
        }

        Order or= new Order(new Random().nextInt(1000),idd, LocalDate.now());
        or.setPrice(totalPrice);
        orderList.put(or,lis);
        setChanged();
        //notifyObservers(itemList);
        clearChanged();
        try {
            generateBill(or);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Serializator.serializeDeliveryService(this);

    }

    @Override
    public List<Order> reportHour(JTextField t0,JTextField t1) {
        assert(t0!=null);
        return orderList.keySet().stream().filter(p->
            (p.getHour().get(Calendar.HOUR_OF_DAY)>Integer.parseInt(t0.getText())) && (p.getHour().get(Calendar.HOUR_OF_DAY)<Integer.parseInt(t1.getText()))
        ).collect(Collectors.toList());
    }

    /**
     * Nr of product int.
     *
     * @param m the m
     * @return the int
     */
    public int nrOfProduct(MenuItem m){
        return orderList.values().stream().mapToInt(p->(int)p.stream().filter(q->q.getTitle().equals(m.getTitle())).count()).sum();
    }

    @Override
    public List<MenuItem> reportProduct(JTextField t0) {
        assert(t0!=null);
        return itemList.stream().filter(p->(nrOfProduct(p)>=Integer.parseInt(t0.getText()))).collect(Collectors.toList());
    }

    /**
     * Nr of orders int.
     *
     * @param u     the u
     * @param price the price
     * @return the int
     */
    public int nrOfOrders(User u,int price){

        return (int)orderList.keySet().stream().filter(p->p.getClientId()==u.getId()).filter(p->p.getPrice()>=price).count();
    }

    @Override
    public List<User> reportClient(JTextField t0, JTextField t1) {
        assert(t0!=null);
        return userList.stream().filter(p->nrOfOrders(p,Integer.parseInt(t1.getText()))>=Integer.parseInt(t0.getText())).collect(Collectors.toList());
    }

    @Override
    public List<Order> reportDay(JTextField t0) {
        assert(t0!=null);
        LocalDate lc = LocalDate.parse(t0.getText());
        return orderList.keySet().stream().filter(p->p.getOrderDate().equals(lc)).collect(Collectors.toList());
    }


    @Override
    public void generateBill(Order o) throws IOException {
        FileWriter f= new FileWriter("bill.txt");
        f.write("An order with the id " + o.getOrderId() + " and with the items " + orderList.get(o).stream().map(p->p.getTitle()).collect(Collectors.joining(", ")));
        f.flush();
        f.close();
    }
    private List<MenuItem> searchTitle(String title){
        return itemList.stream().filter(p->p.getTitle().contains(title)).collect(Collectors.toList());
    }
    private List<MenuItem> searchRating(double rating){
        return itemList.stream().filter(p->p.getRating()>rating).collect(Collectors.toList());
    }
    private List<MenuItem> searchCalories(int cal){
        return itemList.stream().filter(p->p.getRating()<cal).collect(Collectors.toList());
    }
    private List<MenuItem> searchProteins(int pro){
        return itemList.stream().filter(p->p.getRating()<pro).collect(Collectors.toList());
    }
    private List<MenuItem> searchFats(int fat){
        return itemList.stream().filter(p->p.getRating()<fat).collect(Collectors.toList());
    }
    private List<MenuItem> searchSodium(int s){
        return itemList.stream().filter(p->p.getRating()<s).collect(Collectors.toList());
    }
    private List<MenuItem> searchPrice(int price){
        return itemList.stream().filter(p->p.getRating()<price).collect(Collectors.toList());
    }
    @Override
    public List<MenuItem> searchContent(JTextField t0, JTextField t1, JTextField t2, JTextField t3, JTextField t4, JTextField t5, JTextField t6) {
        assert(t0!=null);
        if(!t0.getText().equals("Title"))
        {
            return searchTitle(t0.getText());
        }
        else
        if(!t1.getText().equals("Rating"))
        {
            return searchRating(Double.parseDouble(t1.getText()));
        }
        else
        if(!t2.getText().equals("Calories"))
        {
            return searchCalories(Integer.parseInt(t2.getText()));
        }
        else
        if(!t3.getText().equals("Proteins"))
        {
            return searchProteins(Integer.parseInt(t3.getText()));
        }
        else
        if(!t4.getText().equals("Fats"))
        {
            return searchFats(Integer.parseInt(t4.getText()));
        }
        else
        if(!t5.getText().equals("Sodium"))
        {
            return searchSodium(Integer.parseInt(t5.getText()));
        }
        else
        if(!t6.getText().equals("Price"))
        {
            return searchPrice(Integer.parseInt(t6.getText()));
        }
        else {
            return itemList;
        }

    }


}
