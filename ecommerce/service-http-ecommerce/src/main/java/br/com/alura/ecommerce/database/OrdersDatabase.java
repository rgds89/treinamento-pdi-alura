package br.com.alura.ecommerce.database;

import br.com.alura.ecommerce.Order;

import java.io.Closeable;
import java.sql.SQLException;

public class OrdersDatabase implements Closeable {
    private final LocalDataBase dataBase;

    public OrdersDatabase() {
        this.dataBase = new LocalDataBase("orders_database");
        this.dataBase.createIfNotExists("create table orders (uuid varchar(200) primary key, email varchar(200), amount decimal)");
    }

    public boolean saveNew(Order order) throws SQLException {
        if (wasProcessed(order)) {
            System.out.println("Order " + order.getOrderId() + " was already processed");
            return false;
        }
        dataBase.update("insert into orders (uuid, email, amount) values (?, ?, ?)", order.getOrderId(), order.getEmail(), order.getAmount().toString());
        return true;
    }

    private boolean wasProcessed(Order order) throws SQLException {
        var result = dataBase.query("select uuid from orders where uuid = ? limit 1", order.getOrderId());
        return result.next();
    }
    @Override
    public void close() {
        try {
            dataBase.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
