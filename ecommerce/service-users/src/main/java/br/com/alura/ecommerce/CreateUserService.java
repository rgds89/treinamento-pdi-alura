package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.ConsumerService;
import br.com.alura.ecommerce.consumer.ServiceRunner;
import br.com.alura.ecommerce.database.LocalDataBase;
import br.com.alura.ecommerce.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.SQLException;
import java.util.UUID;

public class CreateUserService implements ConsumerService<Order> {

    private static final int THREADS = 5;

    private static final String TOPIC_NEW_ORDER = "ECOMMERCE_NEW_ORDER";

    private static final String STATEMENT_CREATE_TABLE_USERS = "create table Users (uuid varchar(200) primary key, email varchar(200))";
    private static final String STATEMENT_INSERT_USER = "insert into Users (uuid, email) values (?,?)";
    private static final String STATEMENT_SELECT_USER = "select uuid from Users where email = ? limit 1";
    private final LocalDataBase dataBase;

    CreateUserService() {
        this.dataBase = new LocalDataBase("users_database");
        this.dataBase.createIfNotExists(STATEMENT_CREATE_TABLE_USERS);
    }


    public static void main(String[] args) {
        new ServiceRunner<>(CreateUserService::new).start(THREADS);
    }

    public void parse(ConsumerRecord<String, Message<Order>> record) throws SQLException {
        System.out.println("------------------------------------------");
        System.out.println("Processing new order, checking for new user");
        System.out.println(record.value());
        var message = record.value();
        var order = message.getPayload();
        if (isNewUser(order.getEmail())) {
            insertNewUser(order.getEmail());
        }
    }

    private void insertNewUser(String email) throws SQLException {
        if (isNewUser(email)) {
            dataBase.update(STATEMENT_INSERT_USER, UUID.randomUUID().toString(), email);
            System.out.println("Usuário " + email + " adicionado");
        }
    }

    private boolean isNewUser(String email) throws SQLException {
        var result = dataBase.query(STATEMENT_SELECT_USER, email);
        return !result.next();
    }

    @Override
    public String getTopic() {
        return TOPIC_NEW_ORDER;
    }

    @Override
    public String getConsumerGroup() {
        return CreateUserService.class.getSimpleName();
    }
}
