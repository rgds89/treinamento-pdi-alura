package br.com.alura.ecommerce;

import br.com.alura.ecommerce.consumer.ConsumerService;
import br.com.alura.ecommerce.consumer.ServiceRunner;
import br.com.alura.ecommerce.dispatcher.KafkaDispatcher;
import br.com.alura.ecommerce.message.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchSendMessageService implements ConsumerService<String> {

    private static final String TOPIC_ECOMMERCE_SEND_MESSAGE_TO_ALL_USERS = "ECOMMERCE_SEND_MESSAGE_TO_ALL_USERS";
    private static final int THREADS = 5;
    private final Connection connection;

    BatchSendMessageService() throws SQLException {
        String url = "jdbc:sqlite:target/users_database.db";
        connection = DriverManager.getConnection(url);
        try {
            connection.createStatement().execute("create table Users (" +
                    "uuid varchar(200) primary key," +
                    "email varchar(200))");
        } catch (SQLException ex) {
            // be careful, the sql could be wrong, be reallllly careful
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServiceRunner<>(BatchSendMessageService::new).start(THREADS);
    }

    private final KafkaDispatcher<User> userDispatcher = new KafkaDispatcher<>();

    public void parse(ConsumerRecord<String, Message<String>> record) throws Exception {
        System.out.println("------------------------------------------");
        System.out.println("Processing new batch");
        var message = record.value();
        System.out.println("Topic: " + message.getPayload());

        for (User user : getAllUsers()) {
            userDispatcher.sendAsync(message.getPayload(), user.getUuid(), user, message.getId().continueWith(BatchSendMessageService.class.getSimpleName()));
        }
    }

    private List<User> getAllUsers() throws SQLException {
        var results = connection.prepareStatement("select uuid from Users").executeQuery();
        List<User> users = new ArrayList<>();
        while (results.next()) {
            users.add(new User(results.getString(1)));
        }
        return users;
    }

    @Override
    public String getTopic() {
        return TOPIC_ECOMMERCE_SEND_MESSAGE_TO_ALL_USERS;
    }

    @Override
    public String getConsumerGroup() {
        return BatchSendMessageService.class.getSimpleName();
    }
}
